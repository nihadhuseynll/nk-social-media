package com.nkcode.nksocialmedia.service.abstraction;

import com.nkcode.nksocialmedia.dao.entity.Role;
import com.nkcode.nksocialmedia.dto.request.RoleRequestDto;

import java.util.List;

public interface RoleService {

    Role createRole(RoleRequestDto roleRequestDto);

    List<Role> getRoles();
}
