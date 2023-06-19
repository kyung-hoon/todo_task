package com.itplatform.todo.controller;

import com.itplatform.todo.domain.Task;
import com.itplatform.todo.service.TaskService;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Tag(name = "Task", description = "Task API")

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Operation(summary = "my tasks 조회", description = "사용자 본인이 생성한 task들을 반환한다.")
    @GetMapping("/my/{memberId}")
    public List<Task> findByMemberId(@PathVariable String memberId) {
        return taskService.findAllByMemberId(memberId);
    }

    @Operation(summary = "전체 task 목록 조회", description = "전체 task 목록을 조회한다.")
    @GetMapping
    public List<Task> findAll() {
        return taskService.findAll();
    }

    @Operation(summary = "task를 조회한다", description = "특정 task를 조회한다.")
    @GetMapping("/{id}")
    public Task getTask(@PathVariable int id) {
        return taskService.findById(id);
    }

    @Operation(summary = "task를 추가한다.", description = "task를 추가한다.")
    @PostMapping("")
    public Task addTask(@RequestBody Task task) {
        return taskService.save(task);
    }

    @Operation(summary = "task를 업데이트 한다.", description = "task를 업데이트 한다.")
    @PutMapping("")
    public Task updateTask(@RequestBody Task task) {
        taskService.update(task);

        return task;
    }

    @Operation(summary = "task를 삭제 한다.", description = "task를 삭제 한다.")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask(@PathVariable int id) {

        taskService.deleteById(id);

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
