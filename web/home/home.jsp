
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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

        <% // -----------------------------Success message
    String successMessage = (String) session.getAttribute("successMessage");
    if (successMessage != null) {
        %>
        <div id="toastMessage1">
            <span class="material-symbols-rounded">check</span>
            <span><%= successMessage %></span>
        </div>
        <script>
            setTimeout(function () {
                let toast1 = document.getElementById("toastMessage1");
                toast1.style.opacity = "0";
                setTimeout(() => {
                    toast1.style.display = "none";
                }, 500); // Ẩn hoàn toàn sau 0.5 giây sau khi mờ
            }, 3000);
        </script>
        <%
            session.removeAttribute("successMessage"); // Xóa sau khi hiển thị
            }
        %>

        <%// -----------------------------Error message
    String errorMessage = (String) request.getAttribute("error");
    if (errorMessage == null) {
        errorMessage = (String) session.getAttribute("error");
    }
    if (errorMessage != null) {
        %>
        <div id="toastMessage2">
            <span class="material-symbols-rounded">close</span>
            <span><%= errorMessage %></span>
        </div>
        <script>
            setTimeout(function () {
                let toast2 = document.getElementById("toastMessage2");
                toast2.style.opacity = "0";
                setTimeout(() => {
                    toast2.style.display = "none";
                }, 500);
            }, 3000);
        </script>
        <%
            session.removeAttribute("error");
            }
        %>

        <% // -----------------------------Warning message
    String warningMessage = (String) session.getAttribute("warningMessage");
    if (warningMessage != null) {
        %>
        <div id="toastMessage3">
            <span class="material-symbols-rounded">warning</span>
            <span><%= warningMessage %></span>
        </div>
        <script>
            setTimeout(function () {
                let toast3 = document.getElementById("toastMessage3");
                toast3.style.opacity = "0";
                setTimeout(() => {
                    toast3.style.display = "none";
                }, 500); // Ẩn hoàn toàn sau 0.5 giây sau khi mờ
            }, 5000);
        </script>
        <%
            session.removeAttribute("warningMessage"); // Xóa sau khi hiển thị
            }
        %>

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
                            <a class="create-menu-item" id="createFolderItem"><i class="fa-solid fa-folder"></i> Folder</a>
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
                        <c:if test="${not empty sessionScope.account.userName}">
                            <li class="nav-item">
                                <a href="logout" class="nav-link">
                                    <span class="material-symbols-rounded">logout</span>
                                    <span class="nav-label">Logout</span>
                                </a>
                            </li>
                        </c:if>
                    </ul>
                </nav>
            </aside>
            <div class="body-container">
                <c:if test="${not empty sessionScope.account.userName and quizSetHistoryTop4Size != 0}">
                    <div class="recents-container">
                        <h2>Recents</h2>
                        <div class="recents-list">
                            <c:forEach items="${quizSetHistoryTop4}" var="quizSet"> 
                                <div class="recents-item" onclick="window.location.href = 'quizz?id=${quizSet.quizSetId}'">
                                    <div>
                                        <i class="fa-solid fa-book"></i> 
                                    </div>
                                    <div>
                                        <div>
                                            <p>
                                                <c:choose>
                                                    <c:when test="${fn:length(quizSet.quizSetName) > 40}">
                                                        ${fn:substring(quizSet.quizSetName, 0, 40)}...
                                                    </c:when>
                                                    <c:otherwise>
                                                        ${quizSet.quizSetName}
                                                    </c:otherwise>
                                                </c:choose>
                                            </p>
                                            <span>Quiz Set - ${quizSet.numberOfQuiz} terms - by ${quizSet.author.userName}</span>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </c:if>
                <div class="container ${fn:length(popularQuizSet) <= 3 ? '' : 'swiper'}">
                    <h2>Popular Flashcard sets</h2>
                    <div class="card-wrapper">
                        <ul class="card-list swiper-wrapper">
                            <c:forEach items="${popularQuizSet}" var="i">
                                <li class="card-item swiper-slide" onclick="window.location.href = 'quizz?id=${i.quizSetId}'">
                                    <a href="#" class="card-link">
                                        <h2 class="card-title">
                                            <c:choose>
                                                <c:when test="${fn:length(i.quizSetName) > 25}">
                                                    ${fn:substring(i.quizSetName, 0, 25)}...
                                                </c:when>
                                                <c:otherwise>
                                                    ${i.quizSetName}
                                                </c:otherwise>
                                            </c:choose>
                                        </h2>
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


                <div class="container ${fn:length(popularBlog) <= 3 ? '' : 'swiper'}">
                    <h2>Popular Blog posts</h2>
                    <div class="card-wrapper">
                        <ul class="card-list swiper-wrapper">
                            <c:forEach items="${popularBlog}" var="blog">
                                <li class="card-item swiper-slide">
                                    <a class="card-link"
                                       data-blogid="${blog.blogId}"
                                       data-title="${blog.blogTitle}"
                                       data-author="${blog.author.userName}"
                                       data-content="${fn:escapeXml(blog.blogContent)}"
                                       data-date="${blog.createdDate}"
                                       data-comments='[
                                       <c:forEach items="${blog.comments}" var="comment" varStatus="loop">
                                           {"userName": "${comment.userName}", "content": "${fn:escapeXml(comment.commentContent)}"}
                                           <c:if test="${!loop.last}">,</c:if>
                                       </c:forEach>
                                       ]'
                                       onclick="window.location.href = 'blogdetail?blogId=${blog.blogId}'">
                                        <h2 class="card-title">
                                            <c:choose>
                                                <c:when test="${fn:length(blog.blogTitle) > 25}">
                                                    ${fn:substring(blog.blogTitle, 0, 25)}...
                                                </c:when>
                                                <c:otherwise>
                                                    ${blog.blogTitle}
                                                </c:otherwise>
                                            </c:choose>
                                        </h2>
                                        <p class="preview-content">
                                            <c:choose>
                                                <c:when test="${fn:length(blog.blogContent) > 100}">
                                                    ${fn:substring(blog.blogContent, 0, 100)}...
                                                </c:when>
                                                <c:otherwise>
                                                    ${blog.blogContent}
                                                </c:otherwise>
                                            </c:choose>
                                        </p>
                                        <div class="card-username">
                                            <span style="display: flex; align-items: center;">
                                                <img src="${blog.author.profileImage}" alt="Avatar">
                                                <span>${blog.author.userName}</span>
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
                <div id="blogModal" class="blog-modal">
                    <div class="blog-modal-content">
                        <span class="blog-modal-close material-symbols-rounded">close</span>
                        <div class="blog-card">
                            <div class="content-header" style="cursor: pointer">
                                <h2 id="modalTitle"></h2>
                            </div>
                            <div class="blog-header">
                                <img id="modalAvatar" src="./images/avatar/default.png" alt="Avatar"/>
                                <span id="modalAuthor" style="margin-right: 20px;"></span>
                                <span class="material-symbols-rounded">update</span>
                                <span id="modalCreatedDate"></span>
                            </div>
                            <div class="blog-content">
                                <h3 id="modalContentTitle"></h3>
                                <p style="font-size: 16px;" id="modalContent"></p>
                            </div>
                            <hr>
                            <div class="comment-container">
                                <h4>Comments</h4>
                                <div id="modalComments"></div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="container ${fn:length(topAuthors) <= 3 ? '' : 'swiper'}">
                    <h2>Top creator</h2>
                    <div class="card-wrapper">
                        <ul class="card-list swiper-wrapper">
                            <c:forEach items="${topAuthors}" var="creator"> 
                                <li class="card-item swiper-slide" onclick="window.location.href = 'user?username=${creator.account.userName}&type=sets'">
                                    <a class="card-link">
                                        <div class="card-avt">
                                            <img src="${creator.account.profileImage}" alt="Avatar"> 
                                            <p>${creator.account.userName}</p> 
                                        </div>
                                        <div class="card-username">
                                            <p class="badge2"><i class="fa-solid fa-book"></i> ${creator.numberOfQuizSet} flashcard sets</p> 
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
            </div>
        </div>
        <!-- Linking SwiperJS script -->
        <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
        <script src="./home/home.js"></script>
    </body>

</html>