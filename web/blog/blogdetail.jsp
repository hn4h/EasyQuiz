<%-- 
    Document   : blogdetail
    Created on : Mar 10, 2025, 9:36:26 PM
    Author     : DUCA
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>${requestScope.blogDetail.blogTitle}</title>
        <link rel="stylesheet" href="./blog/blogdetail.css"> 
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
        <div class="body">
            <aside class="sidebar">
                <nav class="sidebar-nav">
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
                <div class="blog-detail-container">
                    <div class="flashcard-header">
                        <h2>${requestScope.blogDetail.blogTitle}</h2>
                        <div class="header-btn">
                            <button class="btn" id="shareButton"><span class="material-symbols-rounded">share</span><p>Share</p></button>
                            <c:if test="${requestScope.blogDetail.author.userName eq sessionScope.account.userName or sessionScope.account.isAdmin}">
                                <button class="btn" id="moreButton"><span class="material-symbols-rounded">more_horiz</span></button>
                                <div class="more-option" id="moreOption">
                                    <ul class="more-option-nav">
                                        <li class="nav-item" onclick="window.location.href='deleteblog?blogId=${requestScope.blogDetail.blogId}'">
                                            <span class="material-symbols-rounded">delete</span>
                                            <a class="nav-link">Delete</a>
                                        </li>
                                    </ul>
                                </div>
                            </c:if>
                        </div>
                    </div>
                    <div id="copyMessage" class="copy-message">Link copied!</div>
                    <div class="blog-content">
                        <div class="blog-header">
                            <img alt="" src="${requestScope.blogDetail.author.profileImage}"/> 
                            <span style="margin-right: 20px;">${requestScope.blogDetail.author.userName}</span>
                            <span class="material-symbols-rounded">update</span>
                            <span>Created ${requestScope.blogDetail.createdDate}</span>
                        </div>
                        <p>${requestScope.blogDetail.blogContent}</p>
                        <div class="comment-title">
                            <h3>Comments</h3>
                        </div>
                        <div class="blog-comment">                            
                            <div class="comment-input-container">
                                <input type="text" id="commentInput${requestScope.blogDetail.blogId}" placeholder="Your comment here...">
                                <span class="send-btn material-symbols-rounded" onclick="submitComment(${requestScope.blogDetail.blogId})">send</span>
                            </div>
                        </div>

                        <div class="comment-window">
                            <div class="comment-content">
                                <c:if test="${requestScope.blogDetail.comments.size() == 0}">
                                    <strong>No comments yet.</strong>
                                </c:if>
                                <c:forEach var="comment" items="${requestScope.blogDetail.comments}">
                                    <div class="comment">
                                        <img src="${comment.profileImage}" alt="Avatar">
                                        <div class="comment-info">
                                            <h4>${comment.userName}</h4>
                                            <span>${comment.commentContent}</span>
                                        </div>
                                        <c:if test="${comment.userName eq sessionScope.account.userName or sessionScope.account.isAdmin}">
                                            <button onclick="window.location.href='deletecomment?commentId=${comment.commentId}'"><span class="material-symbols-rounded">delete</span></button>
                                        </c:if>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>

                        <!-- Related Blogs Section -->
                        <div class="related-blogs">
                            <h3>Related Blogs</h3>
                            <div class="related-blogs-list">
                                <c:forEach var="relatedBlog" items="${requestScope.popularBlogs}">
                                    <div class="related-blog-item">
                                        <a href="blogdetail?blogId=${relatedBlog.blogId}">
                                            <h4>${relatedBlog.blogTitle}</h4>
                                            <p class="blog-snippet">
                                                <c:set var="snippet" value="${fn:substring(relatedBlog.blogContent, 0, 50)}" />
                                                ${snippet}${fn:length(relatedBlog.blogContent) > 50 ? '...' : ''}
                                            </p>
                                            <div class="blog-meta">
                                                <img src="${relatedBlog.author.profileImage}" alt="avatar" class="blog-logo">
                                                <span class="author-name">${relatedBlog.author.userName}</span>
                                                <span class="view-icon material-symbols-rounded">arrow_outward</span>
                                            </div>
                                        </a>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script>

            // Share button functionality (copy link to clipboard)
            document.getElementById('shareButton').addEventListener('click', function () {
                const blogUrl = window.location.href;
                navigator.clipboard.writeText(blogUrl).then(() => {
                    const copyMessage = document.getElementById('copyMessage');
                    copyMessage.style.display = 'block';
                    setTimeout(() => {
                        copyMessage.style.display = 'none';
                    }, 2000);
                });
            });

            document.addEventListener("DOMContentLoaded", function () {
                document.querySelectorAll(".comment-input-container input").forEach(input => {
                    input.addEventListener("keydown", function (event) {
                        if (event.key === "Enter") {
                            event.preventDefault(); // Ngăn chặn hành vi mặc định của Enter
                            const blogId = this.id.replace("commentInput", ""); // Lấy blogId từ input ID
                            submitComment(blogId);
                        }
                    });
                });
            });

            // Submit comment function (giữ nguyên từ blog.jsp)
            function submitComment(blogId) {
                let commentInput = document.getElementById('commentInput' + blogId);
                let commentContent = commentInput.value.trim();

                if (commentContent === '') {
                    alert('Please enter a comment.');
                    return;
                }

                let userName = '${sessionScope.account.userName}';
                if (!userName) {
                    alert('You must be logged in to comment.');
                    return;
                }

                let xhr = new XMLHttpRequest();
                xhr.open('POST', '${pageContext.request.contextPath}/addcomment', true);
                xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4) {
                        if (xhr.status === 200) {
                            let commentWindow = document.querySelector('.comment-window');
                            let newComment = document.createElement('div');
                            newComment.classList.add('comment');
                            newComment.innerHTML =
                                    `<img src="${sessionScope.account.profileImage}" alt="Avatar">
                                        <div class="comment-info">
                                            <h4>` + userName + `</h4>
                                            <span>` + commentContent + `</span>
                                        </div>`;
                            commentWindow.insertBefore(newComment, commentWindow.firstChild);
                            commentInput.value = '';
                        } else {
                            alert('Failed to add comment. Please try again.');
                        }
                    }
                };

                let data = 'blogId=' + encodeURIComponent(blogId) + '&commentContent=' + encodeURIComponent(commentContent);
                xhr.send(data);
            }
            
            
            // Toggle more options
            document.getElementById('moreButton').addEventListener('click', function () {
                const moreOption = document.getElementById('moreOption');
                moreOption.classList.toggle('show');
            });

        </script>
        <script src="./blog/blog.js"></script>
    </body>
</html>
