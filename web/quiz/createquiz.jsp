<%-- 
    Document   : createquiz
    Created on : Mar 1, 2025, 12:51:31 AM
    Author     : Lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Quiz</title>
        <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <link rel="stylesheet" href="createquiz.css">
        <link rel="shortcut icon" href="./images/logo/Easyquiz_logo.png">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <script src="https://cdn.tailwindcss.com"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
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
                <div class="quiz-header">
                    <h1 class="form-title">Create a new quiz</h1>
                    <button class="create-btn">Create</button>
                </div>
                <input type="text" placeholder="Enter a title" class="input">
                <input type="text" placeholder="Add a description..." class="des input">
                <!-- Quiz Items -->
                <div class="quiz-item">
                    <div class="quiz-content">
                        <i class="fas fa-grip-vertical drag-handle"></i>
                        <div class="question">
                            <div class="question-header">
                                <input class="question-text input" type="text" placeholder="Enter question here">
                                <div class="actions">
                                    <button class="edit-btn btn-border">Edit</button>
                                    <i class="fas fa-trash"></i>
                                </div>
                            </div>
                            <p class="text-gray">Answer</p>
                            <div class="options">
                                <div class="option">
                                    <input type="radio" name="question2" value="a1" class="form-radio">
                                    <input type="text" placeholder="Answer here"   class="option-input">
                                </div>
                                <div class="option">
                                    <input type="radio" name="question2" value="a2" class="form-radio">
                                    <input type="text" placeholder="Answer here"  class="option-input">
                                </div>
                                <div class="option">
                                    <input type="radio" name="question2" value="a3" class="form-radio">
                                    <input type="text" placeholder="Answer here" class="option-input">
                                </div>
                                <div class="option">
                                    <input type="radio" name="question2" value="a4" class="form-radio">
                                    <input type="text" placeholder="Answer here" class="option-input">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="center-btn">
                    <button class="add-btn">+ Add a quiz</button>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
        <script src="createquiz.js"></script>
    </body>
</html>
