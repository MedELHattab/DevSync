package com.repository;

import com.model.Tag;
import com.model.Task;

import java.util.List;

public interface TaskRepository {
    Task getTaskById(Long id);
    List<Task> getAllTasks();
    void createTask(Task task, List<Tag> tags);
    void updateTask(Task task, List<Tag> tags);
    void deleteTask(Long id);
}
