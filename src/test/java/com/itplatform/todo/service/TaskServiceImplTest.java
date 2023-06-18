package com.itplatform.todo.service;
import com.itplatform.todo.domain.Task;
import com.itplatform.todo.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceImplTest {

    private TaskServiceImpl taskService;

    @Mock
    private TaskRepository taskRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        taskService = new TaskServiceImpl(taskRepository);
    }

    @Test
    void findAllByMemberId_ValidMemberId_ShouldReturnListOfTasks() {
        // Arrange
        String memberId = "testMemberId";
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task());
        tasks.add(new Task());

        when(taskRepository.findAllByMemberId(memberId)).thenReturn(tasks);

        List<Task> result = taskService.findAllByMemberId(memberId);

        assertEquals(tasks, result);
        verify(taskRepository, times(1)).findAllByMemberId(memberId);
    }

    @Test
    void findAll_ShouldReturnListOfTasks() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task());
        tasks.add(new Task());

        when(taskRepository.findAll()).thenReturn(tasks);

        List<Task> result = taskService.findAll();

        assertEquals(tasks, result);
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    void findById_ExistingId_ShouldReturnTask() {
        int taskId = 1;
        Task task = new Task();
        task.setId(taskId);

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        Task result = taskService.findById(taskId);

        assertEquals(task, result);
        verify(taskRepository, times(1)).findById(taskId);
    }

    @Test
    void findById_NonExistingId_ShouldThrowResponseStatusException() {
        int taskId = 1;

        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () ->
                taskService.findById(taskId));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        verify(taskRepository, times(1)).findById(taskId);
    }

    @Test
    void save_ValidTask_ShouldReturnSavedTask() {
        Task task = new Task();

        when(taskRepository.save(task)).thenReturn(task);

        Task result = taskService.save(task);

        assertEquals(task, result);
        assertEquals(0, result.getId()); // Make sure id is set to 0
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void update_ExistingTask_ShouldReturnUpdatedTask() {
        Task task = new Task();
        task.setId(1);

        when(taskRepository.existsById(task.getId())).thenReturn(true);
        when(taskRepository.save(task)).thenReturn(task);

        Task result = taskService.update(task);

        assertEquals(task, result);
        verify(taskRepository, times(1)).existsById(task.getId());
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void update_NonExistingTask_ShouldThrowResponseStatusException() {
        Task task = new Task();
        task.setId(1);

        when(taskRepository.existsById(task.getId())).thenReturn(false);

        assertThrows(ResponseStatusException.class, () ->
                taskService.update(task));
        verify(taskRepository, times(1)).existsById(task.getId());
    }

    @Test
    void deleteById_ExistingId_ShouldInvokeRepositoryDeleteById() {
        int taskId = 1;

        taskService.deleteById(taskId);

        verify(taskRepository, times(1)).deleteById(taskId);
    }
}
