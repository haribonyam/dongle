package com.dongle.dongle.service;

import com.dongle.dongle.dto.PostsDto;
import com.dongle.dongle.entitiy.PostsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface PostsService {
    /**
     savePost  : 게시물 저장
     getAllPosts : REST API 게시물 불러오기 (pagination  5게시물 / 10페이지 )
     delete
     * */

    void savePost(PostsDto postsDto);

    Page<PostsDto> getAllPosts(PageRequest pageRequest);

    void deleteContentById(Long id);


    PostsDto getPostById(Long id);

    int updateViews(Long id);
}
