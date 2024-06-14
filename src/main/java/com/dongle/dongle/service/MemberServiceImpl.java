package com.dongle.dongle.service;


import com.dongle.dongle.dto.MemberDto;
import com.dongle.dongle.dto.ModifyMemberDto;
import com.dongle.dongle.entitiy.MemberEntity;
import com.dongle.dongle.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.lang.reflect.Member;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public void join(MemberDto memberDto) {
        memberDto.setPassword(bCryptPasswordEncoder.encode(memberDto.getPassword()));
        memberDto.setRole("ROLE_USER");
        memberDto.setPath("/icon/user.png");
        MemberEntity member = memberRepository.save(MemberEntity.toMemberEntity(memberDto));
        if (member == null) {
            System.out.println("실패");
        }

    }

    @Override
    public boolean existByNickname(String nickName) {
        boolean isExist = memberRepository.existsByNickname(nickName);

        return isExist;
    }

    @Override
    public boolean existByEmail(String email) {

        boolean isExist = memberRepository.existsByEmail(email);

        return isExist;
    }

    @Override
    public String getUserNickName() {

        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @Override
    public MemberDto findByNickname(String nickname) {

        try {
            MemberEntity memberEntity = memberRepository.findByNickname(nickname);
          MemberDto memberDto =
                    MemberDto.builder().id(memberEntity.getId())
                            .nickname(memberEntity.getNickname())
                            .town(memberEntity.getTown())
                            .email(memberEntity.getEmail())
                            .path(memberEntity.getProfilePath())
                            .build();
            return  memberDto;
        }catch(Exception e){
            System.out.println(e);
        }
        return  null;
    }

    @Override
    public void updateByNickname(ModifyMemberDto modifyMemberDto) {
        MultipartFile file = modifyMemberDto.getFile();
        try {
            String fileName = UUID.randomUUID() + file.getOriginalFilename();
            String savePath = System.getProperty("user.dir") + uploadPath + "/";
            File dest = new File(savePath, fileName);
            file.transferTo(dest);
            memberRepository.updateByNickname(modifyMemberDto.getId(),modifyMemberDto.getMnickname(),bCryptPasswordEncoder.encode(modifyMemberDto.getMpassword()),"/img/"+fileName);
        }catch(Exception e){
            System.out.println(e);
        }

    }
/*
    @Override
    public String findNicknameByEmail(String email) {
        MemberEntity memberEntity = memberRepository.findByEmail(email);
        if(memberEntity !=null){
            return memberEntity.getNickname();
        }
        return null;
    }

 */
}
