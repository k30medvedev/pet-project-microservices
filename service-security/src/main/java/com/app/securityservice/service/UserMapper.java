package com.app.securityservice.service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

import com.app.securityservice.config.MapperBeanConfig;
import com.app.securityservice.dto.AuthenticationRequestDto;
import com.app.securityservice.dto.UserRequestDto;
import com.app.securityservice.dto.UserResponseDto;
import com.app.securityservice.entity.User;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final MapperBeanConfig modelMapper;

    public UserMapper(MapperBeanConfig modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserResponseDto toDto(User entity) {
        return Objects.isNull(entity) ? null : modelMapper.map(entity, UserResponseDto.class);
    }

    public List<UserResponseDto> toDtoList(List<User> userList) {
        Type listType = new TypeToken<List<UserResponseDto>>() {
        }.getType();
        return modelMapper.map(userList, listType);
    }

    public User toEntity(UserRequestDto dto) {
        return Objects.isNull(dto) ? null : modelMapper.map(dto, User.class);
    }

    public void toEntityUpdate(UserRequestDto dto, User destination) {
        modelMapper.map(dto, destination);
    }

    public AuthenticationRequestDto toDtoLogin(User entity) {
        return Objects.isNull(entity) ? null : modelMapper.map(entity, AuthenticationRequestDto.class);
    }

}
