package com.example.hongpark.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

// Entity 어노테이션이 붙은 클래스를 기반으로 DB에 테이블이 생성됨
// 테이블 이름은 클래스 이름과 동일하게 Article로 생성된다.
@Entity
public class Article {
    // 엔티티의 대푯값 지정
    @Id
    // 대표값을 자동으로 생성
    @GeneratedValue
    private Long id;
    // title 필드 선언, DB 테이블의 title 열과 연결됨
    @Column
    private String title;
    // content 필드 선언, DB 테이블의 content 열과 연결됨
    @Column
    private String content;

    // Article 생성자 추가(객체의 생성 및 초기화)
    public Article(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

}