package com.itplatform.todo.service;

import com.itplatform.todo.domain.Task;
import com.itplatform.todo.repository.TaskRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @Override
    public List<Task> findAllByMemberId(String memberId) {
        return taskRepository.findAllByMemberId(memberId);
    }

    @Override
    @Transactional
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    @Transactional
    public Task findById(int id) {
        Optional<Task> result = taskRepository.findById(id);

        Task task;

        if (result.isPresent()) {
            task = result.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found");
        }

        return task;
    }

    @Override
    @Transactional
    public Task save(Task task) {
        // Make sure id is not set by user
        task.setId(0);

        return taskRepository.save(task);
    }

    @Override
    @Transactional
    public Task update(Task task) {
        if (!taskRepository.existsById(task.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not updated");
        }

        return taskRepository.save(task);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        taskRepository.deleteById(id);
    }
}
