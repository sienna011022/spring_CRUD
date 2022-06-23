package com.example.projectCRUD.controller;

import com.example.projectCRUD.dto.ArticleForm;
import com.example.projectCRUD.entity.Article;
import com.example.projectCRUD.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j //로깅을 위함
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm(){

        return "articles/new";
    }
    //폼에서 던진 주소로
    //데이터를 받으려는 객체dto를 넣어서
    //파라미터로 dto를 받아옴 = ArticleForn
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
        //2. 가져온 데이터를 모델에 등록
        model.addAttribute("article",articleEntity);
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
}
