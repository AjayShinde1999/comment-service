package com.comment.service.controller;

import com.comment.service.payload.CommentDto;
import com.comment.service.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/comment")
@Slf4j
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CommentDto saveComment(@Valid @RequestBody CommentDto commentDto) {
        return commentService.saveComment(commentDto);
    }
}
