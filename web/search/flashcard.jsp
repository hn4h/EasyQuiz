<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
            <form action="searchall">
                <div class="search">
                    <i class="fa-solid fa-magnifying-glass"></i>
                    <input type="text" placeholder="Search for study" name="input">
                </div>
            </form>
            <div class="create-login">
                <div class="create-btn-icon" id="createButton">
                    <span><button><i class="fa-solid fa-plus"></i></button></span>
                    <div class="create-menu" id="createMenu">
                        <a href="#" class="create-menu-item"><i class="fa-solid fa-book"></i> Flashcard set</a>
                        <a href="#" class="create-menu-item" id="createFolderItem"><i class="fa-solid fa-folder"></i>
                            Folder</a>
                    </div>
                </div>
                <div class="upgrade-btn">
                    <button>Upgrade: Free 7-day trial</button>
                </div>
                <div class="avatar-user" id="avatarUser">
                    <img src="../images/avatar/default.png" alt="Not found">
                    <div class="user-menu" id="userMenu">
                        <div class="user-info">
                            <img src="${sessionScope.account.profileImage}" alt="Not found"/>
                            <div>
                                <p>${sessionScope.account.userName}</p>
                                <p>${sessionScope.account.email}</p>
                            </div>
                        </div>
                        <hr />
                        <a href="#" class="user-menu-item"><i class="fa-solid fa-user"></i> Profile</a>
                        <a href="#" class="user-menu-item"><i class="fa-solid fa-gear"></i> Settings</a>
                        <hr />
                        <a href="logout" class="user-menu-item">Logout</a>
                        <hr />
                        <a href="#" class="user-menu-item">Help and feedback</a>
                        <a href="#" class="user-menu-item">Upgrades</a>
                    </div>
                </div>
                <div class="login-btn">
                    <a href="login"><button>Log in</button></a>
                </div>
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
                            <a href="history" class="nav-link">
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
            </aside>
            <div class="body-container">
                <div class="search-container">
                    <h2>Result for "${input}"</h2>
                    <div class="search-header">
                        <a href="searchall?input=${input}" class="search-item">All results</a>
                        <a href="searchquizset?input=${input}" class="search-item-actived">Flashcard sets</a>
                        <a href="searchuser?input=${input}" class="search-item">Users</a>
                    </div>
                    <div class="search-filter">
                        <h3>Filters</h3>
                        <div class="filter-item">
                            <span>Number of terms: </span>
                            <select name="" id="">
                                <option value="">All</option>
                                <option value="">&lt; 19</option>
                                <option value="">20 - 49</option>
                                <option value="">50+</option>
                            </select>
                        </div>
                    </div>
                    <div class="flashcard-container">
                        <div class="flashcard-sets">
                            <h2>Flashcard sets</h2>
                            <div class="flashcard-sets-list">
                                <ul class="card-list">
                                    <c:forEach items="${quizSet}" var="i">
                                        <li class="card-item" onclick="window.location.href = 'quizz?id=${i.quizSetId}'">
                                            <a href="#" class="card-link">
                                                <div class="previewBtn">
                                                    <h2 class="card-title">${i.quizSetName}</h2>
                                                    <button onclick="window.location.href='searchquizset?input=${input}&quizId=${i.quizSetId}'">Preview</button>
                                                </div>
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
                            </div>


                            <div class="number-page">
                                <button>
                                    <span class="material-symbols-rounded">chevron_left</span>
                                    <span>Previous</span>
                                </button>
                                <span>Page ? of ???</span>
                                <button>
                                    <span>Next</span>
                                    <span class="material-symbols-rounded">chevron_right</span>
                                </button>
                            </div>
                        </div>
                        <div class="preview-container">
                            <h2>Preview</h2>
                            <div class="preview-text">
                                <div class="preview-header">
                                    <h3>Title</h3>
                                    <button><a href="">Study</a></button>
                                </div>
                                <div class="preview-list">
                                    <c:forEach items="${requestScope.preview.flashCards}" var="q">
                                        <div class="preview-item">
                                            <strong>${q.definition}</strong>
                                            <p>${q.term}</p>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="./search/search.js"></script>
    </body>

</html>