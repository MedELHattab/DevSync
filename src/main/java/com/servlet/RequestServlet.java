package com.servlet;

import com.model.Request;
import com.model.Task;
import com.model.User;
import com.service.RequestService;
import com.service.TagService;
import com.service.TaskService;
import com.service.UserService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/requests")
public class RequestServlet extends HttpServlet {

    private RequestService requestService = new RequestService();
    private TaskService taskService;
    private UserService userService; // Declare userService

    @Override
    public void init() throws ServletException {
        // Initialize services here
        requestService = new RequestService();
        taskService = new TaskService();

        userService = new UserService(); // Initialize the userService
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Request> requests = requestService.getAllRequests();

            List<Task> tasks = taskService.getAllTasks();

            // Fetch all users
            List<User> users = userService.listUsers();

            // Set the list of requests as a request attribute
            request.setAttribute("requests", requests);

            RequestDispatcher dispatcher = request.getRequestDispatcher("requestList.jsp"); // Adjust the JSP name accordingly
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to retrieve requests.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Get parameters from the form
            Long taskId = Long.parseLong(request.getParameter("taskId"));
            Long creatorId = Long.parseLong(request.getParameter("creatorId"));
            Long assigneeId = Long.parseLong(request.getParameter("assigneeId"));

            // Fetch the Task and User objects
            Task task = taskService.getTaskById(taskId);  // Fetch the task from the TaskService
            User creator = userService.readUser(creatorId); // Fetch the creator from the UserService
            User assignee = userService.readUser(assigneeId); // Fetch the assignee from the UserService

            // Create a new Request object
            Request newRequest = new Request();
            newRequest.setTask(task);
            newRequest.setCreator(creator);
            newRequest.setAssignee(assignee);
            newRequest.setCreatedAt(LocalDateTime.now());
            newRequest.setUpdatedAt(LocalDateTime.now());

            // Use RequestService to create and save the request
            requestService.createRequest(newRequest);

            // Redirect to the task list page or show a success message
            response.sendRedirect("requests");

        } catch (Exception e) {
            // Handle exceptions or invalid parameters
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Error creating the request");
        }
    }
}
