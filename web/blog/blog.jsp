<%-- 
    Document   : blog
    Created on : Feb 18, 2025, 4:27:50 PM
    Author     : DUCA
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Blog</title>
        <link rel="stylesheet" href="./blog/blog.css">
        <link rel="shortcut icon" href="./images/logo/Easyquiz_logo.png">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
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
                        <c:if test="${not empty sessionScope.account.userName}">
                            <button class="btn" onclick="showPopup()">
                                <span class="material-symbols-rounded">create</span>
                                <p>Create</p>
                            </button>
                        </c:if>
                    </div>
                    <c:forEach var="blog" items="${blogs}">
                        <div class="blog-card">
                            <div class="content-header" onclick="window.location.href = 'blogdetail?blogId=${blog.blogId}'">
                                <h2><a style="color: black;text-decoration: none">${blog.blogTitle}</a></h2>
                                <!--                                <div class="header-btn">                               
                                                                    <button class="btn"><span class="material-symbols-rounded">more_horiz</span></button>
                                                                </div>-->
                            </div>
                            <div class="blog-header">
                                <img onclick="window.location.href='user?username=${blog.author.userName}&type='" alt="" src="${blog.author.profileImage}"/> 
                                <span style="margin-right: 20px;">${blog.author.userName}</span>
                                <span class="material-symbols-rounded">update</span>
                                <span>Created ${blog.createdDate}</span>
                            </div>
                            <div class="blog-content">
                                <h3>${blog.blogTitle}</h3>
                                <p>${blog.blogContent}</p>
                                <hr/>
                                <div class="blog-comment">
                                    <h3>Comments</h3>
                                </div>
                                <div class="comment-window" id="commentWindow${blog.blogId}">
                                    <c:if test="${blog.comments.size() == 0}">
                                        <strong>No comments yet.</strong>
                                    </c:if>
                                    <c:forEach var="comment" items="${blog.comments}" varStatus="status">
                                        <div class="comment ${status.index >= 3 ? 'hidden-comment' : ''}" id="comment-${blog.blogId}-${status.index}">
                                            <img src="${comment.profileImage}" alt="Avatar">
                                            <div class="comment-info">
                                                <h4>${comment.userName}</h4>
                                                <span>${comment.commentContent}</span>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <div id="copyMessage" class="copy-message">Link copied!</div>
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
        <div class="overlay" id="overlay" onclick="hidePopup()" style="display: none; position: fixed; top: 0; left: 0; width: 100%; height: 100%; background: rgba(0,0,0,0.5); z-index: 999;"></div>
        <div class="popup" id="createPopup" style="display: none; position: fixed; top: 50%; left: 50%; transform: translate(-50%, -50%); background: white; padding: 30px;
             border-radius: 15px; box-shadow: 0 0 15px rgba(0,0,0,0.5); z-index: 1000; width: 600px; max-width: 90%;">
            <div class="popup-content" style="display: flex; flex-direction: column; align-items: center;">
                <h2 style="margin-bottom: 20px; font-size: 24px;">Create New Blog</h2>
                <form action="${pageContext.request.contextPath}/createblog" method="post" style="width: 100%; display: flex; flex-direction: column; align-items: center;">
                    <div style="width: 100%; margin-bottom: 15px;">
                        <input type="text" name="title" id="blogTitle" placeholder="Blog Title" required style="width: 100%; padding: 10px; font-size: 16px; border: 1px solid #ccc; border-radius: 5px; box-sizing: border-box;">
                    </div>
                    <div style="width: 100%; margin-bottom: 15px;">
                        <textarea name="content" id="blogContent" placeholder="Blog Content" rows="5" required style="width: 100%; padding: 10px; font-size: 16px; border: 1px solid #ccc; border-radius: 5px; box-sizing: border-box; resize: vertical;"></textarea>
                    </div>
                    <div style="display: flex; justify-content: center; gap: 20px; width: 100%;">
                        <button type="submit" class="btn" style="background-color: green; color: white; padding: 10px 20px; border: none;
                                border-radius: 5px; font-size: 16px; cursor: pointer;">Create</button>
                        <button type="button" class="btn" style="background-color: #C62300; color: white; padding: 10px 20px; border: none;
                                border-radius: 5px; font-size: 16px; cursor: pointer;" onclick="hidePopup()">Cancel</button>
                    </div>
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
            function submitComment(blogId) {
                let commentInput = document.getElementById('commentInput' + blogId);
                let commentContent = commentInput.value.trim();

                if (commentContent === '') {
                    alert('Please enter a comment.');
                    return;
                }

                // Get the username from the session (if available)
                let userName = '${sessionScope.account.userName}';
                let profile = '${sessionScope.account.profileImage}';
                console.log(profile);
                if (!userName) {
                    alert('You must be logged in to comment.');
                    return;
                }

                // Send AJAX request to the servlet
                let xhr = new XMLHttpRequest();
                xhr.open('POST', '${pageContext.request.contextPath}/addcomment', true);
                xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4) {
                        if (xhr.status === 200) {
                            // On success, append the new comment to the comment window
                            let commentWindow = document.getElementById('commentWindow' + blogId);
                            let newComment = document.createElement('div');
                            newComment.classList.add('comment');
                            console.log(userName);
                            console.log(newComment);
                            newComment.innerHTML = `
    <img src="` + profileImage + `" alt="Avatar">
    <p><strong>` + userName + `</strong>: ` + commentContent + `</p>
`;
                            console.log(newComment);


                            commentWindow.insertBefore(newComment, commentWindow.firstChild);

                            // Clear the input field
                            commentInput.value = '';

                            // Show the comment window if it's hidden
                            if (commentWindow.style.display === 'none' || commentWindow.style.display === '') {
                                commentWindow.style.display = 'block';
                            }
                        } else {
                            alert('Failed to add comment. Please try again.');
                        }
                    }
                };

                // Send the comment data to the servlet
                let data = 'blogId=' + encodeURIComponent(blogId) + '&commentContent=' + encodeURIComponent(commentContent);
                xhr.send(data);

            }
        </script>
        <script src="./blog/blog.js"></script>
    </body>

</html>