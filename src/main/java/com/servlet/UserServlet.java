package com.servlet;

import com.model.User;
import com.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.util.List;

@WebServlet("/user") // Use a single URL pattern for the servlet
public class UserServlet extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetch users from the service
        List<User> users = userService.listUsers();

        // Set users as request attribute and forward to main.jsp
        request.setAttribute("users", users);
        RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("deleteUser".equals(action)) {
            Long id = Long.parseLong(request.getParameter("id"));
            userService.deleteUser(id); // Call the service to delete the user
            response.sendRedirect("user"); // Redirect to refresh the user list after deletion
            return;
        }else if ("editUser".equals(action)) {
            // Handle user update
            Long id = Long.parseLong(request.getParameter("id"));
            String username = request.getParameter("username");
            String firstName = request.getParameter("first_name");
            String lastName = request.getParameter("last_name");
            String email = request.getParameter("email");

            // Get the existing user and update its fields
            User user = userService.readUser(id);
            user.setUsername(username);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);

            // Save the updated user
            userService.updateUser(user);

            // Redirect to the user list
            response.sendRedirect("user");
            return;
        }


        // Get form data
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String email = request.getParameter("email");

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        // Create the user entity with hashed password
        User user = new User(username, hashedPassword, first_name, last_name, email);

        // Use the UserService to persist the user
        userService.createUser(user);

        // Redirect to the user list page after creation
        response.sendRedirect("user"); // Redirects to the same servlet's doGet method
    }
}
