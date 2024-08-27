package com.codewithmeet.elearningplatform.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class PageController {

   @RequestMapping("/login-page")
    public String login() {
        return "login";
    }


    @RequestMapping("/success")
    public String success() {
       return "success";
    }
}
