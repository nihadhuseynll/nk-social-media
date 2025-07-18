package com.nkcode.nksocialmedia.service.abstraction;

import com.nkcode.nksocialmedia.dao.entity.Reaction;
import com.nkcode.nksocialmedia.dto.request.CreateReactionRequestDto;

import java.util.UUID;

public interface ReactionService {
    Reaction createReaction(UUID userId, CreateReactionRequestDto createReactionRequestDto);
}
