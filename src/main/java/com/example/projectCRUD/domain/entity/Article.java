package com.example.projectCRUD.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import net.bytebuddy.implementation.bind.annotation.BindingPriority;


@NoArgsConstructor // default 생성자 추가
@ToString
@Getter

@Entity//DB가 해당 객체를 인식 가능
public class Article {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //대표값을 하나 지정해야함
    private Long id;
    @Column
    private String title;

    @Column
    private String content;

    public void patch(Article article){
        if(article.title != null)
            this.title = article.title;
        if(article.content != null)
            this.content = article.content;
    }


    @Builder
    public Article(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    }
