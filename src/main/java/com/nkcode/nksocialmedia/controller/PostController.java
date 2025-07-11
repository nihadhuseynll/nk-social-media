package com.nkcode.nksocialmedia.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/post")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Post Management", description = "API's for managing posts")
public class PostController {

    @GetMapping("/nk")
    @Operation(summary = "Get posts from Nk Social Media", description = "Getting posts from Nk Social Media")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nk posts was gotten successfully",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data",
                    content = @Content(schema = @Schema()))
    })
    public String getNkSocialMediaPost() {
        return "Getting Nk Social Media posts.";
    }

    @GetMapping("/linkedIn")
    @Operation(summary = "Get posts from LinkedIn", description = "Getting posts from LinkedIn")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "LinkedIn posts was gotten successfully",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data",
                    content = @Content(schema = @Schema()))
    })
    public String getLinkedinPost() {
        return "Getting Linkedin posts.";
    }

    @GetMapping("/csrf")
    @Operation(summary = "Get csrf token from request", description = "Getting csrf token from requests")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "CSRF Token was gotten successfully",
                    content = @Content(schema = @Schema(implementation = CsrfToken.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data",
                    content = @Content(schema = @Schema()))
    })
    public CsrfToken getToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }
}
