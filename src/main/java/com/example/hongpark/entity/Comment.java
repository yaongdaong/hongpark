package com.example.hongpark.entity;

import com.example.hongpark.dto.CommentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity // 해당 클래스가 엔티티임을 선언, 클래스 필드를 바탕으로 db에 테이블 생성
@Getter // 각 필드 값을 조회할 수 있는 getter 메서드 자동 생성
@ToString // 모든 필드를 출력할 수 있는 toString 메서드 자동 생성
@AllArgsConstructor // 모든 필드를 매개변수로 갖는 생성자 자동 생성
@NoArgsConstructor // 매개변수가 아예 없는 기본 생성자 자동 생성
public class Comment {
    @Id // 대표키 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // db가 자동으로 1씩 증가
    private Long id; // 대표키
    @ManyToOne // comment 엔티티와 article 엔티티를 다대일 관계로 설정
    @JoinColumn(name = "article_id") // 외래키 생성, article 엔티티의 기본키(id)와 매핑
    private Article article; // 해당 댓글의 부모 게시글
    @Column // 해당 필드를 테이블의 속성으로 매핑
    private String nickname; // 댓글을 단 사람
    @Column // 해당 필드를 테이블의 속성으로 매핑
    private String body; // 댓글 본문

    public static Comment createComment(CommentDto dto, Article article) {
        // 게시글의 ID를 콘솔에 출력
        System.out.println("게시글의 ID: " + article.getId());
        System.out.println("게시글의 ID2: " + dto.getArticleId());

        //System.out.println("디티오아이디값: "+dto.getId().getClass());

        // 에외 발생
        if (dto.getId() != null)
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야 합니다.");
        if (dto.getArticleId() != article.getId())
            throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 잘못됐습니다.");
        // 엔티티 생성 및 반환
        return new Comment(dto.getId(), article, dto.getNickname(), dto.getBody());
    }

    public void patch(CommentDto dto) {
        // 예외 발생
        if (this.id != dto.getId())
            throw new IllegalArgumentException("댓글 수정 실패! 잘못된 id가 입력됐습니다.");
        // 객체 갱신
        if (dto.getNickname() != null) // 수정할 닉네임 데이터가 있다면
            this.nickname = dto.getNickname(); // 내용 반영
        if (dto.getBody() != null) // 수정할 본문 데이터가 있다면
            this.body = dto.getBody(); // 내용 반영
    }
}
