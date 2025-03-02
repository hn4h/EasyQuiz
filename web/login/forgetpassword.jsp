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
        <link rel="shortcut icon" href="./images/logo/Easyquiz_logo.png">
        <link rel="stylesheet" href="./login/style.css">
    </head>
    <body>
        <div class="header">
            <div class="logo">
                <a href="home"><span>EasyQuiz</span></a>
            </div>
        </div>
        <div class="forget-password-container">
            <h1>Reset your password</h1>
            <p>
                Enter the email you signed up with. We'll send you a link to log 
                in and reset your password. If you signed up with a parent’s email, 
                we’ll send them the link.
            </p>
            <h3>Email</h3>
            <form action="forgetpassword" method="post">
            <label for="">
                <input type="email" name="email" placeholder="name@email.com">
            </label>
                <p class="error-title">${requestScope.mess}</p>
                <p class="success-msg">${requestScope.success}</p>
            <button type="submit">Send link</button>
            </form>
        </div>
    </body>
</html>
