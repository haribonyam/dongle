package com.dongle.dongle.apiController;


import com.dongle.dongle.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberRestController {
    //수정 필요

    private final MemberService memberService;

    //닉네임 중복체크
    @GetMapping("/{nickName}")
    public ResponseEntity<String> nickNameCheck(@PathVariable String nickName) {

        boolean isExist = memberService.existByNickname(nickName);

        if (isExist) {
            return new ResponseEntity<String>("Nickname is already taken", HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>("Nickname is available", HttpStatus.OK);
        }
    }

    //이메일 중복체크
    @GetMapping("/{email}")
    public ResponseEntity<String> emailCheck(@PathVariable String email){
        boolean isExist = memberService.existByEmail(email);

        if (isExist) {
            return new ResponseEntity<String>("Email is already taken", HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>("Email is available", HttpStatus.OK);
        }
    }

}
