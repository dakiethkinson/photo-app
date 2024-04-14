package com.atkinson.users.controller;

import com.atkinson.users.model.UserRest;
import com.atkinson.users.repository.UserDTO;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping(path="/{userId}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserRest> getUser(@PathVariable String userId){

        UserRest user = new UserRest();
        user.setUserName("dakiethkinson");
        user.setFirstName("Daniel");
        user.setLastName("Atkinson");
        user.setEmail("dakiethkinson@gmail.com");
        user.setUserId(UUID.randomUUID().toString());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @GetMapping()
    public String getUsers(
            @RequestParam(value="page", defaultValue="1") int page,
            @RequestParam(value="limit", defaultValue="50") int limit,
            @RequestParam(value="sort", defaultValue = "desc", required = false) String sort){

        return "get Users was called with page " + page + " limit " + limit + " sort " + sort;
    }
    @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDTO userDTO){
        UserRest user = new UserRest();
        user.setUserName(userDTO.getUserName());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setUserId(UUID.randomUUID().toString());
        user.setPassword(userDTO.getPassword());
        return new ResponseEntity<>(user, HttpStatus.OK);
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
