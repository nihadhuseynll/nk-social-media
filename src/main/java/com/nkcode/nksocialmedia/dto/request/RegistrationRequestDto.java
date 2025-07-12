package com.nkcode.nksocialmedia.dto.request;

import com.nkcode.nksocialmedia.dao.entity.Role;
import com.nkcode.nksocialmedia.dto.enums.Gender;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequestDto {

    @NotBlank(message = "First name can't be blank")
    private String firstName;

    @NotBlank(message = "Last name can't be blank")
    private String lastName;

    @NotBlank(message = "Email can't be blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Username can't be blank")
    @Size(min = 4, max = 20, message = "Username must be between 4 and 20 characters")
    private String userName;

    @NotBlank(message = "Password can't be blank")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    @NotNull(message = "Gender is required")
    private Gender gender;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\+994\\d{9}$", message = "Phone number must be in the format '+994xxxxxxxxx'")
    private String phone;

    @NotEmpty(message = "roles can't be empty")
    private Set<RoleRequestDto> roles;
}
