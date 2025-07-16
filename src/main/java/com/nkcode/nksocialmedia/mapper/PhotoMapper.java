package com.nkcode.nksocialmedia.mapper;

import com.nkcode.nksocialmedia.dao.entity.Photo;
import com.nkcode.nksocialmedia.dto.response.PhotoResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface PhotoMapper {

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "imageUrl", source = "imageUrl")
    })
    PhotoResponseDto toDto(Photo photo);
}
