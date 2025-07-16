package com.nkcode.nksocialmedia.mapper;

import com.nkcode.nksocialmedia.dao.entity.Role;
import com.nkcode.nksocialmedia.dto.request.CreateRoleRequestDto;
import com.nkcode.nksocialmedia.dto.response.CreateRoleResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper {

    @Mapping(source = "roleName", target = "roleName")
    Role toEntity(CreateRoleRequestDto roleRequestDto);

    @Mapping(source = "roleName", target = "roleName")
    CreateRoleResponseDto toRoleResponseDto(Role role);
}
