package com.nkcode.nksocialmedia.mapper;

import com.nkcode.nksocialmedia.dao.entity.Reaction;
import com.nkcode.nksocialmedia.dto.response.CreateReactionResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        uses = {UserMapper.class, PostMapper.class, CommentMapper.class})
public interface ReactionMapper {

    @Mappings({
            @Mapping(target = "createdDate", source = "createdDate"),
            @Mapping(target = "reactionType", source = "reactionType"),
            @Mapping(target = "reactionTargetType", source = "reactionTargetType"),
            @Mapping(target = "userResponseDto", source = "user"),
            @Mapping(target = "postResponseDto", source = "post"),
            @Mapping(target = "commentResponseDto", source = "comment")
    })
    CreateReactionResponseDto toCreateReactionResponseDto(Reaction reaction);
}
