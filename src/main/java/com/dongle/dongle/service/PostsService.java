package com.dongle.dongle.service;

import com.dongle.dongle.dto.PostsDto;
import com.dongle.dongle.entitiy.PostsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostsService {


    void savePost(PostsDto postsDto);

    Page<PostsEntity> getAllPosts(Pageable pageable
    );
}
