package com.dongle.dongle.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/content")
public class ContentsController {

    @PostMapping("/save")
    public String contentsSave(){

        return null;
    }

}
