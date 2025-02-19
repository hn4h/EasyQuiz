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
        <link rel="stylesheet" href="blog.css">
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
                            <a href="#" class="nav-link">
                                <span class="material-symbols-rounded">history</span>
                                <span class="nav-label">History</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="#" class="nav-link-actived">
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
                <h1>Blogs</h1>
                <div class="content blog-container">  <h2 class="font-bold">
                        Blog 1
                    </h2>
                    <div class="blog-header">
                        <img alt="User avatar" height="30" src="./images/avatar/default.png" width="30" /> <span>
                            user_1
                        </span>
                        <span class="mx-2">
                            <i class="fa-solid fa-clock-rotate-left"></i>
                        </span>
                        <span>
                            Created ?/?/?
                        </span>
                    </div>
                    <h3 class="font-bold">
                        Title 1
                    </h3>
                    <div class="mb-4">
                        <ul class="list-disc list-inside">
                            <li>
                                Microorganisms are microscopic organisms like bacteria, viruses, fungi, and protozoa.
                            </li>
                            <li>
                                They play crucial roles in various ecosystems and can be both beneficial and harmful to humans.
                            </li>
                            <li>
                                Microbial growth refers to the increase in the number of microorganisms.
                            </li>
                            <li>
                                It involves processes like binary fission, where a single cell divides into two identical cells.
                            </li>
                            <li>
                                Microbial pathogenesis is the process by which microorganisms cause disease.
                            </li>
                            <li>
                                It involves mechanisms like toxin production, invasion of host tissues, and evasion of the immune system.
                            </li>
                        </ul>
                    </div>
                    <div class="flex items-center justify-between">

                        <input type="text" placeholder="Your comment here..." class="blog-comment">
                        <i class="fa-regular fa-paper-plane absolute right-3 top-1/2 transform -translate-y-1/2 cursor-pointer"></i>

                        <i class="fa-regular fa-comment"></i> </div>
                </div>
                                <div class="content blog-container">  <h2 class="font-bold">
                        Blog 1
                    </h2>
                    <div class="blog-header">
                        <img alt="User avatar" height="30" src="./images/avatar/default.png" width="30" /> <span>
                            user_1
                        </span>
                        <span class="mx-2">
                            <i class="fa-solid fa-clock-rotate-left"></i>
                        </span>
                        <span>
                            Created ?/?/?
                        </span>
                    </div>
                    <h3 class="font-bold">
                        Title 1
                    </h3>
                    <div class="mb-4">
                        <ul class="list-disc list-inside">
                            <li>
                                Microorganisms are microscopic organisms like bacteria, viruses, fungi, and protozoa.
                            </li>
                            <li>
                                They play crucial roles in various ecosystems and can be both beneficial and harmful to humans.
                            </li>
                            <li>
                                Microbial growth refers to the increase in the number of microorganisms.
                            </li>
                            <li>
                                It involves processes like binary fission, where a single cell divides into two identical cells.
                            </li>
                            <li>
                                Microbial pathogenesis is the process by which microorganisms cause disease.
                            </li>
                            <li>
                                It involves mechanisms like toxin production, invasion of host tissues, and evasion of the immune system.
                            </li>
                        </ul>
                    </div>
                    <div class="flex items-center justify-between">

                        <input type="text" placeholder="Your comment here..." class="blog-comment">
                        <i class="fa-regular fa-paper-plane absolute right-3 top-1/2 transform -translate-y-1/2 cursor-pointer"></i>

                        <i class="fa-regular fa-comment"></i> </div>
                </div>
                                <div class="content blog-container">  <h2 class="font-bold">
                        Blog 1
                    </h2>
                    <div class="blog-header">
                        <img alt="User avatar" height="30" src="./images/avatar/default.png" width="30" /> <span>
                            user_1
                        </span>
                        <span class="mx-2">
                            <i class="fa-solid fa-clock-rotate-left"></i>
                        </span>
                        <span>
                            Created ?/?/?
                        </span>
                    </div>
                    <h3 class="font-bold">
                        Title 1
                    </h3>
                    <div class="mb-4">
                        <ul class="list-disc list-inside">
                            <li>
                                Microorganisms are microscopic organisms like bacteria, viruses, fungi, and protozoa.
                            </li>
                            <li>
                                They play crucial roles in various ecosystems and can be both beneficial and harmful to humans.
                            </li>
                            <li>
                                Microbial growth refers to the increase in the number of microorganisms.
                            </li>
                            <li>
                                It involves processes like binary fission, where a single cell divides into two identical cells.
                            </li>
                            <li>
                                Microbial pathogenesis is the process by which microorganisms cause disease.
                            </li>
                            <li>
                                It involves mechanisms like toxin production, invasion of host tissues, and evasion of the immune system.
                            </li>
                        </ul>
                    </div>
                    <div class="flex items-center justify-between">

                        <input type="text" placeholder="Your comment here..." class="blog-comment">
                        <i class="fa-regular fa-paper-plane absolute right-3 top-1/2 transform -translate-y-1/2 cursor-pointer"></i>

                        <i class="fa-regular fa-comment"></i> </div>
                </div>
            </div>
        </div>

        <script src="./blog/blog.js"></script>
    </body>

</html>
