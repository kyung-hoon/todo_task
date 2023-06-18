package com.itplatform.todo.service;

import com.itplatform.todo.domain.Comment;
import com.itplatform.todo.domain.Task;
import com.itplatform.todo.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskCommentServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    private TaskCommentServiceImpl taskCommentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        taskCommentService = new TaskCommentServiceImpl(taskRepository);
    }

    @Test
    void save_ValidTaskIdAndComment_ShouldReturnTaskWithAddedComment() {
        int taskId = 1;
        Comment comment = new Comment();
        comment.setText("Test comment");

        Task task = new Task();
        task.setId(taskId);

        when(taskRepository.getOne(taskId)).thenReturn(task);

        Task result = taskCommentService.save(taskId, comment);

        assertNotNull(result);
        assertEquals(1, result.getComments().size());

        Comment addedComment = result.getComments().get(0);
        assertNotNull(addedComment);
        assertEquals(comment.getText(), addedComment.getText());
        assertNotNull(addedComment.getDate());

        verify(taskRepository, times(1)).getOne(taskId);
        verify(taskRepository, times(1)).saveAndFlush(task);
    }

    @Test
    void save_NonExistingTaskId_ShouldThrowResponseStatusException() {
        int taskId = -1;
        Comment comment = new Comment();
        comment.setText("Test comment");

        when(taskRepository.getOne(taskId)).thenReturn(null);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> taskCommentService.save(taskId, comment));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        verify(taskRepository, times(1)).getOne(taskId);
        verify(taskRepository, never()).saveAndFlush(any(Task.class));
    }
}