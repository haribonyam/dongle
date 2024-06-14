package com.dongle.dongle.entitiy;

import jakarta.persistence.*;
import lombok.*;

import java.lang.reflect.Member;

@Entity
@Data
@Table(name="file")
@Builder
@NoArgsConstructor
@AllArgsConstructor
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


}
