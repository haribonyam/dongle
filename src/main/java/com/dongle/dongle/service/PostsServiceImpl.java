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
            //member Entity찾기
            MemberEntity memberEntity = memberRepository.findByNickname(postsDto.getNickname());
            postsDto.setMemberEntity(memberEntity);
            PostsEntity postsEntity = PostsEntity.toPostsEntity(postsDto, memberEntity);
            /*
            List<MultipartFile> files = postsDto.getFiles();
            String thumbPath="";
            if(files !=null){
                try {

                    String fileName = UUID.randomUUID()+"thumb"+files.get(0).getOriginalFilename();
                    String filePath =System.getProperty("user.dir")+uploadPath +"/"+ fileName;
                    File dest = new File(filePath);
                    files.get(0).transferTo(dest);
                    thumbPath=filePath;

                } catch (IOException e) {
                    e.printStackTrace();

                }
            }


             */
            postsRepository.save(postsEntity);
            //files 전처리
            //List<String> fileUrls = new ArrayList<>();
            for (MultipartFile file : postsDto.getFiles()) {
                String fileUrl = fileService.fileSave(file);
                fileService.save(new FileEntity(null,postsEntity,fileUrl));
               // fileUrls.add(fileUrl);
            }


        }catch(Exception e){
            System.out.println(e);
        }

    }

    @Override
    @Transactional
    public Page<PostsDto> getAllPosts(PageRequest pageRequest) {
        Page<PostsEntity> postsEntities = postsRepository.findAllPosts(pageRequest);

        return postsEntities.map(postEntity -> {
            List<String> filePaths = postEntity.getFiles().stream()
                    .map(fileEntity -> fileEntity.getPath())
                    .collect(Collectors.toList());

            return PostsDto.postSummary(
                    postEntity.getId(),
                    postEntity.getAuthor().getNickname(),
                    postEntity.getTitle(),
                    postEntity.getViews(),
                    filePaths,
                    postEntity.getTown(),
                    postEntity.getCommentsCount()
            );
        });
    }

    @Override
    public void deleteContentById(Long id) {
            postsRepository.deleteById(id);

    }

    @Override
    public PostsDto getPostById(Long id) {
       Optional<PostsEntity> getPost = postsRepository.findById(id);
       if(getPost.isPresent()){
           PostsDto postsDto = PostsDto.toPostsDto(getPost.get());
           return postsDto;
       }
    return null;
    }

}
