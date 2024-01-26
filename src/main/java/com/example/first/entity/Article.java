package com.example.first.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor //생성자대신
@NoArgsConstructor //기본생성자 추가 어노테이션
@ToString //스트링대신
@Entity //디비가 해당 객체를 인식가능하게 함
@Getter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동생성 어노테이션
    private Long id; //기본키

    @Column
    private String title;

    @Column
    private String content;

    public void patch(Article article) {
        if(article.title != null){
            this.title = article.title;
        }
        if(article.content != null){
            this.content = article.content;
        }
    }
}
