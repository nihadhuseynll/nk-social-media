package com.nkcode.nksocialmedia.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateRoleRequestDto {

    @NotBlank(message = "Role name can't be blank")
    private String roleName;
}
