
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Home</title>
        <link rel="stylesheet" href="./home/home.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css" />
        <link rel="shortcut icon" href="./images/logo/Easyquiz_logo.png">
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
                <c:if test="${not empty sessionScope.account.userName}">
                    <div class="create-btn-icon" id="createButton">
                        <span><button><i class="fa-solid fa-plus"></i></button></span>
                        <div class="create-menu" id="createMenu">
                            <a href="#" class="create-menu-item"><i class="fa-solid fa-book"></i> Flashcard set</a>
                            <a href="#" class="create-menu-item"><i class="fa-solid fa-folder"></i> Folder</a>
                            <a href="#" class="create-menu-item"><i class="fa-solid fa-user-group"></i> Class</a>
                        </div>
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
                            <a href="#" class="user-menu-item"><i class="fa-solid fa-user"></i> Profile</a>
                            <a href="#" class="user-menu-item"><i class="fa-solid fa-gear"></i> Settings</a>
                            <hr/>
                            <a href="logout" class="user-menu-item">Logout</a>
                            <hr/>
                            <a href="#" class="user-menu-item">Help and feedback</a>
                            <a href="#" class="user-menu-item">Upgrades</a>
                        </div>
                    </div>
                </c:if>
                <c:if test="${empty sessionScope.account.userName}">
                    <div class="login-btn">
                        <a href="login"><button>Log in</button></a>
                    </div>
                </c:if>

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
                <div class="container swiper">
                    <h2>Popular flashcard sets</h2>
                    <div class="card-wrapper">
                        <ul class="card-list swiper-wrapper">
                            <c:forEach items="${popularQuizSet}" var="i">
                                <li class="card-item swiper-slide">
                                    <a href="#" class="card-link">
                                        <h2 class="card-title">${i.quizSetName}</h2>
                                        <p class="badge">${i.numberOfQuiz} terms</p>
                                        <div class="card-username">
                                            <span style="display: flex; align-items: center;">
                                                <img src="${i.author.profileImage}" alt="Avatar">
                                                <span>${i.author.userName}</span>
                                            </span>
                                            <button class="card-button material-symbols-rounded">arrow_forward</button>
                                        </div>
                                    </a>
                                </li>
                            </c:forEach>
                        </ul>
                        <div class="swiper-pagination"></div>
                        <div class="swiper-button-prev"></div>
                        <div class="swiper-button-next"></div>
                    </div>
                </div>
                <div class="container swiper">
                    <h2>Popular blogs</h2>
                    <div class="card-wrapper">
                        <ul class="card-list swiper-wrapper">
                            <li class="card-item swiper-slide">
                                <a href="#" class="card-link">
                                    <h2 class="card-title">Title of blog</h2>
                                    <p class="preview-content">Lorem ipsum, dolor sit amet consectetur adipisicing elit. Dicta, id adipisci dolor sapiente iusto excepturi, eum omnis voluptatem reprehenderit doloremque repellat at asperiores expedita maxime facere deserunt corrupti culpa officia!</p>
                                    <div class="card-username">
                                        <span style="display: flex; align-items: center;">
                                            <img src="avt1.jpg" alt="Avatar">
                                            <span>Name of user</span>
                                        </span>
                                        <button class="card-button material-symbols-rounded">arrow_forward</button>
                                    </div>
                                </a>
                            </li>
                            <li class="card-item swiper-slide">
                                <a href="#" class="card-link">
                                    <h2 class="card-title">Title of blog</h2>
                                    <p class="preview-content">Lorem ipsum, dolor sit amet consectetur adipisicing elit. Dicta, id adipisci dolor sapiente iusto excepturi, eum omnis voluptatem reprehenderit doloremque repellat at asperiores expedita maxime facere deserunt corrupti culpa officia!</p>
                                    <div class="card-username">
                                        <span style="display: flex; align-items: center;">
                                            <img src="avt1.jpg" alt="Avatar">
                                            <span>Name of user</span>
                                        </span>
                                        <button class="card-button material-symbols-rounded">arrow_forward</button>
                                    </div>
                                </a>
                            </li>
                            <li class="card-item swiper-slide">
                                <a href="#" class="card-link">
                                    <h2 class="card-title">Title of blog</h2>
                                    <p class="preview-content">Lorem ipsum, dolor sit amet consectetur adipisicing elit. Dicta, id adipisci dolor sapiente iusto excepturi, eum omnis voluptatem reprehenderit doloremque repellat at asperiores expedita maxime facere deserunt corrupti culpa officia!</p>
                                    <div class="card-username">
                                        <span style="display: flex; align-items: center;">
                                            <img src="avt1.jpg" alt="Avatar">
                                            <span>Name of user</span>
                                        </span>
                                        <button class="card-button material-symbols-rounded">arrow_forward</button>
                                    </div>
                                </a>
                            </li>
                        </ul>
                        <div class="swiper-pagination"></div>
                        <div class="swiper-button-prev"></div>
                        <div class="swiper-button-next"></div>
                    </div>
                </div>
                <div class="container swiper">
                    <h2>Top creator</h2>
                    <div class="card-wrapper">
                        <ul class="card-list swiper-wrapper">
                            <li class="card-item swiper-slide">
                                <a href="#" class="card-link">
                                    <div class="card-avt">
                                        <img src="avt1.jpg" alt="Avatar">
                                        <p>Name of user</p>
                                    </div>
                                    <div class="card-username">
                                        <p class="badge2"><i class="fa-solid fa-book"></i> ??? flashcard sets</p>
                                        <button class="card-button material-symbols-rounded">arrow_forward</button>
                                    </div>
                                </a>
                            </li>
                            <li class="card-item swiper-slide">
                                <a href="#" class="card-link">
                                    <div class="card-avt">
                                        <img src="avt1.jpg" alt="Avatar">
                                        <p>Name of user</p>
                                    </div>
                                    <div class="card-username">
                                        <p class="badge2"><i class="fa-solid fa-book"></i> ??? flashcard sets</p>
                                        <button class="card-button material-symbols-rounded">arrow_forward</button>
                                    </div>
                                </a>
                            </li>
                            <li class="card-item swiper-slide">
                                <a href="#" class="card-link">
                                    <div class="card-avt">
                                        <img src="avt1.jpg" alt="Avatar">
                                        <p>Name of user</p>
                                    </div>
                                    <div class="card-username">
                                        <p class="badge2"><i class="fa-solid fa-book"></i> ??? flashcard sets</p>
                                        <button class="card-button material-symbols-rounded">arrow_forward</button>
                                    </div>
                                </a>
                            </li>
                            <li class="card-item swiper-slide">
                                <a href="#" class="card-link">
                                    <div class="card-avt">
                                        <img src="avt1.jpg" alt="Avatar">
                                        <p>Name of user</p>
                                    </div>
                                    <div class="card-username">
                                        <p class="badge2"><i class="fa-solid fa-book"></i> ??? flashcard sets</p>
                                        <button class="card-button material-symbols-rounded">arrow_forward</button>
                                    </div>
                                </a>
                            </li>
                            <li class="card-item swiper-slide">
                                <a href="#" class="card-link">
                                    <div class="card-avt">
                                        <img src="avt1.jpg" alt="Avatar">
                                        <p>Name of user</p>
                                    </div>
                                    <div class="card-username">
                                        <p class="badge2"><i class="fa-solid fa-book"></i> ??? flashcard sets</p>
                                        <button class="card-button material-symbols-rounded">arrow_forward</button>
                                    </div>
                                </a>
                            </li>
                        </ul>
                        <div class="swiper-pagination"></div>
                        <div class="swiper-button-prev"></div>
                        <div class="swiper-button-next"></div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Linking SwiperJS script -->
        <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
        <script src="./home/home.js"></script>
    </body>

</html>