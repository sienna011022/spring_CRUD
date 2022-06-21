package com.example.projectCRUD.controller;

import com.example.projectCRUD.dto.ArticleForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {

    @GetMapping("/articles/new")
    public String newArticleForm(){

        return "articles/new";
    }
    //폼에서 던진 주소로
    //데이터를 받으려는 객체dto를 넣어서
    //파라미터로 dto를 받아옴 = ArticleForn
    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
        System.out.println(form.toString());
        return "";
    }
}
