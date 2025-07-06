package com.nkcode.nksocialmedia.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    public record User(Integer id, String username, String password) {
    }

    List<User> users = new ArrayList<User>(
            List.of(
                    new User(1, "Nihad", "Nihad2258."),
                    new User(2, "Ilkin", "Ilkin2258.")
            )
    );

    @GetMapping
    public List<User> getUsers() {
        return users;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        users.add(user);
        return user;
    }
}
