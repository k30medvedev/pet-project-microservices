//package com.solbegsoft.securityservice.integration;
//
//import java.util.List;
//
//import org.junit.Assert;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.testcontainers.junit.jupiter.Testcontainers;
//
//import com.solbegsoft.securityservice.dto.UserResponseDto;
//import com.solbegsoft.securityservice.repository.UserRepository;
//import com.solbegsoft.securityservice.service.UserService;
//
//@SpringBootTest
//@Testcontainers
//public class UserServiceIntegrationTest extends TestContainerConfig {
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public void startSqlContainer() {
//        super.startSqlContainer();
//    }
//
//    @Test
//    void shouldGetOneTest() {
//        UserResponseDto actualUser = userService.create(InitializationDto.createUserRequestDto1());
//        userService.create(InitializationDto.createUserRequestDto2());
//        userService.create(InitializationDto.createUserRequestDto3());
//
//        String id = actualUser.getId();
//        UserResponseDto expectUser = userService.getOne(id);
//
//        assert (actualUser.getId()).equals(expectUser.getId());
//
//        userRepository.deleteAll();
//    }
//
//    @Test
//    void shouldGetAllTest() {
//        userService.create(InitializationDto.createUserRequestDto1());
//        userService.create(InitializationDto.createUserRequestDto2());
//        userService.create(InitializationDto.createUserRequestDto3());
//
//        List<UserResponseDto> list = userService.getAll();
//        Assert.assertNotNull(list);
//        assert (list.size() == 3);
//        userRepository.deleteAll();
//
//    }
//
//    @Test
//    void shouldCreateUserTest() {
//        userService.create(InitializationDto.createUserRequestDto1());
//        List<UserResponseDto> list = userService.getAll();
//        assert (list.size() == 1);
//        Assert.assertNotNull(list);
//        userRepository.deleteAll();
//    }
//
//    @Test
//    void shouldDeleteByIdTest() {
//        userService.create(InitializationDto.createUserRequestDto1());
//        List<UserResponseDto> list = userService.getAll();
//        UserResponseDto userResponseDto = list.get(0);
//        userService.deleteById(userResponseDto.getId());
//        List<UserResponseDto> emptyList = userService.getAll();
//        assert (emptyList.isEmpty());
//        userRepository.deleteAll();
//
//    }
//
//    @Test
//    void shouldUpdateUserTest() {
//        UserResponseDto actualUser = userService.create(InitializationDto.createUserRequestDto1());
//        String id = actualUser.getId();
//        UserResponseDto updateUser = userService.update(id, InitializationDto.createUserRequestDto2());
//
//        assert (updateUser.getUsername().equals(InitializationDto.createUserRequestDto2().getUsername()));
//        assert (updateUser.getEmail()).equals(InitializationDto.createUserRequestDto2().getEmail());
//        assert (updateUser.getRating().equals(InitializationDto.createUserRequestDto2().getRating()));
//        userRepository.deleteAll();
//
//    }
//
//    @Override
//    public void stopContainer() {
//        super.stopContainer();
//    }
//}
