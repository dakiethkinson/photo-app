package com.atkinson.users.controller;

import com.atkinson.users.model.UserRest;
import com.atkinson.users.repository.UserDTO;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {


    // Discovery Service Endpoint

    @GetMapping("/status")
    public String status(){

        return "Service Up";
    }

    // This will need to be replaced with a repository lookup
    Map<String, UserRest> users;

    @GetMapping(path="/{userId}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserRest> getUser(@PathVariable String userId){

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
    public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDTO userDTO){

        UserRest user = new UserRest();
        user.setUserName(userDTO.getUserName());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setUserId(UUID.randomUUID().toString());
        user.setPassword(userDTO.getPassword());

        if(users == null) users = new HashMap<>();
        users.put(user.getUserId(), user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping(path="/{userId}",consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserRest> updateUser(@PathVariable String userId, @Valid @RequestBody UserDTO userDTO){
        UserRest updateUser = users.get(userId);

        if (!userDTO.getFirstName().isBlank()){
            updateUser.setFirstName(userDTO.getFirstName());
        }
        if (!userDTO.getLastName().isBlank()){
            updateUser.setLastName(userDTO.getLastName());
        }
        if (!userDTO.getUserName().isBlank()){
            updateUser.setUserName(userDTO.getUserName());
        }
        if (!userDTO.getEmail().isBlank()){
            updateUser.setEmail(userDTO.getEmail());
        }
        if (!userDTO.getPassword().isBlank()){
            updateUser.setPassword(userDTO.getPassword());
        }

        users.put(updateUser.getUserId(), updateUser);
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
