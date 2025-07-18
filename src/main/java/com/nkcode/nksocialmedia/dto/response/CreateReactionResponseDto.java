package com.nkcode.nksocialmedia.dto.response;

import com.nkcode.nksocialmedia.dto.enums.ReactionTargetType;
import com.nkcode.nksocialmedia.dto.enums.ReactionType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateReactionResponseDto {

    LocalDateTime createdDate;
    ReactionType reactionType;
    ReactionTargetType reactionTargetType;
    UserResponseDto userResponseDto;
    PostResponseDto postResponseDto;
    CommentResponseDto commentResponseDto;
}
