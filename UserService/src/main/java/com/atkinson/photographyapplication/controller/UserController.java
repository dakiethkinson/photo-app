package com.atkinson.photographyapplication.controller;

import com.atkinson.photographyapplication.repository.UserRest;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping(path="/{userId}")
    public UserRest getUser(@PathVariable String userId){

        UserRest user = new UserRest();
        user.setUserName("dakiethkinson");
        user.setFirstName("Daniel");
        user.setLastName("Atkinson");
        user.setEmail("dakiethkinson@gmail.com");
        user.setUserId(UUID.randomUUID().toString());

        return user;
    }
    @GetMapping()
    public String getUsers(
            @RequestParam(value="page", defaultValue="1") int page,
            @RequestParam(value="limit", defaultValue="50") int limit,
            @RequestParam(value="sort", defaultValue = "desc", required = false) String sort){

        return "get Users was called with page " + page + " limit " + limit + " sort " + sort;
    }
    @PostMapping
    public String createUser(){
        return "post User was called";
    }

    @PutMapping
    public String updateUser(){
        return "update User was called";
    }

    @DeleteMapping
    public String deleteUser(){
        return "delete User was called";
    }
}
