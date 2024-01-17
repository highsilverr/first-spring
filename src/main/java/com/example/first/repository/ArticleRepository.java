package com.example.first.repository;

import com.example.first.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article, Long> { //우리가 코드 짤 필요 없이 상속받아 저장

    @Override
    ArrayList<Article> findAll();
}
