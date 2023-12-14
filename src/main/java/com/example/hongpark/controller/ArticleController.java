package com.example.hongpark.controller;

import com.example.hongpark.dto.ArticleForm;
import com.example.hongpark.entity.Article;
import com.example.hongpark.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

// 컨트롤러 선언
@Controller
public class ArticleController {
    @Autowired // 스프링부트가 미리 생성해 놓은 리파지터리 객체 주입(DI)
    private ArticleRepository articleRepository;

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
        Article article = form.toEntity();
        // DTO가 엔티티로 잘 변환되는지 확인 출력
        System.out.println(article.toString());
        // 2. 리파지터리(엔티티가 DB 속 에티블에 저장 및 관리될 수 있게 하는 인터페이스)로 엔티티를 DB에 저장
        // article 엔티티를 저장해 saved 객체에 반환
        Article saved = articleRepository.save(article);
        // article이 DB에 잘 저장되는지 확인 출력
        System.out.println(saved.toString());
        return "";
    }
}
