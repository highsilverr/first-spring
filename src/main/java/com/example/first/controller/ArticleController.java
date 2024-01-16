package com.example.first.controller;

import com.example.first.dto.ArticleForm;
import com.example.first.entity.Article;
import com.example.first.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j //로깅을 위한 어노테이션 문법
public class ArticleController {

    @Autowired //스프링부트가 미리생성해놓은 객체를 가져다가 자동연결 = new 할 필요 x
    private ArticleRepository articleRepository;
    @GetMapping("/articles/new")
    public  String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
        log.info(form.toString());//데이터가 로그로 찍힘

        //System.out.println(form.toString()); 로깅으로 대체

        //1. dto를 엔티티로 변환 articles/new에서 받은 데이터를 엔티티로 변환
        Article article = form.toEntity();
        log.info(article.toString());

        //2. Repository에게 엔티티를 디비 안에 저장
        Article saved = articleRepository.save(article); //상속 받은 클래스에 save라는 메소드가 있음
        log.info(saved.toString());
        return "";
    }
}

