<%-- 
    Document   : addquiztofolder
    Created on : Mar 5, 2025, 12:28:08 AM
    Author     : Lenovo
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
            <form action="searchall" style="width: 100%; max-width: 450px;">
                <div class="search">
                    <i class="fa-solid fa-magnifying-glass"></i>
                    <input type="text" placeholder="Search for study" name="input">
                </div>
            </form>
            <div class="create-login">
                <c:if test="${not empty sessionScope.account.userName}">
                    <div class="create-btn-icon" id="createButton">
                        <span><button><i class="fa-solid fa-plus"></i></button></span>
                        <div class="create-menu" id="createMenu">
                            <a href="addquiz" class="create-menu-item"><i class="fa-solid fa-book"></i> Flashcard set</a>
                            <a href="#" class="create-menu-item" id="createFolderItem"><i class="fa-solid fa-folder"></i> Folder</a>
                        </div>
                    </div>
                    <fmt:formatDate value="<%= new java.util.Date() %>" pattern="yyyy-MM-dd" var="today" />
                    <c:choose>
                        <c:when test="${sessionScope.account.expiredDate > today}">
                            <div class="upgrade-btn">
                                <a href="upgrade">Extend your package</a>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="upgrade-btn">
                                <a href="upgrade">Upgrade your package</a>
                            </div>
                        </c:otherwise>
                    </c:choose>
                    <div class="avatar-user"  id="avatarUser">
                        <img src="${sessionScope.account.profileImage}" alt="Not found">
                        <div class="user-menu" id="userMenu">
                            <div class="user-info">
                                <img src="${sessionScope.account.profileImage}" alt="Not found"/>
                                <div>
                                    <div>
                                        <p>${sessionScope.account.userName}</p>
                                        <c:if test="${sessionScope.account.expiredDate > today}">
                                            <span class="premium-icon material-symbols-rounded">crown</span>
                                        </c:if>
                                    </div>
                                    <p>
                                        <c:choose>
                                            <c:when test="${fn:length(sessionScope.account.email) > 15}">
                                                ${fn:substring(sessionScope.account.email, 0, 15)}...
                                            </c:when>
                                            <c:otherwise>
                                                ${sessionScope.account.email}
                                            </c:otherwise>
                                        </c:choose>
                                    </p>
                                </div>
                            </div>
                            <hr/>
                            <a href="setting" class="user-menu-item"><i class="fa-solid fa-user"></i> Profile</a>
                            <a href="feedback" class="user-menu-item"><i class="fa-solid fa-circle-info"></i> Help and feedback</a>
                            <a href="upgrade" class="user-menu-item"><i class="fa-solid fa-crown"></i> Upgrades</a>
                            <hr/>
                            <a href="logout" class="user-menu-item"><i class="fa-solid fa-arrow-right-from-bracket"></i> Logout</a>
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
        <form action="createfolder" method="post">
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
                <button class="btn-close" id="closePopup"><span class="close-btn material-symbols-rounded">close</span></button>
                <h2 class="add-folder">Add Quiz Set to folder</h2>
                <c:if test="${empty requestScope.allList}">
                    <div class="empty-item">
                        <p>You haven't created or studied any items yet.</p>
                        <button onclick="window.location.href = 'addquiz'" class="create-item" type="button">Create Quiz Set</button>
                    </div>
                </c:if>
                <c:forEach items="${requestScope.allList}" var="quizItem">
                    <div class="item-list">
                        <div class="item ${fn:contains(quizIds, quizItem.quizSetId) ? 'selected' : ''}" data-id="${quizItem.quizSetId}">
                            <div class="item-info">
                                <img class="item-icon" src="./images/icon/flashcard_icon.png" alt="Not Found" />
                                <div>
                                    <p class="item-name">${quizItem.quizSetName}</p>
                                    <p class="item-meta">Quiz set &nbsp;· &nbsp; ${quizItem.numberOfQuiz} terms &nbsp;· &nbsp;  Author: 
                                        <c:choose>
                                            <c:when test="${quizItem.author.userName eq account.userName}">
                                                You
                                            </c:when>
                                            <c:otherwise>
                                                ${quizItem.author.userName}
                                            </c:otherwise>
                                        </c:choose></p>
                                </div>
                            </div>
                            <div class="ticked"><i class="fa-solid fa-check"></i></div>
                        </div>
                    </div>
                </c:forEach>
                <div class="footer">
                    <button onclick="window.location.href = 'foldercontain?folderId=${folder.folderId}'" class="submit-button">Complete</button>
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
                        <c:if test="${sessionScope.account.isAdmin}">
                            <li class="nav-item">
                                <a href="dashboard" class="nav-link">
                                    <span class="material-symbols-rounded">dashboard</span>
                                    <span class="nav-label">Dashboard</span>
                                </a>
                            </li>
                        </c:if>
                    </ul>
                    <!--Bottom nav-->
                    <ul class="nav-list secondary-nav">
                        <li class="nav-item">
                            <a href="setting" class="nav-link">
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
                    <input type="hidden" id="folderId" value="${folder.folderId}">
                    <div class="btn-area">
                        <button class="add-btn"><i class="fa-solid fa-plus"></i></button>
                        <button onclick="window.location.href = 'deletefolder?folderId=${folder.folderId}'" class="delete-btn"><i class="fa-solid fa-trash"></i></button>
                    </div>
                </div>
                <c:if test="${empty requestScope.quiz}">
                    <div class="empty-folder">
                        Empty folder
                    </div>
                </c:if>
                <c:forEach items="${requestScope.quiz}" var="quiz">
                    <div onclick="window.location.href = 'quizz?id=${quiz.quizSetId}'" class="quiz-card bg-white-100 rounded mb-4">
                        <div class="quiz-title flex">
                            <div>
                                <span class="text-sm text-gray-600">${quiz.numberOfQuiz} terms</span>
                                <span class="text-sm text-gray-600 ml-2">|</span>
                                <span class="text-sm text-gray-600 ml-2">
                                    <c:choose>
                                        <c:when test="${quiz.author.userName eq account.userName}">
                                            You
                                        </c:when>
                                        <c:otherwise>
                                            ${quiz.author.userName}
                                        </c:otherwise>
                                    </c:choose>
                                </span>
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
