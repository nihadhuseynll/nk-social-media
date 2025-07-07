package com.nkcode.nksocialmedia.controller;

import com.nkcode.nksocialmedia.dao.entity.UserReg;
import com.nkcode.nksocialmedia.dao.repository.UserRegRepository;
import com.nkcode.nksocialmedia.service.CustomUserDetailsService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {

    UserRegRepository userRegRepository;
    CustomUserDetailsService customUserDetailsService;

    @PostMapping("/registration")
    public UserReg registration(@RequestBody UserReg userReg) {
        return userRegRepository.save(userReg);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserReg userReg) {
        UserDetails checkUser = customUserDetailsService.loadUserByUsername(userReg.getUsername());
        return checkUser.getUsername();
    }
}
