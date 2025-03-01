<%-- 
    Document   : forgetpassword
    Created on : Feb 19, 2025, 3:06:08 AM
    Author     : admin
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forgotten Password</title>
        <link rel="stylesheet" href="style.css">
        <link rel="shortcut icon" href="./images/logo/Easyquiz_logo.png">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    </head>
    <body>
        <div class="header">
            <div class="logo">
                <a href="home"><span>EasyQuiz</span></a>
            </div>
        </div>
        <div class="resetpassword-container">
            <h1>Reset password</h1>
            <p>
                Reset a new password for your EasyQuiz account
            </p>
            <form action="forgetpassword" method="post">
                <div class="input-wrapper">
                    <input id="passwordInput" type="password" name="password" placeholder="New password">
                    <button type="button" class="icon-eye" id="togglePassword"><i class="fa-regular fa-eye"></i></button>
                </div>
                <div class="input-wrapper">
                    <input id="passwordInput" type="password" name="password" placeholder="Confirm your new password">
                    <button type="button" class="icon-eye" id="togglePassword"><i class="fa-regular fa-eye"></i></button>
                </div>
                <button type="submit">Save</button>
            </form>
        </div>
        <script src="script.js"></script>
    </body>
</html>
