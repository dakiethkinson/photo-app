package com.atkinson.accountmanagementservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/accounts")
public class AccountController {

    // Discovery Service Endpoint
    @GetMapping("/status")
    public String status(){
        return "Service Up";
    }

}
