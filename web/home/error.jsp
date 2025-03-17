<%-- 
    Document   : error
    Created on : Mar 17, 2025, 9:55:47 PM
    Author     : 11
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Home</title>
        <link rel="shortcut icon" href="./images/logo/Easyquiz_logo.png">
        <style>
        body {
            background-color: #1A2A44;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            font-family: Arial, sans-serif;
        }
        .container {
            text-align: center;
            color: white;
        }
        .main-text {
            font-size: 2.5em;
            font-weight: bold;
            margin-bottom: 10px;
        }
        .sub-text {
            font-size: 1.2em;
            color: #A0B0C0;
            margin-bottom: 20px;
        }
        .search-bar {
            width: 300px;
            padding: 10px;
            border: none;
            border-radius: 5px;
            background-color: #2E4066;
            color: #A0B0C0;
            font-size: 1em;
        }
        .search-bar::placeholder {
            color: #A0B0C0;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="main-text">Hmm, we can't seem to find that page</div>
        <div class="sub-text">Could not find that set</div>
        <input type="text" class="search-bar" placeholder="Try a search instead">
    </div>
</body>
</html>
