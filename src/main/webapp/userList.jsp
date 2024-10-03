<%@ page import="com.model.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h2 class="text-lg font-semibold mb-4">User List</h2>

<table class="table-auto w-full border-collapse border border-gray-300">
  <thead>
  <tr class="bg-gray-100 text-left">
    <th class="border border-gray-300 px-4 py-2">ID</th>
    <th class="border border-gray-300 px-4 py-2">Username</th>
    <th class="border border-gray-300 px-4 py-2">First Name</th>
    <th class="border border-gray-300 px-4 py-2">Last Name</th>
    <th class="border border-gray-300 px-4 py-2">Email</th>
    <th class="border border-gray-300 px-4 py-2">Actions</th>
  </tr>
  </thead>
  <tbody>
  <%
    // Assuming 'users' is a List of User objects passed to the JSP
    List<User> users = (List<User>) request.getAttribute("users");
    if (users != null) {
      for (User user : users) {
  %>
  <tr class="bg-white">
    <td class="border border-gray-300 px-4 py-2"><%= user.getId() %></td>
    <td class="border border-gray-300 px-4 py-2"><%= user.getUsername() %></td>
    <td class="border border-gray-300 px-4 py-2"><%= user.getFirstName() %></td>
    <td class="border border-gray-300 px-4 py-2"><%= user.getLastName() %></td>
    <td class="border border-gray-300 px-4 py-2"><%= user.getEmail() %></td>
    <td class="border border-gray-300 px-4 py-2">
      <a href="editUser?id=<%= user.getId() %>" class="text-blue-500 hover:underline">Edit</a> |
      <a href="deleteUser?id=<%= user.getId() %>" class="text-red-500 hover:underline" onclick="return confirm('Are you sure you want to delete this user?');">Delete</a>
    </td>
  </tr>
  <%
      }
    }
  %>
  </tbody>
</table>
