<%-- 
    Document   : history
    Created on : Feb 21, 2025, 2:14:10 PM
    Author     : DUCA
--%>
<%@ page import="java.util.*, model.QuizHistory" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Quizzes</title>
        <link rel="stylesheet" href="./history/history.css">
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
                <p class="history-title">History</p>
                <div class="history-card">
                    <div class="button2">
                        <a href="quizhistory" class="quiz-button">Quiz</a>
                        <a href="folderhistory" class="folders-button">Folders</a>
                    </div>
                    <div style="margin-top: 20px;" class="flex justify-between items-center mb-4">
                        <select class="option-select border border-gray-300  rounded">
                            <option>Recent</option>
                            <option>Created</option>
                        </select>
                        <div class="relative w-1/3">
                            <input style="border: 2px solid #e6e6e6;" type="text" placeholder="Search for a quiz" class="w-full p-2 border border-gray-300 rounded pr-10">
                            <div class="absolute inset-y-0 right-0 pr-3 flex items-center pointer-events-none">
                                <svg class="h-5 w-5 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"></path>
                                </svg>
                            </div>
                        </div>
                    </div>
                    <div class="recent">
                        <c:if test="${hasData}">

                            <c:if test="${not empty todayQuizzes}">
                                <div class="term-header">
                                    <h2 class="text-lg font-bold">TODAY</h2>
                                    <hr/>
                                </div>
                                <c:forEach var="quiz" items="${todayQuizzes}">
                                    <div onclick="window.location.href = 'quizz?id=${quiz.quizSetId}'" class="quiz-box">
                                        <div class="quiz-title flex">
                                            <div class="quiz-detail">
                                                <span class="">${quiz.numberOfQuiz} terms</span>
                                                <span class="">|</span>
                                                <span class="">${quiz.author}</span>
                                            </div>
                                            <p class="title-text text-gray-600 ">${quiz.quizSetName}</p>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:if>


                            <c:if test="${not empty weekQuizzes}">
                                <div class="term-header">
                                    <h2 class="text-lg font-bold">THIS WEEK</h2>
                                    <hr/>
                                </div>
                                <c:forEach var="quiz" items="${weekQuizzes}">
                                    <div onclick="window.location.href = 'quizz?id=${quiz.quizSetId}'" class="quiz-box">
                                        <div class="quiz-title flex">
                                            <div>
                                                <span class="text-sm text-gray-600">${quiz.numberOfQuiz} terms</span>
                                                <span class="text-sm text-gray-600 ml-2">|</span>
                                                <span class="text-sm text-gray-600 ml-2">${quiz.author}</span>
                                            </div>
                                            <p class="title-text text-gray-600 mt-1">${quiz.quizSetName}</p>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:if>


                            <c:if test="${not empty monthlyQuizzes}">
                                <c:forEach var="entry" items="${monthlyQuizzes}">
                                    <div class="term-header">
                                        <h2 class="text-lg font-bold mb-2">${fn:split(entry.key, '-')[0]} / ${fn:split(entry.key, '-')[1]}</h2>
                                        <hr/>
                                    </div>
                                    <c:forEach var="quiz" items="${entry.value}">
                                        <div onclick="window.location.href = 'quizz?id=${quiz.quizSetId}'" class="quiz-box">
                                            <div class="quiz-title flex">
                                                <div>
                                                    <span class="text-sm text-gray-600">${quiz.numberOfQuiz} terms</span>
                                                    <span class="text-sm text-gray-600 ml-2">|</span>
                                                    <span class="text-sm text-gray-600 ml-2">${quiz.author}</span>
                                                </div>
                                                <p class="title-text text-gray-600 mt-1">${quiz.quizSetName}</p>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </c:forEach>
                            </c:if>
                        </c:if>


                        <c:if test="${not hasData}">
                            <div class="empty-quiz" style="margin-top: 140px">
                                <p style="text-align: center; font-size: 0.8rem; font-weight: 600; color: #666;">
                                    You haven't accessed any quiz set before
                                </p>
                                <img style="margin: 0 auto; width: 150px;" src="./images/icon/flashcard_icon.png" alt="Not found">
                            </div>
                        </c:if>
                    </div>
                    <div class="created">
                        <c:if test="${empty createdList}">
                            <div class="empty-quiz" style="margin-top: 140px">
                                <p style="text-align: center; font-size: 0.8rem; font-weight: 600; color: #666;">
                                    You haven't created any quiz set before
                                </p>
                                <img style="margin: 0 auto; width: 150px;" src="./images/icon/flashcard_icon.png" alt="Not found">
                            </div>
                        </c:if>
                        <c:forEach var="quiz" items="${createdList}">
                            <div onclick="window.location.href = 'quizz?id=${quiz.quizSetId}'" class="quiz-box">
                                <div class="quiz-title flex">
                                    <div>
                                        <span class="text-sm text-gray-600">${quiz.numberOfQuiz} terms</span>
                                    </div>
                                    <p class="title-text text-gray-600 mt-1">${quiz.quizSetName}</p>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
        <script src="./history/history.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/fuse.js@6.6.2"></script>
        <script>
                                document.addEventListener("DOMContentLoaded", function () {
                                    const searchInput = document.querySelector("input[placeholder='Search for a quiz']");
                                    const quizCards = Array.from(document.querySelectorAll(".quiz-card"));

                                    const quizzes = quizCards.map(card => ({
                                            element: card,
                                            name: card.querySelector(".title-text").innerText.trim()
                                        }));
                                    const fuse = new Fuse(quizzes, {
                                        keys: ["name"],
                                        threshold: 0.3
                                    });

                                    searchInput.addEventListener("input", function () {
                                        const query = this.value.trim().toLowerCase();

                                        if (!query) {
                                            quizCards.forEach(card => card.style.display = "block");
                                            return;
                                        }

                                        const results = fuse.search(query).map(result => result.item.element);
                                        quizCards.forEach(card => card.style.display = "none");
                                        results.forEach(card => card.style.display = "block");
                                    });
                                });
        </script>

    </body>

</html>
