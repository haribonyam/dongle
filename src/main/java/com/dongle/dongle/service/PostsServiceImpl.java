package com.dongle.dongle.service;


import com.dongle.dongle.dto.PostsDto;
import com.dongle.dongle.entitiy.FileEntity;
import com.dongle.dongle.entitiy.MemberEntity;
import com.dongle.dongle.entitiy.PostsEntity;
import com.dongle.dongle.repository.MemberRepository;
import com.dongle.dongle.repository.PostsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostsServiceImpl implements  PostsService{

    private final PostsRepository postsRepository;
    private final MemberRepository memberRepository;
    private final FileService fileService;

    @Value("${upload.path}")
    private String uploadPath;
    @Override
    @Transactional
    public void savePost(PostsDto postsDto) {

        try {
            //member
            MemberEntity memberEntity = memberRepository.findById(postsDto.getMemberId()).get();
            PostsEntity postsEntity = PostsEntity.toPostsEntity(postsDto, memberEntity);
            postsRepository.save(postsEntity);

            //files 전처리
            for (MultipartFile file : postsDto.getFiles()) {
                String fileUrl = fileService.fileSave(file);
                fileService.save(FileEntity.builder()
                                .Posts(postsEntity)
                                .path(fileUrl)
                        .build());
            }

        }catch(Exception e){
            System.out.println("error : "+e);
        }

    }

    @Override
    @Transactional
    public Page<PostsDto> getAllPosts(Pageable pageable) {
        Page<PostsEntity> postsEntities = postsRepository.findAllPosts(pageable);

        return postsEntities.map(postEntity -> {
            List<String> filePaths = postEntity.getFiles().stream()
                    .map(fileEntity -> fileEntity.getPath())
                    .collect(Collectors.toList());

            return PostsDto.postSummary(
                    postEntity.getId(),
                    postEntity.getMember().getId(),
                    postEntity.getTitle(),
                    postEntity.getViews(),
                    filePaths,
                    postEntity.getTown(),
                    postEntity.getCommentsCount()
            );
        });
    }

    /*
    * n+1 문제 발생함
    * */
    @Override
    public void deletePostById(Long id) {
        postsRepository.deleteById(id);
    }

    @Override
    @Transactional
    public PostsDto getPostById(Long id) {
       updateViews(id);
       Optional<PostsEntity> getPost = postsRepository.findById(id);
       if(getPost.isPresent()){
           PostsDto postsDto = PostsDto.toPostsDto(getPost.get());
           return postsDto;
       }
    return null;
    }

    @Override
    public int updateViews(Long id) {

        return postsRepository.updateViews(id);
    }

    @Override
    public void updatePost(Long id,PostsDto postsDto) {
        postsDto.setUpdatedDate(postsDto.getUpdatedDate().now());
        postsRepository.updatePost(id,postsDto.getTitle(),postsDto.getContent(),postsDto.getUpdatedDate());
    }

    @Override
    public List<PostsDto> findByMemberNickname(String nickname) {
        Optional<List<PostsEntity>> postsEntities = postsRepository.findByMemberNicknameOrderByIdDesc(nickname);
        if(postsEntities.isPresent()){
          List<PostsDto>  postsDtos = new ArrayList<>();
          for(PostsEntity postsEntity : postsEntities.get()){
                postsDtos.add(PostsDto.toPostsDto(postsEntity));
          }
            return postsDtos;
        }
        return null;
    }

}
