package com.nkcode.nksocialmedia.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginRequestDto {

    @NotBlank(message = "Username can't be blank")
    String userName;

    @NotBlank(message = "Password can't be blank")
    String password;
}
