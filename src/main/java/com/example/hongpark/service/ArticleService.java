package com.example.hongpark.service;

import com.example.hongpark.dto.ArticleForm;
import com.example.hongpark.entity.Article;
import com.example.hongpark.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        if (article.getId() != null) {
            return null;
        }
        return articleRepository.save(article);
    }


    public Article update(Long id, ArticleForm dto) {
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
                return null; // 응답은 컨트롤러가 하므로 여기서는 null 반환
            }
            // 4. 업데이트하기
            target.patch(article);
            Article updated = articleRepository.save(target);
            return updated; //응답은 컨트롤러가 하므로 여기서는 수정데이터만 반환
    }

    public Article delete(Long id) {
        // 1. 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);
        // 2. 잘못된 요청 삭제하기
        if (target == null){
            return null;
        }
        // 3. 대상 삭제하기
        articleRepository.delete(target);
        return target;
    }
}
