package com.dongle.dongle.repository;

import com.dongle.dongle.entitiy.PostsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsRepository extends JpaRepository<PostsEntity, Long> {
}
