package com.dongle.dongle.service;

import com.dongle.dongle.dto.MemberDto;

public interface MemberService {
    void join(MemberDto memberDto);

    boolean existByNickname(String nickName);

    boolean existByEmail(String email);
}
