<%-- 
    Document   : addquiztofolder
    Created on : Mar 5, 2025, 12:28:08 AM
    Author     : Lenovo
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Folder</title>
        <link rel="stylesheet" href="./history/foldercontain.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <script src="https://cdn.tailwindcss.com"></script>
        <link rel="shortcut icon" href="./images/logo/Easyquiz_logo.png">
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
                                <img src="${sessionScope.account.profileImage}" alt="Not found"/>
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
        </div>
        <form action="foldercontain" method="get">
            <div class="folderPopup-container">
                <div id="folderPopup" class="folder-popup">
                    <div class="folder-popup-content">
                        <span class="close-btn material-symbols-rounded">close</span>
                        <h2>Create a new folder</h2>
                        <input type="text" id="folderName" name="folderName" placeholder="Title" class="folder-input">
                        <div class="create-folder-btn">
                            <button type="submit" id="createFolderConfirm">Create folder</button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
        <div class="popup-overlay" id="popup-folder">
            <div class="popup-content">
                <button class="btn-close" id="closePopup">&times;</button>
                <h2 class="add-folder">Add Quiz Set to folder</h2>
                <c:if test="${empty requestScope.allList}">
                    <div class="empty-item">
                        <p>You haven't created or studied any items yet.</p>
                        <button class="create-item" type="button">Create Quiz Set</button>
                    </div>
                </c:if>
                <c:forEach items="${requestScope.allList}" var="quizItem">
                    <div class="item-list">
                        <div class="item">
                            <div class="item-info">
                                <img class="item-icon" src="./images/icon/flashcard_icon.png" alt="Not Found" />
                                <div>
                                    <p class="item-name">${quizItem.quizSetName}</p>
                                    <p class="item-meta">Quiz set · ${quizItem.numberOfQuiz} terms · Author: ${quizItem.author}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                <div class="footer">
                    <button class="submit-button">Complete</button>
                </div>
            </div>

        </div>
        <div class="body">
            <aside class="sidebar">
                <nav class="sidebar-nav">
                    <!--Top nav-->
                    <ul class="nav-list primary-nav">
                        <li class="nav-item">
                            <a href="home" class="nav-link">
                                <span class="material-symbols-rounded">home</span>
                                <span class="nav-label">Home</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="quizhistory" class="nav-link-actived">
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
                <div class="content">
                    <h1 class="folder-title">${folder.folderName}</h1>
                    <div class="btn-area">
                        <button class="add-btn"><i class="fa-solid fa-plus"></i></button>
                        <button class="delete-btn"><i class="fa-solid fa-trash"></i></button>
                    </div>
                </div>
                <c:if test="${empty requestScope.quiz}">
                    <div class="empty-folder">
                        Empty folder
                    </div>
                </c:if>
                <c:forEach items="${requestScope.quiz}" var="quiz">
                    <div class="quiz-card bg-white-100 rounded mb-4">
                        <div class="quiz-title flex">
                            <div>
                                <span class="text-sm text-gray-600">${quiz.numberOfQuiz} terms</span>
                                <span class="text-sm text-gray-600 ml-2">|</span>
                                <span class="text-sm text-gray-600 ml-2">${quiz.author}</span>
                            </div>
                            <span class="title-text text-gray-600 mt-1">${quiz.quizSetName}</span>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
        <script src="./history/history.js"></script>
        <script src="./history/foldercontain.js"></script>
    </body>
</html>
