package com.example.hongpark.service;

import com.example.hongpark.dto.ArticleForm;
import com.example.hongpark.entity.Article;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleServiceTest {
    @Autowired
    ArticleService articleService;

    @Test
    void index() {
        // 1. 예상 데이터
        Article a = new Article(1L, "가", "1");
        Article b = new Article(2L, "나", "2");
        Article c = new Article(3L, "다", "3");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a, b, c));
        //String[] arr = {"kim", "na", "park", "lee"};
        //List<String> fixedSizeListA = Arrays.asList(arr);
        //List<String> fixedSizeListB = Arrays.asList("김", "나", "박", "이");
        //List<String> list = new ArrayList<>(fixedSizeListA);
        // 2. 실제 데이터
        List<Article> articles = articleService.index();
        // 3. 비교 및 검증
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void show_성공_존재하는_id_입력() {
        // 1. 예상 데이터
        Long id = 1L;
        Article expected = new Article(id, "가", "1");
        // 2. 실제 데이터
        Article article = articleService.show(id);
        // 3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void show_실패_존재하지_않는_id_입력() {
        // 1. 예상 데이터
        Long id = -1L;
        Article expected = null;
        // 2. 실제 데이터
        Article article = articleService.show(id);
        // 3. 비교 및 검증
        assertEquals(expected, article);
    }

    @Transactional
    @Test
    void create_성공_title과_content만_있는_dto_입력() {
        // 1. 예상 데이터
        String title = "라";
        String content = "4";
        ArticleForm dto = new ArticleForm(null, title, content);
        Article expected = new Article(4L, title, content);
        // 2. 실제 데이터
        Article article = articleService.create(dto);
        // 3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }

    @Transactional
    @Test
    void create_실패_id가_포함된_dto_입력() {
        // 1. 예상 데이터
        Long id = 4L;
        String title = "라";
        String content = "4";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = null;
        // 2. 실제 데이터
        Article article = articleService.create(dto);
        // 3. 비교 및 검증
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void update_성공_존재하는_id와_title_content가_있는_dto_입력() {
        // 예상 데이터
        Long id = 1L;
        String title = "가";
        String content = "1";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = new Article(id, title, content);
        // 실제 데이터
        Article article = articleService.update(id, dto);
        // 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void update_성공_존재하는_id와_title만_있는_dto_입력() {
        // 예상 데이터
        Long id = 1L;
        String title = "A";
        String content = null;
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = new Article(1L, "A", "1");
        // 실제 데이터
        Article article = articleService.update(id, dto);
        // 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void update_실패_존재하지_않는_id의_dto_입력() {
        // 예상 데이터
        Long id = -1L;
        String title = "가나다라";
        String content = "1234";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = null;
        // 실제 데이터
        Article article = articleService.update(id, dto);
        // 비교 및 검증
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void delete_성공_존재하는_id_입력() {
        // 예상 데이터
        Long id = 1L;
        Article expected = new Article(id, "가", "1");
        // 실제 데이터
        Article article = articleService.delete(id);
        // 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void delete_실패_존재하지_않는_id_입력() {
        // 예상 데이터
        Long id = -1L;
        Article expected = null;
        // 실제 데이터
        Article article = articleService.delete(id);
        // 비교 및 검증
        assertEquals(expected, article);
    }
}