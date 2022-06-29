package com.example.projectCRUD.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne//해당 엔티티 여러개가 하나의 aeticle에 연관됨.
    @JoinColumn(name="article_id") // 컬럼 이름
    private Article article;

    @Column
    private String nickname;

    @Column
    private String body;
}
