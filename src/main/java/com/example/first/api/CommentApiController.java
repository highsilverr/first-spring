package com.example.first.api;

import com.example.first.dto.CommentDto;
import com.example.first.entity.Comment;
import com.example.first.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.Callable;

@RestController
public class CommentApiController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/api/articles/{articleID}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleID){
        //서비스에 위임
        List<CommentDto> dtos = commentService.comments(articleID);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    @PostMapping("/api/articles/{articleID}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long articleID, @RequestBody CommentDto dto){
        CommentDto createdDto = commentService.create(articleID,dto);
        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }
    @PatchMapping("api/comments/{id}")
    public ResponseEntity<CommentDto> update (@PathVariable Long id, @RequestBody CommentDto dto){
         CommentDto updateDto = commentService.update(id,dto);
         return ResponseEntity.status(HttpStatus.OK).body(updateDto);
    }

    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> delete(@PathVariable Long id){
        CommentDto deletedDto = commentService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(deletedDto);
    }
}
