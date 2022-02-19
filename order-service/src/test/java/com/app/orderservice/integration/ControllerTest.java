package com.app.orderservice.integration;

import com.app.orderservice.domain.OrderRequestDto;
import com.app.orderservice.domain.OrderResponseDto;
import com.app.orderservice.model.Order;
import com.app.orderservice.repository.OrderRepository;
import com.app.orderservice.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
public class ControllerTest extends PostgresContainer {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ObjectMapper objectMapper;



    @Override
    public void startSqlContainer() {
        super.startSqlContainer();
    }

    @Test
    void shouldAddNewOrderTest() throws Exception {
        OrderRequestDto orderRequestDto = Helper.getRequestDto();

        mockMvc.perform(post("/orders")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(orderRequestDto)))
                .andExpect(status().is(201));

        List<Order> orderList = orderRepository.findAll();
        assert (orderList.get(0).getUserId().equals(Helper.getRequestDto().getUserId()));
        assert (orderList.get(0).getStartGeolocation().equals(Helper.getRequestDto().getStartGeolocation()));
        assert (orderList.get(0).getEndGeolocation().equals(Helper.getRequestDto().getEndGeolocation()));
        assert (orderList.get(0).getOperatorId().equals(Helper.getRequestDto().getOperatorId()));

        orderRepository.deleteAll();
    }

    @Test
    void shouldFindOrderByIdTest() throws Exception {

        OrderRequestDto orderRequestDto = Helper.getRequestDto();
        OrderResponseDto orderResponseDto = orderService.create(orderRequestDto);
        String id = orderResponseDto.getId();

        mockMvc.perform(get("/orders/" + id))
                .andExpect(status().is(200))
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.startGeolocation").value("-82.2,40.2"))
                .andExpect(jsonPath("$.endGeolocation").value("-83.2,40.2"))
                .andExpect(jsonPath("$.operatorId").value(1));

        orderRepository.deleteAll();
    }

    @Test
    void shouldFindAllOrderTest() throws Exception {

        orderService.create(Helper.getRequestDto());
        orderService.create(Helper.getRequestDto2());
        orderService.create(Helper.getRequestDto3());

        mockMvc.perform(get("/orders"))
                .andExpect(status().is(200))
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));


        List<Order> orderList = orderRepository.findAll();
        assert (!orderList.isEmpty());
        assert (orderList.get(0).getUserId().equals(Helper.getRequestDto().getUserId()));
        assert (orderList.get(1).getUserId().equals(Helper.getRequestDto2().getUserId()));
        assert (orderList.get(2).getUserId().equals(Helper.getRequestDto3().getUserId()));
        assert (orderList.size() == 3);
        orderRepository.deleteAll();
    }

    @Test
    void shouldUpdateOrderTest() throws Exception {
        OrderRequestDto orderRequestDto = Helper.getRequestDto();
        OrderResponseDto orderResponseDto = orderService.create(orderRequestDto);
        String id = orderResponseDto.getId();


        mockMvc.perform(put("/orders/" + id)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(Helper.getRequestDto2())))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.startGeolocation").value("-92.2,40.2"))
                .andExpect(jsonPath("$.endGeolocation").value("-93.2,40.2"))
                .andExpect(jsonPath("$.operatorId").value(2));

        orderRepository.deleteAll();
    }

    public void stopContainer() {
        postgreSQLContainer.stop();
    }
}
