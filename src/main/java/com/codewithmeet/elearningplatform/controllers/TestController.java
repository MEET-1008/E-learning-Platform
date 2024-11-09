package com.codewithmeet.elearningplatform.controllers;

import org.springframework.context.ApplicationContext;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController

public class TestController {

    private ApplicationContext context;

    public TestController(ApplicationContext context) {
        this.context = context;
    }

    @PostMapping("/test")
    @PreAuthorize("hasRole('ADMIN')")
    public  String testing(){
        return "Testing";
    }

    @GetMapping("/test")
    @PreAuthorize("hasRole('USER')")
    public  String testing1(){
        return "Testing1";
    }

    @GetMapping("/all")
    public  String all(){
        System.out.println(context.getDisplayName());
        return "open api endpoint" ;
    }

}