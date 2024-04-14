package com.atkinson.photographyapplication.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping(path="/{userId}")
    public String getUser(@PathVariable String userId){
        return "get User was called with userId = " + userId;
    }
    @GetMapping()
    public String getUsers(@RequestParam(value="page") int page, @RequestParam(value="limit") int limit){
        return "get Users was called with page " + page + " limit " + limit ;
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
