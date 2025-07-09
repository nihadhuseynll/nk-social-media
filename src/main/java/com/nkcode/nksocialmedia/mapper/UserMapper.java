package com.nkcode.nksocialmedia.mapper;

import com.nkcode.nksocialmedia.dao.entity.User;
import com.nkcode.nksocialmedia.dto.request.RegistrationRequestDto;
import com.nkcode.nksocialmedia.dto.response.RegistrationResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mappings({
            @Mapping(source = "firstName", target = "firstName"),
            @Mapping(source = "lastName", target = "lastName"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "userName", target = "userName"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "gender", target = "gender"),
            @Mapping(source = "phone", target = "phone")
    })
    User toEntity(RegistrationRequestDto registrationRequestDto);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "firstName", target = "firstName"),
            @Mapping(source = "lastName", target = "lastName"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "userName", target = "userName"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "gender", target = "gender"),
            @Mapping(source = "phone", target = "phone")
    })
    RegistrationResponseDto toRegistrationResponseDto(User user);
}
