package com.nkcode.nksocialmedia.dao.entity;

import com.nkcode.nksocialmedia.dto.enums.ReactionTargetType;
import com.nkcode.nksocialmedia.dto.enums.ReactionType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reactions", uniqueConstraints =
        {
                @UniqueConstraint(columnNames = {"user_id", "post_id"}),
                @UniqueConstraint(columnNames = {"user_id", "comment_id"})
        }
)
@Check(constraints =
        "(" +
                "(reaction_target_type = 'POST' AND comment_id IS NULL AND post_id IS NOT NULL) OR " +
                "(reaction_target_type = 'COMMENT' AND comment_id IS NOT NULL AND post_id IS NULL)" +
                ")"
)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Reaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @CreationTimestamp
    LocalDateTime createdDate;

    @UpdateTimestamp
    LocalDateTime updatedDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    ReactionType reactionType;

    @Column(name = "reaction_target_type", nullable = false)
    @Enumerated(EnumType.STRING)
    ReactionTargetType reactionTargetType;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    Post post;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    Comment comment;
}
