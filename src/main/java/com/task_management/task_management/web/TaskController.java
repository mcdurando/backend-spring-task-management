package com.task_management.task_management.web;

import com.task_management.task_management.application.service.TaskService;
import com.task_management.task_management.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}

