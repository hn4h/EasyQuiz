<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Settings</title>
    <link rel="stylesheet" href="./setting/setting.css">
    <link rel="shortcut icon" href="./images/logo/Easyquiz_logo.png">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css" />
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
        <div class="create-login">
            <c:if test="${sessionScope.account != null}">
                <div class="avatar-user" id="avatarUser">
                    <img src="${sessionScope.account.profileImage}" alt="Not found">
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
                        <hr/>
                        <a href="logout" class="user-menu-item">Logout</a>
                        <hr/>
                        <a href="#" class="user-menu-item">Help and feedback</a>
                        <a href="#" class="user-menu-item">Upgrades</a>
                    </div>
                </div>
            </c:if>
            <c:if test="${sessionScope.account == null}">
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
                    <li class="nav-item"><a href="home" class="nav-link-actived"><span class="material-symbols-rounded">home</span><span class="nav-label">Home</span></a></li>
                    <li class="nav-item"><a href="quizhistory" class="nav-link"><span class="material-symbols-rounded">history</span><span class="nav-label">History</span></a></li>
                    <li class="nav-item"><a href="blog" class="nav-link"><span class="material-symbols-rounded">rss_feed</span><span class="nav-label">Blog</span></a></li>
                </ul>
                <ul class="nav-list secondary-nav">
                    <li class="nav-item"><a href="#" class="nav-link"><span class="material-symbols-rounded">person</span><span class="nav-label">Profile</span></a></li>
                    <li class="nav-item"><a href="setting" class="nav-link"><span class="material-symbols-rounded">settings</span><span class="nav-label">Setting</span></a></li>
                    <li class="nav-item"><a href="logout" class="nav-link"><span class="material-symbols-rounded">logout</span><span class="nav-label">Logout</span></a></li>
                </ul>
            </nav>
        </aside>
        <div class="body-container">
            <h1>Settings</h1>
            <div class="personal-setting">
                <h2>Personal information</h2>
                <div class="personal-setting-box">
                    <div style="border-bottom: 2px solid #f3f3f3;">
                        <h3>Profile picture</h3>
                        <div style="display: flex; align-items: center;">
                            <img src="${sessionScope.account.profileImage}" alt="Not found">
                            <div class="avt-list">
                                <c:forEach begin="1" end="14" var="i">
                                    <div class="avt-item">
                                        <form action="setting" method="post" style="display: inline;">
                                            <input type="hidden" name="action" value="updateAvatar">
                                            <input type="hidden" name="avatar" value="./images/avatar/avt${i}.jpg">
                                            <button type="submit">
                                                <img src="./images/avatar/avt${i}.jpg" alt="Not found">
                                            </button>
                                        </form>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                    <div class="attribute-box">
                        <div>
                            <h3>Username</h3>
                            <p>${sessionScope.account.userName}</p>
                        </div>
                            <button type="submit">Edit</button>
                    </div>
                    <div class="attribute-box">
                        <div>
                            <h3>Email</h3>
                            <p>${sessionScope.account.email}</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="personal-setting">
                <h2>Appearance</h2>
                <div class="personal-setting-box">
                    <div class="attribute-box">
                        <h3>Theme</h3>
                        <select name="theme" id="theme">
                            <option value="light">Light</option>
                            <option value="dark">Dark</option>
                        </select>
                    </div>
                    <div class="attribute-box">
                        <h3>Language</h3>
                        <select name="language" id="language">
                            <option value="english">English</option>
                            <option value="vietnamese">Vietnamese</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="personal-setting">
                <h2>Account</h2>
                <div class="personal-setting-box">
                    <div class="attribute-box">
                        <h3>Change password</h3>
                        <button>Change</button>
                    </div>
                    <div class="attribute-box">
                        <div>
                            <h3>Delete your account</h3>
                            <p>This will delete all your data and cannot be undone.</p>
                        </div>
                        <button style="color: #fff; background-color: #970000;">Delete account</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="./setting/setting.js"></script>
</body>
</html>