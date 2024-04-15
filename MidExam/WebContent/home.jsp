<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Auca Website</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f8f9fa;
            background-image: url('D:\SEMISTERS');
            background-size: cover;
            background-position: center; /* Adjust as needed */
        }
        .container {
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            background-color: rgba(255, 255, 255, 0.7); /* Add transparency to the container */
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
            background-color: #28a745;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .button:hover {
            background-color: #218838; 
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>WELCOME TO ADVENTIST UNIVERSITY OF CENTRAL AFRICA</h1>
        <button class="button" onclick="window.location.href='home.jsp'">HOME</button>
        <button class="button" onclick="window.location.href='Academics.jsp'">ACADEMICS</button>
        <button class="button" onclick="window.location.href='MediaCenter.jsp'">MEDIA CENTER</button>
        <button class="button" onclick="window.location.href='Research.jsp'">RESEARCH</button>
        <button class="button" onclick="window.location.href='Login.jsp'">APPLICATION</button>
        <button class="button" onclick="window.location.href='Login.jsp'">Login</button>
        <button class="button" onclick="window.location.href='Login.jsp'">Logout</button>
    </div>
</body>
</html>
