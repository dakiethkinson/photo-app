package com.atkinson.users.service;

import com.atkinson.users.repository.UserDTO;

import java.util.UUID;

public class UserService implements UserServiceIF{
    @Override
    public UserDTO createUser(UserDTO userDetails) {
        userDetails.setUserId(UUID.randomUUID().toString());
        return null;
    }
}
