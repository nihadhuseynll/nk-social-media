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
@Table(name = "user_regs")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserReg {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(name = "username", nullable = false)
    String username;

    @Column(name = "password", nullable = false)
    String password;
}
