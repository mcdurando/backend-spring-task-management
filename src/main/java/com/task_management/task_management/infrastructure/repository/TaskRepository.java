package com.task_management.task_management.infrastructure.repository;

import com.task_management.task_management.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
