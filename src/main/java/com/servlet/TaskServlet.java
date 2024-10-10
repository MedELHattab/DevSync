package com.servlet;

import com.model.Task;
import com.model.Tag;
import com.model.User;
import com.service.TaskService;
import com.service.TagService; // Import the TagService
import com.service.UserService; // Import the UserService
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/tasks")
public class TaskServlet extends HttpServlet {

    private TaskService taskService;
    private TagService tagService;
    private UserService userService; // Declare userService

    @Override
    public void init() throws ServletException {
        // Initialize services here
        taskService = new TaskService();
        tagService = new TagService();
        userService = new UserService(); // Initialize the userService
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetch all tasks
        List<Task> tasks = taskService.getAllTasks();

        // Fetch all tags
        List<Tag> tags = tagService.getAllTags();

        // Fetch all users
        List<User> users = userService.listUsers();

        // Set tasks, tags, and users as request attributes
        request.setAttribute("tags", tags);
        request.setAttribute("users", users);

        // Determine if the request is for task creation, editing, or listing
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            // Forward to the create task page
            RequestDispatcher dispatcher = request.getRequestDispatcher("createTask.jsp");
            dispatcher.forward(request, response);

        } else if ("edit".equals(action)) {
            // Get the task ID from the request
            String taskIdStr = request.getParameter("taskId");
            if (taskIdStr != null && !taskIdStr.isEmpty()) {
                try {
                    // Parse taskId from request
                    Long taskId = Long.parseLong(taskIdStr);

                    // Fetch the task by its ID
                    Task taskToEdit = taskService.getTaskById(taskId);

                    if (taskToEdit != null) {
                        // Fetch related tags and ensure they're initialized (if necessary)
                        taskToEdit.getTags().size();  // This forces the tags to be loaded to avoid LazyInitializationException

                        // Set the task to edit as a request attribute
                        request.setAttribute("task", taskToEdit);

                        // Forward to the edit task page
                        RequestDispatcher dispatcher = request.getRequestDispatcher("editTask.jsp");
                        dispatcher.forward(request, response);
                    } else {
                        // Handle case where task is not found
                        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Task not found.");
                    }
                } catch (NumberFormatException e) {
                    // Handle invalid task ID format
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid task ID.");
                }
            } else {
                // Handle missing task ID parameter
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Task ID is required for editing.");
            }

        } else {
            // Default action: display the task list
            request.setAttribute("tasks", tasks);
            RequestDispatcher dispatcher = request.getRequestDispatcher("taskList.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            Long id = Long.parseLong(request.getParameter("id"));
            taskService.deleteTask(id);
            response.sendRedirect("tasks");
            return;
        }else if ("edit".equals(action)) {
            // Retrieve the task ID from the request
            Long id = Long.parseLong(request.getParameter("id"));
            Task task = taskService.getTaskById(id);

            if (task != null) {
                // Update basic fields
                task.setTitle(request.getParameter("title"));
                task.setDescription(request.getParameter("description"));

                // Update due date
                String dueDateStr = request.getParameter("dueDate");
                if (dueDateStr != null && !dueDateStr.isEmpty()) {
                    LocalDate dueDate = LocalDate.parse(dueDateStr);
                    task.setDueDate(dueDate);
                }

                // Update status using a dropdown with limited options
                String status = request.getParameter("status");
                if (status != null && (status.equals("Pending") || status.equals("Completed") || status.equals("Overdue"))) {
                    task.setStatus(status);
                } else {
                    // Handle invalid status case
                    System.err.println("Invalid status provided: " + status);
                }

                // Set Creator
                try {
                    Long creatorId = Long.parseLong(request.getParameter("creator"));
                    task.setCreator(userService.readUser(creatorId)); // Assuming readUser fetches the user by ID
                } catch (NumberFormatException e) {
                    System.err.println("Invalid creator ID: " + request.getParameter("creator"));
                }

                // Set Assignee
                try {
                    Long assigneeId = Long.parseLong(request.getParameter("assignee"));
                    task.setAssignee(userService.readUser(assigneeId)); // Assuming readUser fetches the user by ID
                } catch (NumberFormatException e) {
                    System.err.println("Invalid assignee ID: " + request.getParameter("assignee"));
                }

                // Update Tags
                String[] tagIds = request.getParameterValues("tags[]");
                List<Tag> tags = new ArrayList<>();
                if (tagIds != null) {
                    for (String tagIdStr : tagIds) {
                        try {
                            Long tagId = Long.parseLong(tagIdStr);
                            Tag tag = new Tag();
                            tag.setId(tagId); // Create a tag instance using just the ID
                            tags.add(tag);
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid tag ID: " + tagIdStr);
                        }
                    }
                }

                // Update the task with the new tags
                taskService.updateTask(task, tags); // Make sure to update the service method to accept tags
            }

            // Redirect back to the task list
            response.sendRedirect("tasks");
            return;
        }




        // Handle task creation
        Task newTask = new Task();
        newTask.setTitle(request.getParameter("title"));
        newTask.setDescription(request.getParameter("description"));
        newTask.setDueDate(LocalDate.parse(request.getParameter("dueDate")));

        // Set creator and assignee
        Long creatorId = Long.parseLong(request.getParameter("creator"));
        Long assigneeId = Long.parseLong(request.getParameter("assignee"));
        newTask.setCreator(userService.readUser(creatorId));
        newTask.setAssignee(userService.readUser(assigneeId));

        // Get tag IDs from the request
        String[] tagIds = request.getParameterValues("tags[]");
        List<Tag> tags = new ArrayList<>();
        if (tagIds != null) {
            for (String tagIdStr : tagIds) {
                Long tagId = Long.parseLong(tagIdStr);
                Tag tag = new Tag();
                tag.setId(tagId); // Assuming you have a way to create a tag instance with just an ID
                tags.add(tag);
            }
        }

        // Call service to create the task with tags
        taskService.createTask(newTask, tags);

        response.sendRedirect("tasks");
    }

}
