<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Settings</title>
        <link rel="stylesheet" href="setting.css">
        <link rel="shortcut icon" href="./images/logo/Easyquiz_logo.png">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css" />

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    </head>

    <body>
        <div class="header">
            <div class="logo">
                <div class="menu-btn">
                    <button class="sidebar-toggler" id="menuToggle"><i class="fa-solid fa-bars"></i></button>
                </div>
                <a href="home"><span>EasyQuiz</span></a>
            </div>
            <form action="searchBlog" method="get">
                <div class="search">
                    <i class="fa-solid fa-magnifying-glass"></i>
                    <input type="text" name="keyword" placeholder="Search for blog">
                </div>
            </form>

            <div class="create-login">
                <div type="button" class="create-btn-icon" id="createButton">
                    <span><button><i class="fa-solid fa-plus"></i></button></span>
                    <div class="create-menu" id="createMenu">
                        <a href="#" class="create-menu-item"><i class="fa-solid fa-book"></i> Flashcard set</a>
                        <a href="#" class="create-menu-item" id="createFolderItem"><i class="fa-solid fa-folder"></i> Folder</a>
                    </div>
                </div>

                <div class="upgrade-btn">
                    <button>Upgrade: Free 7-day trial</button>
                </div>
                <div class="avatar-user" id="avatarUser">
                    <img src="./images/avatar/default.png" alt="Not found">
                    <div class="user-menu" id="userMenu">
                        <div class="user-info">
                            <img src="${sessionScope.account.profileImage}" alt="Not found"/>
                            <div>
                                <p>${sessionScope.account.userName}</p>
                                <p>${sessionScope.account.email}</p>
                            </div>
                        </div>
                        <hr/>
                        <a href="#" class="user-menu-item">Profile</a>
                        <a href="#" class="user-menu-item">Settings</a>
                        <hr/>
                        <a href="#" class="user-menu-item">Logout</a>
                        <hr/>
                        <a href="#" class="user-menu-item">Help and feedback</a>
                        <a href="#" class="user-menu-item">Upgrades</a>
                    </div>
                </div>
                <div class="login-btn">
                    <a href="login"><button>Log in</button></a>
                </div>
            </div>
        </div>
        <div class="body">
            <aside class="sidebar">
                <nav class="sidebar-nav">
                    <!--Top nav-->
                    <ul class="nav-list primary-nav">
                        <li class="nav-item">
                            <a href="home" class="nav-link-actived">
                                <span class="material-symbols-rounded">home</span>
                                <span class="nav-label">Home</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="quizhistory" class="nav-link">
                                <span class="material-symbols-rounded">history</span>
                                <span class="nav-label">History</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="blog" class="nav-link">
                                <span class="material-symbols-rounded">rss_feed</span>
                                <span class="nav-label">Blog</span>
                            </a>
                        </li>
                    </ul>
                    <!--Bottom nav-->
                    <!--Bottom nav-->
                    <ul class="nav-list secondary-nav">
                        <li class="nav-item">
                            <a href="#" class="nav-link">
                                <span class="material-symbols-rounded">person</span>
                                <span class="nav-label">Profile</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="#" class="nav-link">
                                <span class="material-symbols-rounded">settings</span>
                                <span class="nav-label">Setting</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="logout" class="nav-link">
                                <span class="material-symbols-rounded">logout</span>
                                <span class="nav-label">Logout</span>
                            </a>
                        </li>
                    </ul>
                </nav>
            </aside>
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