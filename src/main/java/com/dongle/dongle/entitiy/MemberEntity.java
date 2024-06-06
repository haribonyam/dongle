package com.dongle.dongle.entitiy;

import com.dongle.dongle.dto.MemberDto;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="member")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="email", unique = true)
    private String email;
    @Column(name="password")
    private String password;
    @Column(name="nickname", unique = true)
    private String nickname;
    @Column(name="role")
    private String role;
    @Column(name="town")
    private String town;



    public static MemberEntity toMemberEntity(MemberDto memberDto){

        MemberEntity memberEntity = new MemberEntity();

        memberEntity.setEmail(memberDto.getEmail());
        memberEntity.setNickname(memberDto.getNickname());
        memberEntity.setPassword(memberDto.getPassword());
        memberEntity.setRole(memberDto.getRole());

        return memberEntity;
    }
}



