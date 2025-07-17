package com.nkcode.nksocialmedia.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateCommentRequestDto {

    @NotBlank(message = "Description can't be blank")
    String description;

    @NotNull(message = "Post Id can't be null")
    UUID postId;

    UUID parentCommentId;
}
