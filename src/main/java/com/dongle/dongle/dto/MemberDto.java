package com.dongle.dongle.dto;

import com.dongle.dongle.entitiy.MemberEntity;
import com.dongle.dongle.entitiy.PostsEntity;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Member;

@Data
@Builder
public class MemberDto {


    private Long id;

    private String email;

    private String password;

    private String nickname;

    private String role;

    private MultipartFile profile;

    private String town;

    private String path;


}
