<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Name of user</title>
    <link rel="stylesheet" href="profile.css">
    <link rel="shortcut icon" href="./images/logo/Easyquiz_logo.png">
    <link rel="stylesheet"
        href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
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
        <div class="search">
            <i class="fa-solid fa-magnifying-glass"></i>
            <input type="text" placeholder="Search for study guides" name="">
        </div>
        <div class="create-login">
            <div class="create-btn-icon" id="createButton">
                <span><button><i class="fa-solid fa-plus"></i></button></span>
                <div class="create-menu" id="createMenu">
                    <a href="#" class="create-menu-item"><i class="fa-solid fa-book"></i> Flashcard set</a>
                    <a href="#" class="create-menu-item" id="createFolderItem"><i class="fa-solid fa-folder"></i>
                        Folder</a>
                </div>
            </div>
            <div class="upgrade-btn">
                <button>Upgrade: Free 7-day trial</button>
            </div>
            <div class="avatar-user" id="avatarUser">
                <img src="../images/avatar/default.png" alt="Not found">
                <div class="user-menu" id="userMenu">
                    <div class="user-info">
                        <img src="../images/avatar/default.png" alt="Not found" />
                        <div>
                            <p>Do Duc Anh</p>
                            <p>duca@gmail.com</p>
                        </div>
                    </div>
                    <hr />
                    <a href="#" class="user-menu-item"><i class="fa-solid fa-user"></i> Profile</a>
                    <a href="#" class="user-menu-item"><i class="fa-solid fa-gear"></i> Settings</a>
                    <hr />
                    <a href="logout" class="user-menu-item">Logout</a>
                    <hr />
                    <a href="#" class="user-menu-item">Help and feedback</a>
                    <a href="#" class="user-menu-item">Upgrades</a>
                </div>
            </div>
            <div class="login-btn">
                <a href="login"><button>Log in</button></a>
            </div>
        </div>
    </div>
    <div class="folderPopup-container">
        <div id="folderPopup" class="folder-popup">
            <div class="folder-popup-content">
                <span class="close-btn material-symbols-rounded">close</span>
                <h2>Create a new folder | EasyQuiz</h2>
                <input type="text" id="folderName" placeholder="Title" class="folder-input">
                <div class="create-folder-btn">
                    <button id="createFolderConfirm">Create folder</button>
                </div>
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
                        <a href="history" class="nav-link">
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
            <div class="user-container">
                <img src="../images/avatar/default.png" alt="">
                <div class="user-name">
                    <h2>Name</h2>
                    <p>Email name</p>
                </div>
            </div>
            <div class="flashcard-folder">
                <div class="flashcard-folder-header">
                    <a href="#" class="flashcard-folder-item">Flashcard sets</a>
                    <a href="#" class="flashcard-folder-item-actived">Folders</a>
                </div>
                <div class="search-user">
                    <input type="text" placeholder="Search your folders" name="">
                    <i class="fa-solid fa-magnifying-glass"></i>
                </div>
                <div class="folder-container">
                    <ul class="folder-list">
                        <li class="folder-item">
                            <a href="" class="folder-link">
                                <p>15 terms</p>
                                <div class="title-icon">
                                    <span class="material-symbols-rounded">folder</span>
                                    <h3>Lorem ipsum, dolor sit amet consectetur adipisicing elit. </h3>
                                </div>
                            </a>
                        </li>
                        <li class="folder-item">
                            <a href="" class="folder-link">
                                <p>15 terms</p>
                                <div class="title-icon">
                                    <span class="material-symbols-rounded">folder</span>
                                    <h3>Lorem ipsum, dolor sit amet consectetur adipisicing elit. </h3>
                                </div>
                            </a>
                        </li>
                        <li class="folder-item">
                            <a href="" class="folder-link">
                                <p>15 terms</p>
                                <div class="title-icon">
                                    <span class="material-symbols-rounded">folder</span>
                                    <h3>Lorem ipsum, dolor sit amet consectetur adipisicing elit. </h3>
                                </div>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <script src="profile.js"></script>
</body>

</html>