package com.nkcode.nksocialmedia.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PhotoRequestDto {

    @NotBlank(message = "Image Url can't be blank")
    String imageUrl;
}
