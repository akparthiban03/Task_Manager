package com.example.personal_task_manager.repository;

import com.example.personal_task_manager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> { // ✅ correct model.Task, not Spring Task
}
