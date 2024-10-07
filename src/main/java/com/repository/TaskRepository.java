package com.repository;

import com.model.Task;
import java.util.List;

public interface TaskRepository {

    Task createTask(Task task);

    Task getTaskById(Long id);

    List<Task> getAllTasks();

    Task updateTask(Task task);

    void deleteTask(Task task);
}
