package com.atkinson.users.service;

import com.atkinson.users.repository.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserServiceIF extends UserDetailsService {
    UserDTO createUser(UserDTO userDetails);
    UserDTO getUserByUserName(String username);
}
