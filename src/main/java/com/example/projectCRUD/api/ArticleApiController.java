package com.example.projectCRUD.api;

import com.example.projectCRUD.dto.ArticleForm;
import com.example.projectCRUD.entity.Article;
import com.example.projectCRUD.repository.ArticleRepository;
import com.example.projectCRUD.service.ArticleService;
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

    private final ArticleService articleService;

    //GET
    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleService.index();
    }

    @GetMapping("/api/articles/{id}")
    public Article index(@PathVariable Long id){
        return articleService.show(id);
    }
    //POST
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto){
        Article created = articleService.create(dto);
        return (created !=null)?
                ResponseEntity.status(HttpStatus.OK).body(created):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();



    }
    //PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto) {
        //1. 수정 엔티티 생성
        Article updated = articleService.update(id, dto);
        return (updated != null) ? ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    //delete
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){

     Article deleted = articleService.delete(id);
     return(deleted != null)? ResponseEntity.status(HttpStatus.OK).body(deleted):
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

}
