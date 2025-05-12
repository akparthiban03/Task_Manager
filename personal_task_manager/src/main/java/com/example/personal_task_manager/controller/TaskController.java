package com.example.personal_task_manager.controller;

import com.example.personal_task_manager.model.Task;
import com.example.personal_task_manager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

    @Autowired
    private TaskService service;

    @GetMapping
    public List<Task> getTasks() {
        return service.getAllTasks();
    }

    @PostMapping
    public Task addTask(@RequestBody Task task) {
        return service.addTask(task);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        return service.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        service.deleteTask(id);
    }

    // 👇 You must put this inside the class
    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return service.getTaskById(id);
    }
}
