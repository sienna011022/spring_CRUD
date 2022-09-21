package com.example.projectCRUD;

import com.example.projectCRUD.aspect.AspectV1;
import com.example.projectCRUD.trace.LogTrace;
import com.example.projectCRUD.trace.ThreadLocalLogTrace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(AspectV1.class) // 주로 설정 파일을 추가할 떄 사용
public class ProjectCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectCrudApplication.class, args);
	}

	@Bean
	public LogTrace logTrace(){
		return new ThreadLocalLogTrace();
	}



}
