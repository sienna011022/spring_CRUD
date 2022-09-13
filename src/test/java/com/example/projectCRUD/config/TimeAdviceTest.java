package com.example.projectCRUD.config;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.projectCRUD.domain.entity.Article;
import com.example.projectCRUD.repository.ArticleRepository;
import com.example.projectCRUD.service.ArticleService;
import com.example.projectCRUD.service.ArticleServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class TimeAdviceTest {

  @Autowired
  private ArticleRepository articleRepository;



  @Test
  @DisplayName("인터페이스가 있으면 동적 프록시 사용")
  void interfaceProxy(){

   Article testEntity = Article.builder().id(1L).title("test").content("test2").build();

    articleRepository.save(testEntity);

    ArticleService target = new ArticleServiceImpl(articleRepository);
    ProxyFactory proxyFactory = new ProxyFactory(target);
    proxyFactory.addAdvice(new TimeAdvice());
    ArticleService proxy = (ArticleService) proxyFactory.getProxy();
    log.info("target Class = {}",target.getClass());
    log.info("Proxy Class = {}",proxy.getClass());
    long testId = 1022;
    proxy.show(testId);


    assertThat(AopUtils.isAopProxy(proxy)).isTrue();
    assertThat(AopUtils.isJdkDynamicProxy(proxy)).isTrue();
    assertThat(AopUtils.isCglibProxy(proxy)).isFalse();
  }
}