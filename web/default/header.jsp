<%-- 
    Document   : header
    Created on : Mar 1, 2025, 12:36:28 AM
    Author     : Lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Header</title>
        <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <link rel="shortcut icon" href="./images/logo/Easyquiz_logo.png">
        <link rel="stylesheet" href="default.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <script src="https://cdn.tailwindcss.com"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    </head>
    <body>
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
                            <a href="#" class="create-menu-item" id="createFolderItem"><i class="fa-solid fa-folder"></i> Folder</a>
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
                                    <p>${sessionScope.account.userName}</p>
                                    <p>${sessionScope.account.email}</p>
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
        <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
        <script src="default.js"></script>
    </body>
</html>
