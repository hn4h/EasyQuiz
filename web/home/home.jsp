<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Home</title>
        <link rel="stylesheet" href="./home/home.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    </head>

    <body>
        <div class="header">
            <div class="logo">
                <div class="menu-btn">
                    <button class="sidebar-toggler" id="menuToggle"><i class="fa-solid fa-bars"></i></button>
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
                    <button>Upgrade: Free 7-day trial</button>
                </div>
                <div class="avatar-user"  id="avatarUser">
                    <img src="./images/avatar/default.png" alt="Not found">
                    <div class="user-menu" id="userMenu">
                        <div class="user-info">
                            <img src="./images/avatar/default.png" alt="Not found"/>
                            <div>
                                <p>Do Duc Anh</p>
                                <p>duca@gmail.com</p>
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
                            <a href="#" class="nav-link-actived">
                                <span class="material-symbols-rounded">home</span>
                                <span class="nav-label">Home</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="#" class="nav-link">
                                <span class="material-symbols-rounded">history</span>
                                <span class="nav-label">History</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="#" class="nav-link">
                                <span class="material-symbols-rounded">rss_feed</span>
                                <span class="nav-label">Blog</span>
                            </a>
                        </li>
                        <!--Dropdown -->
                        <li class="nav-item dropdown-container">
                            <a href="#" class="nav-link dropdown-toggle">
                                <span class="material-symbols-rounded">subject</span>
                                <span class="nav-label">Subject</span>
                                <span class="dropdown-icon material-symbols-rounded">keyboard_arrow_down</span>
                            </a>
<!--                            Dropdown subject-->
                            <ul class="dropdown-subject">
                                <li class="nav-item">
                                    <a class="nav-link dropdown-title" style="font-weight: bold">Subject</a>
                                </li>
                                <li class="nav-item">
                                    <a href="#" class="nav-link dropdown-link">Subject A</a>
                                </li>
                                <li class="nav-item">
                                    <a href="#" class="nav-link dropdown-link">Subject B</a>
                                </li>
                                <li class="nav-item">
                                    <a href="#" class="nav-link dropdown-link">Subject C</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
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
                            <a href="#" class="nav-link">
                                <span class="material-symbols-rounded">logout</span>
                                <span class="nav-label">Logout</span>
                            </a>
                        </li>
                    </ul>
                </nav>
            </aside>
            <div class="body-container">
                <div class="recents-container">
                    <h2>Recents</h2>
                    <div class="recents-list">
                        <div class="recents-item">
                            <div>
                                <i class="fa-solid fa-book"></i>
                            </div>
                            <div>
                                <p>Name</p>
                                <div>
                                    Type - ?? terms - by ????
                                </div>
                            </div>
                        </div>
                        <div class="recents-item">
                            <div>
                                <i class="fa-solid fa-book"></i>
                            </div>
                            <div>
                                <p>Name</p>
                                <div>
                                    Type - ?? terms - by ????
                                </div>
                            </div>
                        </div>
                        <div class="recents-item">
                            <div>
                                <i class="fa-solid fa-book"></i>
                            </div>
                            <div>
                                <p>Name</p>
                                <div>
                                    Type - ?? terms - by ????
                                </div>
                            </div>
                        </div>
                        <div class="recents-item">
                            <div>
                                <i class="fa-solid fa-book"></i>
                            </div>
                            <div>
                                <p>Name</p>
                                <div>
                                    Type - ?? terms - by ????
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="popular-card-container">
                    <h2>Name</h2>
                    <div class="flash-card-list">
                        <div class="flash-card-item">
                            <div>
                                <p>Name</p>
                            </div>
                            <div>
                                <span>??? terms</span>
                            </div>
                            <div>
                                <img src="/images/avt1.jpg" alt="Avatar">
                                <p>Name of user</p>
                            </div>
                        </div>
                        <div class="flash-card-item">
                            <div>
                                <p>Name</p>
                            </div>
                            <div>
                                <span>??? terms</span>
                            </div>
                            <div>
                                <img src="" alt="Avatar">
                                <p>Name of user</p>
                            </div>
                        </div>
                        <div class="flash-card-item">
                            <div>
                                <p>Name</p>
                            </div>
                            <div>
                                <span>??? terms</span>
                            </div>
                            <div>
                                <img src="" alt="Avatar">
                                <p>Name of user</p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="popular-card-container">
                    <h2>Name</h2>
                    <div class="flash-card-list">
                        <div class="flash-card-item">
                            <div>
                                <p>Name</p>
                            </div>
                            <div>
                                <span>??? terms</span>
                            </div>
                            <div>
                                <img src="/images/avt1.jpg" alt="Avatar">
                                <p>Name of user</p>
                            </div>
                        </div>
                        <div class="flash-card-item">
                            <div>
                                <p>Name</p>
                            </div>
                            <div>
                                <span>??? terms</span>
                            </div>
                            <div>
                                <img src="" alt="Avatar">
                                <p>Name of user</p>
                            </div>
                        </div>
                        <div class="flash-card-item">
                            <div>
                                <p>Name</p>
                            </div>
                            <div>
                                <span>??? terms</span>
                            </div>
                            <div>
                                <img src="" alt="Avatar">
                                <p>Name of user</p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="top-creator-container">
                    <h2>Top creator</h2>
                    <div class="top-creator-list">
                        <div class="top-creator-item">
                            <div>
                                <img src="/images/avt1.jpg" alt="Avatar">
                            </div>
                            <div>
                                <p>Name of user</p>
                            </div>
                            <div>
                                <span><i class="fa-solid fa-book"></i> ??? flashcard sets</span>
                                <span><i class="fa-solid fa-user-group"></i> ??? classes</span>
                            </div>
                        </div>
                        <div class="top-creator-item">
                            <div>
                                <img src="/images/avt1.jpg" alt="Avatar">
                            </div>
                            <div>
                                <p>Name of user</p>
                            </div>
                            <div>
                                <span><i class="fa-solid fa-book"></i> ??? flashcard sets</span>
                                <span><i class="fa-solid fa-user-group"></i> ??? classes</span>
                            </div>
                        </div>
                        <div class="top-creator-item">
                            <div>
                                <img src="/images/avt1.jpg" alt="Avatar">
                            </div>
                            <div>
                                <p>Name of user</p>
                            </div>
                            <div>
                                <span><i class="fa-solid fa-book"></i> ??? flashcard sets</span>
                                <span><i class="fa-solid fa-user-group"></i> ??? classes</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="./home/home.js"></script>
    </body>

</html>