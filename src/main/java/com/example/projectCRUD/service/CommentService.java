package com.example.projectCRUD.service;

import com.example.projectCRUD.dto.CommentDto;
import com.example.projectCRUD.entity.Article;
import com.example.projectCRUD.entity.Comment;
import com.example.projectCRUD.repository.ArticleRepository;
import com.example.projectCRUD.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final ArticleRepository articleRepository;


    public List<CommentDto> comments(Long articleId) {


        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());
        }
    @Transactional
    public CommentDto create(Long articleId, CommentDto dto) {

        //게시글 조회 및 예외 처리
        Article article = articleRepository.findById(articleId)
                .orElseThrow(()->new IllegalArgumentException("댓글 생성 실패 ,대상 게시글이 없습니다"));
        //댓글 엔티티 생성
        Comment comment = Comment.createComment(dto,article);

        //댓글 엔티티를 DB로 저장

        Comment created = commentRepository.save(comment);

        //DTO로 변경하여 반환


        return CommentDto.createCommentDto(created);

    }
}

