package com.nkcode.nksocialmedia.controller;

import com.nkcode.nksocialmedia.dao.entity.Role;
import com.nkcode.nksocialmedia.dto.request.CreateRoleRequestDto;
import com.nkcode.nksocialmedia.dto.response.CreateRoleResponseDto;
import com.nkcode.nksocialmedia.mapper.RoleMapper;
import com.nkcode.nksocialmedia.service.abstraction.RoleService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {

    RoleMapper roleMapper;
    RoleService roleService;

    @GetMapping("/getRoles")
    public ResponseEntity<List<CreateRoleResponseDto>> getRoles() {

        List<Role> roles = roleService.getRoles();
        return new ResponseEntity<>(
                roles.stream().map(roleMapper::toRoleResponseDto).toList(),
                HttpStatus.OK
        );
    }

    @PostMapping("/createRole")
    public ResponseEntity<CreateRoleResponseDto> createRole
            (@Valid @RequestBody CreateRoleRequestDto roleRequestDto) {

        Role role = roleService.createRole(roleRequestDto);
        return new ResponseEntity<>(
                roleMapper.toRoleResponseDto(role),
                HttpStatus.CREATED
        );
    }
}
