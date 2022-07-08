package com.example.projectCRUD.entity;

import com.example.projectCRUD.dto.CommentDto;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;
    @Column
    private String nickname;
    @Column
    private String body;

    public static Comment createComment(CommentDto dto, Article article) {
        //예외 처리
        if (dto.getId()!=null)
        throw new IllegalArgumentException("댓글 생성 실패 댓글의 id가 있어야합니다");
        //엔티티 생성 및 반환
        if(dto.getArticleId()!=null)
            throw new IllegalArgumentException("댓글 생성 실패 게시글의 id가 잘못되었습니다");

        return new Comment(
                dto.getId(),
                article,
                dto.getNickname(),
                dto.getBody()
        );
    }
}

