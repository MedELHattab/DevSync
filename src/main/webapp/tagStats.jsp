<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
  <title>Tag Statistics</title>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://cdn.tailwindcss.com"></script>
  <link href="https://cdn.jsdelivr.net/npm/flowbite@2.5.2/dist/flowbite.min.css" rel="stylesheet" />
</head>

<body class="flex flex-col min-h-screen bg-gray-100">
<main class="flex-grow p-4 mb-4">

  <!-- Include header -->
  <jsp:include page="header.jsp" />

  <div class="container mx-auto p-8">
    <h2 class="text-xl font-semibold mb-6">Tag Statistics by Task Status</h2>

    <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
      <%
        Map<String, Map<String, Double>> tagStats = (Map<String, Map<String, Double>>) request.getAttribute("tagStats");

        if (tagStats != null && !tagStats.isEmpty()) {
          for (Map.Entry<String, Map<String, Double>> tagEntry : tagStats.entrySet()) {
            String tagName = tagEntry.getKey();
            Map<String, Double> statusStats = tagEntry.getValue();

            // Extract the percentages for each status
            double pendingPercentage = statusStats.getOrDefault("Pending", 0.0);
            double completedPercentage = statusStats.getOrDefault("Completed", 0.0);
            double overduePercentage = statusStats.getOrDefault("Overdue", 0.0);
      %>
      <div class="bg-white shadow-lg rounded-lg p-6">
        <h3 class="text-lg font-bold mb-2"><%= tagName %></h3>

        <!-- Pending tasks percentage -->
        <div class="mb-4">
          <div class="text-sm font-medium text-gray-700 mb-2">Pending</div>
          <div class="w-full bg-gray-200 rounded-full h-4">
            <div class="bg-blue-500 h-4 rounded-full" style="width: <%= pendingPercentage %>%;"></div>
          </div>
          <div class="text-xs text-gray-600 mt-1"><%= String.format("%.2f", pendingPercentage) %> %</div>
        </div>

        <!-- Completed tasks percentage -->
        <div class="mb-4">
          <div class="text-sm font-medium text-gray-700 mb-2">Completed</div>
          <div class="w-full bg-gray-200 rounded-full h-4">
            <div class="bg-green-500 h-4 rounded-full" style="width: <%= completedPercentage %>%;"></div>
          </div>
          <div class="text-xs text-gray-600 mt-1"><%= String.format("%.2f", completedPercentage) %> %</div>
        </div>

        <!-- Overdue tasks percentage -->
        <div class="mb-4">
          <div class="text-sm font-medium text-gray-700 mb-2">Overdue</div>
          <div class="w-full bg-gray-200 rounded-full h-4">
            <div class="bg-red-500 h-4 rounded-full" style="width: <%= overduePercentage %>%;"></div>
          </div>
          <div class="text-xs text-gray-600 mt-1"><%= String.format("%.2f", overduePercentage) %> %</div>
        </div>
      </div>
      <%
        }
      } else {
      %>
      <div class="col-span-3 text-center">
        <p>No statistics available for tags.</p>
      </div>
      <%
        }
      %>
    </div>
  </div>
</main>

<!-- Footer -->
<div class="bg-gray-800 text-white py-4">
  <jsp:include page="footer.jsp" />
</div>
</body>
