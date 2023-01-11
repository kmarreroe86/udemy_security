package com.eazybytes.springsecuritybasic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {


    @GetMapping(value = "/welcome")
    public String sayWelcome() {
        return "Welcome to Spring Boot Application with Security.";
    }
}
