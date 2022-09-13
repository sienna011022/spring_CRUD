package com.example.projectCRUD.controller;

import com.example.projectCRUD.domain.dto.ArticleForm;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@RequestMapping//스프링은 @Controller 또는 @RequestMapping 이 있어야 스프링 컨트롤러로 인식
@ResponseBody
public interface ArticleController {

  @PostMapping("/articles/create")
  String createArticle(ArticleForm form);

  @GetMapping("/articles/{id}")
  String show(@PathVariable Long id, Model model);

  @GetMapping("/articles/{id}/edit")
  String edit(@PathVariable Long id, Model model);

  @GetMapping("/articles")
  String index(Model model);

  @PostMapping("/articles/update")
    //원래는 patch 매핑
  String update(ArticleForm form);

  @GetMapping("/articles/{id}/delete")
  String delete(@PathVariable Long id, RedirectAttributes rttr);
}
