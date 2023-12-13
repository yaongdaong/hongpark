package com.example.hongpark.dto;

import com.example.hongpark.entity.Article;

// 전송받은 데이터를 담아 둘 객체인 DTO를 생성
public class ArticleForm {

    private String title; // 제목을 받을 필드
    private String content; // 내용을 받을 필드

    // 전송받은 제목과 내용을 필드에 저장하는 생성자 추가
    public ArticleForm(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // 데이터를 잘 받았는지 확인할 toString() 메서드 추가
    @Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    // DTO인 form 객체를 엔티티 객체로 변환하는 역할
    // Article.java에서 생성자를 확인해 보면 id, title, content를 매개변수로 받음
    // 아직 ArticleForm 객체에 id 정보는 없으므로 첫번 째 전달값은 null
    public Article toEntity() {
        return new Article(null, title, content);
    }
}
