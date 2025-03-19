<html lang="en">

    <head>
        <meta charset="utf-8" />
        <meta content="width=device-width, initial-scale=1.0" name="viewport" />
        <title>Register</title>
        <link rel="stylesheet" href="./login/style.css">
        <link rel="shortcut icon" href="./images/logo/Easyquiz_logo.png">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
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
            <div class="search">
                <i class="fa-solid fa-magnifying-glass"></i>
                <input type="text" placeholder="Search for study guides" name="">
            </div>
            <div class="create-login">
                <div class="login-btn">
                    <a href="login"><button>Log in</button></a>
                </div>
            </div>
        </div>
        <div class="register-container">
            <div class="register-banner">
                <img src="./login/images/banner_login.png" alt="Not found">
            </div>
            <div class="register-content">
                <div class="register-header">
                    <a href="signup"><h3>Sign up</h3></a>
                    <a href="login"><h3>Log in</h3></a>
                </div>
                <div class="register-with">
                    <div class="register-google">
                        <i class="fa-brands fa-google"></i>
                        <a href="https://accounts.google.com/o/oauth2/auth?scope=email profile openid &redirect_uri=http://localhost:9999/QuizzApp/login&response_type=code&client_id=591866101388-93m4n7avvp7v3sl4fbf8mqcmbt5otbk3.apps.googleusercontent.com&approval_prompt=force">Log in with Google</a>
                    </div>
                    <div class="login-email">
                        <span>
                            or email
                        </span>
                    </div>
                    <form action="signup" method="post" id="registerForm">
                        <div class="input-email">
                            <div>
                                <b>Email</b>
                            </div>
                            <div>
                                <label for="">
                                    <input id="emailInput" type="text" name="email" placeholder="user@email.com" required="">
                                </label>
                            </div>
                            <div class="error-message" id="emailError"></div>
                        </div>
                        <div class="input-username">
                            <div>
                                <b>Username</b>
                            </div>
                            <div>
                                <label for="">
                                    <input type="text" name="username" placeholder="andrew123" required="">
                                </label>
                            </div>
                        </div>
                        <div class="input-password">
                            <div class="password-header">
                                <div>
                                    <b>Password</b>
                                </div>
                            </div>
                            <div class="input-wrapper">
                                <input id="passwordInput" type="password" name="password" placeholder="********" required="">
                                <button type="button" class="icon-eye" id="togglePassword"><i
                                        class="fa-regular fa-eye"></i></button>
                            </div>
                            <div class="error-message" id="passwordError"></div>
                        </div>
                        <div class="input-password">
                            <div class="password-header">
                                <div>
                                    <b>Confirm password</b>
                                </div>
                            </div>
                            <div class="input-wrapper">
                                <input id="confirmPasswordInput" type="password" name="confirmPassword" placeholder="********" required="">
                                <button type="button" class="icon-eye" id="toggleConfirmPassword"><i
                                        class="fa-regular fa-eye"></i></button>
                            </div>
                            <div class="error-message" id="confirmPasswordError"></div>
                        </div>
                        <div class="register-button">
                            <button type="submit" id="submitButton">Sign up</button>
                        </div>
                    </form>
                    <div>
                        <div class="have-account-button">
                            <a href="login"><button>Already have an account? Log in</button></a>
                        </div>
                    </div>
                </div>
            </div>
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
        <script src="./login/script.js"></script></script>
</body>

</html>