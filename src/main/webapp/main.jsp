<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp" />

<html>
<head>
  <title>Main Page</title>
  <script src="https://cdn.tailwindcss.com"></script>
  <link href="https://cdn.jsdelivr.net/npm/flowbite@2.5.2/dist/flowbite.min.css" rel="stylesheet" />
</head>
<body>
<main class="p-4"> <!-- Added padding to the main section -->

  <div class="mb-6"> <!-- Margin bottom to separate header and user list -->
    <h1 class="text-2xl font-bold mb-4">User Management</h1> <!-- Added a title for better clarity -->
    <a href="createUser.jsp" class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">Create User</a> <!-- Button to create user -->
  </div>

  <jsp:include page="userList.jsp" />

</main>

<jsp:include page="footer.jsp" />
<script src="https://cdn.jsdelivr.net/npm/flowbite@2.5.2/dist/flowbite.min.js"></script>
</body>
</html>
