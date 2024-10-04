<%@ page import="com.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.model.manager_status" %>
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
    <th class="border border-gray-300 px-4 py-2">Status</th>
    <th class="border border-gray-300 px-4 py-2">Actions</th>
  </tr>
  </thead>
  <tbody>
  <%
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
    <td class="border border-gray-300 px-4 py-2"><%= user.getManager() %></td>
    <td class="border border-gray-300 px-4 py-2">
      <!-- Button to trigger the modal -->
      <button class="text-blue-500 hover:underline" onclick="showModal('modal-<%= user.getId() %>')">Edit</button> |

      <form action="user" method="POST" style="display:inline;">
        <input type="hidden" name="action" value="deleteUser" />
        <input type="hidden" name="id" value="<%= user.getId() %>" />
        <button type="submit" class="text-red-500 hover:underline" onclick="return confirm('Are you sure you want to delete this user?');">Delete</button>
      </form>

      <!-- Modal for editing the user -->
      <div id="modal-<%= user.getId() %>" class="hidden fixed inset-0 z-50 flex items-center justify-center bg-gray-500 bg-opacity-50">
        <div class="bg-white p-6 rounded-lg w-1/2">
          <h2 class="text-xl font-semibold mb-4">Edit User</h2>
          <form action="user" method="POST">
            <input type="hidden" name="id" value="<%= user.getId() %>" />
            <input type="hidden" name="action" value="editUser" />

            <!-- Username Field -->
            <div class="mb-4">
              <label class="block text-sm font-medium mb-2" for="username">Username</label>
              <input type="text" name="username" id="username" value="<%= user.getUsername() %>" class="w-full px-4 py-2 border rounded-lg" required />
            </div>

            <!-- First Name Field -->
            <div class="mb-4">
              <label class="block text-sm font-medium mb-2" for="first_name">First Name</label>
              <input type="text" name="first_name" id="first_name" value="<%= user.getFirstName() %>" class="w-full px-4 py-2 border rounded-lg" required />
            </div>

            <!-- Last Name Field -->
            <div class="mb-4">
              <label class="block text-sm font-medium mb-2" for="last_name">Last Name</label>
              <input type="text" name="last_name" id="last_name" value="<%= user.getLastName() %>" class="w-full px-4 py-2 border rounded-lg" required />
            </div>

            <!-- Email Field -->
            <div class="mb-4">
              <label class="block text-sm font-medium mb-2" for="email">Email</label>
              <input type="email" name="email" id="email" value="<%= user.getEmail() %>" class="w-full px-4 py-2 border rounded-lg" required />
            </div>

            <!-- Manager Status Field -->
            <div class="mb-4">
              <label class="block text-sm font-medium mb-2" for="manager">Manager Status</label>
              <select name="manager" id="manager" class="w-full px-4 py-2 border rounded-lg" required>
                <option value="user" <%= user.getManager().name().equals("user") ? "selected" : "" %>>User</option>
                <option value="manager" <%= user.getManager().name().equals("manager") ? "selected" : "" %>>Manager</option>
              </select>
            </div>


            <!-- Submit Button -->
            <div class="flex justify-end">
              <button type="submit" class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">Save</button>
              <button type="button" class="ml-2 bg-gray-300 text-gray-700 px-4 py-2 rounded hover:bg-gray-400" onclick="hideModal('modal-<%= user.getId() %>')">Cancel</button>
            </div>
          </form>
        </div>
      </div>
    </td>
  </tr>
  <%
      }
    }
  %>
  </tbody>
</table>

<!-- JavaScript to toggle modal visibility -->
<script>
  function showModal(modalId) {
    document.getElementById(modalId).classList.remove('hidden');
  }

  function hideModal(modalId) {
    document.getElementById(modalId).classList.add('hidden');
  }

  // Optional: Close the modal when clicking outside of it
  window.onclick = function(event) {
    const modals = document.querySelectorAll("[id^='modal-']");
    modals.forEach(modal => {
      if (event.target == modal) {
        modal.classList.add('hidden');
      }
    });
  }
</script>
