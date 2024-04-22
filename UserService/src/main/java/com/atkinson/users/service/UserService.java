package com.atkinson.users.service;

import com.atkinson.users.repository.UserDTO;
import com.atkinson.users.repository.UserEntity;
import com.atkinson.users.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

import static org.modelmapper.convention.MatchingStrategies.STRICT;
@Service
public class UserService implements UserServiceIF{

    UserRepo userRepo;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepo userRepo, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepo = userRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDTO createUser(UserDTO userDetails) {
        userDetails.setUserId(UUID.randomUUID().toString());
        userDetails.setEncryptedPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(STRICT);
        UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);
        userRepo.save(userEntity);
        return null;
    }

    @Override
    public UserDTO getUserByUserName(String username) {
        UserEntity userEntity = userRepo.findByUserName(username);

        if (userEntity == null) throw new UsernameNotFoundException(username);

        return new ModelMapper().map(userEntity, UserDTO.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepo.findByUserName(username);

        if (userEntity == null) throw new UsernameNotFoundException(username);

        return new User(
                userEntity.getUserName(),
                userEntity.getEncryptedPassword(),
                true,
                true,
                true,
                true,
                new ArrayList<>());
    }
}
