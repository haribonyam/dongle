package com.dongle.dongle.service;


import com.dongle.dongle.dto.PostsDto;
import com.dongle.dongle.entitiy.FileEntity;
import com.dongle.dongle.entitiy.MemberEntity;
import com.dongle.dongle.entitiy.PostsEntity;
import com.dongle.dongle.repository.MemberRepository;
import com.dongle.dongle.repository.PostsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostsServiceImpl implements  PostsService{

    private final PostsRepository postsRepository;
    private final MemberRepository memberRepository;
    private final FileService fileService;

    @Override
    @Transactional
    public void savePost(PostsDto postsDto) {
        try {
            //member Entity찾기
            MemberEntity memberEntity = memberRepository.findByNickname(postsDto.getNickname());
            postsDto.setMemberEntity(memberEntity);
            PostsEntity postsEntity = PostsEntity.toPostsEntity(postsDto, memberEntity);
            postsRepository.save(postsEntity);
            //files 전처리
            List<String> fileUrls = new ArrayList<>();
            for (MultipartFile file : postsDto.getFiles()) {
                String fileUrl = fileService.fileSave(file);

                fileService.save(new FileEntity(null,postsEntity,fileUrl));
                fileUrls.add(fileUrl);
            }

        }catch(Exception e){
            System.out.println(e);
        }

    }

    @Override
    @Transactional
    public Page<PostsEntity> getAllPosts(Pageable pageable) {

        return postsRepository.findAll(pageable);
    }

    private PostsDto convertToDto(PostsEntity entity) {

    }
}
