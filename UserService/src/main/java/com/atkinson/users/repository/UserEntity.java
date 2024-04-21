package com.atkinson.users.repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
@Table(name = "app_users")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -5667444093602396011L;
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false, unique = true)
    private String userId;
    @Column(nullable = false, length = 50)
    private String firstName;
    @Column(nullable = false, length = 50)
    private String lastName;
    @Column(nullable = false, unique = true, length = 100)
    private String email;
    @Column(nullable = false)
    private String encryptedPassword;
    @Column(nullable = false, unique = true, length = 16)
    private String userName;
}
