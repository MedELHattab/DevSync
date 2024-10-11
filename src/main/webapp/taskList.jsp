<%@ page import="com.model.Task" %>
<%@ page import="com.model.User" %>
<%@ page import="com.model.Tag" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <title>Task List</title>
    <title>Create Task</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://cdn.jsdelivr.net/npm/flowbite@2.5.2/dist/flowbite.min.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
    <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
</head>

<body class="flex flex-col min-h-screen"> <!-- Make the body a flex container -->
<main class="flex-grow p-4 mb-4">

<!-- Include header.jsp here -->
<jsp:include page="header.jsp" />

<div class="p-16">
    <h2 class="text-lg font-semibold mb-4">Task List</h2>

    <!-- Button to create a new task -->
   <div class="mb-4">
    <a href="tasks?action=create" class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">Add Task</a>
</div>

    <table class="table-auto w-full border-collapse border border-gray-300 rounded-lg overflow-hidden">
        <thead>
        <tr class="bg-gray-100 text-left">
            <th class="border border-gray-300 px-4 py-2 bg-blue-100">ID</th>
            <th class="border border-gray-300 px-4 py-2">Title</th>
            <th class="border border-gray-300 px-4 py-2">Description</th>
            <th class="border border-gray-300 px-4 py-2">Due Date</th>
            <th class="border border-gray-300 px-4 py-2">Status</th>
            <th class="border border-gray-300 px-4 py-2">Creator</th>
            <th class="border border-gray-300 px-4 py-2">Assignee</th>
            <th class="border border-gray-300 px-4 py-2">Tags</th>
            <th class="border border-gray-300 px-4 py-2">Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Task> tasks = (List<Task>) request.getAttribute("tasks");
            if (tasks != null) {
                for (Task task : tasks) {
        %>
        <tr class="bg-white">
            <td class="border border-gray-300 px-4 py-2 bg-blue-100 rounded-l-lg"><%= task.getId() %></td>
            <td class="border border-gray-300 px-4 py-2"><%= task.getTitle() %></td>
            <td class="border border-gray-300 px-4 py-2"><%= task.getDescription() %></td>
            <td class="border border-gray-300 px-4 py-2"><%= task.getDueDate() %></td>
            <td class="border border-gray-300 px-4 py-2"><%= task.getStatus() %></td>
            <td class="border border-gray-300 px-4 py-2"><%= task.getCreator() != null ? task.getCreator().getUsername()  : "" %></td>
            <td class="border border-gray-300 px-4 py-2"><%= task.getAssignee() != null ?  task.getAssignee().getUsername() : "" %></td>
            <td class="border border-gray-300 px-4 py-2">
                <%
                    for(Tag tag : task.getTags()) {
                %>
                <%= tag.getName() %>
                <%
                    }
                %>
            </td>

            <td class="border border-gray-300 px-4 py-2 rounded-r-lg">
                <!-- Edit Button -->
                <a href="tasks?action=edit&taskId=<%= task.getId() %>" class="text-blue-500 hover:underline">Edit</a>

                <!-- Delete Form -->
                <form action="tasks" method="POST" style="display:inline;">
                    <input type="hidden" name="action" value="delete" />
                    <input type="hidden" name="id" value="<%= task.getId() %>" />
                    <button type="submit" class="text-red-500 hover:underline" onclick="return confirm('Are you sure you want to delete this task?');">Delete</button>
                </form>
            </td>
        </tr>
        <%
                }
            }
        %>
        </tbody>
    </table>
</div>
</main>

<!-- Fixed Footer -->
<div class="bg-gray-800 text-white py-4">
    <jsp:include page="footer.jsp" />
</div>
</body>

<!-- JavaScript to handle modals and alternating row colors -->
<script>

    document.addEventListener('DOMContentLoaded', function () {
        const tableRows = document.querySelectorAll('tbody tr');
        tableRows.forEach((row, index) => {
            if (index % 2 !== 0) {
                row.classList.add('bg-gray-200');
            }
        });
    });

</script>

