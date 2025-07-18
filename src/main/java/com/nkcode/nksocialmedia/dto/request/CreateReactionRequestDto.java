package com.nkcode.nksocialmedia.dto.request;

import com.nkcode.nksocialmedia.dto.enums.ReactionTargetType;
import com.nkcode.nksocialmedia.dto.enums.ReactionType;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateReactionRequestDto {

    @NotNull
    ReactionType reactionType;

    @NotNull(message = "Reaction Target Type can't be null")
    ReactionTargetType reactionTargetType;

    UUID postId;
    UUID commentId;
}
