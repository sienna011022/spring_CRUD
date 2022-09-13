package com.example.projectCRUD.service;

import com.example.projectCRUD.domain.dto.CommentDto;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface CommentServiceImpl {

  List<CommentDto> comments(Long articleId);

  @Transactional
  CommentDto create(Long articleId, CommentDto dto);

  @Transactional
  CommentDto update(Long id, CommentDto dto);

  @Transactional
  CommentDto delete(Long id);
}
