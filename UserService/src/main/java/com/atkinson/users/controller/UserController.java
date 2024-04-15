package com.atkinson.users.controller;

import com.atkinson.users.model.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    // Discovery Service Endpoint
    @GetMapping("/status")
    public String status(){

        return "Service Up";
    }

    // This will need to be replaced with a repository lookup
    Map<String, User> users;

    @GetMapping(path="/{userId}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<User> getUser(@PathVariable String userId){

        if(users.containsKey(userId)){
            return new ResponseEntity<>(users.get(userId), HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

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
    public ResponseEntity<User> createUser(@Valid @RequestBody User newUserRequest){

        User newUser = new User();
        newUser.setUserName(newUserRequest.getUserName());
        newUser.setFirstName(newUserRequest.getFirstName());
        newUser.setLastName(newUserRequest.getLastName());
        newUser.setEmail(newUserRequest.getEmail());
        newUser.setPassword(newUserRequest.getPassword());

        if(users == null) users = new HashMap<>();
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @PutMapping(path="/{userId}",consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<User> updateUser(@PathVariable String userId, @Valid @RequestBody User updateUserRequest){
        User updateUser = users.get(userId);

        if (!updateUserRequest.getFirstName().isBlank()){
            updateUser.setFirstName(updateUserRequest.getFirstName());
        }
        if (!updateUserRequest.getLastName().isBlank()){
            updateUser.setLastName(updateUserRequest.getLastName());
        }
        if (!updateUserRequest.getUserName().isBlank()){
            updateUser.setUserName(updateUserRequest.getUserName());
        }
        if (!updateUserRequest.getEmail().isBlank()){
            updateUser.setEmail(updateUserRequest.getEmail());
        }
        if (!updateUserRequest.getPassword().isBlank()){
            updateUser.setPassword(updateUserRequest.getPassword());
        }

        return new ResponseEntity<>(updateUser, HttpStatus.OK);

    }

    @DeleteMapping(path="/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId){
        if(users.containsKey(userId)){
            users.remove(userId);
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
