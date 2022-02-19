package com.app.orderservice.module;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.app.orderservice.domain.OrderRequestDto;
import com.app.orderservice.domain.OrderResponseDto;
import com.app.orderservice.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-module.properties")
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService service;

    private HelperControllerTest helper;
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        helper = new HelperControllerTest();
        mapper = new ObjectMapper();
    }

    @Test
    void shouldGetAllOrders() throws Exception {
        // GIVEN
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        List<OrderResponseDto> orderResponseDtoList = new ArrayList<>();
        orderResponseDtoList.add(orderResponseDto);

        // WHEN
        when(service.findAll()).thenReturn(orderResponseDtoList);

        // THEN
        mockMvc.perform(get("/orders/"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetOrderByIdOrders() throws Exception {
        // GIVEN
        String id = "6a5d4dc7-82e4-4f64-bbf2-2c801c8d08d0";
        OrderResponseDto orderResponseDto = helper.getOrderResponseDto();
        orderResponseDto.setId(id);

        // WHEN
        when(service.findById(id)).thenReturn(orderResponseDto);

        // THEN
        mockMvc.perform(get("/orders/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.userId").value(2))
                .andExpect(jsonPath("$.startGeolocation").value("-82.2,40.2"))
                .andExpect(jsonPath("$.endGeolocation").value("-82.2,40.2"))
                .andExpect(jsonPath("$.startDelivery").value("-1000000000-01-01T00:00:00Z"))
                .andExpect(jsonPath("$.endDelivery").value("-1000000000-01-01T00:00:00Z"))
                .andExpect(jsonPath("$.operatorId").value(1));
    }

    @Test
    void shouldDeleteById() throws Exception {
        // GIVEN
        String id = "6a5d4dc7-82e4-4f64-bbf2-2c801c8d08d0";
        OrderResponseDto orderResponseDto = helper.getOrderResponseDto();
        orderResponseDto.setId(id);

        // THEN
        mockMvc.perform(delete("/orders/" + id))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldCreateOrder() throws Exception {
        // GIVEN
        OrderRequestDto requestDto = new OrderRequestDto();
        OrderResponseDto responseDto = new OrderResponseDto();
        String orderRequestToJson = mapper.writeValueAsString(requestDto);

        // WHEN
        when(service.create(requestDto)).thenReturn(responseDto);

        // THEN
        mockMvc.perform(post("/orders").contentType(MediaType.APPLICATION_JSON)
                .content(orderRequestToJson)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldUpdateOrder() throws Exception {
        // GIVEN
        String id = "6a5d4dc7-82e4-4f64-bbf2-2c801c8d08d0";
        OrderRequestDto requestDto = new OrderRequestDto();
        OrderResponseDto responseDto = new OrderResponseDto();
        String orderRequestToJson = mapper.writeValueAsString(requestDto);
        responseDto.setId(id);

        // WHEN
        when(service.update(id, requestDto)).thenReturn(responseDto);

        // THEN
        mockMvc.perform(put("/orders/" + id).contentType(MediaType.APPLICATION_JSON)
                .content(orderRequestToJson)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}