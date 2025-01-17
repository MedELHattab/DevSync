package com.service;

import com.model.Tag;
import com.model.Task;
import com.model.User;
import com.model.UserTokens;
import com.repository.TaskRepositoryImpl;
import com.repository.UserRepositoryImpl;
import com.repository.userTokensRepositoryImpl;

import java.util.List;
import java.util.Set;

public class TaskService {

    private TaskRepositoryImpl taskRepository = new TaskRepositoryImpl(); // Corrected variable name
    private userTokensRepositoryImpl userTokensRepository = new userTokensRepositoryImpl();
    private UserRepositoryImpl userRepository = new UserRepositoryImpl();

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
        // First, retrieve the task to get the assignee information before deleting
        Task taskToDelete = taskRepository.getTaskById(id);

        if (taskToDelete != null) {
            User user = taskToDelete.getAssignee(); // Assuming Task entity has assigneeId field
            User creator = taskToDelete.getCreator();

            if (user != creator) {
                Long userId = user.getId();

                // Delete the task from the repository
                taskRepository.deleteTask(id);

                // Now update the user's tokens based on the assigneeId (which corresponds to user_id)
                if (userId != null) {
                    UserTokens userTokens = userTokensRepository.findByUserId(userId); // Assuming you have a method to fetch by user ID

                    if (userTokens != null) {
                        userTokensRepository.setMonthlyTokensToZero();
                    }
                }
            }else {
                taskRepository.deleteTask(id);
            }


        }
    }

    public void updateTaskAssignee(Long taskId, Long newAssigneeId) {
        // Get the task by its ID
        Task task = taskRepository.getTaskById(taskId);
        if (task == null) {
            throw new IllegalArgumentException("Task not found with id: " + taskId);
        }

        // Get the new assignee by their ID
        User newAssignee = userRepository.readUser(newAssigneeId);
        if (newAssignee == null) {
            throw new IllegalArgumentException("User not found with id: " + newAssigneeId);
        }

        // Set the new assignee
        task.setAssignee(newAssignee);

        // Update the task in the database
        taskRepository.updateAssignee(task);
    }

}
