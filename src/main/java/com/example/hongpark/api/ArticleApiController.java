package com.example.hongpark.api;

import com.example.hongpark.dto.ArticleForm;
import com.example.hongpark.entity.Article;
import com.example.hongpark.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ArticleApiController {
    @Autowired
    private ArticleRepository articleRepository;
    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleRepository.findAll();
    }
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){
        return articleRepository.findById(id).orElse(null);
    }
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto){
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }
    // 컨트롤러의 역할: 클라이언트 요청받기
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto){
        // 서비스의 역할: 리파지터리에 데이터 가져오도록 명렁하기
        // 1. DTO -> 엔티티 변환하기
        Article article = dto.toEntity(); // dto를 엔티티로 변환
        log.info("id: {}, article: {}", article.toString()); // 로그 찍기
        // 2. 타깃 조회하기
        Article target = articleRepository.findById(id).orElse(null);
        // 3. 잘못된 요청 처리하기
        if (target == null || id != article.getId()){ // 잘못된 요청인지 판별
            // 400, 잘못된 요청 응답
            log.info("잘못된 요청! id: {}, article: {}", id, article.toString()); // 로그 찍기
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // ResponseEntity 반환
        }
        // 4. 업데이트 및 정상 응답(200)하기
        target.patch(article);
        Article updated = articleRepository.save(target);
        // 컨트롤러의 역할: 클라이언트에 응답하기
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        // 1. 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);
        // 2. 잘못된 요청 삭제하기
        if (target == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        // 3. 대상 삭제하기
        articleRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
