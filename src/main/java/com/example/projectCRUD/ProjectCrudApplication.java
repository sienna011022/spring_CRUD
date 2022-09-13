package com.example.projectCRUD;

import com.example.projectCRUD.domain.entity.Article;
import com.example.projectCRUD.repository.ArticleRepository;
import com.example.projectCRUD.trace.LogTrace;
import com.example.projectCRUD.trace.ThreadLocalLogTrace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class ProjectCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectCrudApplication.class, args);
	}

	@Bean
	public LogTrace logTrace(){
		return new ThreadLocalLogTrace();
	}




}
