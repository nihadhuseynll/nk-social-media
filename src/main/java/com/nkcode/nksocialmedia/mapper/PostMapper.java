package com.nkcode.nksocialmedia.mapper;

import com.nkcode.nksocialmedia.dao.entity.Post;
import com.nkcode.nksocialmedia.dto.projection.PostSummaryProjection;
import com.nkcode.nksocialmedia.dto.response.CreatePostResponseDto;
import com.nkcode.nksocialmedia.dto.response.PostResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE, uses = {PhotoMapper.class, UserMapper.class})
public interface PostMapper {

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "description", source = "description"),
            @Mapping(target = "createdDate", source = "createdDate"),
            @Mapping(target = "user", source = "user"),
            @Mapping(target = "photos", source = "photos")
    })
    CreatePostResponseDto toCreatePostResponseDto(Post post);

    @Mappings({
            @Mapping(target = "description", source = "description")
    })
    PostResponseDto toPostResponseDto(Post post);

    List<PostResponseDto> toPostResponseDtoList
            (List<PostSummaryProjection> postSummaryProjections);
}
