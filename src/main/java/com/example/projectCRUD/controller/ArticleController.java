package com.example.projectCRUD.controller;

import com.example.projectCRUD.dto.ArticleForm;
import com.example.projectCRUD.entity.Article;
import com.example.projectCRUD.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm(){

        return "articles/new";
    }
    //폼에서 던진 주소로
    //데이터를 받으려는 객체dto를 넣어서
    //파라미터로 dto를 받아옴 = ArticleForn
    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
        //1.DTO 를 Entity로 변환
        Article article = form.toEntity();
        System.out.println(article.toString());


        //2.Repository를 통해 DB에 저장
        Article saved = articleRepository.save(article);
        System.out.println(saved.toString());
        return "";
    }
}
