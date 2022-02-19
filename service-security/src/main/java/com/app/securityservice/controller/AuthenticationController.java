package com.app.securityservice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.securityservice.dto.AuthenticationRequestDto;
import com.app.securityservice.entity.User;
import com.app.securityservice.security.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.securityservice.repository.UserRepository;
import com.app.securityservice.service.UserDetailsServiceSecurity;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private JwtTokenProvider jwtTokenProvider;
    private UserDetailsServiceSecurity userDetailsServiceSecurity;

    public AuthenticationController(AuthenticationManager authenticationManager, UserRepository userRepository,
            JwtTokenProvider jwtTokenProvider, UserDetailsServiceSecurity userDetailsServiceSecurity) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsServiceSecurity = userDetailsServiceSecurity;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequestDto request) {

        User user = userDetailsServiceSecurity.findByEmailAndPassword(request.getEmail(), request.getPassword());
        String token = jwtTokenProvider.createToken(user.getEmail(), user.getAuthorities());
        return new ResponseEntity(token, HttpStatus.OK);

    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }
}
