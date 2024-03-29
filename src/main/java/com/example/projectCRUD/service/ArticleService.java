package com.example.projectCRUD.service;

import com.example.projectCRUD.domain.dto.ArticleForm;
import com.example.projectCRUD.domain.entity.Article;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface ArticleService {

  List<Article> index();

  Article show(Long id);

  Article create(ArticleForm dto);

  Article update(Long id, ArticleForm dto);

  Article delete(Long id);
}
