package com.nkcode.nksocialmedia.controller;

import com.nkcode.nksocialmedia.dao.entity.Reaction;
import com.nkcode.nksocialmedia.dto.request.CreateReactionRequestDto;
import com.nkcode.nksocialmedia.dto.response.CreateReactionResponseDto;
import com.nkcode.nksocialmedia.mapper.ReactionMapper;
import com.nkcode.nksocialmedia.security.auth.CustomUserDetails;
import com.nkcode.nksocialmedia.service.abstraction.ReactionService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reaction")
@SecurityRequirement(name = "bearerAuth")
public class ReactionController {

    private final ReactionMapper reactionMapper;
    private final ReactionService reactionService;

    @PostMapping("/createReaction")
    public ResponseEntity<CreateReactionResponseDto> createReaction
            (@RequestBody CreateReactionRequestDto createReactionRequestDto,
             @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        UUID userId = customUserDetails.getUser().getId();
        Reaction reaction = reactionService.createReaction(userId, createReactionRequestDto);
        return new ResponseEntity<>(
                reactionMapper.toCreateReactionResponseDto(reaction),
                HttpStatus.CREATED
        );
    }
}
