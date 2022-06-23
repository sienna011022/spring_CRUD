package com.example.projectCRUD.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@AllArgsConstructor
@NoArgsConstructor // default 생성자 추가
@ToString
@Getter
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


    }
