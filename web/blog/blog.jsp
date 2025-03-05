<%-- 
    Document   : blog
    Created on : Feb 18, 2025, 4:27:50 PM
    Author     : DUCA
--%>

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
    </head>

    <body>
        <div class="header">
            <div class="logo">
                <div class="menu-btn">
                    <button class="sidebar-toggler" id="menuToggle"><i class="fa-solid fa-bars"></i></button>
                </div>
                <span>EasyQuiz</span>
            </div>
            <div class="search">
                <i class="fa-solid fa-magnifying-glass"></i>
                <input type="text" placeholder="Search for blog" name="">
            </div>
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
                <div class="avatar-user"  id="avatarUser">
                    <img src="./images/avatar/default.png" alt="Not found">
                    <div class="user-menu" id="userMenu">
                        <div class="user-info">
                            <img src="./images/avatar/default.png" alt="Not found"/>
                            <div>
                                <p>Do Duc Anh</p>
                                <p>duca@gmail.com</p>
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
                            <a href="history" class="nav-link">
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
                <%@ page import="java.util.List" %>
                <%@ page import="java.util.List" %>
                <%@ page import="model.Blog" %>
                <%@ page import="model.Comment" %>
                <%@ page import="model.Account" %>
                <%@ page contentType="text/html;charset=UTF-8" language="java" %>

                <%
                    List<Blog> blogs = (List<Blog>) request.getAttribute("blogs");
                %>

                <div class="blog-container">
                    <h1>Blogs</h1>
                    <% for (Blog blog : blogs) { %>
                    <div class="blog-card">
                        <div class="content-header">
                            <h2><%= blog.getBlogTitle() %></h2>
                            <div class="header-btn">
                                <button class="btn"><span class="material-symbols-rounded">share</span><p>Share</p></button>
                                <button class="btn"><span class="material-symbols-rounded">more_horiz</span></button>
                            </div>
                        </div>
                        <div class="blog-header">
                            <img alt="" src="./images/avatar/default.png"/> 
                            <span style="margin-right: 20px;"><%= blog.getAuthor().getUserName() %></span>
                            <span class="material-symbols-rounded">update</span>
                            <span>Created <%= blog.getCreatedDate() %></span>
                        </div>
                        <div class="blog-content">
                            <h3><%= blog.getBlogTitle() %></h3>
                            <p><%= blog.getBlogContent() %></p>
                            <div class="blog-comment">
                                <input type="text" placeholder="Your comment here...">
                                <span class="send-btn material-symbols-rounded">send</span>
                                <span class="chat-btn material-symbols-rounded" onclick="toggleComments('<%= blog.getBlogId() %>')">chat</span>
                            </div>
                            <div class="comment-window" id="commentWindow<%= blog.getBlogId() %>" style="display: none;">
                                <% if (blog.getComments().isEmpty()) { %>
                                <p>No comments yet.</p>
                                <% } else { %>
                                <% for (Comment comment : blog.getComments()) { %>
                                <div class="comment">
                                    <img src="./images/avatar/default.png" alt="Avatar">
                                    <p><strong><%= comment.getUserName() %></strong>: <%= comment.getCommentContent() %></p>
                                </div>
                                <% } %>
                                <% } %>
                                <a href="#">View more comments...</a>
                            </div>
                        </div>
                    </div>
                    <% } %>
                </div>

                <script>
                    function toggleComments(blogId) {
                        var commentWindow = document.getElementById("commentWindow" + blogId);
                        if (commentWindow.style.display === "none") {
                            commentWindow.style.display = "block";
                        } else {
                            commentWindow.style.display = "none";
                        }
                    }
                </script>
            </div>
        </div>
        <style>
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
        </style>
        <script src="./blog/blog.js"></script>
    </body>

</html>
