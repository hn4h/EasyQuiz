<%-- 
    Document   : editquiz
    Created on : Mar 13, 2025, 2:08:22 PM
    Author     : 11
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Quiz</title>
        <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <link rel="stylesheet" href="./quiz/createquiz.css">
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
                            <a href="home" class="nav-link-actived">
                                <span class="material-symbols-rounded">home</span>
                                <span class="nav-label">Home</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="quizhistory" class="nav-link">
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
                <form action="editquiz" method="post" id="quizForm">
                    <div class="quiz-header">
                        <h1 class="form-title">Edit quiz</h1>
                        <button type="submit" class="create-btn">Save changes</button>
                    </div>
                    <input type="hidden" name="quizSetId" value="${requestScope.quizset.quizSetId}">
                    <input type="hidden" id="questionCount" name="questionCount">
                    <input type="text" value="${requestScope.quizset.quizSetName}" placeholder="Enter a title" class="input" name="quizTitle">
                    <input type="text" value="${requestScope.quizset.quizSetDescription}" placeholder="Add a description..." class="des input" name="quizDescription">
                    <!-- Quiz Items -->
                    <c:forEach var="quiz" items="${quizzes}">
                        <div class="quiz-item">
                            <div class="quiz-content">
                                <i class="fas fa-grip-vertical drag-handle"></i>
                                <div class="question">
                                    <div class="question-header">
                                        <input class="question-id" type="hidden" name="quizId1" value="${quiz.quizID}">
                                        <input class="question-text input" type="text" value="${quiz.content}" name="question1" placeholder="Enter question here">
                                        <div class="actions">  
                                            <i class="fas fa-trash"></i>
                                        </div>
                                    </div>
                                    <p class="text-gray">Answer</p>
                                    <div class="options">
                                        <c:forEach var="a" items="${quiz.answers}" varStatus="status">
                                            <div class="option">
                                                <input type="hidden" name="answerId_${quizLoop.index}_${answerLoop.index}" value="${a.answerID}">
                                                <input type="radio" name="correct${a.quizID}" value="${status.index + 1}"
                                                       <c:if test="${a.isCorrect}">checked</c:if> class="form-radio">
                                                <input type="text" value="${a.content}" placeholder="Option ${status.index + 1}" name="answer${a.quizID}.${status.index + 1}" class="option-input">
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </form>
                <div class="center-btn">
                    <button class="add-btn">+ Add a Quiz</button>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
        <script src="./quiz/createquiz.js"></script>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                document.querySelectorAll(".quiz-item").forEach((quizItem, quizIndex) => {
                    let options = quizItem.querySelectorAll(".option");

                    options.forEach((option, optionIndex) => {
                        let radio = option.querySelector("input[type='radio']");
                        let textInput = option.querySelector("input[type='text']");
//                        let isCorrect = textInput.dataset.correct === "true";

                        radio.value = optionIndex + 1;
                        textInput.placeholder = `Option ${optionIndex + 1}`;
                        radio.checked = isCorrect;
                    });
                });
            });


        </script>
    </body>
</html>
