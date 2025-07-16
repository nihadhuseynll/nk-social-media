package com.nkcode.nksocialmedia.service.abstraction;

import com.nkcode.nksocialmedia.dao.entity.Role;
import com.nkcode.nksocialmedia.dto.request.CreateRoleRequestDto;

import java.util.List;

public interface RoleService {

    Role createRole(CreateRoleRequestDto roleRequestDto);

    List<Role> getRoles();
}
