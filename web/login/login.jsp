<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="./login/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>

<body>
    <div class="login-container">
        <div class="login-banner">
            <img src="./login/images/banner_login.jpg" alt="Not found">
        </div>
        <div class="login-content">
            <div class="login-header">
                <a href="register.jsp"><h3>Sign up</h3></a>
                <a href="login.jsp"><h3>Log in</h3></a>
            </div>
            <div class="login-with">
                <div class="login-google">
                    <i class="fa-brands fa-google"></i>
                    <a href="">Log in with Google</a>
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
                            <div class="forget-password">
                                <a href="">Forgot password</a>
                            </div>
                        </div>
                        <div class="input-wrapper">
                            <input id="passwordInput" type="password" name="password" placeholder="Enter your password">
                            <button type="button" class="icon-eye" id="togglePassword"><i class="fa-regular fa-eye"></i></button>
                        </div>
                    </div>
                    <div>
                        <!-- <div class="term-policy">
                            By clicking Log in, you accept EasyQuiz's <b>Terms of Service</b> and <b>Privacy Policy</b>
                        </div> -->
                    </div>
                    <div class="login-button">
                        <button type="submit">Log in</button>
                    </div>
                </form>
                <div>
                    <div class="create-button">
                        <a href="register.jsp"><button>Already have an account? Log in</button></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="script.js"></script>
</body>

</html>