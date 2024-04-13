package com.example.first;

import com.example.first.entity.Article;
import com.example.first.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Equals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class FirstApplicationTests {

	@Autowired
	ArticleService articleService;

	@Test
	void contextLoads() {

	}

}
