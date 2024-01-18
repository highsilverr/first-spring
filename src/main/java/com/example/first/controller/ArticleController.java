package com.example.first.controller;

import org.springframework.ui.Model;
//import ch.qos.logback.core.model.Model;
import com.example.first.dto.ArticleForm;
import com.example.first.entity.Article;
import com.example.first.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@Slf4j //로깅을 위한 어노테이션 문법
public class ArticleController {

    @Autowired //스프링부트가 미리생성해놓은 객체를 가져다가 자동연결 = new 할 필요 x
    private ArticleRepository articleRepository;
    @GetMapping("/articles/new")
    public String newArticleForm(){
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
        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}") //데이터 조회 요청 접수
    public String show(@PathVariable Long id, Model model) {
        log.info("id = " +id);
        // 1. 아이디를 조회해 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        // 2. 모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);
        // 3. 뷰 페이지 반환하기
        return "articles/show";
    }

    @GetMapping("/articles")
    public String nidex(Model model){
        //1. 모든 데이터 가져오기
        ArrayList<Article> articleEntityList = articleRepository.findAll();
        //2. 모델에 데이터 등록하기
        model.addAttribute("articleList", articleEntityList);
        //3. 뷰페이지 설정하기
        return "articles/index";
    }
}

