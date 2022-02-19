
package com.app.securityservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.app.securityservice.dto.UserRequestDto;
import com.app.securityservice.dto.UserResponseDto;
import com.app.securityservice.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.securityservice.repository.UserRepository;

@Slf4j
@SpringBootTest
class UserServiceTest {

    private final Instant currentTime = Instant.now();
    private final UserMapper userMapper = mock(UserMapper.class);
    private final UserRepository userRepository = mock(UserRepository.class);
    private final RabbitMqProducer rabbitMqProducer = mock(RabbitMqProducer.class);
    private final UserResponseDto userResponseDto = createUserResponseDto();
    private final UserRequestDto userRequestDto = createUserRequestDto();
    private final UserService userService = getService();
    private final String id = "f3aa0ba4-47e6-47b3-853f-3ed8cda54cd3";
    private final User user = createUser();

    private UserService service;

    public UserService getService() {
        if (service == null) {
            service = new UserService(userRepository, userMapper, rabbitMqProducer);
        }
        return service;
    }

    @Test
    void shouldGetOneTest() {

        when(userRepository.findById(id)).thenReturn(java.util.Optional.ofNullable(user));
        when(userMapper.toDto(any(User.class))).thenReturn(userResponseDto);

        assertEquals(user.getEmail(), userService.getOne(id)
                .getEmail());

        verify(userMapper).toDto(any(User.class));
        verify(userRepository).findById(id);
    }

    @Test
    void shouldGetAllTest() {

        List<User> userList = new ArrayList<>();
        List<UserResponseDto> dtoList = new ArrayList<>();

        when(userRepository.findAll()).thenReturn(userList);
        when(userMapper.toDtoList(any(List.class))).thenReturn(dtoList);

        assertEquals(userList, dtoList);
        userService.getAll();

        verify(userRepository).findAll();
        verify(userMapper).toDtoList(userList);

    }

    @Test
    void shouldDeleteByIdTest() {

        when(userRepository.existsById(id)).thenReturn(true);

        userService.deleteById(id);

        verify(userRepository).deleteById(id);

    }

    @Test
    void shouldCreateTest() {

        when(userMapper.toEntity(userRequestDto)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toDto(user)).thenReturn(userResponseDto);

        UserResponseDto savedUser = userService.create(userRequestDto);

        assertEquals(currentTime, savedUser.getCreatedAt());

        verify(userRepository).save(user);
        verify(userMapper).toEntity(userRequestDto);
        verify(userMapper).toDto(user);
    }

    @Test
    void shouldUpdateTest() {

        doNothing().when(userMapper)
                .toEntityUpdate(userRequestDto, user);
        when(userRepository.findById(id)).thenReturn(java.util.Optional.ofNullable(user));
        when(userRepository.save(user)).thenReturn(user);

        user.setCreatedAt(Instant.now());
        service.update(id, userRequestDto);
        assertNotNull(user);

        verify(userRepository).save(user);
        verify(userRepository).findById(id);
        verify(userMapper).toEntityUpdate(userRequestDto, user);

    }

    private User createUser() {
        return User.builder()
                .id(id)
                .username("John")
                .email("John@email.com")
                // .role("driver")
                .createdAt(currentTime)

                .build();
    }

    private UserResponseDto createUserResponseDto() {
        return UserResponseDto.builder()
                .id(id)
                .username("John")
                .email("John@email.com")
                // .role("driver")
                .createdAt(currentTime)
                .build();
    }

    private UserRequestDto createUserRequestDto() {
        return UserRequestDto.builder()
                .username("John")
                .email("John@email.com")
                // .role("driver")
                .build();
    }
}
