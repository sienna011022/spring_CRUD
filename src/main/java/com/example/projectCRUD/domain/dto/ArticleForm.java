package com.example.projectCRUD.dto;

import com.example.projectCRUD.entity.Article;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@NoArgsConstructor
@Setter
public class ArticleForm {

    private Long id;

    private String title;


    private String content;

//    public ArticleForm(String title, String content) {
//        this.title = title;
//        this.content = content;
//    }
//    @Override
//    public String toString() {
//        return "ArticleForm{" +
//                "title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                '}';
//    }


    public Article toEntity() {
        //entity 객체 반환
        return new Article(id,title,content);
    }
}
