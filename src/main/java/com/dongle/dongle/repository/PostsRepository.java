package com.dongle.dongle.repository;

import com.dongle.dongle.entitiy.PostsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsRepository extends JpaRepository<PostsEntity, Long> {


    @Query("SELECT p FROM PostsEntity p LEFT JOIN FETCH p.files LEFT JOIN FETCH p.author ORDER BY p.createdDate DESC")
    Page<PostsEntity> findAllPosts(Pageable pageable);

    @Modifying
    @Query("update PostsEntity p set p.views = p.views + 1 where p.id = :id")
    int updateViews(@Param("id") Long id);
}
