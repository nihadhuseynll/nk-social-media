package com.nkcode.nksocialmedia.dao.repository;

import com.nkcode.nksocialmedia.dao.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {
    Comment findParentCommentById(UUID id);
}
