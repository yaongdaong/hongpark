package com.example.hongpark.repository;

import com.example.hongpark.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

// <>안은 제네릭 요소를 받는다. Article은 관리 대상 엔티티의 클래스 타입, Long은 관리 대상 엔티티의 대표값 타입
// Article.java에서 Long타입의 id를 대표값으로 지정
// CrudRepository는 JPA에서 제공하는 인터페이스로 이를 상속해 엔티티를 관리(생성,조회,수정,삭제) 가능
public interface ArticleRepository extends CrudRepository<Article, Long> {
    @Override
    ArrayList<Article> findAll();
}
