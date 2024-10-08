<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="header.jsp" />

<!DOCTYPE html>
<html>
<head>
    <title>Create Tag</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://cdn.jsdelivr.net/npm/flowbite@2.5.2/dist/flowbite.min.css" rel="stylesheet" />
</head>
<body>
<main class="p-4"> <!-- Added padding to the main section -->
    <h1 class="text-2xl font-bold mb-4">Create a New Tag</h1>

    <form action="${pageContext.request.contextPath}/tags" method="post" class="bg-white p-6 rounded shadow-md">
        <div class="mb-4">
            <label for="name" class="block text-sm font-medium mb-2">name:</label>
            <input type="text" id="name" name="name" required class="border rounded-lg w-full p-2">
        </div>

        <input type="submit" value="Create Tag" class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">
    </form>
</main>
<!-- Fixed Footer -->
<div class="fixed bottom-0 left-0 w-full bg-gray-800 text-white py-4">
    <jsp:include page="footer.jsp" />
</div>

<script src="https://cdn.jsdelivr.net/npm/flowbite@2.5.2/dist/flowbite.min.js"></script>
</body>
</html>
