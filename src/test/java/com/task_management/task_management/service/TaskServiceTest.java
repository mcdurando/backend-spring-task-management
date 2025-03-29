package com.task_management.task_management.service;

import com.task_management.task_management.application.service.TaskService;
import com.task_management.task_management.domain.Task;
import com.task_management.task_management.domain.TaskStatus;
import com.task_management.task_management.domain.User;
import com.task_management.task_management.infrastructure.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        // Setup before each test if needed
    }

    @Test
    void testGetAllTasks() {
        Task task1 = new Task(1L, "Task 1", "Description 1", TaskStatus.IN_PROGRESS,new User(1L, "mcdur", "pwd"));
        Task task2 = new Task(2L, "Task 2", "Description 2",TaskStatus.COMPLETED,new User(1L, "mcdur", "pwd"));

        when(taskRepository.findAll()).thenReturn(Arrays.asList(task1, task2));

        List<Task> tasks = taskService.getAllTasks();

        assertEquals(2, tasks.size());
        verify(taskRepository, times(1)).findAll();
    }
}
