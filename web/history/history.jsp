<%-- 
    Document   : history
    Created on : Feb 21, 2025, 2:14:10 PM
    Author     : DUCA
--%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>History</title>
        <link rel="stylesheet" href="./history/history.css">
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
                <span>EasyQuiz</span>
            </div>
            <div class="search">
                <i class="fa-solid fa-magnifying-glass"></i>
                <input type="text" placeholder="Search for blog" name="">
            </div>
            <div class="create-login">
                <div type="button" class="create-btn">
                    <span><i class="fa-solid fa-plus"></i></span>
                    <p>Create</p>
                </div> 
                <div type="button" class="create-btn-icon">
                    <span><i class="fa-solid fa-plus"></i></span>
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
        <div class="body">
            <aside class="sidebar">
                <nav class="sidebar-nav">
                    <!--Top nav-->
                    <ul class="nav-list primary-nav">
                        <li class="nav-item">
                            <a href="#" class="nav-link">
                                <span class="material-symbols-rounded">home</span>
                                <span class="nav-label">Home</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="#" class="nav-link-actived">
                                <span class="material-symbols-rounded">history</span>
                                <span class="nav-label">History</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="#" class="nav-link">
                                <span class="material-symbols-rounded">rss_feed</span>
                                <span class="nav-label">Blog</span>
                            </a>
                        </li>
                        <!--Dropdown -->
                        <li class="nav-item dropdown-container">
                            <a href="#" class="nav-link dropdown-toggle">
                                <span class="material-symbols-rounded">subject</span>
                                <span class="nav-label">Subject</span>
                                <span class="dropdown-icon material-symbols-rounded">keyboard_arrow_down</span>
                            </a>
                            <!--                            Dropdown subject-->
                            <ul class="dropdown-subject">
                                <li class="nav-item">
                                    <a class="nav-link dropdown-title" style="font-weight: bold">Subject</a>
                                </li>
                                <li class="nav-item">
                                    <a href="#" class="nav-link dropdown-link">Subject A</a>
                                </li>
                                <li class="nav-item">
                                    <a href="#" class="nav-link dropdown-link">Subject B</a>
                                </li>
                                <li class="nav-item">
                                    <a href="#" class="nav-link dropdown-link">Subject C</a>
                                </li>
                            </ul>
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
                            <a href="#" class="nav-link">
                                <span class="material-symbols-rounded">logout</span>
                                <span class="nav-label">Logout</span>
                            </a>
                        </li>
                    </ul>
                </nav>
            </aside>   
            <div class="body-container">
                <h1 class="text-2xl font-bold mb-4" style="margin-top: 10px;">History</h1>
                <div class="flex space-x-4 mb-4">
                    <button class="border-b-2 border-blue-600 pb-2">Quiz</button>
                    <button class="pb-2">Folders</button>
                </div>
                <div class="flex justify-between items-center mb-4">
                    <select class="border border-gray-300 px-4 py-2 rounded">
                        <option>Recent</option>
                        <option>Created</option>
                    </select>
                    <div class="relative w-1/3">
                        <input type="text" placeholder="Search for a quiz" class="w-full p-2 border border-gray-300 rounded pr-10">
                        <div class="absolute inset-y-0 right-0 pr-3 flex items-center pointer-events-none">
                            <svg class="h-5 w-5 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"></path>
                            </svg>
                        </div>
                    </div>
                </div>
                <div>
                    <h2 class="text-lg font-bold mb-2">TODAY</h2>
                    <div class="bg-gray-100 p-4 rounded mb-4">
                        <div class="flex justify-between items-center">
                            <div>
                                <span class="text-sm text-gray-600">10 questions</span>
                                <span class="text-sm text-gray-600 ml-2">|</span>
                                <span class="text-sm text-gray-600 ml-2">denvau</span>
                            </div>
                            <span class="text-sm text-gray-600">Quiz 1</span>
                        </div>
                    </div>
                    <h2 class="text-lg font-bold mb-2">THIS WEEK</h2>
                    <div class="bg-gray-100 p-4 rounded mb-4">
                        <div class="flex justify-between items-center">
                            <div>
                                <span class="text-sm text-gray-600">100 questions</span>
                                <span class="text-sm text-gray-600 ml-2">|</span>
                                <span class="text-sm text-gray-600 ml-2">duongdomic</span>
                            </div>
                            <span class="text-sm text-gray-600">tieng anh</span>
                        </div>
                    </div>
                    <div class="bg-gray-100 p-4 rounded mb-4">
                        <div class="flex justify-between items-center">
                            <div>
                                <span class="text-sm text-gray-600">69 questions</span>
                                <span class="text-sm text-gray-600 ml-2">|</span>
                                <span class="text-sm text-gray-600 ml-2">j97</span>
                            </div>
                            <span class="text-sm text-gray-600">tieng trung</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <script src="./history/history.js"></script>
    </body>

</html>
