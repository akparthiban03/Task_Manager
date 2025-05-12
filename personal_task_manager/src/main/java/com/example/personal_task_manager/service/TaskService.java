package com.example.personal_task_manager.service;

import com.example.personal_task_manager.model.Task;
import com.example.personal_task_manager.repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepo repository;

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Task addTask(Task task) {
        return repository.save(task);
    }

    public void deleteTask(Long id) {
        repository.deleteById(id);
    }
    public Task getTaskById(Long id) {
        return repository.findById(id).orElseThrow();
    }


    public Task updateTask(Long id, Task updatedTask) {
        Task task = repository.findById(id).orElseThrow();
        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setDueDate(updatedTask.getDueDate());
        task.setPriority(updatedTask.getPriority());
        task.setCompleted(updatedTask.isCompleted());
        return repository.save(task);
    }
}
