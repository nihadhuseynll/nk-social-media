package com.nkcode.nksocialmedia.controller;

import com.nkcode.nksocialmedia.dao.entity.UserReg;
import com.nkcode.nksocialmedia.dao.repository.UserRegRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
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

    @PostMapping("/registration")
    public UserReg registration(@RequestBody UserReg userReg) {
        return userRegRepository.save(userReg);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserReg userReg) {
        UserReg checkUser = userRegRepository.findByUsernameAndPassword(userReg.getUsername(), userReg.getPassword());
        if (Objects.isNull(checkUser)) {
            return "Invalid username or password";
        }
        return checkUser.getUsername();
    }
}
