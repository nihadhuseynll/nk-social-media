package com.nkcode.nksocialmedia.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "photos")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Lob
    @Column(nullable = false)
    String imageUrl;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    Post post;
}
