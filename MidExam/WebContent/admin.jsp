<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="model.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        .container {
            max-width: 800px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .button {
            display: block;
            width: 100%;
            padding: 15px;
            margin-bottom: 10px;
            text-align: center;
            font-size: 18px;
            color: #fff;
            background-color: #007bff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <button class="button" onclick="window.location.href='AcademicUnit.jsp'">Academic Unit</button>
        <button class="button" onclick="window.location.href='Course.jsp'">Course</button>
        <button class="button" onclick="window.location.href='Semester.jsp'">Semester</button>
        <button class="button" onclick="window.location.href='Student.jsp'">Student</button>
        <button class="button" onclick="window.location.href='Teacher.jsp'">Teacher</button>
        <button class="button" onclick="window.location.href='Login.jsp'">Logout</button>
    </div>
</body>
</html>
