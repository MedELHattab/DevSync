<%@ page import="com.model.Tag" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <title>Main Page</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://cdn.jsdelivr.net/npm/flowbite@2.5.2/dist/flowbite.min.css" rel="stylesheet" />
</head>

<!-- Include header.jsp here -->
<jsp:include page="header.jsp" />

<div class="p-16">
    <h2 class="text-lg font-semibold mb-4">Tag List</h2>

    <!-- Button to create a new tag -->
    <div class="mb-4">
        <a href="createTag.jsp" class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">Add Tag</a>
    </div>

    <table class="table-auto w-full border-collapse border border-gray-300 rounded-lg overflow-hidden">
        <thead>
        <tr class="bg-gray-100 text-left">
            <th class="border border-gray-300 px-4 py-2 bg-blue-100">ID</th> <!-- Background color for ID column -->
            <th class="border border-gray-300 px-4 py-2">Tag Name</th>
            <th class="border border-gray-300 px-4 py-2">Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Tag> tags = (List<Tag>) request.getAttribute("tags");
            if (tags != null) {
                for (Tag tag : tags) {
        %>
        <tr class="bg-white">
            <td class="border border-gray-300 px-4 py-2 bg-blue-100 rounded-l-lg"><%= tag.getId() %></td>
            <td class="border border-gray-300 px-4 py-2"><%= tag.getName() %></td>
            <td class="border border-gray-300 px-4 py-2 rounded-r-lg">
                <!-- Edit Button -->
                <button class="text-blue-500 hover:underline" onclick="showModal('modal-<%= tag.getId() %>')">Edit</button> |

                <!-- Delete Form -->
                <form action="tags" method="POST" style="display:inline;">
                    <input type="hidden" name="action" value="deleteTag" />
                    <input type="hidden" name="id" value="<%= tag.getId() %>" />
                    <button type="submit" class="text-red-500 hover:underline" onclick="return confirm('Are you sure you want to delete this tag?');">Delete</button>
                </form>

                <!-- Modal for editing the tag -->
                <div id="modal-<%= tag.getId() %>" class="hidden fixed inset-0 z-50 flex items-center justify-center bg-gray-500 bg-opacity-50">
                    <div class="bg-white p-6 rounded-lg w-1/2">
                        <h2 class="text-xl font-semibold mb-4">Edit Tag</h2>
                        <form action="tags" method="POST">
                            <input type="hidden" name="id" value="<%= tag.getId() %>" />
                            <input type="hidden" name="action" value="editTag" />

                            <!-- Tag Name Field -->
                            <div class="mb-4">
                                <label class="block text-sm font-medium mb-2" for="name">Tag Name</label>
                                <input type="text" name="name" id="name" value="<%= tag.getName() %>" class="w-full px-4 py-2 border rounded-lg" required />
                            </div>

                            <!-- Submit Button -->
                            <div class="flex justify-end">
                                <button type="submit" class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">Save</button>
                                <button type="button" class="ml-2 bg-gray-300 text-gray-700 px-4 py-2 rounded hover:bg-gray-400" onclick="hideModal('modal-<%= tag.getId() %>')">Cancel</button>
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
</div>
<!-- Fixed Footer -->
<div class="fixed bottom-0 left-0 w-full bg-gray-800 text-white py-4">
    <jsp:include page="footer.jsp" />
</div>
<!-- JavaScript to apply alternating column background colors and handle modals -->
<script>
    document.addEventListener('DOMContentLoaded', function () {
        const tableRows = document.querySelectorAll('tbody tr');
        tableRows.forEach((row, index) => {
            if (index % 2 !== 0) {
                row.classList.add('bg-gray-200');
            }
        });
    });

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
