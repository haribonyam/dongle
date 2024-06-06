package com.dongle.dongle.entitiy;


import com.dongle.dongle.dto.PostsDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@Table(name="posts")
public class PostsEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_nickname", referencedColumnName = "nickname", nullable = false)
    private MemberEntity author;

    @Column(name="title", nullable = false, length = 255)
    private String title;

    @Column(name="content")
    private String content;

    @Column(name="views", nullable = false)
    private int views = 0;

    @Column(name = "comments_count", nullable = false)
    private int commentsCount;

    @Column(name="town", length = 100)
    private String town;

    @Column(name="category", length = 100)
    private String category;

    @Column(name = "created_date", nullable = false)
    private LocalDate createdDate;

    @Column(name = "updated_date", nullable = false)
    private LocalDate updatedDate;

    @OneToMany(mappedBy = "authPost", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FileEntity> files;

    @PrePersist
    protected void onCreate() {
        createdDate = LocalDate.now();
        updatedDate = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDate = LocalDate.now();
    }

    public static PostsEntity toPostsEntity(PostsDto postsDto, MemberEntity memberEntity) {
        PostsEntity postsEntity = new PostsEntity();

        postsEntity.setId(postsDto.getId());
        postsEntity.setCategory(postsDto.getCategory());
        postsEntity.setAuthor(memberEntity);
        postsEntity.setContent(postsDto.getContent());
        postsEntity.setTitle(postsDto.getTitle());
        postsEntity.setTown(postsDto.getTown());
        postsEntity.setViews(postsDto.getCommentsCount());
        return postsEntity;
    }

}




