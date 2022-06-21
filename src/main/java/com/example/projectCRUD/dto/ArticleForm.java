package com.example.projectCRUD.dto;

import com.example.projectCRUD.entity.Article;

public class ArticleForm {

    private String title;


    private String content;

    public ArticleForm(String title, String content) {
        this.title = title;
        this.content = content;
    }
    @Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }


    public Article toEntity() {
        //entity 객체 반환
        return new Article(null,title,content);
    }
}
