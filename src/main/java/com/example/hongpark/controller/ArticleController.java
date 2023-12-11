package com.example.hongpark.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 컨트롤러 선언
@Controller
public class ArticleController {

    // URL 요청 접수
    @GetMapping("/articles/new")
    // 메서드 생성 및 반환값 작성
    public String newArticleForm() {
        return "articles/new";
    }
}
