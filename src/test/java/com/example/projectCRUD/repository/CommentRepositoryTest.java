package com.example.projectCRUD.repository;

import com.example.projectCRUD.ProjectCrudApplication;
import com.example.projectCRUD.entity.Article;
import com.example.projectCRUD.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = ProjectCrudApplication.class)
@DataJpaTest //JPA 테스트
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId() {

        // 준비
        Long articleId = 4L;
        // 수행
        List<Comment> comments = commentRepository.findByArticleId(articleId);
        // 예상
        Article article = new Article(4L, "당신의 인생 영화는?", "댓글 ㄱ");
        Comment a = new Comment(1L, article, "Park", "굳 윌 헌팅");
        Comment b = new Comment(2L, article, "Kim", "아이 엠 샘");
        Comment c = new Comment(3L, article, "Choi", "쇼생크의 탈출");
        List<Comment> expected = Arrays.asList(a, b, c);
        // 검증
        assertEquals(expected.toString(), comments.toString(), "4번 글의 모든 댓글을 출력!");
    }

    @Test
    void findByNickName() {
    }
}