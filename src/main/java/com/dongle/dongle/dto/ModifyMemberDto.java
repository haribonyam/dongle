package com.dongle.dongle.dto;

import lombok.Data;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Data
public class ModifyMemberDto {

    private Long id;
    private String mnickname;
    private String mpassword;
    private String nickname;
    private MultipartFile file;
}
