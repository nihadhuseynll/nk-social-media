package com.nkcode.nksocialmedia.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "CreatePostRequestDto")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreatePostRequestDto {

    @NotBlank(message = "User Id can't be blank")
    UUID userId;

    @NotBlank(message = "Description can't be blank")
    String description;

    Set<PhotoRequestDto> photos;
}
