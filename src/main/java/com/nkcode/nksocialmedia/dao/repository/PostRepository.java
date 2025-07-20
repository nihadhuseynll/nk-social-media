package com.nkcode.nksocialmedia.dao.repository;

import com.nkcode.nksocialmedia.dao.entity.Post;
import com.nkcode.nksocialmedia.dto.projection.PostSummaryProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {

    @Query("""
            Select p.description AS description,
            p.createdDate AS createdDate,
            u.userName AS userName,
            COUNT(DISTINCT c.id) AS commentCount,
            COUNT(DISTINCT r.id) AS reactionCount
            from Post p
            JOIN p.user u
            LEFT JOIN p.comments c
            LEFT JOIN p.reactions r
            GROUP BY p.description, p.createdDate, u.userName
            """)
    List<PostSummaryProjection> getAllPosts();
}
