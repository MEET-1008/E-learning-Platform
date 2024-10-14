package com.codewithmeet.elearningplatform.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class testController {

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String test() {
        return "testing Post method :- ADMIN";
    }

    @PutMapping
    @PreAuthorize("hasRole('TEACHER')")
    public String test2() {
        return "testing Put method :- TEACHER";
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public  String test3() {
        return "testing Get method :- USER";
    }




}
