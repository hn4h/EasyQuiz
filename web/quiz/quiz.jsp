<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <link rel="stylesheet" href="../quiz/quiz.css">
        <link rel="shortcut icon" href="./images/logo/Easyquiz_logo.png">
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
            <div class="search">
                <i class="fa-solid fa-magnifying-glass"></i>
                <input type="text" placeholder="Search for study guides" name="">
            </div>
            <div class="create-login">
                <div class="create-btn-icon" id="createButton">
                    <span><button><i class="fa-solid fa-plus"></i></button></span>
                    <div class="create-menu" id="createMenu">
                        <a href="#" class="create-menu-item"><i class="fa-solid fa-book"></i> Flashcard set</a>
                        <a href="#" class="create-menu-item"><i class="fa-solid fa-folder"></i> Folder</a>
                        <a href="#" class="create-menu-item"><i class="fa-solid fa-user-group"></i> Class</a>
                    </div>
                </div>
                <div class="upgrade-btn">
                    <button>Upgrade: Free 7-day trial</button>
                </div>
                <div class="avatar-user" id="avatarUser">
                    <img src="../images/avatar/default.png" alt="Not found">
                    <div class="user-menu" id="userMenu">
                        <div class="user-info">
                            <img src="./images/avatar/default.png" alt="Not found" />
                            <div>
                                <p>Do Duc Anh</p>
                                <p>duca@gmail.com</p>
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
                <div class="flashcard-container">
                    <div class="flashcard-header">
                        <h2>Title</h2>
                        <div class="header-btn">
                            <button class="btn"><span class="material-symbols-rounded">share</span>
                                <p>Share</p>
                            </button>
                            <button class="btn"><span class="material-symbols-rounded">more_horiz</span></button>
                            <div class="more-option">
                                <ul class="more-option-nav">
                                    <li class="nav-item">
                                        <span class="material-symbols-rounded">folder_open</span>
                                        <a href="" class="nav-link">Flashcards</a>
                                    </li>
                                    <li class="nav-item">
                                        <span class="material-symbols-rounded">quiz</span>
                                        <a href="" class="nav-link">Make a copy</a>
                                    </li>
                                    <li class="nav-item">
                                        <span class="material-symbols-rounded">report</span>
                                        <a href="" class="nav-link">Report</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="flashcard-test">
                        <button class="btn">
                            <img src="../images/icon/flashcard_icon.png" alt="">
                            <a href="">Flashcards</a>
                        </button>
                        <button class="btn">
                            <img src="../images/icon/learn_icon.png" alt="">
                            <a href="">Learn</a>
                        </button>
                        <button class="btn">
                            <img src="../images/icon/test_icon.png" alt="">
                            <a href="">Test</a>
                        </button>
                    </div>
                    <div class="flashcard-content">
                        <div class="flashcard-slide" id="flashcardSlide">
                            <div class="flashcard">
                                <div class="flashcard-inner">
                                    <div class="flashcard-front">
                                        <p>1 + 1</p>
                                    </div>
                                    <div class="flashcard-back">
                                        <p>2</p>
                                    </div>
                                </div>
                            </div>
                            <div class="flashcard">
                                <div class="flashcard-inner">
                                    <div class="flashcard-front">
                                        <p>3 + 2</p>
                                    </div>
                                    <div class="flashcard-back">
                                        <p>5</p>
                                    </div>
                                </div>
                            </div>
                            <div class="flashcard">
                                <div class="flashcard-inner">
                                    <div class="flashcard-front">
                                        <p>4 + 4</p>
                                    </div>
                                    <div class="flashcard-back">
                                        <p>8</p>
                                    </div>
                                </div>
                            </div>
                            <div class="flashcard">
                                <div class="flashcard-inner">
                                    <div class="flashcard-front">
                                        <p>6 x 2</p>
                                    </div>
                                    <div class="flashcard-back">
                                        <p>12</p>
                                    </div>
                                </div>
                            </div>
                            <div class="flashcard">
                                <div class="flashcard-inner">
                                    <div class="flashcard-front">
                                        <p>10 ÷ 2</p>
                                    </div>
                                    <div class="flashcard-back">
                                        <p>5</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="previous-next">
                        <div class="nav-controls">
                            <button class="btn" id="prevBtn">
                                <span class="material-symbols-rounded">arrow_back</span>

                            </button>
                            <span class="page-number" id="pageIndicator">1 / 5</span>
                            <button class="btn" id="nextBtn">
                                <span class="material-symbols-rounded">arrow_forward</span>
                            </button>
                        </div>
                        <div class="extra-controls">
                            <button class="extra-btn" id="swapFaceBtn">
                                <span class="material-symbols-rounded">swap_horiz</span>
                                <span class="tooltip">Swap face</span>
                            </button>
                            <button class="extra-btn" id="shuffleBtn">
                                <span class="material-symbols-rounded">shuffle</span>
                                <span class="tooltip">Shuffle</span>
                            </button>
                            <button class="extra-btn" id="fullscreenBtn">
                                <span class="material-symbols-rounded">fullscreen</span>
                                <span class="tooltip">Fullscreen</span>
                            </button>
                        </div>
                    </div>
                    <!-- Màn hình hoàn thành -->
                    <div class="flashcard-complete" id="flashcardComplete" style="display: none">
                        <h2>Congratulations! You've reviewed all the cards.</h2>
                        <div class="progress-actions">
                            <div class="progress">
                                <h3>How you're doing</h3>
                                <div class="progress-info">
                                    <span class="complete-icon material-symbols-rounded">check_circle</span>
                                    <div class="progress-info-complete">
                                        <p>Completed:<span id="completedCount">0</span></p>
                                        <p style="background: #e6e6e6; color: #555;"><strong>Terms remaining:</strong> 0</p>
                                    </div>
                                </div>
                                <button class="backToLastBtn" id="backToLast"><span class="material-symbols-rounded">arrow_back</span>Back to the last question</button>
                            </div>
                            <div class="actions">
                                <h3>Next steps</h3>
                                <div class="actions-info">
                                    <button id="nextSteps"><a href="#">Practise with questions</a></button>
                                    <button id="restartFlashcards"><a href="#">Restart flashcards</a></button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="flashcard-info">
                        <div class="actor-info">
                            <img src="../images/avatar/default.png" alt="">
                            <div class="name-info">
                                <p>Created by</p>
                                <h3>Duca</h3>
                                <p>Created 3 years ago</p>
                            </div>
                        </div>
                        <p class="flashcard-description">Day la quiz de test</p>
                    </div>
                </div>
                <div class="term-container">
                    <div class="term-header">
                        <h3>Term in this set (???)</h3>
                        <select name="" id="">
                            <option value="original">Original</option>
                            <option value="alphabetical">Alphabetical</option>
                        </select>
                    </div>
                    <div class="term-content">
                        <div class="term-card">
                            <div class="definition">
                                <p>1 + 1</p>
                            </div>
                            <div class="term">
                                <p>2</p>
                            </div>
                        </div>
                        <div class="term-card">
                            <div class="definition">
                                <p>c</p>
                            </div>
                            <div class="term">
                                <p>4</p>
                            </div>
                        </div>
                        <div class="term-card">
                            <div class="definition">
                                <p>a</p>
                            </div>
                            <div class="term">
                                <p>2</p>
                            </div>
                        </div>
                        <div class="term-card">
                            <div class="definition">
                                <p>b</p>
                            </div>
                            <div class="term">
                                <p>2</p>
                            </div>
                        </div>
                    </div>
                    <div class="edit-term">
                        <button class="btn">
                            <a href="#">Edit terms</a>
                            <span class="material-symbols-rounded">edit</span>
                        </button>
                    </div>
                    <div class="mode-btn">
                        <div class="show-hide">
                            <div class="mode-content">Hide definitions</div>
                            <div class="vertical-line-mode"></div>
                            <div class="swapBtn"><span class="material-symbols-rounded">swap_horiz</span></div>
                        </div>
                        <div class="study-mode">
                            <div>Choose a study mode</div>
                            <div class="arrowBtn"><span class="material-symbols-rounded">keyboard_arrow_down</span></div>
                            <div class="more-mode">
                                <ul class="study-mode-nav">
                                    <li class="nav-item">
                                        <img src="../images/icon/flashcard_icon.png" alt="">
                                        <a href="" class="nav-link">Flashcards</a>
                                    </li>
                                    <li class="nav-item">
                                        <img src="../images/icon/test_icon.png" alt="">
                                        <a href="" class="nav-link">Test</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <button id="scrollToTop" class="scroll-top-btn">
                    <span class="material-symbols-rounded">arrow_upward</span>
                </button>
            </div>
        </div>
        <script src="../quiz/quiz.js"></script>
    </body>

</html>