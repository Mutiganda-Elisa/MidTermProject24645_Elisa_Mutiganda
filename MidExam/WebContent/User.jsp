<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>User Registration</title>
  <style>
    body {
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      margin: 0;
      background-color: #f0f0f0;
    }

    .registration-container {
      width: 400px;
      padding: 40px;
      background-color: white;
      border-radius: 10px;
      box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
    }

    .registration-header {
      text-align: center;
      color: #0072c6;
      font-size: 24px;
      font-weight: bold;
      margin-bottom: 30px;
    }

    .registration-form input,
    .registration-form select {
      width: 100%;
      padding: 12px 16px;
      margin-bottom: 20px;
      border: 1px solid #ccc;
      border-radius: 4px;
      font-size: 16px;
    }

    .registration-form button {
      width: 100%;
      padding: 12px 16px;
      background-color: #0072c6;
      color: white;
      border: none;
      border-radius: 4px;
      font-size: 16px;
      cursor: pointer;
    }

    .registration-form button:hover {
      background-color: #005a9e;
    }

    .registration-form a {
      color: #0072c6;
      text-decoration: none;
    }

    .registration-form a:hover {
      text-decoration: underline;
    }
  </style>
</head>
<body>
  <div class="registration-container">
    <div class="registration-header">User Registration</div>
    <% if (request.getParameter("error") != null && request.getParameter("error").equals("invalid_input")) { %>
    <p style="color: red;">Invalid input! Please fill all fields.</p>
    <% } %>
    <form class="registration-form" action="register" method="post">
      <input type="text" name="email" placeholder="Email">
      <input type="password" name="password" placeholder="Password">
      <select name="role">
        <option value="admin">Admin</option>
        <option value="student">Student</option>
        <option value="teacher">Teacher</option>
      </select>
      <button type="submit">Register</button>
      <p>Already Have an Account? <a href="Login.jsp">Sign In</a></p>
    </form>
  </div>
</body>
</html>