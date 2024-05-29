package com.dongle.dongle.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class memberDto {

    private Long id;

    private String email;

    private String password;

    private String nickname;

    private String role;

    private String town;
}
