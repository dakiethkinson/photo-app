package com.atkinson.users.repository;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

@Getter
@Setter
public class UserDTO {
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
