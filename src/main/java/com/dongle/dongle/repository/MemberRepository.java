package com.dongle.dongle.repository;

import com.dongle.dongle.entitiy.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity,Long> {

    boolean existsByNickname(String nickname);

    MemberEntity findByEmail(String email);

    boolean existsByEmail(String email);

   MemberEntity findByNickname(String nickname);
}
