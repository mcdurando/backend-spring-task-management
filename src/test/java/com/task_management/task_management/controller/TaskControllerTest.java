package com.task_management.task_management.controller;


import com.task_management.task_management.application.service.TaskService;
import com.task_management.task_management.domain.Task;
import com.task_management.task_management.domain.TaskStatus;
import com.task_management.task_management.domain.User;
import com.task_management.task_management.web.TaskController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    @Test
    void testGetAllTasks() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();

        when(taskService.getAllTasks()).thenReturn(List.of(
                new Task(1L, "Task 1", "Description 1", TaskStatus.TODO, new User(1L, "mcdur", "pwd"))
        ));

        mockMvc.perform(get("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].title").value("Task 1"))
                .andExpect(jsonPath("$[0].assignedTo.username").value("mcdur"))  // Test the assigned user's username
                .andExpect(jsonPath("$[0].status").value("TODO"));  // Test the task status
    }
}
