package com.nkcode.nksocialmedia.mapper;

import com.nkcode.nksocialmedia.dao.entity.Role;
import com.nkcode.nksocialmedia.dto.request.RoleRequestDto;
import com.nkcode.nksocialmedia.dto.response.RoleResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper {

    @Mapping(source = "roleName", target = "roleName")
    Role toEntity(RoleRequestDto roleRequestDto);

    @Mapping(source = "roleName", target = "roleName")
    RoleResponseDto toRoleResponseDto(Role role);
}
