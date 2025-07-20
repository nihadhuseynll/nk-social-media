package com.nkcode.nksocialmedia.service.abstraction;

import com.nkcode.nksocialmedia.dao.entity.Comment;
import com.nkcode.nksocialmedia.dto.request.CreateCommentRequestDto;
import com.nkcode.nksocialmedia.dto.response.CommentResponseDto;

import java.util.List;
import java.util.UUID;

public interface CommentService {

    Comment createComment(UUID userId, CreateCommentRequestDto createCommentRequestDto);

    List<CommentResponseDto> getComments(UUID postId);
}
