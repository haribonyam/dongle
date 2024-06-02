package com.dongle.dongle.dto;

import com.dongle.dongle.entitiy.MemberEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.List;

@Data
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

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private MemberEntity memberEntity;

    private String filePath;




}
