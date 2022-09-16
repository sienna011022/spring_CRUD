package com.example.projectCRUD.config;


import com.example.projectCRUD.trace.LogTrace;
import com.example.projectCRUD.trace.LogTraceAdvice;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutoProxyConfig {


  @Bean
  public Advisor advisor1(LogTrace logTrace){
    NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
    pointcut.setMappedNames("show*","newArticleForm*");
    LogTraceAdvice advice = new LogTraceAdvice(logTrace);
    return new DefaultPointcutAdvisor(pointcut,advice);
  }


}
