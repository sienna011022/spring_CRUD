package com.example.projectCRUD.repository;


import com.example.projectCRUD.aop.Trace;
import com.example.projectCRUD.domain.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Long> {
//curd 안에 <관리대상 entity,대표값>

  @Override
  @Trace
  ArrayList<Article> findAll();



}
