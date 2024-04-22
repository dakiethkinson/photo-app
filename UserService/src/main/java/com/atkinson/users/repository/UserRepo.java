package com.atkinson.users.repository;

import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<UserEntity, Long> {

    public UserEntity findByUserName(String userName);
}
