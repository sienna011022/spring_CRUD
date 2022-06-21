package com.example.projectCRUD.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {
    @GetMapping("/greetings")
    public String niceToMeetYou(Model model){
        model.addAttribute("username","sienna");
        //파일 이름만 return 로
        //templates/greetings.mustache 를 찾아 브라우저로 전송
        return "greetings";
    }

    @GetMapping("/goodbye")
    public String bye(Model model){
        model.addAttribute("username","sienna");
        //파일 이름만 return 로
        //templates/greetings.mustache 를 찾아 브라우저로 전송
        return "goodbye";
    }
}
