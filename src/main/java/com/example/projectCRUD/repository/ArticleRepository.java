package com.example.projectCRUD.repository;


import com.example.projectCRUD.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article,Long> {
//curd 안에 <관리대상 entity,대표값>

    @Override
    ArrayList<Article> findAll();
}
