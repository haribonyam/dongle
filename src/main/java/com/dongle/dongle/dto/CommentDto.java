package com.dongle.dongle.dto;

import com.dongle.dongle.entitiy.CommentEntity;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
public class CommentDto {
    private Long id;
    private Long postId;
    private String memberNickname;
    private String content;
    private Long parentId;
    private List<CommentDto> children;
    private String date;

    public static CommentDto toCommentDto(CommentEntity commentEntity) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(commentEntity.getId());
        commentDto.setPostId(commentEntity.getPost().getId());
        commentDto.setMemberNickname(commentEntity.getMember().getNickname());
        commentDto.setContent(commentEntity.getContent());
        commentDto.setDate(commentEntity.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        if(commentEntity.getParent()!=null){
            commentDto.setParentId(commentEntity.getParent().getId());
        }

        // 자식 댓글 Entity -> Dto
        if (commentEntity.getChildren() != null) {
            List<CommentDto> childDtoList = commentEntity.getChildren().stream()
                    .map(CommentDto::toCommentDto)
                    .collect(Collectors.toList());
            commentDto.setChildren(childDtoList);
        }

        return commentDto;
    }
}
