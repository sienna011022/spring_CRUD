package com.example.projectCRUD.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity//DB가 해당 객체를 인식 가능
public class Article {
    @Id
    @GeneratedValue
    //대표값을 하나 지정해야함
    private Long id;
    @Column
    private String title;


    @Column
    private String content;


    public Article(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }


    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
