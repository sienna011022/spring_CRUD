package com.example.projectCRUD.dto;

import com.example.projectCRUD.entity.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Setter
public class CommentDto {

    private Long id;
    @JsonProperty("artice_id")
    private Long articleId;
    private String nickname;
    private String body;


    public static CommentDto createCommentDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getArticle().getId(),
                comment.getNickname(),
                comment.getBody()
        );

        //test
    }
}
