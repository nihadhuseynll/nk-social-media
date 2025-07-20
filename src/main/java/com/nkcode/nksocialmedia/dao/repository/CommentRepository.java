package com.nkcode.nksocialmedia.dao.repository;

import com.nkcode.nksocialmedia.dao.entity.Comment;
import com.nkcode.nksocialmedia.dto.projection.CommentSummaryProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {

    @Query("""
            Select c.description AS description,
            c.createdDate AS createdDate,
            c.parentComment.id AS parentCommentId,
            u.userName AS userName,
            COUNT (DISTINCT r.id) AS reactionCount,
            COUNT (DISTINCT rc.id) AS replyCount
             from Comment c
             JOIN c.user u
             LEFT JOIN c.reactions r
             LEFT JOIN c.replyComments rc
             WHERE c.post.id = :postId
             GROUP BY c.description, c.createdDate, c.parentComment.id, u.userName
            """)
    List<CommentSummaryProjection> getComments(UUID postId);
}
