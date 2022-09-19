package com.example.projectCRUD;

import com.example.projectCRUD.config.AutoProxyConfig;
import com.example.projectCRUD.config.LogTraceAspect;
import com.example.projectCRUD.trace.LogTrace;
import com.example.projectCRUD.trace.ThreadLocalLogTrace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@SpringBootApplication
public class ProjectCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectCrudApplication.class, args);
	}

	@Bean
	public LogTrace logTrace(){
		return new ThreadLocalLogTrace();
	}


	@Bean
	public LogTraceAspect logTraceAspect(LogTrace logTrace){
		return new LogTraceAspect(logTrace);
	}



}
