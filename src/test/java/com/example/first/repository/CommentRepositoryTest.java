package com.example.first.repository;

import com.example.first.entity.Article;
import com.example.first.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    @DisplayName("특정게시물의 모든 댓글 조회")
    void findByArticleID() {
        //케이스1 4번 게시글의 모든 댓글 조회
        {
            // 1. 입력데이터 준비
            Long articleId = 4L;
            // 2. 실제데이터 준비
            List<Comment> comments = commentRepository.findByArticleID(articleId);

            //3. 예상데이터 분비
            Article article = new Article(4L,"영화", "댓 ㄱ");
            Comment a = new Comment(1L, article, "응 아니야","드래곤 길들이기");
            Comment b = new Comment(2L, article, "닉네임","하늘에서 음식이 내린다면");
            List<Comment> ex = Arrays.asList(a,b);
            // 4. 비교 및 검증
            assertEquals(ex.toString(),comments.toString());
        }
        //케이스2 1번 게시글의 모든 댓글 조회
        {
            // 1. 입력데이터 준비
            Long articleId = 1L;
            // 2. 실제데이터 준비
            List<Comment> comments = commentRepository.findByArticleID(articleId);

            //3. 예상데이터 분비
            Article article = new Article(1L,"가가가", "1111");
            List<Comment> ex = Arrays.asList();
            // 4. 비교 및 검증
            assertEquals(ex.toString(),comments.toString(),"1번 글은 댓글이 없음");
        }
    }

    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {
        //케이스1:
        {
            //1. 입력 데이터 준비
            String nickname = "응 아니야";
            //2. 실제데이터
            List<Comment> comments = commentRepository.findByNickname(nickname);
            //3. 예상데이터
            Comment a =new Comment(1L, new Article(4L,"영화", "댓 ㄱ"),nickname,"드래곤 길들이기");
            Comment b =new Comment(3L, new Article(5L,"간식", "댓 ㄱ"),nickname,"짜파게티");
            Comment c =new Comment(5L, new Article(6L,"취미", "댓 ㄱ"),nickname,"자기");
            List<Comment> ex = Arrays.asList(a,b,c);
            //4. 비교 및 검증
            assertEquals(ex.toString(),comments.toString(),"응 아니야의 모든 댓글 출력");
        }
    }
}