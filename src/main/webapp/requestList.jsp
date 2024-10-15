<%@ page import="com.model.Request" %>
<%@ page import="com.model.Task" %>
<%@ page import="com.model.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <title>Requests List</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://cdn.jsdelivr.net/npm/flowbite@2.5.2/dist/flowbite.min.css" rel="stylesheet" />
</head>

<body class="flex flex-col min-h-screen">
<main class="flex-grow p-4 mb-4">

    <!-- Include header -->
    <jsp:include page="header.jsp" />

    <div class="p-16">
        <h2 class="text-lg font-semibold mb-4">Request List</h2>

        <!-- Display messages based on status -->
        <%
            String status = request.getParameter("status");
            if ("success".equals(status)) {
        %>
        <div class="bg-green-100 border-l-4 border-green-500 text-green-700 p-4" role="alert">
            <p>Request successfully approved and assignee changed!</p>
        </div>
        <%
        } else if ("not_enough_tokens".equals(status)) {
        %>
        <div class="bg-red-100 border-l-4 border-red-500 text-red-700 p-4" role="alert">
            <p>The selected user does not have enough tokens to be assigned!</p>
        </div>
        <%
            }
        %>

        <table class="table-auto w-full border-collapse border border-gray-300 rounded-lg overflow-hidden">
            <thead>
            <tr class="bg-gray-100 text-left">
                <th class="border border-gray-300 px-4 py-2 bg-blue-100">Request ID</th>
                <th class="border border-gray-300 px-4 py-2">Task</th>
                <th class="border border-gray-300 px-4 py-2">Creator</th>
                <th class="border border-gray-300 px-4 py-2">Assignee</th>
                <th class="border border-gray-300 px-4 py-2">Created At</th>
                <th class="border border-gray-300 px-4 py-2">New Assignee</th>
                <th class="border border-gray-300 px-4 py-2">Actions</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<Request> requests = (List<Request>) request.getAttribute("requests");
                List<User> users = (List<User>) request.getAttribute("users");
                if (requests != null) {
                    for (Request reqItem : requests) {
            %>
            <tr class="bg-white">
                <td class="border border-gray-300 px-4 py-2 bg-blue-100 rounded-l-lg"><%= reqItem.getId() %></td>
                <td class="border border-gray-300 px-4 py-2"><%= reqItem.getTask().getTitle() %></td>
                <td class="border border-gray-300 px-4 py-2"><%= reqItem.getCreator().getUsername() %></td>
                <td class="border border-gray-300 px-4 py-2"><%= reqItem.getAssignee().getUsername() %></td>
                <td class="border border-gray-300 px-4 py-2"><%= reqItem.getCreatedAt() %></td>
                <td class="border border-gray-300 px-4 py-2">
                    <!-- Approve form -->
                    <form action="requests" method="POST">
                        <input type="hidden" name="action" value="approve" />
                        <input type="hidden" name="requestId" value="<%= reqItem.getId() %>" />
                        <input type="hidden" name="taskID" value="<%= reqItem.getTask().getId() %>" />
                        <select name="newAssignee" class="border-gray-300 rounded">
                            <option value="">Select Assignee</option>
                            <%
                                for (User user : users) {
                                    // Only add users who are not currently the assignee
                                    if (!user.equals(reqItem.getAssignee())) {
                            %>
                            <option value="<%= user.getId() %>"><%= user.getUsername() %></option>
                            <%
                                    }
                                }
                            %>

                        </select>
                        <button type="submit" class="bg-green-500 text-white px-2 py-1 rounded hover:bg-green-600">
                            Approve
                        </button>
                    </form>
                </td>

                <td class="border border-gray-300 px-4 py-2 rounded-r-lg">
                    <!-- Approve Button handles both approval and assignee change -->



                    <!-- Reject Button -->
                    <form action="requests" method="POST" style="display:inline;">
                        <input type="hidden" name="action" value="reject" />
                        <input type="hidden" name="requestId" value="<%= reqItem.getId() %>" />
                        <button type="submit" class="bg-red-500 text-white px-2 py-1 rounded hover:bg-red-600">Reject</button>
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

<!-- Footer -->
<div class="bg-gray-800 text-white py-4">
    <jsp:include page="footer.jsp" />
</div>
</body>

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
