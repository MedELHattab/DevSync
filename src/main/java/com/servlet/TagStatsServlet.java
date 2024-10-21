package com.servlet;

import com.service.StatService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

@WebServlet("/tag-stats")
public class TagStatsServlet extends HttpServlet {

    private final StatService statService = new StatService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the tag stats data from the service
        Map<String, Map<String, Double>> tagStats = statService.displayTagStats();

        // Set the data as a request attribute
        request.setAttribute("tagStats", tagStats);

        // Forward the request to the JSP page
        request.getRequestDispatcher("/tagStats.jsp").forward(request, response);
    }
}
