package com.dongle.dongle.repository;

import com.dongle.dongle.entitiy.PostsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface PostsRepository extends JpaRepository<PostsEntity, Long> {


    @Query("SELECT p FROM PostsEntity p LEFT JOIN FETCH p.files LEFT JOIN FETCH p.member ORDER BY p.id DESC")
    Page<PostsEntity> findAllPosts(Pageable pageable);

    @Modifying
    @Query("update PostsEntity p set p.views = p.views + 1 where p.id = :id")
    int updateViews(@Param("id") Long id);

    @Modifying
    @Query("update PostsEntity p set p.title = :title, p.content = :content, p.updatedDate = :updatedDate where p.id = :id")
    void updatePost(@Param("id") Long id, @Param("title") String title, @Param("content") String content, @Param("updatedDate") LocalDate updatedDate);

    Optional<List<PostsEntity>> findByMemberNicknameOrderByIdDesc(String nickname);
}
