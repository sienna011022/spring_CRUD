package com.example.projectCRUD.service;

import com.example.projectCRUD.aop.Trace;
import com.example.projectCRUD.domain.dto.ArticleForm;
import com.example.projectCRUD.domain.entity.Article;
import com.example.projectCRUD.repository.ArticleRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor

public class ArticleServiceImpl implements ArticleService {

  private final ArticleRepository articleRepository;

  @Override
  @Trace
  public List<Article> index(){
    return articleRepository.findAll();

  }
  @Override
  @Trace
  public Article show(Long id) {
    log.info("아이디 찾기 실행");

    return articleRepository.findById(id).orElse(null);
  }
  @Override
  public Article create(ArticleForm dto) {
    Article article = dto.toEntity();
    //이미 있는 아이디에생성하려고 할 때 막아야함.
    if(article.getId() != null){
      return null;
    }
    return articleRepository.save(article);

  }
  @Override
  public Article update(Long id, ArticleForm dto) {
    Article article = dto.toEntity();
    log.info("id:{},article:{}",id, article.toString());
    //2. 대상 엔티티 조회
    Article target = articleRepository.findById(id).orElse(null);


    //3. 잘못된 요청 처리(대상이 없거나, id가 다른 경우)
    if(target == null || id != article.getId()){
      //400 잘못된 요청에 대한 응답

      log.info("잘못된 요청 id:{},article:{}",id, article.toString());
      log.info("targrt type{},내가 입력한 값 타입:{}",target.getClass().getName(),id.getClass().getName());
      //상태코드를 실어서 보내줘야해서 responseEntity<>
      return null;

    }
    //4.업데이트 및 정상 응답
    target.patch(article);
    log.info("targrt type{},내가 입력한 값 타입:{}",target.getClass().getName(),id.getClass().getName());
    Article updated = articleRepository.save(target);
    return updated;
  }
  @Override
  public Article delete(Long id) {
    Article target = articleRepository.findById(id).orElse(null);

    if(target == null){return null;
    }

    //대상 삭제
    articleRepository.deleteById(id);
    return target;
  }

}
