package com.dongle.dongle.repository;

import com.dongle.dongle.entitiy.memberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface memberRepository extends JpaRepository<memberEntity,Long> {


}
