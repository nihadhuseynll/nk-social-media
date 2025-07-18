package com.nkcode.nksocialmedia.service.concrete;

import com.nkcode.nksocialmedia.dao.entity.Comment;
import com.nkcode.nksocialmedia.dao.entity.Post;
import com.nkcode.nksocialmedia.dao.entity.Reaction;
import com.nkcode.nksocialmedia.dao.entity.User;
import com.nkcode.nksocialmedia.dao.repository.CommentRepository;
import com.nkcode.nksocialmedia.dao.repository.PostRepository;
import com.nkcode.nksocialmedia.dao.repository.ReactionRepository;
import com.nkcode.nksocialmedia.dao.repository.UserRepository;
import com.nkcode.nksocialmedia.dto.request.CreateReactionRequestDto;
import com.nkcode.nksocialmedia.exception.custom.InvalidReactionTargetException;
import com.nkcode.nksocialmedia.exception.custom.UserNotFoundException;
import com.nkcode.nksocialmedia.service.abstraction.ReactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReactionServiceImpl implements ReactionService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final ReactionRepository reactionRepository;

    @Override
    public Reaction createReaction(UUID userId, CreateReactionRequestDto createReactionRequestDto) {
        Post post = null;
        Comment comment = null;

        if (createReactionRequestDto.getPostId() == null && createReactionRequestDto.getCommentId() == null
                || createReactionRequestDto.getPostId() != null && createReactionRequestDto.getCommentId() != null) {
            throw new InvalidReactionTargetException
                    ("Reaction must be associated with either a post or a comment, not both.");
        }

        User user = userRepository.findById(userId).orElseThrow(()
                -> new UserNotFoundException("The specified user does not exist or has been deleted."));

        if (createReactionRequestDto.getPostId() != null) {
            post = postRepository.findById(createReactionRequestDto.getPostId())
                    .orElse(null);
        }

        if (createReactionRequestDto.getCommentId() != null) {
            comment = commentRepository.findById(createReactionRequestDto.getCommentId())
                    .orElse(null);
        }

        Reaction reaction = Reaction.builder()
                .reactionType(createReactionRequestDto.getReactionType())
                .reactionTargetType(createReactionRequestDto.getReactionTargetType())
                .user(user)
                .post(post)
                .comment(comment)
                .build();

        return reactionRepository.save(reaction);
    }
}
