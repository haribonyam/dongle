package com.dongle.dongle.service;

import com.dongle.dongle.dto.MemberDto;
import com.dongle.dongle.dto.ModifyMemberDto;
import com.dongle.dongle.entitiy.MemberEntity;

public interface MemberService {
    void join(MemberDto memberDto);

    boolean existByNickname(String nickname);

    boolean existByEmail(String email);

    String getUserNickName();

    MemberDto findByNickname(String nickname);

    void updateByNickname(ModifyMemberDto modifyMemberDto);

}
