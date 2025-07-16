package com.nkcode.nksocialmedia.service.concrete;

import com.nkcode.nksocialmedia.dao.entity.Role;
import com.nkcode.nksocialmedia.dao.repository.RoleRepository;
import com.nkcode.nksocialmedia.dto.request.CreateRoleRequestDto;
import com.nkcode.nksocialmedia.exception.custom.RoleNotFoundException;
import com.nkcode.nksocialmedia.mapper.RoleMapper;
import com.nkcode.nksocialmedia.service.abstraction.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleServiceImpl implements RoleService {

    RoleMapper roleMapper;
    RoleRepository roleRepository;

    @Override
    public Role createRole(CreateRoleRequestDto roleRequestDto) {
        Role role = roleMapper.toEntity(roleRequestDto);
        return roleRepository.save(role);
    }

    @Override
    public List<Role> getRoles() {
        List<Role> roles = roleRepository.findAll();

        if (roles.isEmpty()) {
            throw new RoleNotFoundException("No roles found in the system.");
        }
        return roles;
    }
}
