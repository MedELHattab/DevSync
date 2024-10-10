package com.service;

import com.model.Tag;
import com.model.Task;
import com.repository.TaskRepositoryImpl;

import java.util.List;
import java.util.Set;

public class TaskService {

    private TaskRepositoryImpl taskRepository = new TaskRepositoryImpl(); // Corrected variable name

    public Task getTaskById(Long id) {
        return taskRepository.getTaskById(id);
    }

    public List<Task> getAllTasks() {
        return taskRepository.getAllTasks();
    }

    public void createTask(Task task, List<Tag> tags) {
        taskRepository.createTask(task, tags);
    }

    public void updateTask(Task task, List<Tag> tags) {
        taskRepository.updateTask(task, tags);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteTask(id);
    }

}
