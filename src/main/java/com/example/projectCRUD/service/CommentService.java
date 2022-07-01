package com.example.projectCRUD.service;

import com.example.projectCRUD.dto.CommentDto;
import com.example.projectCRUD.entity.Comment;
import com.example.projectCRUD.repository.ArticleRepository;
import com.example.projectCRUD.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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

    }

