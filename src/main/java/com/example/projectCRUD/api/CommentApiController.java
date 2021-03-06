package com.example.projectCRUD.api;

import com.example.projectCRUD.dto.CommentDto;
import com.example.projectCRUD.entity.Comment;
import com.example.projectCRUD.service.ArticleService;
import com.example.projectCRUD.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

@RequiredArgsConstructor
public class CommentApiController {

    private final CommentService commentService;

    private final ArticleService articleService;



    //댓글 목록 조회

    @GetMapping("api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId){
        //서비스에게 위임
        List<CommentDto> dtos = commentService.comments(articleId);



        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    //댓글 생성

    //댓글 수정

    //댓글 삭제
}
