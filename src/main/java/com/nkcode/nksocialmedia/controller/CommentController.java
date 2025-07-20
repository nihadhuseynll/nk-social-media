package com.nkcode.nksocialmedia.controller;

import com.nkcode.nksocialmedia.dao.entity.Comment;
import com.nkcode.nksocialmedia.dto.request.CreateCommentRequestDto;
import com.nkcode.nksocialmedia.dto.response.CommentResponseDto;
import com.nkcode.nksocialmedia.dto.response.CreateCommentResponseDto;
import com.nkcode.nksocialmedia.mapper.CommentMapper;
import com.nkcode.nksocialmedia.security.auth.CustomUserDetails;
import com.nkcode.nksocialmedia.service.abstraction.CommentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
@SecurityRequirement(name = "bearerAuth")
public class CommentController {

    private final CommentService commentService;
    private final CommentMapper commentMapper;

    @PostMapping("/createComment")
    public ResponseEntity<CreateCommentResponseDto> createComment
            (@RequestBody CreateCommentRequestDto createCommentRequestDto,
             @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        UUID userId = customUserDetails.getUser().getId();
        Comment comment = commentService.createComment(userId, createCommentRequestDto);

        return new ResponseEntity<>(
                commentMapper.toCreateCommentResponseDto(comment),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/getComments/{postId}")
    public ResponseEntity<List<CommentResponseDto>> getComments(@PathVariable UUID postId) {

        List<CommentResponseDto> comments = commentService.getComments(postId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}
