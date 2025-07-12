package com.nkcode.nksocialmedia.controller;

import com.nkcode.nksocialmedia.dao.entity.User;
import com.nkcode.nksocialmedia.dto.request.LoginRequestDto;
import com.nkcode.nksocialmedia.dto.request.RegistrationRequestDto;
import com.nkcode.nksocialmedia.dto.response.JwtResponseDto;
import com.nkcode.nksocialmedia.dto.response.RegistrationResponseDto;
import com.nkcode.nksocialmedia.mapper.UserMapper;
import com.nkcode.nksocialmedia.service.abstraction.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {

    UserMapper userMapper;
    UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<RegistrationResponseDto> registration
            (@Valid @RequestBody RegistrationRequestDto registrationRequestDto) {

        User user = userService.register(registrationRequestDto);
        RegistrationResponseDto registrationResponseDto = userMapper.toRegistrationResponseDto(user);
        return ResponseEntity.ok(registrationResponseDto);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {

        String generatedToken = userService.login(loginRequestDto);
        return ResponseEntity.ok(new JwtResponseDto(generatedToken));
    }
}