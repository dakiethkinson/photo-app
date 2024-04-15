package com.atkinson.users.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    @NotNull
    @Size(min = 3)
    private String firstName;

    @NotNull
    @Size(min = 3)
    private String lastName;

    @Email
    @NotNull
    private String email;

    @NotNull
    @Size(min = 8, max = 16)
    private String password;

    @NotNull
    @Size(min = 5, max = 16)
    private String userName;
}


