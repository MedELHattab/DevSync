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

        <table class="table-auto w-full border-collapse border border-gray-300 rounded-lg overflow-hidden">
            <thead>
            <tr class="bg-gray-100 text-left">
                <th class="border border-gray-300 px-4 py-2 bg-blue-100">Request ID</th>
                <th class="border border-gray-300 px-4 py-2">Task</th>
                <th class="border border-gray-300 px-4 py-2">Creator</th>
                <th class="border border-gray-300 px-4 py-2">Assignee</th>
                <th class="border border-gray-300 px-4 py-2">Created At</th>
                <th class="border border-gray-300 px-4 py-2">Actions</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<Request> requests = (List<Request>) request.getAttribute("requests");
                if (requests != null) {
                    for (Request reqItem : requests) {
            %>
            <tr class="bg-white">
                <td class="border border-gray-300 px-4 py-2 bg-blue-100 rounded-l-lg"><%= reqItem.getId() %></td>
                <td class="border border-gray-300 px-4 py-2"><%= reqItem.getTask().getTitle() %></td>
                <td class="border border-gray-300 px-4 py-2"><%= reqItem.getCreator().getUsername() %></td>
                <td class="border border-gray-300 px-4 py-2"><%= reqItem.getAssignee().getUsername() %></td>
                <td class="border border-gray-300 px-4 py-2"><%= reqItem.getCreatedAt() %></td>
                <td class="border border-gray-300 px-4 py-2 rounded-r-lg">
                    <!-- Approve Button -->
                    <form action="requests?action=approve" method="POST" style="display:inline;">
                        <input type="hidden" name="requestId" value="<%= reqItem.getId() %>" />
                        <button type="submit" class="bg-green-500 text-white px-2 py-1 rounded hover:bg-green-600">Approve</button>
                    </form>

                    <!-- Reject Button -->
                    <form action="requests?action=reject" method="POST" style="display:inline;">
                        <input type="hidden" name="requestId" value="<%= reqItem.getId() %>" />
                        <button type="submit" class="bg-red-500 text-white px-2 py-1 rounded hover:bg-red-600">Reject</button>
                    </form>
                </td>
            </tr>
            <%
                    }
                }else{
            %>
            <h1>test</h1>
            <%}%>
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
