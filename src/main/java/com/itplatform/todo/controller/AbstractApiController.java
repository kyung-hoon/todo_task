package com.itplatform.todo.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public abstract class AbstractApiController {
    protected <T> ResponseEntity<T> ok(T data){
        return ResponseEntity.ok(data);
    }
}
