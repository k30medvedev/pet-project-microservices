//package com.solbegsoft.securityservice.controller;
//
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.solbegsoft.securityservice.dto.UserRequestDto;
//import com.solbegsoft.securityservice.dto.UserResponseDto;
//import com.solbegsoft.securityservice.service.UserService;
//
//@ExtendWith(SpringExtension.class)
//@AutoConfigureMockMvc
//@SpringBootTest
//class UserControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private UserService userService;
//
//    @Autowired
//    private ObjectMapper mapper;
//
//    private final String id = "f3aa0ba4-47e6-47b3-853f-3ed8cda54cd3";
//    private UserResponseDto userResponseDto = createUserResponseDto();
//    private UserRequestDto userRequestDto = createUserRequestDto();
//
//    @Test
//    @WithMockUser(authorities = "developers:read")
//    void shouldGetUserById() throws Exception {
//
//        when(userService.getOne(id)).thenReturn(userResponseDto);
//
//        userService.getOne(id);
//        verify(userService).getOne(id);
//
//        mockMvc.perform(get("/users/{id}", id))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(id))
//                .andExpect(jsonPath("$.username").value("John"))
//                .andExpect(jsonPath("$.email").value("John@email.com"));
//        // .andExpect(jsonPath("$.role").value("driver"));
//
//    }
//
//    @Test
//    @WithMockUser(authorities = "developers:read")
//    void shouldGetAllUsers() throws Exception {
//
//        List<UserResponseDto> dtoList = new ArrayList<>();
//        when(userService.getAll()).thenReturn(dtoList);
//
//        userService.getAll();
//        verify(userService).getAll();
//
//        mockMvc.perform(get("/users"))
//                .andExpect(status().isOk());
//
//    }
//
//    @Test
//    @WithMockUser(authorities = "developers:read")
//    void shouldDeleteUserById() throws Exception {
//
//        mockMvc.perform(delete("/users/{id}", id))
//                .andExpect(status().isNoContent());
//    }
//
//    @Test
//    @WithMockUser(authorities = "developers:write")
//    void shouldCreatUser() throws Exception {
//
//        String userRequestToJason = mapper.writeValueAsString(userRequestDto);
//
//        when(userService.create(userRequestDto)).thenReturn(userResponseDto);
//
//        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
//                .content(userRequestToJason)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated());
//    }
//
//    @Test
//    @WithMockUser(authorities = "developers:write")
//    void shouldUpdateUser() throws Exception {
//        String userRequestToJson = mapper.writeValueAsString(userRequestDto);
//        userResponseDto.setId(id);
//
//        when(userService.update(id, userRequestDto)).thenReturn(userResponseDto);
//
//        mockMvc.perform(put("/users/{id}", id).contentType(MediaType.APPLICATION_JSON)
//                .content(userRequestToJson)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//
//    }
//
//    private UserResponseDto createUserResponseDto() {
//        return UserResponseDto.builder()
//                .id(id)
//                .username("John")
//                .email("John@email.com")
//                // .role("driver")
//                .build();
//    }
//
//    private UserRequestDto createUserRequestDto() {
//        return UserRequestDto.builder()
//                .username("John")
//                .email("John@email.com")
//                // .role("driver")
//                .build();
//    }
//
//}