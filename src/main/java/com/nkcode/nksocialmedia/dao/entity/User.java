package com.nkcode.nksocialmedia.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nkcode.nksocialmedia.dto.enums.Sex;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users", indexes = {@Index(name = "idx_user_email", columnList = "email")})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(name = "first_name", nullable = false)
    String firstName;

    @Column(name = "last_name", nullable = false)
    String lastName;

    @Column(name = "email", nullable = false, unique = true)
    String email;

    @Column(name = "user_name", nullable = false, unique = true)
    String userName;

    @JsonIgnore
    @Column(name = "password", nullable = false)
    String password;

    @Column(name = "date_of_birth", nullable = false)
    LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "sex", nullable = false)
    Sex sex;

    @Column(name = "phone", nullable = false, unique = true)
    String phone;

    @JsonIgnore
    @Column(name = "salt")
    String salt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Reaction> reactions = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Comment> comments = new ArrayList<>();
}
