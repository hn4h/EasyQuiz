<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Settings</title>
    <link rel="stylesheet" href="setting.css">
    <link rel="shortcut icon" href="./images/logo/Easyquiz_logo.png">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>

<body>
    <div class="header">
        <div class="logo">
            <div class="menu-btn">
                <button id="menuToggle"><i class="fa-solid fa-bars"></i></button>
            </div>
            <span>EasyQuiz</span>
        </div>
        <div class="search">
            <i class="fa-solid fa-magnifying-glass"></i>
            <input type="text" placeholder="Search for study guides" name="">
        </div>
        <div class="create-login">
            <div type="button" class="create-btn">
                <span><i class="fa-solid fa-plus"></i></span>
                <p>Create</p>
            </div>
            <div type="button" class="create-btn-icon">
                <span><i class="fa-solid fa-plus"></i></span>
            </div>

            <div class="upgrade-btn">
                <button>Upgrade</button>
            </div>
            <div class="avatar-user">
                <img src="/images/avt1.jpg" alt="">
            </div>
            <div class="login-btn">
                <button id="openPopupButton" class="open-popup-btn">Log in</button>
            </div>
        </div>
    </div>
    <div class="body">
        <div class="side-bar" id="sidebar">
            <div class="home-btn">
                <i class="fa-solid fa-house"></i>
                <a href=""><span>Home</span></a>
            </div>
            <div class="history-btn">
                <i class="fa-solid fa-clock-rotate-left"></i>
                <a href=""><span>History</span></a>
            </div>
            <div class="blogs-btn">
                <i class="fa-solid fa-blog"></i>
                <a href=""><span>Blogs</span></a>
            </div>
            <hr>
            <div class="filter">
                <div class="filter-header">
                    <i class="fa-solid fa-filter"></i>
                    <span>Filter by topic</span>
                </div>
                <select name="" id="">
                    <option value="" selected disabled>-- Topic --</option>
                    <option value="">Subject A</option>
                    <option value="">Subject B</option>
                    <option value="">Subject C</option>
                    <option value="">Subject D</option>
                    <option value="">Subject E</option>
                </select>
            </div>
        </div>
        <div class="body-container">
            <h1>Settings</h1>
            <div class="personal-setting">
                <h2>Personal information</h2>
                <div class="personal-setting-box">
                    <div style="border-bottom: 2px solid #f3f3f3;">
                        <h3>Profile picture</h3>
                        <div style="display: flex;">
                            <img src="images/avt1.jpg" alt="Not found">
                            <div class="avt-list">
                                <div class="avt-item">
                                    <button>
                                        <img src="images/avt1.jpg" alt="Not found">
                                    </button>
                                </div>
                                <div>
                                    <button>
                                        <img src="images/avt2.jpg" alt="Not found">
                                    </button>
                                </div>
                                <div>
                                    <button>
                                        <img src="images/avt3.jpg" alt="Not found">
                                    </button>
                                </div>
                                <div>
                                    <button>
                                        <img src="images/avt4.jpg" alt="Not found">
                                    </button>
                                </div>
                                <div>
                                    <button>
                                        <img src="images/avt5.jpg" alt="Not found">
                                    </button>
                                </div>
                                <div>
                                    <button>
                                        <img src="images/avt6.jpg" alt="Not found">
                                    </button>
                                </div>
                                <div>
                                    <button>
                                        <img src="images/avt7.jpg" alt="Not found">
                                    </button>
                                </div>
                                <div>
                                    <button>
                                        <img src="images/avt8.jpg" alt="Not found">
                                    </button>
                                </div>
                                <div>
                                    <button>
                                        <img src="images/avt9.jpg" alt="Not found">
                                    </button>
                                </div>
                                <div>
                                    <button>
                                        <img src="images/avt10.jpg" alt="Not found">
                                    </button>
                                </div>
                                <div>
                                    <button>
                                        <img src="images/avt11.jpg" alt="Not found">
                                    </button>
                                </div>
                                <div>
                                    <button>
                                        <img src="images/avt12.jpg" alt="Not found">
                                    </button>
                                </div>
                                <div>
                                    <button>
                                        <img src="images/avt13.jpg" alt="Not found">
                                    </button>
                                </div>
                                <div>
                                    <button>
                                        <img src="images/avt14.jpg" alt="Not found">
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="attribute-box">
                        <div>
                            <h3>Username</h3>
                            <p>Name of user</p>
                        </div>
                        <button>Edit</button>
                    </div>
                    <div class="attribute-box">
                        <div>
                            <h3>Email</h3>
                        <p>email@gmail.com</p>
                        </div>
                        <button>Edit</button>
                    </div>
                    <div class="attribute-box">
                        <h3>Account type</h3>
                        <select name="" id="">
                            <option value="">Student</option>
                            <option value="">Teacher</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="personal-setting">
                <h2>Appearance</h2>
                <div class="personal-setting-box">
                    <div class="attribute-box">
                        <h3>Theme</h3>
                        <select name="" id="">
                            <option value="">Auto</option>
                            <option value="">Light</option>
                            <option value="">Dark</option>
                        </select>
                    </div>
                    <div class="attribute-box">
                        <h3>Language</h3>
                        <select name="" id="">
                            <option value="">English</option>
                            <option value="">Vietnamese</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="personal-setting">
                <h2>Account</h2>
                <div class="personal-setting-box">
                    <div class="attribute-box">
                        <h3>Create a EasyQuiz password</h3>
                        <button>Create</button>
                    </div>
                    <div class="attribute-box">
                        <div>
                            <h3>Delete your account</h3>
                            <p>This will delete all your data and cannot be undone.</p>
                        </div>
                        <button style="color: #fff; background-color: #970000;">Delete account</button>
                    </div>
                    
                </div>
            </div>
        </div>
    </div>
    <script src="setting.js"></script>
</body>

</html>