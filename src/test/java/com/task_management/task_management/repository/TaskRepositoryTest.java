package com.task_management.task_management.repository;

import com.task_management.task_management.domain.Task;
import com.task_management.task_management.infrastructure.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void testSaveAndFindTask() {
        Task task = new Task(null, "Test Task", "Testing persistence layer" , null ,null);
        Task savedTask = taskRepository.save(task);

        assertNotNull(savedTask.getId());
        assertEquals("Test Task", savedTask.getTitle());
    }
}
