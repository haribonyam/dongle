package com.dongle.dongle.service;

import com.dongle.dongle.dto.CustomUserDetails;
import com.dongle.dongle.entitiy.MemberEntity;
import com.dongle.dongle.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        MemberEntity memberEntity = memberRepository.findByEmail(email);

        if(memberEntity != null){
            return new CustomUserDetails(memberEntity);

        }

        return null;
    }
}
