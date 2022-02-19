package com.app.securityservice.service;

import java.time.Instant;
import java.util.List;

import com.app.securityservice.dto.MailDto;
import com.app.securityservice.dto.UserRequestDto;
import com.app.securityservice.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.securityservice.entity.User;
import com.app.securityservice.repository.UserRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final RabbitMqProducer rabbitMqProducer;

    public UserResponseDto getOne(final String id) {

        return userRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("User has not been found"));

    }

    public List<UserResponseDto> getAll() {
        List<User> userList = userRepository.findAll();
        return mapper.toDtoList(userList);
    }

    public void deleteById(final String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
    }

    public UserResponseDto create(UserRequestDto dto) {
        if (userRepository.existsUserByEmail(dto.getEmail()) || userRepository.existsUserByUsername(dto.getUsername())) {
            throw new RuntimeException("User with this mail/name already exists");
        }
        User user = mapper.toEntity(dto);
        user.setCreatedAt(Instant.now());
        userRepository.save(user);

        MailDto mailDto = new MailDto();
        mailDto.setEmail(user.getEmail());
        mailDto.setUserName(user.getUsername());
        mailDto.setTemplate(MailDto.Template.EMAIL_APPROVE);
        mailDto.setSubject("Creat user");

        rabbitMqProducer.sent(mailDto);
        return mapper.toDto(user);
    }
//dfgdfgfdghdfg ывавы 44 55

    public UserResponseDto update(final String id, final UserRequestDto dto) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User has not been found"));

        if ((userRepository.existsUserByEmailAndIdNot(dto.getEmail(), id)
                || userRepository.existsUserByUsernameAndIdNot(dto.getUsername(), id))) {
            throw new RuntimeException("User with this mail/name already exists");
        }

        mapper.toEntityUpdate(dto, user);
        user.setUpdatedAt(Instant.now());
        User updatedUser = userRepository.save(user);
        return mapper.toDto(updatedUser);
    }

}
