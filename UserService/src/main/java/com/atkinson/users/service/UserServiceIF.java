package com.atkinson.users.service;

import com.atkinson.users.repository.UserDTO;

public interface UserServiceIF {
    UserDTO createUser(UserDTO userDetails);
}
