
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login</title>
        <link rel="stylesheet" href="./login/style.css">
        <link rel="shortcut icon" href="./images/logo/Easyquiz_logo.png">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    </head>

    <body>
        <%
    String successMessage = (String) session.getAttribute("successMessage");
    if (successMessage != null) {
        %>
        <div id="toastMessage">
            <span class="material-symbols-rounded">check</span>
            <span><%= successMessage %></span>
        </div>
        <script>
            setTimeout(function () {
                let toast = document.getElementById("toastMessage");
                toast.style.opacity = "0";
                setTimeout(() => {
                    toast.style.display = "none";
                }, 500); // ?n hoàn toàn sau 0.5 giây sau khi m?
            }, 3000);
        </script>
        <%
            session.removeAttribute("successMessage"); // Xóa sau khi hi?n th?
            }
        %>
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
        <div class="login-container">
            <div class="login-banner">
                <img src="./login/images/banner_login.png" alt="Not found">
            </div>
            <div class="login-content">
                <div class="login-header">
                    <a href="signup"><h3>Sign up</h3></a>
                    <a href="login"><h3>Log in</h3></a>
                </div>
                <div class="login-with">
                    <div class="login-google">
                        <i class="fa-brands fa-google"></i>
                        <a href="https://accounts.google.com/o/oauth2/auth?scope=email profile openid &redirect_uri=http://localhost:9999/QuizzApp/login&response_type=code&client_id=591866101388-93m4n7avvp7v3sl4fbf8mqcmbt5otbk3.apps.googleusercontent.com&approval_prompt=force">Log in with Google</a>
                    </div>
                    <div class="login-email">
                        <span>
                            or email
                        </span>
                    </div>
                    <form action="login" method="post">
                        <div class="input-email">
                            <div>
                                <b>Email</b>
                            </div>
                            <div>
                                <label for="">
                                    <input type="text" name="email" placeholder="Enter your email address or username">
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
                                <input id="passwordInput" type="password" name="password" placeholder="Enter your password">
                                <button type="button" class="icon-eye" id="togglePassword"><i class="fa-regular fa-eye"></i></button>
                            </div>
                            <div class="forget-password">
                                <a href="forgetpassword">Forgot password?</a>
                            </div>
                            <div class="error-message">${requestScope.error}</div>
                        </div>
                        <div class="login-button">
                            <button type="submit">Log in</button>
                        </div>
                    </form>
                    <div>
                        <div class="create-button">
                            <a href="signup"><button>Create an account</button></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="./login/script.js"></script>
    </body>

</html>