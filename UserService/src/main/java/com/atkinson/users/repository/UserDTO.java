package com.atkinson.users.repository;


import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;


@Data
public class UserDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -2686664094901441707L;
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String encryptedPassword;
    private String userName;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Timestamp lastLogin;
}
