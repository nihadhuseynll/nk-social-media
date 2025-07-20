package com.nkcode.nksocialmedia.mapper;

import com.nkcode.nksocialmedia.dao.entity.Comment;
import com.nkcode.nksocialmedia.dto.projection.CommentSummaryProjection;
import com.nkcode.nksocialmedia.dto.response.CommentResponseDto;
import com.nkcode.nksocialmedia.dto.response.CreateCommentResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {UserMapper.class, PostMapper.class})
public interface CommentMapper {

    @Mappings({
            @Mapping(target = "description", source = "description"),
            @Mapping(target = "createdDate", source = "createdDate"),
            @Mapping(target = "user", source = "user"),
            @Mapping(target = "post", source = "post"),
            @Mapping(target = "parentComment", source = "parentComment")
    })
    CreateCommentResponseDto toCreateCommentResponseDto(Comment comment);

    CommentResponseDto toCommentResponseDto(Comment comment);

    List<CommentResponseDto> toCommentResponseDtoList
            (List<CommentSummaryProjection> commentSummaryProjections);
}
