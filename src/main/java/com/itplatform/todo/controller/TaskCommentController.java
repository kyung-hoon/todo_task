package com.itplatform.todo.controller;

import com.itplatform.todo.domain.Comment;
import com.itplatform.todo.domain.Task;
import com.itplatform.todo.service.TaskCommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Task Comment", description = "Task Comment API")

@RestController
@RequestMapping("/api/tasks")
public class TaskCommentController {

    private final TaskCommentService taskCommentService;

    public TaskCommentController(TaskCommentService taskCommentService) {
        this.taskCommentService = taskCommentService;
    }

    @Operation(summary = "task에 comment 추가.", description = "task에 comment를 추가한다.")
    @PostMapping("/{taskId}/comments")
    public Task addComment(@PathVariable int taskId, @RequestBody Comment comment) {
        return taskCommentService.save(taskId, comment);
    }
}
