package com.dongle.dongle.repository;

import com.dongle.dongle.entitiy.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity,Long> {




    List<CommentEntity> findAllByPostId(int i);

    @Query("SELECT c FROM CommentEntity c WHERE c.post.id = :postId AND c.parent IS NULL ORDER BY c.id ASC")
    List<CommentEntity> findAllByPostIdAndParentIsNullOrderByAsc(@Param("postId") Long postId);
}
