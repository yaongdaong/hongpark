package com.example.hongpark.controller;

import com.example.hongpark.dto.ArticleForm;
import com.example.hongpark.entity.Article;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

// 컨트롤러 선언
@Controller
public class ArticleController {

    // URL 요청 접수
    @GetMapping("/articles/new")
    // 메서드 생성 및 반환값 작성
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){ // 폼 데이터를 DTO로 받기
        System.out.println(form.toString()); // DTO에 폼 데이터가 잘 담겼는지 확인
        // 1. DTO를 엔티티(자바 객체를 DB가 이해할 수 있게 만든 것, 이를 기반으로 테이블 생성됨)로 변환
        // 2. 리파지터리(엔티티가 DB 속 에티블에 저장 및 관리될 수 있게 하는 인터페이스)로 엔티티를 DB에 저장
        Article article = form.toEntity();
        return "";
    }
}
