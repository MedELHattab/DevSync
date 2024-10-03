<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Create User</title>
</head>
<body>
<h1>Create a New User</h1>

<form action="${pageContext.request.contextPath}/user" method="post">
  <label for="username">Username:</label>
  <input type="text" id="username" name="username" required><br><br>

  <label for="password">Password:</label>
  <input type="password" id="password" name="password" required><br><br>

  <label for="first_name">First Name:</label>
  <input type="text" id="first_name" name="first_name" required><br><br>

  <label for="last_name">Last Name:</label>
  <input type="text" id="last_name" name="last_name" required><br><br>

  <label for="email">Email:</label>
  <input type="email" id="email" name="email" required><br><br>

  <input type="submit" value="Create User">
</form>

</body>
</html>
