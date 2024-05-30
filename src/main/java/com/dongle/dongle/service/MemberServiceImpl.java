package com.dongle.dongle.service;


import com.dongle.dongle.dto.MemberDto;
import com.dongle.dongle.entitiy.MemberEntity;
import com.dongle.dongle.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void join(MemberDto memberDto) {
        memberDto.setPassword(bCryptPasswordEncoder.encode(memberDto.getPassword()));
        memberDto.setRole("ROLE_USER");

        MemberEntity member = memberRepository.save(MemberEntity.toMemberEntity(memberDto));
        if (member == null) {
            System.out.println("실패");
        }

    }

    @Override
    public boolean existByNickname(String nickName) {
        boolean isExist = memberRepository.existsBynickname(nickName);

        return false;
    }

    @Override
    public boolean existByEmail(String email) {

        boolean isExist = memberRepository.existsByemail(email);

        return isExist;
    }
}
