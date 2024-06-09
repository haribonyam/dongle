package com.dongle.dongle.entitiy;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@Table(name="file")
@RequiredArgsConstructor
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="file_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id", nullable = false)
    private PostsEntity Posts;

    @Column(name="file_path")
    private String path;

    public FileEntity(Long id, PostsEntity postsEntity, String fileUrl) {
        this.id = id;
        this.Posts = postsEntity;
        this.path = fileUrl;
    }
}
