<%@ page import="com.model.User" %>
<%@ page import="com.model.Tag" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="header.jsp" />

<!DOCTYPE html>
<html>
<head>
    <title>Create Task</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://cdn.jsdelivr.net/npm/flowbite@2.5.2/dist/flowbite.min.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
    <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
</head>
<body class="flex flex-col min-h-screen"> <!-- Make the body a flex container -->
<main class="flex-grow p-4 mb-4"> <!-- Allow main to grow and take available space -->
    <h1 class="text-2xl font-bold mb-4">Create a New Task</h1>

    <% String errorMessage = (String) request.getAttribute("error"); %>
    <% if (errorMessage != null) { %>
    <div class="bg-red-500 text-white p-2 mb-4 rounded">
        <%= errorMessage %>
    </div>
    <% } %>

    <form action="${pageContext.request.contextPath}/tasks" method="post" class="bg-white p-6 rounded shadow-md">
        <!-- Task Title -->
        <div class="mb-4">
            <label for="title" class="block text-sm font-medium mb-2">Task Title:</label>
            <input type="text" id="title" name="title" required class="border rounded-lg w-full p-2">
        </div>

        <!-- Task Description -->
        <div class="mb-4">
            <label for="description" class="block text-sm font-medium mb-2">Task Description:</label>
            <textarea id="description" name="description" required class="border rounded-lg w-full p-2"></textarea>
        </div>

        <!-- Due Date -->
        <div class="mb-4">
            <label for="dueDate" class="block text-sm font-medium mb-2">Due Date:</label>
            <input type="date" id="dueDate" name="dueDate" required class="border rounded-lg w-full p-2">
        </div>

        <!-- Creator (Select User) -->
        <div class="mb-4">
            <label for="creator" class="block text-sm font-medium mb-2">Creator:</label>
            <select id="creator" name="creator" required class="border rounded-lg w-full p-2">
                <%
                    List<User> users = (List<User>) request.getAttribute("users");
                    for (User user : users) {
                %>
                <option value="<%= user.getId() %>"><%= user.getUsername() %></option>
                <%
                    }
                %>
            </select>
        </div>

        <!-- Assignee (Select User) -->
        <div class="mb-4">
            <label for="assignee" class="block text-sm font-medium mb-2">Assignee:</label>
            <select id="assignee" name="assignee" required class="border rounded-lg w-full p-2">
                <%
                    for (User user : users) {
                %>
                <option value="<%= user.getId() %>"><%= user.getUsername() %></option>
                <%
                    }
                %>
            </select>
        </div>

        <!-- Tags (Multi-select) -->
        <div class="mb-4">
            <label for="tags" class="block text-sm font-medium mb-2">Tags:</label>
            <select id="tags" name="tags[]" multiple class="js-example-basic-multiple border rounded-lg w-full p-2">
                <%
                    List<Tag> tags = (List<Tag>) request.getAttribute("tags");
                    for (Tag tag : tags) {
                %>
                <option value="<%= tag.getId() %>"><%= tag.getName() %></option>
                <%
                    }
                %>
            </select>
        </div>

        <!-- Submit Button -->
        <input type="submit" value="Create Task" class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">
    </form>
</main>

<!-- Fixed Footer -->
<div class="bg-gray-800 text-white py-4"> <!-- Removed fixed positioning -->
    <jsp:include page="footer.jsp" />
</div>

<script>
    $(document).ready(function() {
        $('#tags').select2(); // Apply select2 to the multi-select dropdown
    });
</script>
<script src="https://cdn.jsdelivr.net/npm/flowbite@2.5.2/dist/flowbite.min.js"></script>

</body>
</html>
