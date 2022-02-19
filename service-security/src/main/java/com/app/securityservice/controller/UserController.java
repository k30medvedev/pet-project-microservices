package com.app.securityservice.controller;

import com.app.securityservice.dto.UserRequestDto;
import com.app.securityservice.dto.UserResponseDto;
import com.app.securityservice.entity.User;
import com.app.securityservice.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasAnyAuthority('user:read')")
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable final String id) {
        return new ResponseEntity(userService.getOne(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('user:read')")
    @GetMapping("/users")
    public ResponseEntity<User> getAllUsers() {
        return new ResponseEntity(userService.getAll(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('user:read')")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") final String id) {
        userService.deleteById(id);
        return ResponseEntity.noContent()
                .build();
    }
    @PreAuthorize("hasAnyAuthority('user:write')")
    @PostMapping("/users")
    public ResponseEntity<UserResponseDto> creatUser(@RequestBody final UserRequestDto userRequestDto) {
        return new ResponseEntity<>(userService.create(userRequestDto), HttpStatus.CREATED);
    }
    @PreAuthorize("hasAnyAuthority('user:write')")
    @PutMapping("/users/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable final String id,
            @RequestBody final UserRequestDto userRequestDto) {
        return new ResponseEntity<>(userService.update(id, userRequestDto), HttpStatus.OK);
    }
}
