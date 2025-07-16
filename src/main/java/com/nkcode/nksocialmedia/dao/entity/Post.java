package com.nkcode.nksocialmedia.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "posts")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(nullable = false)
    String description;

    @CreationTimestamp
    @Column(nullable = false)
    LocalDateTime createdDate;

    @UpdateTimestamp
    LocalDateTime updatedDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @Builder.Default
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Photo> photos = new HashSet<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Reaction> reactions = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Comment> comments = new ArrayList<>();
}
