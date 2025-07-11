package com.nkcode.nksocialmedia.dto.response;

import com.nkcode.nksocialmedia.dto.enums.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegistrationResponseDto {

    UUID id;

    String firstName;

    String lastName;

    String email;

    String userName;

    String password;

    Gender gender;

    String phone;
}
