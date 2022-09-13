package com.example.projectCRUD.service;

import com.example.projectCRUD.domain.dto.CommentDto;
import com.example.projectCRUD.domain.entity.Article;
import com.example.projectCRUD.domain.entity.Comment;
import com.example.projectCRUD.repository.ArticleRepository;
import com.example.projectCRUD.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentService implements CommentServiceImpl {

    private final CommentRepository commentRepository;

    private final ArticleRepository articleRepository;

    @Override
    public List<CommentDto> comments(Long articleId) {


        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());
    }
    @Override
    @Transactional
    public CommentDto create(Long articleId, CommentDto dto) {

        //게시글 조회 및 예외 처리
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패 ,대상 게시글이 없습니다"));
        //댓글 엔티티 생성
        Comment comment = Comment.createComment(dto, article);

        //댓글 엔티티를 DB로 저장

        Comment created = commentRepository.save(comment);

        //DTO로 변경하여 반환


        return CommentDto.createCommentDto(created);

    }
    @Override
    @Transactional
    public CommentDto update(Long id, CommentDto dto) {
        // 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패! 대상 댓글이 없습니다."));
        // 댓글 수정
        target.patch(dto);
        // DB로 갱신
        Comment updated = commentRepository.save(target);
        // 댓글 엔티티를 DTO로 변환 및 반환
        return CommentDto.createCommentDto(updated);
    }
    @Override
    @Transactional
    public CommentDto delete(Long id) {
        // 댓글 조회(및 예외 발생)
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패! 대상이 없습니다."));
        // 댓글 삭제
        commentRepository.delete(target);
        // 삭제 댓글을 DTO로 반환
        return CommentDto.createCommentDto(target);
    }
}
