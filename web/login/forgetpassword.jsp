<%-- 
    Document   : forgetpassword
    Created on : Feb 19, 2025, 3:06:08 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forgotten Password</title>
        <link rel="stylesheet" href="style.css">
    </head>
    <body>
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
            <button type="submit">Send link</button>
            </form>
        </div>
    </body>
</html>
