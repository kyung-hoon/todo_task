package com.itplatform.todo.controller;

import com.itplatform.todo.domain.Comment;
import com.itplatform.todo.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Tag(name = "Comment", description = "Comment API")
@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Operation(summary = "comment 수정", description = "comment를 수정한다.")
    @PutMapping("")
    public Comment updateComment(@RequestBody Comment comment) {
        commentService.update(comment);

        return comment;
    }

    @Operation(summary = "comment 삭제", description = "comment를 삭제한다.")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteComment(@PathVariable int id) {

        commentService.deleteById(id);

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
