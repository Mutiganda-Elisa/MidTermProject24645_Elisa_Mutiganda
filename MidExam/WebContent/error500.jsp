<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error 500 - Internal Server Error</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            text-align: center;
            padding-top: 100px;
        }
        h1 {
            color: #333;
        }
        p {
            color: #666;
        }
    </style>
</head>
<body>
    <h1>Error 500 - Internal Server Error</h1>
    <p>${errorMessage}</p>
    <% if (request.getAttribute("errorDetails") != null) { %>
        <p>Error details: ${errorDetails}</p>
    <% } %>
</body>
</html>