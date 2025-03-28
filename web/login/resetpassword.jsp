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
        <link rel="stylesheet" href="./login/style.css">
        <link rel="shortcut icon" href="./images/logo/Easyquiz_logo.png">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
        <style>
            #toastMessage1 {
                display: flex;
                align-items: center;
                gap: 15px;
                position: fixed;
                bottom: 20px;
                right: 20px;
                background: #007f00;
                color: #fff;
                font-size: 18px;
                font-weight: bold;
                padding: 15px 20px;
                border-radius: 5px;
                opacity: 1;
                transition: opacity 0.5s;
                z-index: 1010;
            }

            #toastMessage2 {
                display: flex;
                align-items: center;
                gap: 15px;
                position: fixed;
                bottom: 20px;
                right: 20px;
                background: #d32f2f;
                color: #fff;
                font-size: 18px;
                font-weight: bold;
                padding: 15px 20px;
                border-radius: 5px;
                opacity: 1;
                transition: opacity 0.5s;
                z-index: 1010;
            }
        </style>
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
                Reset a new password for ${requestScope.email}
            </p>
            <form action="resetPassword" method="post">
                <div class="input-wrapper">
                    <input type="hidden" name="email" placeholder="Email" value="${requestScope.email}">
                    <input id="newPasswordInput" type="password" name="password" placeholder="New password">
                    <button type="button" class="icon-eye" id="togglePassword"><i class="fa-regular fa-eye"></i></button>
                </div>
                <div class="error-message" id="newPasswordError"></div>
                <div class="input-wrapper">
                    <input id="newConfirmPasswordInput" type="password" name="confirm_password" placeholder="Confirm your new password">
                    <button type="button" class="icon-eye" id="togglePassword"><i class="fa-regular fa-eye"></i></button>
                </div>
                <div class="error-message" id="newConfirmPasswordError"></div>
                <button id="signUpBtn" type="submit">Save</button>
            </form>
        </div>
        <%
            String errorMessage = (String) request.getAttribute("error");
            if (errorMessage == null) {
            errorMessage = (String) session.getAttribute("error");
            }
            if (errorMessage != null) {
        %>
        <div id="toastMessage2">
            <span class="material-symbols-rounded">close</span>
            <span><%= errorMessage %></span>
        </div>
        <script>
            setTimeout(function () {
                let toast2 = document.getElementById("toastMessage2");
                toast2.style.opacity = "0";
                setTimeout(() => {
                    toast2.style.display = "none";
                }, 500);
            }, 3000);
        </script>
        <%
            session.removeAttribute("error");
            }
        %>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                const newPasswordInput = document.getElementById("newPasswordInput");
                const newConfirmPasswordInput = document.getElementById("newConfirmPasswordInput");
                const passwordError1 = document.getElementById("newPasswordError");
                const confirmPasswordError1 = document.getElementById("newConfirmPasswordError");
                const submitBtn = document.getElementById("signUpBtn");
                let newConfirmPasswordTouched = false;
                let newPasswordTouched = false;

                function setError(input, message, errorElement) {
                    errorElement.textContent = message;
                    input.classList.add("input-error");
                }

                function clearError(input, errorElement) {
                    errorElement.textContent = "";
                    input.classList.remove("input-error");
                }

                function validatePassword() {

                    if (!newPasswordTouched)
                        return false;

                    if (newPasswordInput.value.length < 8) {
                        setError(newPasswordInput, "Your password is too short. The minimum length is 8 characters.", passwordError1);
                        return false;
                    } else {
                        clearError(newPasswordInput, passwordError1);
                        return true;
                    }
                }

                function validateConfirmPassword() {

                    if (!newConfirmPasswordTouched)
                        return false; // Chưa nhập confirm password thì không kiểm tra

                    if (newConfirmPasswordInput.value !== newPasswordInput.value) {
                        setError(newConfirmPasswordInput, "Your password does not match.", confirmPasswordError1);
                        return false;
                    } else {
                        clearError(newConfirmPasswordInput, confirmPasswordError1);
                        return true;
                    }
                }
                function validateForm() {
                    const isEmailValid = validateEmail();
                    const isPasswordValid = validatePassword();
                    const isConfirmPasswordValid = validateConfirmPassword();

                    submitBtn.disabled = !(isEmailValid && isPasswordValid && isConfirmPasswordValid);
                }

                newPasswordInput.addEventListener("input", function () {
                    newPasswordTouched = true;
                    validatePassword();
                    validateForm();
                });

                newConfirmPasswordInput.addEventListener("input", function () {
                    newConfirmPasswordTouched = true;
                    validateConfirmPassword();
                    validateForm();
                });
            });
        </script>
    </body>
</html>
