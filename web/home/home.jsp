<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Home</title>
        <link rel="stylesheet" href="./home/home.css">
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
                    <a href="test"><button>Log in</button></a>
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
                                <img src="" alt="Avatar">
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
                                <img src="" alt="Avatar">
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
                                <img src="" alt="Avatar">
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