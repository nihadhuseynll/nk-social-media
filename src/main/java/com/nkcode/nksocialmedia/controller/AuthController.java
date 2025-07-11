package com.nkcode.nksocialmedia.controller;

import com.nkcode.nksocialmedia.dao.entity.User;
import com.nkcode.nksocialmedia.dto.request.LoginRequestDto;
import com.nkcode.nksocialmedia.dto.request.RegistrationRequestDto;
import com.nkcode.nksocialmedia.dto.response.JwtResponseDto;
import com.nkcode.nksocialmedia.dto.response.RegistrationResponseDto;
import com.nkcode.nksocialmedia.mapper.UserMapper;
import com.nkcode.nksocialmedia.service.abstraction.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/auth")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "Authentication Controller", description = "Functions for user registration and login")
public class AuthController {

    UserMapper userMapper;
    UserService userService;

    @Operation(
            summary = "Register new user",
            description = "Registers a new user in the system and returns user details"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User registered successfully",
                    content = @Content(schema = @Schema(implementation = RegistrationResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "User already exists",
                    content = @Content)
    })
    @PostMapping("/registration")
    public ResponseEntity<RegistrationResponseDto> registration(@Valid @RequestBody RegistrationRequestDto registrationRequestDto) {
        User user = userService.register(registrationRequestDto);
        RegistrationResponseDto registrationResponseDto = userMapper.toRegistrationResponseDto(user);
        return ResponseEntity.ok(registrationResponseDto);
    }

    @Operation(
            summary = "Login existing user",
            description = "Logs in a user and returns JWT token upon successful authentication"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User logged in successfully",
                    content = @Content(schema = @Schema(implementation = JwtResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "Invalid credentials",
                    content = @Content)
    })
    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        String generatedToken = userService.login(loginRequestDto);
        return ResponseEntity.ok(new JwtResponseDto(generatedToken));
    }
}