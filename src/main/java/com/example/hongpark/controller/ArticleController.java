package com.example.hongpark.controller;

import com.example.hongpark.dto.ArticleForm;
import com.example.hongpark.entity.Article;
import com.example.hongpark.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;


// 로깅 기능을 위한 어노테이션 추가
@Slf4j
// 컨트롤러 선언
@Controller
public class ArticleController {
    @Autowired // 스프링부트가 미리 생성해 놓은 리파지터리 객체 주입(DI)
    // DB에서 데이터를 가져오는 주체
    private ArticleRepository articleRepository;

    // URL 요청 접수
    @GetMapping("/articles/new")
    // 메서드 생성 및 반환값 작성
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) { // 폼 데이터를 DTO로 받기
        log.info(form.toString());
        //System.out.println(form.toString()); // DTO에 폼 데이터가 잘 담겼는지 확인
        // 1. DTO를 엔티티(자바 객체를 DB가 이해할 수 있게 만든 것, 이를 기반으로 테이블 생성됨)로 변환
        Article article = form.toEntity();
        // DTO가 엔티티로 잘 변환되는지 확인 출력
        System.out.println(article.toString());
        // 2. 리파지터리(엔티티가 DB 속 에티블에 저장 및 관리될 수 있게 하는 인터페이스)로 엔티티를 DB에 저장
        // article 엔티티를 저장해 saved 객체에 반환
        Article saved = articleRepository.save(article);
        // article이 DB에 잘 저장되는지 확인 출력
        log.info(saved.toString());
        //System.out.println(saved.toString());
        // 실제 서버에서는 pringln()문으로 데이터를 검증하면 기록에 남지 않을뿐더러 서버의 성능에도 악영향을 끼침
        return "redirect:/articles/"+saved.getId();
    }

    @GetMapping("/articles/{id}") // 데이터 조회 요청 접수
    // @PathVariable은 URL요청으로 들어온 전달값을 컨트롤러의 매개변수로 가져오는 어노테이션
    public String show(@PathVariable Long id, Model model) { // 매개변수로 id 받아 오기
        log.info("id = " + id); // id를 잘 받았는지 확인하는 로그 찍기
        //1. id를 조회해 db에서 해당 데이터 가져오기
        //Optional<Article> articleEntity = articleRepository.findById(id);
        Article articleEntity = articleRepository.findById(id).orElse(null);
        //2. 가져온 데이터를 모델에 등록하기
        // 데이터를 모델에 등록하는 이유는 MVC 패턴에 따라 조회한 데이터를 뷰페이지에서 사용하기 위해서임
        model.addAttribute("article", articleEntity);
        //3. 조회한 데이터를 사용자에게 보여 주기 위한 뷰 페이지 만들기
        return "articles/show";
    }


    // 데이터 조회 과정
    //웹 페이지에서 게시글을 등록하면 서버를 통해 db에 저장됨.
    //1.사용자가 웹 페이지에서 데이터를 조회해 달라고 url 요청 보냄
    //2.서버의 컨트롤러가 이 요청을 받아 해당 url에서 찾으려는 데이터 정보(id)를 리파지터리에 전달
    //3.리파지터리는 정보(id)를 가지고 db에 데이터 조회 요청
    //4.db는 해당 데이터를 찾아 이를 엔티티로 반환
    //5.반환된 엔티티는 모델을 통해 뷰 템플릿으로 전달
    @GetMapping("/articles")
    // 단일 데이터를 조회할 때는 리파지터리가 엔티티를 반환, 데이터 목록을 조회할 때는 엔티티 묶음인 리스트를
    public String index(Model model){
        //반환 데이터 타입 불일치 문제 해결 방법
        //특정 메서드가 반환하는 데이터 타입과 사용자가 작성한 반환 데이터 타입이 다를 경우, 3가지 방법으로 해결 가능
        //1. 메서드가 반환하는 데이터 타입을 사용자가 작성한 데이터 타입으로 캐스팅(형변환)하기
        //2. 사용자가 작성한 데이터 타입을 메서드가 반환하는 데이터 타입으로 수정하기
        //3. 메서드의 반환 데이터 타입을 원하는 타입으로 오버라이딩 하기
        // 1. DB에서 모든 Articles 데이터 가져오기
        ArrayList<Article> articleList = articleRepository.findAll();
        // 2. 가져온 Article 묶음을 모델에 등록하기
        model.addAttribute("articleList",articleList);
        // 3. 사용자에게 보여 줄 뷰 페이지 설정하기
        return "articles/index";
    }
}
