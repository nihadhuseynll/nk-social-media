package com.nkcode.nksocialmedia.dto.response;

import com.nkcode.nksocialmedia.dao.entity.Photo;
import com.nkcode.nksocialmedia.dao.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreatePostResponseDto {

    UUID id;
    String description;
    LocalDateTime createdDate;
    UserResponseDto user;
    Set<PhotoResponseDto> photos;
}
