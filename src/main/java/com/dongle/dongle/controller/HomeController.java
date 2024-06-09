package com.dongle.dongle.controller;

import com.dongle.dongle.dto.MemberDto;
import com.dongle.dongle.service.MemberService;
import com.dongle.dongle.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.lang.reflect.Member;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;
    private final PostsService postsService;

    @GetMapping("/")
    public String mainPage(Model model){

        String nickname= memberService.getUserNickName(); //spring security USER 정보 받아옴

        model.addAttribute("nickname",nickname);

        return "main";
    }
    @GetMapping("/join")
    public String joinPage(){

        return "joinForm";
    }
    @GetMapping("/login")
    public String loginPage(){

        return "loginForm";
    }

    @PostMapping("/joinProc")
    public String joinProcess(MemberDto memberDto){
        memberService.join(memberDto);

        return "redirect:/";
    }

    @GetMapping("/error")
    public String errorPage(){
        return "error";
    }


    @GetMapping("/delete")
    public String testPage(){return "delete";}

    @GetMapping("/userinfo/{nickname}")
    public String userInfo(@PathVariable String nickname,
                           Model model){
        System.out.println(nickname);
       model.addAttribute("member",memberService.findByNickname(nickname));
       model.addAttribute("posts",postsService.findByMemberNickname(nickname));
       return "userinfo";
    }
}
