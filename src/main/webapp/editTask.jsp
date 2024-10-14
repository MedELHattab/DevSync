<%@ page import="com.model.Task" %>
<%@ page import="com.model.User" %>
<%@ page import="com.model.Tag" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
  <title>Edit Task</title>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://cdn.tailwindcss.com"></script>
  <link href="https://cdn.jsdelivr.net/npm/flowbite@2.5.2/dist/flowbite.min.css" rel="stylesheet" />
  <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
  <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
</head>

<!-- Include header.jsp here -->
<jsp:include page="header.jsp" />

<%
  // Retrieve the taskId from the query string
  String taskId = request.getParameter("taskId");

  // Fetch the task details based on the taskId
  Task task = (Task) request.getAttribute("task");

  // Fetch users and tags for selection options
  List<User> users = (List<User>) request.getAttribute("users");
  List<Tag> tags = (List<Tag>) request.getAttribute("tags");
%>

<div class="p-16">
  <h2 class="text-lg font-semibold mb-4">Edit Task</h2>

  <form action="tasks" method="POST">
    <input type="hidden" name="id" value="<%= taskId %>" />
    <input type="hidden" name="action" value="edit" />

    <!-- Title Field -->
    <div class="mb-4">
      <label class="block text-sm font-medium mb-2" for="title">Title</label>
      <input type="text" name="title" id="title" value="<%= task.getTitle() %>" class="w-full px-4 py-2 border rounded-lg" required />
    </div>

    <!-- Description Field -->
    <div class="mb-4">
      <label class="block text-sm font-medium mb-2" for="description">Description</label>
      <textarea name="description" id="description" class="w-full px-4 py-2 border rounded-lg"><%= task.getDescription() %></textarea>
    </div>

    <!-- Due Date Field -->
    <div class="mb-4">
      <label class="block text-sm font-medium mb-2" for="dueDate">Due Date</label>
      <input type="date" name="dueDate" id="dueDate" value="<%= task.getDueDate() %>" class="w-full px-4 py-2 border rounded-lg" required />
    </div>

    <!-- Status Field -->
    <div class="mb-4">
      <label class="block text-sm font-medium mb-2" for="status">Status</label>
      <select name="status" id="status" class="w-full px-4 py-2 border rounded-lg" required>
        <option value="Pending" <%= "Pending".equals(task.getStatus()) ? "selected" : "" %>>Pending</option>
        <option value="Overdue" <%= "Overdue".equals(task.getStatus()) ? "selected" : "" %>>Overdue</option>
        <option value="Completed" <%= "Completed".equals(task.getStatus()) ? "selected" : "" %>>Completed</option>
      </select>
    </div>

    <!-- Creator Field (Disabled for editing) -->
    <div class="mb-4">
      <label class="block text-sm font-medium mb-2" for="creator">Creator</label>

      <!-- Hidden input to store the creator ID -->
      <input type="hidden" name="assignee" id="assignee" value="<%= task.getAssignee().getId() %>" />

      <!-- Read-only input to display the creator's username -->
      <input type="text" value="<%= task.getAssignee().getUsername() %>" class="w-full px-4 py-2 border rounded-lg bg-gray-200" readonly />
    </div>

    <!-- Creator Field (Disabled for editing) -->
    <div class="mb-4">
      <label class="block text-sm font-medium mb-2" for="creator">Creator</label>

      <!-- Hidden input to store the creator ID -->
      <input type="hidden" name="creator" id="creator" value="<%= task.getCreator().getId() %>" />

      <!-- Read-only input to display the creator's username -->
      <input type="text" value="<%= task.getCreator().getUsername() %>" class="w-full px-4 py-2 border rounded-lg bg-gray-200" readonly />
    </div>


    <!-- Tags Field -->
    <div class="mb-4">
      <label class="block text-sm font-medium mb-2" for="tags">Tags</label>
      <select name="tags[]" id="tags" class="js-example-basic-multiple w-full px-4 py-2 border rounded-lg" multiple>
        <% if (tags != null) {
          for (Tag tag : tags) { %>
        <option value="<%= tag.getId() %>" <%= (task.getTags().stream().anyMatch(t -> t.getId().equals(tag.getId()))) ? "selected" : "" %>>
          <%= tag.getName() %></option>
        <% } } %>
      </select>
    </div>

    <!-- Submit Button -->
    <div class="flex justify-end">
      <button type="submit" class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">Save</button>
      <a href="tasks" class="ml-2 bg-gray-300 text-gray-700 px-4 py-2 rounded hover:bg-gray-400">Cancel</a>
    </div>
  </form>
</div>

<!-- Include footer.jsp here -->
<jsp:include page="footer.jsp" />

<script>
  $(document).ready(function() {
    $('.js-example-basic-multiple').select2();
  });
</script>
