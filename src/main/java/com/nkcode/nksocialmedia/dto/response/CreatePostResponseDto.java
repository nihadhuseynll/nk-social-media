package com.nkcode.nksocialmedia.dto.response;

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
