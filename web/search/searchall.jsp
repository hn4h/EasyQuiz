<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Search</title>
        <link rel="shortcut icon" href="./images/logo/Easyquiz_logo.png">
        <link rel="stylesheet" href="./search/search.css">
        <link rel="stylesheet"
              href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
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
                <div class="search-container">
                    <h2>Result for "${requestScope.input}"</h2>
                    <div class="search-header">
                        <a href="searchall?input=${input}" class="search-item-actived">All results</a>
                        <a href="searchquizset?input=${input}" class="search-item">Flashcard sets</a>
                        <a href="searchuser?input=${input}" class="search-item">Users</a>
                        <a href="searchBlog?input=${input}" class="search-item">Blogs</a>
                    </div>
                    <div class="search-result">
                        <div class="result-container">
                            <div class="result-header">
                                <h4>Flashcard sets</h4>
                                <c:if test="${not empty quizSet}"><a href="searchquizset?input=${input}">View all</a></c:if>
                                </div>
                                <ul class="card-list">
                                <c:forEach begin="0" end="2" step="1" items="${quizSet}" var="i">
                                    <li class="card-item" onclick="window.location.href = 'quizz?id=${i.quizSetId}'">
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
                                <c:if test="${empty quizSet}">
                                    <li class="empty-card-item">
                                        No result founded
                                    </li>
                                </c:if>
                            </ul>
                        </div>
                        <div class="result-container">
                            <div class="result-header">
                                <h4>Users</h4>
                                <c:if test="${not empty creator}"><a href="searchuser?input=${input}">View all</a></c:if>
                                </div>
                                <ul class="card-list">
                                <c:forEach begin="0" end="2" step="1" items="${requestScope.creator}" var="creator">
                                    <li class="card-item" onclick="window.location.href='user?username=${creator.account.userName}'">
                                        <a href="#" class="card-link">
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
                                <c:if test="${empty creator}">
                                    <li class="empty-card-item">
                                        No result founded
                                    </li>
                                </c:if>
                            </ul>
                        </div>
                        <div class="result-container">
                            <div class="result-header">
                                <h4>Blogs</h4>
                                <c:if test="${not empty blog}"><a href="searchBlog?input=${input}">View all</a></c:if>
                                </div>
                                <ul class="card-list">
                                <c:forEach begin="0" end="2" step="1" items="${blog}" var="blog">
                                    <li class="card-item swiper-slide card-position" onclick="window.location.href = 'blogdetail?blogId=${blog.blogId}'">
                                        <a href="javascript:void(0);" class="card-link"
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
                                           onclick="openBlogModal(this)">
                                            <h2 class="card-title">${blog.blogTitle}</h2>
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
                                            <div class="card-username blog-bottom">
                                                <span style="display: flex; align-items: center;">
                                                    <img src="${blog.author.profileImage}" alt="Avatar">
                                                    <span>${blog.author.userName}</span>
                                                </span>
                                                <button class="card-button material-symbols-rounded">arrow_forward</button>
                                            </div>
                                        </a>
                                    </li>
                                </c:forEach>
                                <c:if test="${empty blog}">
                                    <li class="empty-card-item">
                                        No result founded
                                    </li>
                                </c:if>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="./search/search.js"></script>
    </body>
</html>