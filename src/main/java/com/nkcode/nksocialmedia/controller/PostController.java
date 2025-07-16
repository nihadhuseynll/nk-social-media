package com.nkcode.nksocialmedia.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nkcode.nksocialmedia.dao.entity.Post;
import com.nkcode.nksocialmedia.dto.request.CreatePostRequestDto;
import com.nkcode.nksocialmedia.dto.response.CreatePostResponseDto;
import com.nkcode.nksocialmedia.mapper.PostMapper;
import com.nkcode.nksocialmedia.service.abstraction.PostService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
@SecurityRequirement(name = "bearerAuth")
public class PostController {

    private final PostMapper postMapper;
    private final PostService postService;
    private final ObjectMapper objectMapper;

    @PostMapping(value = "/createPost", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CreatePostResponseDto> createPost(
            @RequestPart("dto") String createPostRequestDto,
            @RequestPart("file") MultipartFile[] multipartFile) throws IOException {

        CreatePostRequestDto dto = objectMapper.readValue(createPostRequestDto, CreatePostRequestDto.class);
        Post post = postService.createPost(dto, multipartFile);
        return new ResponseEntity<>(
                postMapper.toCreatePostResponseDto(post),
                HttpStatus.CREATED
        );
    }
}
