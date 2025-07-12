package com.nkcode.nksocialmedia.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
@SecurityRequirement(name = "bearerAuth")
public class PostController {

    @GetMapping("/nk")
    public String getNkSocialMediaPost() {
        return "Getting Nk Social Media posts.";
    }

    @GetMapping("/linkedIn")
    public String getLinkedinPost() {
        return "Getting Linkedin posts.";
    }
}
