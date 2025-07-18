package com.nkcode.nksocialmedia.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateCommentResponseDto {

    String description;
    LocalDateTime createdDate;
    UserResponseDto user;
    PostResponseDto post;
    CommentResponseDto parentComment;
}
