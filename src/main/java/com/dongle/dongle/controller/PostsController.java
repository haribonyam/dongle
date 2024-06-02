package com.dongle.dongle.controller;


import com.dongle.dongle.dto.PostsDto;
import com.dongle.dongle.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostsController {

    private final PostsService postsService;

    @PostMapping("/save")
    public String contentsSave(PostsDto postsDto){
        postsService.savePost(postsDto);

        return "redirect:/";
    }

}