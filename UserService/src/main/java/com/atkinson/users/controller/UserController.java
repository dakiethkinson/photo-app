package com.atkinson.users.controller;

import com.atkinson.users.model.CreateResponse;
import com.atkinson.users.model.Login;
import com.atkinson.users.model.User;
import com.atkinson.users.repository.UserDTO;
import com.atkinson.users.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

import static org.modelmapper.convention.MatchingStrategies.STRICT;

@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

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

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody @Valid Login loginRequest){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(STRICT);

        UserDTO userDTO = modelMapper.map(loginRequest, UserDTO.class);
        userService.loadUserByUsername(userDTO.getUserName());
        return null;
    }
    @PostMapping(path = "/signup", consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CreateResponse> createUser(@Valid @RequestBody User newUserRequest){

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(STRICT);

        UserDTO userDTO = modelMapper.map(newUserRequest, UserDTO.class);
        userService.createUser(userDTO);

        return new ResponseEntity<>(modelMapper.map(userDTO, CreateResponse.class), HttpStatus.CREATED);

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
