<%-- 
    Document   : sidebar
    Created on : Mar 1, 2025, 12:37:19 AM
    Author     : Lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Side bar</title>
        <link rel="stylesheet" href="default.css">
        <link rel="shortcut icon" href="./images/logo/Easyquiz_logo.png">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <script src="https://cdn.tailwindcss.com"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    </head>
    <body>
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
                    <a href="quiz" class="nav-link-actived">
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
        <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
        <script src="default.js"></script>
    </body>
</html>
