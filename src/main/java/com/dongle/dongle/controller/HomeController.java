package com.dongle.dongle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/join")
    public String joinPage(){

        return "joinForm";
    }
    @GetMapping("/login")
    public String loginPage(){

        return "loginForm";
    }
}
