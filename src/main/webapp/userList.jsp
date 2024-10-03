<%--
  Created by IntelliJ IDEA.
  User: Youcode
  Date: 03/10/2024
  Time: 09:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
  <c:forEach var="user" items="${users}">
    <tr class="bg-white">
      <td class="border border-gray-300 px-4 py-2">${user.id}</td>
      <td class="border border-gray-300 px-4 py-2">${user.username}</td>
      <td class="border border-gray-300 px-4 py-2">${user.firstName}</td>
      <td class="border border-gray-300 px-4 py-2">${user.lastName}</td>
      <td class="border border-gray-300 px-4 py-2">${user.email}</td>
      <td class="border border-gray-300 px-4 py-2">
        <a href="editUser?id=${user.id}" class="text-blue-500 hover:underline">Edit</a> |
        <a href="deleteUser?id=${user.id}" class="text-red-500 hover:underline" onclick="return confirm('Are you sure you want to delete this user?');">Delete</a>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>

