
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Quiz</title>
        <link rel="stylesheet" href="./quiz/quiz.css">
        <link rel="shortcut icon" href="./images/logo/Easyquiz_logo.png">
        <link rel="stylesheet"
              href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    </head>

    <body data-logged-in="${not empty sessionScope.account}">
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
                            <a href="#" class="create-menu-item"><i class="fa-solid fa-book"></i> Flashcard set</a>
                            <a href="#" class="create-menu-item" id="createFolderItem"><i class="fa-solid fa-folder"></i> Folder</a>
                        </div>
                    </div>
                    <div class="upgrade-btn">
                        <a href="upgrade">Upgrade your package</a>
                    </div>
                    <div class="avatar-user"  id="avatarUser">
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
                            <a href="#" class="user-menu-item"><i class="fa-solid fa-user"></i> Profile</a>
                            <hr/>
                            <a href="logout" class="user-menu-item">Logout</a>
                            <hr/>
                            <a href="upgrade" class="user-menu-item">Upgrades</a>
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
        <form action="testquiz">
            <div class="optionPopup-container">
                <div id="optionPopup" class="option-popup">
                    <div class="option-popup-content">
                        <input name="quizSetID" type="hidden" value="${requestScope.quizDetail.qs.quizSetId}">
                        <span class="closetest-btn material-symbols-rounded">close</span>
                        <h2>Options</h2>
                        <div class="option-list">
                            <div class="option-item">
                                <span class="option-name">Questions (max 10)</span>
                                <input name="numberQuiz" min="5" max="10" type="number" value="${requestScope.quizDetail.qs.numberOfQuiz}"/>
                                <span class="option-name">Time (minutes)</span>
                                <input name="timeLimit" min="5" max="20" type="number" value="${requestScope.quizDetail.qs.numberOfQuiz + 10}"/>
                            </div>
                        </div>
                        <div class="option-btn">
                            <button type="button" class="cancel-btn"><span>Cancel</span></button>
                            <button type="submit" class="create-btn"><span>Create new test</span></button>
                        </div>
                    </div>
                </div>
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
                            <a href="quizhistory" class="nav-link">
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
                <div class="flashcard-container">
                    <div class="flashcard-header">
                        <h2>${requestScope.quizDetail.qs.quizSetName}</h2>
                        <div class="header-btn">
                            <button class="btn" id="shareButton"><span class="material-symbols-rounded">share</span>
                                <p>Share</p>
                            </button>
                            <button class="btn"><span class="material-symbols-rounded">more_horiz</span></button>
                            <div class="more-option">
                                <ul class="more-option-nav">
                                    <li class="nav-item">
                                        <span class="material-symbols-rounded">folder_open</span>
                                        <a href="" class="nav-link">Add to folder</a>
                                    </li>
                                    <li class="nav-item">
                                        <span class="material-symbols-rounded">report</span>
                                        <a href="" class="nav-link">Report</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div id="copyMessage" class="copy-message">Link copied!</div>
                    <div class="flashcard-test">
                        <button class="btn" onclick="window.location.href = 'flashcard?id=${requestScope.quizDetail.qs.quizSetId}'">
                            <img src="./images/icon/flashcard_icon.png" alt="">
                            <a>Flashcards</a>
                        </button>
                        <button class="btn" onclick="window.location.href = 'learnquiz?quizSetID=${requestScope.quizDetail.qs.quizSetId}'">
                            <img src="./images/icon/learn_icon.png" alt="">
                            <a>Learn</a>
                        </button>
                        <button class="btn test-btn">
                            <img src="./images/icon/test_icon.png" alt="">
                            <a>Test</a>
                        </button>
                    </div>
                    <div class="flashcard-content">
                        <div class="flashcard-slide" id="flashcardSlide">
                            <c:forEach items="${requestScope.quizDetail.flashCards}" var="q">
                                <div class="flashcard">
                                    <div class="flashcard-inner">
                                        <div class="flashcard-front">
                                            <p class="term-text">${q.definition}</p>
                                        </div>
                                        <div class="flashcard-back">
                                            <p class="term-text">${q.term}</p>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="previous-next">
                        <div class="nav-controls">
                            <button class="btn" id="prevBtn">
                                <span class="material-symbols-rounded">arrow_back</span>

                            </button>
                            <span class="page-number" id="pageIndicator">? / ?</span>
                            <button class="btn" id="nextBtn">
                                <span class="material-symbols-rounded">arrow_forward</span>
                            </button>
                        </div>
                        <div class="extra-controls">
                            <button class="extra-btn" id="shuffleBtn">
                                <span class="material-symbols-rounded">shuffle</span>
                                <span class="tooltip">Shuffle</span>
                            </button>
                            <button class="extra-btn" id="fullscreenBtn" onclick="window.location.href = 'flashcard?id=${requestScope.quizDetail.qs.quizSetId}'">
                                <span class="material-symbols-rounded">fullscreen</span>
                                <span class="tooltip">Fullscreen</span>
                            </button>
                        </div>
                    </div>
                    <c:if test="${empty sessionScope.account.userName}">
                        <div class="login-required-container" id="loginRequired" style="display: none;">
                            <h2>Log in or Sign up to see this flashcards. It's free!</h2>
                            <div class="login-required">
                                <a href="login">
                                    <button>Log in</button>
                                </a>
                                <a href="signup">
                                    <button>Sign up</button>
                                </a>
                            </div>
                        </div>
                    </c:if>

                    <!-- Man hinh hoan thanh -->
                    <div class="flashcard-complete" id="flashcardComplete" style="display: none">
                        <div class="flashcard-complete-header">
                            <h2>Congratulations! You've reviewed all the cards.</h2>
                            <div class="congratulations-icon">
                                <img src="./images/icon/congratulations_icon.png" alt="alt"/>
                            </div>
                        </div>
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
                                    <button id="nextSteps"><a href="#">Practice with questions</a></button>
                                    <button onclick="window.location.href = 'quizz?id=${requestScope.quizDetail.qs.quizSetId}'" id="restartFlashcards"><a>Restart flashcards</a></button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="flashcard-info">
                        <div class="actor-info">
                            <img src="${requestScope.quizDetail.qs.author.profileImage}" alt="">
                            <div class="name-info">
                                <p>Created by</p>
                                <h3>${requestScope.quizDetail.qs.author.userName}</h3>
                                <!--                                <p>Created 3 years ago</p>-->
                            </div>
                        </div>
                        <p class="flashcard-description">${requestScope.quizDetail.qs.quizSetDescription}</p>
                    </div>
                </div>
                <div class="term-container">
                    <div class="term-header">
                        <h3>Term in this set (${requestScope.quizDetail.qs.numberOfQuiz})</h3>
                        <select name="" id="">
                            <option value="original">Original</option>
                            <option value="alphabetical">Alphabetical</option>
                        </select>
                    </div>
                    <input type="hidden" id="account-check" value="${sessionScope.account.userName}">
                    <div class="term-content">
                        <c:forEach items="${requestScope.quizDetail.flashCards}" var="q">
                            <div class="term-card">
                                <div class="definition">
                                    <p>${q.definition}</p>
                                </div>
                                <div class="term">
                                    <p>${q.term}</p>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <c:if test="${requestScope.quizDetail.qs.author.userName eq sessionScope.account.userName}">
                        <div class="edit-term">
                            <button class="btn">
                                <a href="editquiz?id=${requestScope.quizDetail.qs.quizSetId}">Edit terms</a>
                                <span class="material-symbols-rounded">edit</span>
                            </button>
                        </div>
                    </c:if>
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
                                    <li class="nav-item" onclick="window.location.href = 'flashcard?id=${requestScope.quizDetail.qs.quizSetId}'">
                                        <img src="./images/icon/flashcard_icon.png" alt="">
                                        <a class="nav-link">Flashcards</a>
                                    </li>
                                    <li class="nav-item" onclick="window.location.href = 'learnquiz?quizSetID=${requestScope.quizDetail.qs.quizSetId}'">
                                        <img src="./images/icon/learn_icon.png" alt="">
                                        <a class="nav-link">Learn</a>
                                    </li>
                                    <li class="nav-item test-btn">
                                        <img src="./images/icon/test_icon.png" alt="">
                                        <a class="nav-link">Test</a>
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
        <script src="./quiz/quiz.js"></script>
    </body>

</html>