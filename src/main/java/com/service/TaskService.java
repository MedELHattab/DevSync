package com.service;

import com.model.Task;
import com.repository.TaskRepository;

import java.util.List;

public class TaskService {

    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {
        return taskRepository.createTask(task);
    }

    public Task getTaskById(Long id) {
        return taskRepository.getTaskById(id);
    }

    public List<Task> getAllTasks() {
        return taskRepository.getAllTasks();
    }

    public Task updateTask(Task task) {
        return taskRepository.updateTask(task);
    }

    public void deleteTask(Long id) {
        Task task = taskRepository.getTaskById(id);
        if (task != null) {
            taskRepository.deleteTask(task);
        }
    }
}
