package com.servlet;

import com.model.User;
import com.model.manager_status;
import com.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.util.List;

@WebServlet( name = "user" , urlPatterns = "/user") // Use a single URL pattern for the servlet
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
            userService.deleteUser(id);
            response.sendRedirect("user");
            return;
        } else if ("editUser".equals(action)) {
            // Handle user update
            Long id = Long.parseLong(request.getParameter("id"));
            String username = request.getParameter("username");
            String firstName = request.getParameter("first_name");
            String lastName = request.getParameter("last_name");
            String email = request.getParameter("email");
            manager_status managerStatus = manager_status.valueOf(request.getParameter("manager").toLowerCase());

            // Get the existing user and update its fields
            User user = userService.readUser(id);
            user.setUsername(username);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setManager(managerStatus);  // Update manager status
//            System.out.println("Manager Status: " + managerStatus);


            userService.updateUser(user);
            response.sendRedirect("user");
            return;
        }

        // Handle user creation
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String email = request.getParameter("email");
        manager_status managerStatus = manager_status.valueOf(request.getParameter("manager")); // Get manager status from form

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        // Create user entity with manager_status
        User user = new User(username, hashedPassword, first_name, last_name, email, managerStatus);
        userService.createUser(user);

        response.sendRedirect("user");
    }
}
