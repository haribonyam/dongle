package com.dongle.dongle.repository;

import com.dongle.dongle.entitiy.MemberEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity,Long> {

    boolean existsByNickname(String nickname);

    MemberEntity findByEmail(String email);

    boolean existsByEmail(String email);

   MemberEntity findByNickname(String nickname);
    @Modifying
    @Transactional
    @Query("update MemberEntity m set m.nickname = :nickname , m.password = :password , m.profilePath = :path where m.id = :id")
    void updateByNickname(Long id,String nickname,String password,String path);
}
