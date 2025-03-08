<%-- 
    Document   : blog
    Created on : Feb 18, 2025, 4:27:50 PM
    Author     : DUCA
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Blog</title>
        <link rel="stylesheet" href="./blog/blog.css">
        <link rel="shortcut icon" href="../images/logo/Easyquiz_logo.png">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <style>
            .pagination {
                display: flex;
                justify-content: center;
                margin-top: 20px;
            }

            .page-link {
                display: inline-block;
                padding: 8px 12px;
                margin: 0 5px;
                background: #640D5F;
                color: white;
                text-decoration: none;
                border-radius: 5px;
            }

            .page-link.active {
                background: #640D5F;
                font-weight: bold;
            }

            .page-link:hover {
                background: #2A004E;
            }

            .comment-window {
                border: 1px solid #ccc;
                padding: 10px;
                margin-top: 10px;
            }

            .comment {
                display: flex;
                align-items: center;
                margin-bottom: 5px;
            }

            .comment img {
                width: 30px;
                height: 30px;
                border-radius: 50%;
                margin-right: 10px;
            }

            .popup {
                display: none;
                position: fixed;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                background: white;
                padding: 20px;
                border: 1px solid #ccc;
                box-shadow: 0 0 10px rgba(0,0,0,0.5);
                z-index: 1000;
            }

            .popup-content {
                display: flex;
                flex-direction: column;
            }

            .popup-content input, .popup-content textarea {
                margin-bottom: 10px;
                padding: 5px;
            }

            .overlay {
                display: none;
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background: rgba(0,0,0,0.5);
                z-index: 999;
            }

            .message {
                padding: 10px;
                margin: 10px 0;
                border-radius: 5px;
            }

            .success-message {
                background-color: #d4edda;
                color: #155724;
            }

            .error-message {
                background-color: #f8d7da;
                color: #721c24;
            }
        </style>
    </head>

    <body>
        <div class="header">
            <div class="logo">
                <div class="menu-btn">
                    <button class="sidebar-toggler" id="menuToggle"><i class="fa-solid fa-bars"></i></button>
                </div>
                <span>EasyQuiz</span>
            </div>
            <form action="searchBlog" method="get">
                <div class="search">
                    <i class="fa-solid fa-magnifying-glass"></i>
                    <input type="text" name="keyword" placeholder="Search for blog">
                </div>
            </form>

            <div class="create-login">
                <div type="button" class="create-btn-icon" id="createButton">
                    <span><button><i class="fa-solid fa-plus"></i></button></span>
                    <div class="create-menu" id="createMenu">
                        <a href="#" class="create-menu-item"><i class="fa-solid fa-book"></i> Flashcard set</a>
                        <a href="#" class="create-menu-item" id="createFolderItem"><i class="fa-solid fa-folder"></i> Folder</a>
                    </div>
                </div>

                <div class="upgrade-btn">
                    <button>Upgrade: Free 7-day trial</button>
                </div>
                <div class="avatar-user" id="avatarUser">
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
                    <a href="login"><button>Log in</button></a>
                </div>
            </div>
        </div>
        <div class="folderPopup-container">
            <div id="folderPopup" class="folder-popup">
                <div class="folder-popup-content">
                    <span class="close-btn material-symbols-rounded">close</span>
                    <h2>Create a new folder</h2>
                    <input type="text" id="folderName" placeholder="Title" class="folder-input">
                    <div class="create-folder-btn">
                        <button id="createFolderConfirm">Create folder</button>
                    </div>
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
                            <a href="quizhistory" class="nav-link">
                                <span class="material-symbols-rounded">history</span>
                                <span class="nav-label">History</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="blog" class="nav-link-actived">
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
                <div class="blog-container">
                    <!-- Display success or error message -->
                    <c:if test="${not empty successMessage}">
                        <div class="message success-message">${successMessage}</div>
                    </c:if>
                    <c:if test="${not empty errorMessage}">
                        <div class="message error-message">${errorMessage}</div>
                    </c:if>

                    <div class="header-blog">
                        <h1>Blogs</h1>
                        <button class="btn" onclick="showPopup()">
                            <span class="material-symbols-rounded">create</span>
                            <p>Create</p>
                        </button>
                    </div>
                    <c:forEach var="blog" items="${blogs}">
                        <div class="blog-card">
                            <div class="content-header">
                                <h2>${blog.blogTitle}</h2>
                                <div class="header-btn">
                                    <button class="btn"><span class="material-symbols-rounded">share</span><p>Share</p></button>
                                    <button class="btn"><span class="material-symbols-rounded">more_horiz</span></button>
                                </div>
                            </div>
                            <div class="blog-header">
                                <img alt="" src="./images/avatar/default.png"/> 
                                <span style="margin-right: 20px;">${blog.author.userName}</span>
                                <span class="material-symbols-rounded">update</span>
                                <span>Created ${blog.createdDate}</span>
                            </div>
                            <div class="blog-content">
                                <h3>${blog.blogTitle}</h3>
                                <p>${blog.blogContent}</p>
                                <div class="blog-comment">
                                    <input type="text" placeholder="Your comment here...">
                                    <span class="send-btn material-symbols-rounded">send</span>
                                    <span class="chat-btn material-symbols-rounded" id="chatBtn${blog.blogId}">chat</span>
                                </div>
                                <div class="comment-window" id="commentWindow${blog.blogId}" style="display: none;">
                                    <c:forEach var="comment" items="${blog.comments}" varStatus="status">
                                        <div class="comment ${status.index >= 3 ? 'hidden-comment' : ''}" id="comment-${blog.blogId}-${status.index}">
                                            <img src="./images/avatar/default.png" alt="Avatar">
                                            <p><strong>${comment.userName}</strong>: ${comment.commentContent}</p>
                                        </div>
                                    </c:forEach>
                                    <c:if test="${fn:length(blog.comments) > 3}">
                                        <a href="#" class="view-more" data-blogid="${blog.blogId}">View more comment...</a>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <div class="pagination">
                        <c:if test="${currentPage > 1}">
                            <a href="blog?page=${currentPage - 1}" class="page-link">Previous</a>
                        </c:if>
                        <c:forEach var="i" begin="1" end="${totalPages}">
                            <a href="blog?page=${i}" class="page-link ${i == currentPage ? 'active' : ''}">${i}</a>
                        </c:forEach>
                        <c:if test="${currentPage < totalPages}">
                            <a href="blog?page=${currentPage + 1}" class="page-link">Next</a>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>

        <!-- Popup for creating a new blog -->
        <div class="overlay" id="overlay" onclick="hidePopup()"></div>
        <div class="popup" id="createPopup">
            <div class="popup-content">
                <h2>Create New Blog</h2>
                <form action="${pageContext.request.contextPath}/createblog" method="post">
                    <input type="text" name="title" id="blogTitle" placeholder="Blog Title" required>
                    <textarea name="content" id="blogContent" placeholder="Blog Content" rows="5" required></textarea>
                    <button type="submit" class="btn">Submit</button>
                    <button type="button" class="btn" onclick="hidePopup()">Cancel</button>
                </form>
            </div>
        </div>

        <script>
            document.addEventListener("DOMContentLoaded", function () {
                document.querySelector(".blog-container").addEventListener("click", function (event) {
                    if (event.target.classList.contains("chat-btn")) {
                        let blogId = event.target.id.replace("chatBtn", "");
                        let commentWindow = document.getElementById("commentWindow" + blogId);
                        commentWindow.style.display = (commentWindow.style.display === "none" || commentWindow.style.display === "") ? "block" : "none";
                    }

                    if (event.target.classList.contains("view-more")) {
                        event.preventDefault();
                        let blogId = event.target.getAttribute("data-blogid");
                        let commentWindow = document.getElementById("commentWindow" + blogId);
                        let comments = commentWindow.querySelectorAll(".comment");
                        let hiddenCount = 0;

                        for (let i = 0; i < comments.length; i++) {
                            if (comments[i].classList.contains("hidden-comment")) {
                                if (hiddenCount < 3) {
                                    comments[i].classList.remove("hidden-comment");
                                    hiddenCount++;
                                }
                            }
                        }

                        if (commentWindow.querySelectorAll(".hidden-comment").length === 0) {
                            event.target.style.display = "none";
                        }
                    }
                });
            });

            function showPopup() {
                document.getElementById('createPopup').style.display = 'block';
                document.getElementById('overlay').style.display = 'block';
            }

            function hidePopup() {
                document.getElementById('createPopup').style.display = 'none';
                document.getElementById('overlay').style.display = 'none';
                document.getElementById('blogTitle').value = '';
                document.getElementById('blogContent').value = '';
            }
        </script>
        <script src="./blog/blog.js"></script>
    </body>

</html>