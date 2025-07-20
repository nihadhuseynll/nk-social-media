package com.nkcode.nksocialmedia.service.concrete;

import com.nkcode.nksocialmedia.dao.entity.Comment;
import com.nkcode.nksocialmedia.dao.entity.Post;
import com.nkcode.nksocialmedia.dao.entity.User;
import com.nkcode.nksocialmedia.dao.repository.CommentRepository;
import com.nkcode.nksocialmedia.dao.repository.PostRepository;
import com.nkcode.nksocialmedia.dao.repository.UserRepository;
import com.nkcode.nksocialmedia.dto.projection.CommentSummaryProjection;
import com.nkcode.nksocialmedia.dto.request.CreateCommentRequestDto;
import com.nkcode.nksocialmedia.dto.response.CommentResponseDto;
import com.nkcode.nksocialmedia.exception.custom.CommentNotFoundException;
import com.nkcode.nksocialmedia.exception.custom.PostNotFoundException;
import com.nkcode.nksocialmedia.exception.custom.UserNotFoundException;
import com.nkcode.nksocialmedia.mapper.CommentMapper;
import com.nkcode.nksocialmedia.service.abstraction.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Override
    public Comment createComment(UUID userId, CreateCommentRequestDto createCommentRequestDto) {

        User user = userRepository.findById(userId).orElseThrow(() ->
                new UserNotFoundException("The specified user does not exist or has been deleted."));

        Post post = postRepository.findById(createCommentRequestDto.getPostId()).orElseThrow(() ->
                new PostNotFoundException("The specified post does not exist or has been deleted."));

        Comment parentComment = null;
        if (Objects.nonNull(createCommentRequestDto.getParentCommentId())) {
            parentComment = commentRepository.findById(createCommentRequestDto.getParentCommentId()).orElseThrow(()
                    -> new CommentNotFoundException("The specified comment does not exist or has been deleted."));
        }

        Comment comment = Comment.builder()
                .description(createCommentRequestDto.getDescription())
                .user(user)
                .post(post)
                .parentComment(parentComment)
                .build();

        return commentRepository.save(comment);
    }

    @Override
    public List<CommentResponseDto> getComments(UUID postId) {

        List<CommentSummaryProjection> allPostComments = commentRepository.getAllPostComments(postId);
        return commentMapper.toGetAllPostCommentsDtoList(allPostComments);
    }
}
