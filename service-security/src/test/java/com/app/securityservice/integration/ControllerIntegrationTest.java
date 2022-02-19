//package com.solbegsoft.securityservice.integration;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//import org.testcontainers.junit.jupiter.Testcontainers;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.solbegsoft.securityservice.dto.UserRequestDto;
//import com.solbegsoft.securityservice.dto.UserResponseDto;
//import com.solbegsoft.securityservice.entity.User;
//import com.solbegsoft.securityservice.repository.UserRepository;
//import com.solbegsoft.securityservice.service.UserMapper;
//import com.solbegsoft.securityservice.service.UserService;
//
//@SpringBootTest
//@Testcontainers
//@AutoConfigureMockMvc
//public class ControllerIntegrationTest extends TestContainerConfig {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Autowired
//    private UserMapper mapper;
//
//    @Override
//    public void startSqlContainer() {
//        super.startSqlContainer();
//    }
//
//    @Test
//    @WithMockUser(authorities = "user:read")
//
//    void shouldFindUserById() throws Exception {
//        UserRequestDto userRequestDto = InitializationDto.createUserRequestDto1();
//        UserResponseDto userResponseDto = userService.create(userRequestDto);
//        String id = userResponseDto.getId();
//
//        mockMvc.perform(get("/users/{id}", id))
//                .andExpect(status().is(200))
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.username").value("John"))
//                .andExpect(jsonPath("$.email").value("John@mail.com"))
//                .andExpect(jsonPath("$.rating").value("5.9"));
//
//        userRepository.deleteAll();
//    }
//
//    @Test
//    @WithMockUser(authorities = "user:read")
//    void shouldGetAllUsers() throws Exception {
//        userService.create(InitializationDto.createUserRequestDto1());
//        userService.create(InitializationDto.createUserRequestDto2());
//        userService.create(InitializationDto.createUserRequestDto3());
//
//        mockMvc.perform(get("/users"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
//        userRepository.deleteAll();
//    }
//
//    @Test
//    @WithMockUser(authorities = "user:write")
//    void shouldCreateUser() throws Exception {
//        UserRequestDto userRequestDto = InitializationDto.createUserRequestDto1();
//
//        mockMvc.perform(post("/users").contentType("application/json")
//                .content(objectMapper.writeValueAsString(userRequestDto)))
//                .andExpect(status().isCreated());
//
//        List<User> userList = userRepository.findAll();
//        assert (userList.get(0)
//                .getEmail()
//                .equals(InitializationDto.createUserRequestDto1()
//                        .getEmail()));
//        assert (userList.get(0)
//                .getUsername()
//                .equals(InitializationDto.createUserRequestDto1()
//                        .getUsername()));
//        assert (userList.get(0)
//                .getRating()
//                .equals(InitializationDto.createUserRequestDto1()
//                        .getRating()));
//        userRepository.deleteAll();
//    }
//
//    @Test
//    @WithMockUser(authorities = "user:write")
//    void shouldUpdateUser() throws Exception {
//        UserRequestDto userRequestDto = InitializationDto.createUserRequestDto1();
//        UserResponseDto userResponseDto = userService.create(userRequestDto);
//        String id = userResponseDto.getId();
//
//        mockMvc.perform(put("/users/{id}", id).contentType("application/json")
//                .content(objectMapper.writeValueAsString(InitializationDto.createUserRequestDto1())))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.username").value("John"))
//                .andExpect(jsonPath("$.email").value("John@mail.com"))
//                .andExpect(jsonPath("$.rating").value("5.9"));
//
//        userRepository.deleteAll();
//
//    }
//
//    @Test
//    @WithMockUser(authorities = "user:read")
//    void shouldDeleteUser() throws Exception {
//        UserRequestDto userRequestDto = InitializationDto.createUserRequestDto1();
//        UserResponseDto userResponseDto = userService.create(userRequestDto);
//        String id = userResponseDto.getId();
//        mockMvc.perform(delete("/users/{id}", id))
//                .andExpect(status().isNoContent());
//        List<User> userList = userRepository.findAll();
//        assert (userList.isEmpty());
//
//    }
//
//    @Test
//    void shouldActivateUser() throws Exception {
//        UserRequestDto userRequestDto = InitializationDto.createUserRequestDto1();
//        UserResponseDto userResponseDto = userService.create(userRequestDto);
//
//        List<User> userList = userRepository.findAll();
//
//        String activationKey = userList.get(0).getActivationKey();
//        String answer = "User activated, well done!";
//        mockMvc.perform(get("/users/activate?key=" + activationKey)).andExpect(status().is(200));
////        assert (answer.equals(userService.activate(activationKey)));
//
//    }
//
//    @Override
//    public void stopContainer() {
//        super.stopContainer();
//    }
//}
