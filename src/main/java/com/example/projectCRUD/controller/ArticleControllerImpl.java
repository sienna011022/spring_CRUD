package com.example.projectCRUD.controller;

import com.example.projectCRUD.domain.dto.ArticleForm;
import com.example.projectCRUD.domain.dto.CommentDto;
import com.example.projectCRUD.domain.entity.Article;
import com.example.projectCRUD.repository.ArticleRepository;
import com.example.projectCRUD.service.ArticleService;
import com.example.projectCRUD.service.CommentService;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@AllArgsConstructor
@Slf4j
public class ArticleControllerImpl implements ArticleController {
  @Autowired
  private ArticleRepository articleRepository;
  @Autowired
  private CommentService commentService;
  @GetMapping("/articles/new")
  public String newArticleForm(){

    return "articles/new";
  }
  //폼에서 던진 주소로
  //데이터를 받으려는 객체dto를 넣어서
  //파라미터로 dto를 받아옴 = ArticleForm
  @PostMapping("/articles/create")
  public String createArticle(ArticleForm form){
    //1.DTO 를 Entity로 변환
    Article article = form.toEntity();
    log.info(article.toString());
    //    System.out.println(article.toString());v-> log로 대체


    //2.Repository를 통해 DB에 저장
    Article saved = articleRepository.save(article);
    log.info(saved.toString());
    return "redirect:/articles/"+saved.getId();
  }

  @GetMapping("/articles/{id}")
  public String show(@PathVariable Long id, Model model){
    log.info("id = "+id);
    //1.아이디로 데이터를 가져옴
    Article articleEntity = articleRepository.findById(id).orElse(null);
    //데이터를 가져오기
    List<CommentDto> commentDtos = commentService.comments(id);
    //2. 가져온 데이터를 모델에 등록
    model.addAttribute("article",articleEntity);
    model.addAttribute("commentDtos",commentDtos);
    //3. 보여줄 페이지를 성정
    return "articles/show";
  }
  @GetMapping("/articles/{id}/edit")
  public String edit(@PathVariable Long id , Model model){
    //수정할 데이터 DB에서 가져오기
    Article articleEntity = articleRepository.findById(id).orElse(null);
    //가져온 데이터 모델에 등록
    model.addAttribute("article",articleEntity);
    //페이지 설정
    return "articles/edit";
  }
  @GetMapping("/articles")
  public String index(Model model){

    //1.모든 article(DTO)를 가져옴
    //오버라이딩 해주기기
    List<Article> articleEntityList = articleRepository.findAll();
    //2. 가져온 article 묶음을 뷰로 전달
    model.addAttribute("articleList",articleEntityList);
    //3. 뷰 페이지를 설정
    return "articles/index";
  }

  @PostMapping("/articles/update")
  //원래는 patch 매핑
  public String update(ArticleForm form){
    //1.dto를 엔티티로 변환한다.
    Article articleEntity = form.toEntity();
    log.info(articleEntity.toString());
    //2.엔티티를 DB로 저장한다.
    //2.1 데이터 아이디를 매핑해서 기존 값 가져오기
    Optional<Article> target = articleRepository.findById(articleEntity.getId());
    //2.2 기존 데이터에 값을 갱신
    if(target != null){
      articleRepository.save(articleEntity);
    }

    //3.수정결과를 페이지로 반환
    log.info(form.toString());
    return "redirect:/articles/"+articleEntity.getId();

  }
  @GetMapping("/articles/{id}/delete")
  public String delete(@PathVariable Long id, RedirectAttributes rttr){
    log.info("삭제 요청이 들어왔습니다");
    //1. 삭제 대상을 가져오기
    //url에서 가져오기=pathvariable
    Optional<Article> target = articleRepository.findById(id);
    log.info(target.toString());
    //2. 대상을 삭제
    if(target != null){
      articleRepository.deleteById(id);
      //메세지 남겨주기
      rttr.addFlashAttribute("msg","Delete complete");
    }

    //3. 결과 페이지로 리다이렉트
    return "redirect:/articles";
  }
}
