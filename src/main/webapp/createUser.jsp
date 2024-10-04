<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="header.jsp" />

<!DOCTYPE html>
<html>
<head>
  <title>Create User</title>
  <script src="https://cdn.tailwindcss.com"></script>
  <link href="https://cdn.jsdelivr.net/npm/flowbite@2.5.2/dist/flowbite.min.css" rel="stylesheet" />
</head>
<body>
<main class="p-4"> <!-- Added padding to the main section -->
  <h1 class="text-2xl font-bold mb-4">Create a New User</h1>

  <form action="${pageContext.request.contextPath}/user" method="post" class="bg-white p-6 rounded shadow-md">
    <div class="mb-4">
      <label for="username" class="block text-sm font-medium mb-2">Username:</label>
      <input type="text" id="username" name="username" required class="border rounded-lg w-full p-2">
    </div>

    <div class="mb-4">
      <label for="password" class="block text-sm font-medium mb-2">Password:</label>
      <input type="password" id="password" name="password" required class="border rounded-lg w-full p-2">
    </div>

    <div class="mb-4">
      <label for="first_name" class="block text-sm font-medium mb-2">First Name:</label>
      <input type="text" id="first_name" name="first_name" required class="border rounded-lg w-full p-2">
    </div>

    <div class="mb-4">
      <label for="last_name" class="block text-sm font-medium mb-2">Last Name:</label>
      <input type="text" id="last_name" name="last_name" required class="border rounded-lg w-full p-2">
    </div>

    <div class="mb-4">
      <label for="email" class="block text-sm font-medium mb-2">Email:</label>
      <input type="email" id="email" name="email" required class="border rounded-lg w-full p-2">
    </div>

    <div class="mb-4">
      <label for="manager" class="block text-sm font-medium mb-2">Manager Status:</label>
      <select id="manager" name="manager" required class="border rounded-lg w-full p-2">
        <option value="user">User</option>
        <option value="manager">Manager</option>
      </select>
    </div>

    <input type="submit" value="Create User" class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">
  </form>
</main>

<jsp:include page="footer.jsp" />
<script src="https://cdn.jsdelivr.net/npm/flowbite@2.5.2/dist/flowbite.min.js"></script>
</body>
</html>
