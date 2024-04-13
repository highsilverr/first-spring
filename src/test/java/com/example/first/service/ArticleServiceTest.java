package com.example.first.service;

import com.example.first.dto.ArticleForm;
import com.example.first.entity.Article;
import com.sun.tools.attach.AgentLoadException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@SpringBootTest
class ArticleServiceTest {

    @Autowired
    ArticleService articleService;

    @Test
    void index() {
        // 1. 예상 데이터
        Article a = new Article(1L, "가가가", "1111");
        Article b = new Article(2L, "나나나", "2222");
        Article c = new Article(3L, "다다다", "3333");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a,b,c));
        // 2. 실제 데이터
        List<Article> articles = articleService.index();
        // 3. 비교 및 검증
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void show_성공_존재하는_id_입력() {
        Long id =1L;
        Article ex = new Article(id, "가가가","1111");

        Article article =articleService.show(id);

        assertEquals(ex.toString(), article.toString());
    }
    @Test
    void show_실패_존재하지_않는_id_입력() {
        Long id = -1L;
        Article ex = null;

        Article article =articleService.show(id);

        assertEquals(ex, article);
    }


    @Test
    @org.springframework.transaction.annotation.Transactional
    void create_성공_title과_content만_있는_dto_입력() {
        String title ="라라라라;";
        String content = "444444";
        ArticleForm dto = new ArticleForm(null,title,content);
        Article ex = new Article(4L,title,content);

        Article article = articleService.create(dto);

        assertEquals(ex.toString(),article.toString());
    }

    @Test
    @Transactional
    void create_실패_id가_포함된_dto_입력() {
        Long id=4L;
        String title ="라라라라;";
        String content = "444444";
        ArticleForm dto = new ArticleForm(id,title,content);
        Article ex = null;

        Article article = articleService.create(dto);

        assertEquals(ex,article);
    }
}