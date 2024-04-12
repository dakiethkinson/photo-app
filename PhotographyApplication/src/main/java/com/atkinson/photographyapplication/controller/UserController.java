package com.atkinson.photographyapplication.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public String getUser(){
        return "get User was called.";
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
