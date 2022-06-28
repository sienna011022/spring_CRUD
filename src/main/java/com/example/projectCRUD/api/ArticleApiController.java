package com.example.projectCRUD.api;

import com.example.projectCRUD.dto.ArticleForm;
import com.example.projectCRUD.entity.Article;
import com.example.projectCRUD.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController //JSON 형태로 반환
public class ArticleApiController {
    private final ArticleRepository articleRepository;
    //GET
    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleRepository.findAll();
    }

    @GetMapping("/api/articles/{id}")
    public Article index(@PathVariable Long id){
        return articleRepository.findById( id).orElse(null);
    }
    //POST
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto){
        Article article = dto.toEntity();
        return articleRepository.save(article);

    }
    //PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto){
        //1. 수정 엔티티 생성
        Article article = dto.toEntity();
        log.info("id:{},article:{}",id, article.toString());
        //2. 대상 엔티티 조회
        Article target = articleRepository.findById(id).orElse(null);


        //3. 잘못된 요청 처리(대상이 없거나, id가 다른 경우)
        if(target == null || id != article.getId()){
            //400 잘못된 요청에 대한 응답
            log.info("잘못된 요청 id:{},article:{}",id, article.toString());
            //상태코드를 실어서 보내줘야해서 responseEntity<>


            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        }
        //4.업데이트 및 정상 응답
        target.patch(article);
        Article updated = articleRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(updated);

    }

    //delete
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        //대상 찾기
        Article target = articleRepository.findById(id).orElse(null);

        if(target ==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);


        }

        //대상 삭제
        articleRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
