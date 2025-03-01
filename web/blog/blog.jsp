<%-- 
    Document   : blog
    Created on : Feb 18, 2025, 4:27:50 PM
    Author     : DUCA
--%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Blog</title>
        <link rel="stylesheet" href="blog.css">
        <link rel="shortcut icon" href="./images/logo/Easyquiz_logo.png">
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
                <input type="text" placeholder="Search for blog" name="">
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
                            <a href="#" class="nav-link">
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
                            <a href="#" class="nav-link-actived">
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
                            <a href="#" class="nav-link">
                                <span class="material-symbols-rounded">logout</span>
                                <span class="nav-label">Logout</span>
                            </a>
                        </li>
                    </ul>
                </nav>
            </aside>
            <div class="body-container">
                <div class="blog-container">
                    <h1>Blogs</h1>
                    <div class="blog-card">
                        <div class="content-header">
                            <h2>Blog 1</h2>
                            <div class="header-btn">
                                <button class="btn"><span class="material-symbols-rounded">share</span><p>Share</p></button>
                                <button class="btn"><span class="material-symbols-rounded">more_horiz</span></button>
                            </div>
                        </div>
                        <div class="blog-header">
                            <img alt="" src="./images/avatar/default.png"/> 
                            <span style="margin-right: 20px;">Name of user</span>
                            <span class="material-symbols-rounded">update</span>
                            <span>Created 01/01/25</span>
                        </div>
                        <div class="blog-content">
                            <h3>Title</h3>
                            <div class="">
                                <p>
                                    Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolores tempore exercitationem neque voluptate temporibus quam veniam earum, enim totam tempora qui consectetur itaque quibusdam amet et praesentium? Deleniti, consequuntur nostrum? 
                                    Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolores tempore exercitationem neque voluptate temporibus quam veniam earum, enim totam tempora qui consectetur itaque quibusdam amet et praesentium? Deleniti, consequuntur nostrum?
                                </p>
                            </div>
                            <div class="blog-comment">
                                <input type="text" placeholder="Your comment here...">
                                <span class="send-btn material-symbols-rounded">send</span>
                                <span class="chat-btn material-symbols-rounded">chat</span>
                            </div>
                        </div>
                    </div>
                    <div class="blog-card">
                        <div class="content-header">
                            <h2>Blog 2</h2>
                            <div class="header-btn">
                                <button class="btn"><span class="material-symbols-rounded">share</span><p>Share</p></button>
                                <button class="btn"><span class="material-symbols-rounded">more_horiz</span></button>
                            </div>
                        </div>
                        <div class="blog-header">
                            <img alt="" src="./images/avatar/default.png"/> 
                            <span style="margin-right: 20px;">Name of user</span>
                            <span class="material-symbols-rounded">update</span>
                            <span>Created 01/01/25</span>
                        </div>
                        <div class="blog-content">
                            <h3>Title</h3>
                            <div class="">
                                <p>
                                    Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolores tempore exercitationem neque voluptate temporibus quam veniam earum, enim totam tempora qui consectetur itaque quibusdam amet et praesentium? Deleniti, consequuntur nostrum? 
                                    Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolores tempore exercitationem neque voluptate temporibus quam veniam earum, enim totam tempora qui consectetur itaque quibusdam amet et praesentium? Deleniti, consequuntur nostrum?
                                </p>
                            </div>
                            <div class="blog-comment">
                                <input type="text" placeholder="Your comment here...">
                                <span class="send-btn material-symbols-rounded">send</span>
                                <span class="chat-btn material-symbols-rounded">chat</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="blog.js"></script>
    </body>

</html>
