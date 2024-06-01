package com.dongle.dongle.controller;

import com.dongle.dongle.dto.response.ResponseDto;
import com.dongle.dongle.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class RestMemberController {

    private final MemberService memberService;
    @GetMapping("/email/{email}")
    public ResponseEntity<ResponseDto> emailCheck(@PathVariable String email){
        if(memberService.existByEmail(email)){
            return ResponseDto.duplicatedId();
        }
        return ResponseDto.success();
    }

    @GetMapping("/nickname/{nickname}")
    public ResponseEntity<ResponseDto> nicknameCheck(@PathVariable String nickname){
        if(memberService.existByNickname(nickname)){
            return ResponseDto.duplicatedId();
        }
        return ResponseDto.success();
    }
}
