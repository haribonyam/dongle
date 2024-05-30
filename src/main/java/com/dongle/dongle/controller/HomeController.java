package com.dongle.dongle.controller;

import com.dongle.dongle.dto.MemberDto;
import com.dongle.dongle.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;

    @GetMapping("/")
    public String mainPage(){

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

}
