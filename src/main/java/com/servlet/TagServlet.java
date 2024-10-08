package com.servlet;

import com.model.Tag;
import com.service.TagService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/tags") // URL pattern for the servlet
public class TagServlet extends HttpServlet {

    private TagService tagService = new TagService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetch all tags from the service
        List<Tag> tags = tagService.getAllTags();

        // Set tags as request attribute and forward to a JSP page
        request.setAttribute("tags", tags);
        RequestDispatcher dispatcher = request.getRequestDispatcher("tagList.jsp"); // Adjust the JSP name accordingly
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("deleteTag".equals(action)) {
            Long id = Long.parseLong(request.getParameter("id"));
            tagService.deleteTag(id);
            response.sendRedirect("tags");
            return;
        } else if ("editTag".equals(action)) {
            Long id = Long.parseLong(request.getParameter("id"));
            String name = request.getParameter("name");

            // Get the existing tag and update its fields
            Tag tag = tagService.getTagById(id);
            if (tag != null) {
                tag.setName(name);
                tagService.createTag(tag); // Save updated tag
            }
            response.sendRedirect("tags");
            return;
        }

        // Handle tag creation
        String name = request.getParameter("name");
        Tag newTag = new Tag();
        newTag.setName(name);
        tagService.createTag(newTag);

        response.sendRedirect("tags");
    }
}
