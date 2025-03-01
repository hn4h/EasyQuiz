<html lang="en">

    <head>
        <meta charset="utf-8" />
        <meta content="width=device-width, initial-scale=1.0" name="viewport" />
        <title>Register</title>
        <link rel="stylesheet" href="./login/style.css">
        <link rel="shortcut icon" href="./images/logo/Easyquiz_logo.png">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
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
                <img src="./login/images/banner_login.jpg" alt="Not found">
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
                                    <input type="text" name="email" placeholder="user@email.com">
                                </label>
                            </div>
                        </div>
                        <div class="input-username">
                            <div>
                                <b>Username</b>
                            </div>
                            <div>
                                <label for="">
                                    <input type="text" name="username" placeholder="andrew123">
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
                                <input id="passwordInput" type="password" name="password" placeholder="********">
                                <button type="button" class="icon-eye" id="togglePassword"><i
                                        class="fa-regular fa-eye"></i></button>
                            </div>
                        </div>
                        <!-- <div>
                            <div class="accept-term-policy">
                                <label>
                                    <input type="checkbox" name="acceptTerms">
                                    I accept EasyQuiz's <b>Terms of Service</b> and <b>Privacy Policy</b>
                                </label>
                            </div>
                            <div id="errorMessage" class="error-message"></div>
                        </div> -->
                        <div class="input-password">
                            <div class="password-header">
                                <div>
                                    <b>Confirm password</b>
                                </div>
                            </div>
                            <div class="input-wrapper">
                                <input id="confirmPasswordInput" type="password" name="confirmPassword" placeholder="********">
                                <button type="button" class="icon-eye" id="togglePassword"><i
                                        class="fa-regular fa-eye"></i></button>
                            </div>
                        </div>
                        <div class="register-button">
                            <button type="submit">Sign up</button>
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
        <script src="./login/script.js"></script></script>
</body>

</html>