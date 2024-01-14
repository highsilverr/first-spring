package com.example.first.controller;

import com.example.first.dto.ArticleForm;
import com.example.first.entity.Article;
import com.example.first.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {

    @Autowired //스프링부트가 미리생성해놓은 객체를 가져다가 자동연결 = new 할 필요 x
    private ArticleRepository articleRepository;
    @GetMapping("/articles/new")
    public  String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){


        System.out.println(form.toString());

        //1. dto를 엔티티로 변환 articles/new에서 받은 데이터를 엔티티로 변환
        Article article = form.toEntity();
        System.out.println(article.toString());

        //2. Repository에게 엔티티를 디비 안에 저장
        Article saved = articleRepository.save(article); //상속 받은 클래스에 save라는 메소드가 있음
        System.out.println(saved.toString());
        return "";
    }
}

