package com.dongle.dongle.controller;


import com.dongle.dongle.dto.PostsDto;
import com.dongle.dongle.service.MemberService;
import com.dongle.dongle.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostsController {

    private final PostsService postsService;
    private final MemberService memberService;

    @GetMapping("/write")
    public String contentWrite(Model model){
        String nickname = memberService.getUserNickName();
        model.addAttribute("nickname",nickname);
        return "postWrite";
    }
    @PostMapping("/save")
    public String contentSave(PostsDto postsDto){
        postsService.savePost(postsDto);

        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String contentView(@PathVariable Long id, Model model
    ){
        PostsDto postsDto = postsService.getPostById(id);
        model.addAttribute("postView",postsDto);
        return "postView";
    }

    @PostMapping("/delete/{id}")
    public String contentDelete(@PathVariable Long id){
         postsService.deleteContentById(id);
        return"redirect:/";
    }


}