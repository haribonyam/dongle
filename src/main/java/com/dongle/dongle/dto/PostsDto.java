package com.dongle.dongle.dto;

import com.dongle.dongle.entitiy.FileEntity;
import com.dongle.dongle.entitiy.MemberEntity;
import com.dongle.dongle.entitiy.PostsEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
public class PostsDto {

    private Long id;

    private String nickname;

    private String title;

    private String content;

    private List<MultipartFile> files;

    private int commentsCount;

    private int views;

    private String town;

    private String category;

    private LocalDate createdDate;

    private LocalDate updatedDate;

    private MemberEntity memberEntity;

    private List<String> filePaths;

    public static PostsDto postSummary(Long postId,String nickname,String title, int views,List<String> filePaths,
                                       String town,int commentsCount)
    {

        PostsDto postsDto =new PostsDto();

        postsDto.setId(postId);
        postsDto.setTown(town);
        postsDto.setTitle(title);
        postsDto.setCommentsCount(commentsCount);
        postsDto.setViews(views);
        postsDto.setFilePaths(filePaths);
        postsDto.setNickname(nickname);

        return  postsDto;
    }

    public static PostsDto toPostsDto(PostsEntity postsEntity) {
        PostsDto postsDto = new PostsDto();
         postsDto.setNickname(postsEntity.getAuthor().getNickname());
         postsDto.setTitle(postsEntity.getTitle());
         postsDto.setId(postsEntity.getId());
         postsDto.setViews(postsEntity.getViews());
         postsDto.setCommentsCount(postsEntity.getCommentsCount());
         postsDto.setContent(postsEntity.getContent());
         postsDto.setCategory(postsEntity.getCategory());
         postsDto.setUpdatedDate(postsEntity.getUpdatedDate());
         postsDto.setCreatedDate(postsEntity.getCreatedDate());
         postsDto.setFilePaths(postsEntity.getFiles()
                 .stream()
                 .map(FileEntity::getPath)
                 .collect(Collectors.toList())
         );

        return postsDto;

    }
}
