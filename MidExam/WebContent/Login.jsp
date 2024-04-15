<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Login</title>
  <style>
    body {
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      margin: 0;
      background-color: #f0f0f0;
    }

    .login-container {
      width: 400px;
      padding: 40px;
      background-color: white;
      border-radius: 10px;
      box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
    }

    .login-header {
      text-align: center;
      color: #0072c6;
      font-size: 24px;
      font-weight: bold;
      margin-bottom: 30px;
    }

    .login-form input {
      width: 100%;
      padding: 12px 16px;
      margin-bottom: 20px;
      border: 1px solid #ccc;
      border-radius: 4px;
      font-size: 16px;
    }

    .login-form button {
      width: 100%;
      padding: 12px 16px;
      background-color: #0072c6;
      color: white;
      border: none;
      border-radius: 4px;
      font-size: 16px;
      cursor: pointer;
    }

    .login-form button:hover {
      background-color: #005a9e;
    }

    .login-form a {
      color: #0072c6;
      text-decoration: none;
    }

    .login-form a:hover {
      text-decoration: underline;
    }
  </style>
</head>
<body>
  <div class="login-container">
    <div class="login-header">Login</div>
    <% if (request.getParameter("error") != null && request.getParameter("error").equals("invalid_input")) { %>
    <p style="color: red;">Invalid input! Please fill all fields.</p>
    <% } else if (request.getParameter("error") != null && request.getParameter("error").equals("invalid_credentials")) { %>
    <p style="color: red;">Invalid email or password.</p>
    <% } %>
    <form class="login-form" action="login" method="post">
      <input type="text" name="email" placeholder="Email">
      <input type="password" name="password" placeholder="Password">
      <button type="submit">Login</button>
      <p>Have No Account? <a href="User.jsp">SignUp</a></p>
    </form>
  </div>
</body>
</html>