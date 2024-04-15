<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Teacher Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f8f9fa;
        }
        .container {
            max-width: 600px;
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
            background-color: #28a745; /* Preferred color for buttons */
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .button:hover {
            background-color: #218838; /* Darker shade for hover effect */
        }
    </style>
</head>
<body>
    <div class="container">
        <button class="button" onclick="window.location.href='Research.jsp'">Research</button>
        <button class="button" onclick="window.location.href='MediaCenter.jsp'">Media Center</button>
        <button class="button" onclick="window.location.href='Teacher.jsp'">Teacher</button>
        <button class="button" onclick="window.location.href='Login.jsp'">Logout</button>
    </div>
</body>
</html>
