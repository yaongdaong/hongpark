package com.example.hongpark.repository;

import com.example.hongpark.entity.Article;
import com.example.hongpark.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest // 해당 클래스를 jpa와 연동해 테스팅
class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository; // commentrepository 객체 주입

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId() {
        /*Case 1: 4번 게시글의 모든 댓글 조회*/
        {
            // 1. 입력 데이터 준비
            Long articleId = 4L;
            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 3. 예상 데이터
            Article article = new Article(4L, "당신의 인생 영화는?", "댓글고");
            Comment a = new Comment(1L, article, "park", "굿윌헌팅");
            Comment b = new Comment(2L, article, "kim", "아이엠샘");
            Comment c = new Comment(3L, article, "choi", "쇼생크탈출");
            List<Comment> expected = Arrays.asList(a, b, c);
            // 4. 비교 및 검증
            assertEquals(expected.toString(), comments.toString(), "4번 글의 모든 댓글을 출력!");
        }
        /*Case 2: 4번 게시글의 모든 댓글 조회*/
        {
            // 1. 입력 데이터 준비
            Long articleId = 1L;
            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 3. 예상 데이터
            Article article = new Article(1L, "가", "1");
            List<Comment> expected = Arrays.asList();
            // 4. 비교 및 검증
            assertEquals(expected.toString(), comments.toString(), "1번 글은 댓글이 없음");
        }
        /*Case 3: 9번 게시글의 모든 댓글 조회*/
        {
            // 1. 입력 데이터 준비
            Long articleId = 9L;
            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 3. 예상 데이터
            Article article = null;
            List<Comment> expected = Arrays.asList();
            // 4. 비교 및 검증
            assertEquals(expected.toString(), comments.toString(),"9번 글 자체가 없으므로 댓글은 비어 있어야 함");
        }
        /*Case 4: 999번 게시글의 모든 댓글 조회*/
        {
            // 1. 입력 데이터 준비
            Long articleId = 999L;
            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 3. 예상 데이터
            Article article = null;
            List<Comment> expected = Arrays.asList();
            // 4. 비교 및 검증
            assertEquals(expected.toString(), comments.toString(),"999번 글 자체가 없으므로, 댓글은 비어 있어야 함");
        }
        /* Case 5: -1번 게시글의 모든 댓글 조회 */
        {
            // 1. 입력 데이터 준비
            Long articleId = -1L;
            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 3. 예상 데이터
            Article article = null;
            List<Comment> expected = Arrays.asList();
            // 4. 비교 및 검증
            assertEquals(expected.toString(), comments.toString(), "-1번 글 자체가 없으므로, 댓글은 비어 있어야 함");
        }
    }

    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {
        /*Case 1: "park"의 모든 댓글 조회*/
        {
            // 1. 입력 데이터 준비
            String nickname = "park";
            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByNickname(nickname);
            // 3. 예상 데이터
            Comment a = new Comment(1L, new Article(4L, "당신의 인생 영화는?", "댓글고"), nickname, "굿윌헌팅");
            Comment b = new Comment(4L, new Article(5L, "음식", "댓글"), nickname, "치킨");
            Comment c = new Comment(7L, new Article(6L, "취미", "댓글"), nickname, "조깅");
            List<Comment> expected = Arrays.asList(a, b, c);
            // 4. 비교 및 검증
            assertEquals(expected.toString(),comments.toString(),"park의 모든 댓글을 출력");
        }
        /* Case 2: "kim"의 모든 댓글 조회 */
        {
            // 1. 입력 데이터 준비
            String nickname = "kim";
            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByNickname(nickname);
            // 3. 예상 데이터
            Comment a = new Comment(2L, new Article(4L, "당신의 인생 영화는?","댓글고"),nickname,"아이엠샘");
            Comment b = new Comment(5L, new Article(5L,"음식","댓글"),nickname,"샤브샤브");
            Comment c = new Comment(8L, new Article(6L,"취미","댓글"),nickname,"유튜브");
            List<Comment> expected = Arrays.asList(a,b,c);
            // 4. 비교 및 검증
            assertEquals(expected.toString(), comments.toString(), "kim의 모든 댓글을 출력");
        }
        /* Case 3: null의 모든 댓글 조회 */
        {
            // 1. 입력 데이터 준비
            String nickname = null;
            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByNickname(nickname);
            // 3. 예상 데이터
            List<Comment> expected = Arrays.asList();
            // 4. 비교 및 검증
            assertEquals(expected.toString(), comments.toString(), "null의 모든 댓글 출력");
        }
        /* Case 4: ""의 모든 댓글 조회*/
        {
            // 1. 입력 데이터 준비
            String nickname = "";
            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByNickname(nickname);
            // 3. 예상 데이터
            List<Comment> expected = Arrays.asList();
            // 4. 비교 및 검증
            assertEquals(expected.toString(), comments.toString(), "\"\"의 모든 댓글을 출략");
        }
    }
}