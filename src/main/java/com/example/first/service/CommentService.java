package com.example.first.service;

import com.example.first.dto.CommentDto;
import com.example.first.entity.Article;
import com.example.first.entity.Comment;
import com.example.first.repository.ArticleRepository;
import com.example.first.repository.CommentRepository;
import org.hibernate.annotations.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleID) {
//        //댓글조회
//        List<Comment> comments = commentRepository.findByArticleID(articleID);
//        //엔티티->디티오변환
//        List<CommentDto> dtos = new ArrayList<CommentDto>();
//        for(int i=0; i<comments.size();i++){
//            Comment c = comments.get(i);
//            CommentDto dto = CommentDto.createCommentDto(c);
//            dtos.add(dto);
//        }
        //결과반환
        return commentRepository.findByArticleID(articleID).stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentDto create(Long articleID, CommentDto dto) {
        Article article = articleRepository.findById(articleID)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패" + "대상 게시글이 없습니다."));
        Comment comment = Comment.createComment(dto,article);
        Comment created = commentRepository.save(comment);
        return CommentDto.createCommentDto(created);
    }

    @Transactional
    public CommentDto update(Long id, CommentDto dto) {

        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패" + "대상 댓글이 없습니다."));
        target.patch(dto);

        Comment updated = commentRepository.save(target);

        return CommentDto.createCommentDto(updated);
    }

    @Transactional
    public CommentDto delete(Long id) {
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패" + "대상 댓글이 없습니다."));
        commentRepository.delete(target);

        return CommentDto.createCommentDto(target);
    }
}
