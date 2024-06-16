package com.dongle.dongle.entitiy;


import com.dongle.dongle.dto.CommentDto;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.BatchSize;
import org.hibernate.metamodel.mapping.AttributeMappingsList;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="comment")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private PostsEntity post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private MemberEntity member;

    @Column(nullable = false)
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private CommentEntity parent;

    @OneToMany(mappedBy = "parent",orphanRemoval = true)
    @BatchSize(size = 100)
    private List<CommentEntity> children = new ArrayList<>();

    @PrePersist
    protected void createDate(){
      date = LocalDateTime.now();
    }

   public static CommentEntity toCommentEntity(CommentDto commentDto,MemberEntity member,PostsEntity post){

       CommentEntity commentEntity = new CommentEntity();
       commentEntity.setContent(commentDto.getContent());
       commentEntity.setMember(member);
       commentEntity.setPost(post);
       return commentEntity;
   }
}
