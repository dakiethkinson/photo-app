package com.atkinson.users.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String userId;
}
