package com.dongle.dongle.dto;

import com.dongle.dongle.entitiy.MemberEntity;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Member;

@Data
public class MemberDto {


    private Long id;

    private String email;

    private String password;

    private String nickname;

    private String role;

    private String town;

}
