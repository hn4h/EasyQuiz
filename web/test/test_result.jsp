<%-- 
    Document   : test_result
    Created on : Mar 3, 2025, 10:05:41 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Test Result</title>
        <link rel="stylesheet" href="test_result.css">

        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css" />
    </head>
    <body>
        <div class="header">
            <div class="test">
                <div class="">
                    <div class="avatar-user"  id="avatarUser">
                        <img class="test-img" src="./images/icon/#640D5F.png">
                        <span class="test-word">Test</span>
                        <div class="user-menu" id="userMenu">

                            <hr/>
                            <a href="#" class="user-menu-item">Learn</a>
                            <a href="#" class="user-menu-item">Flashcard</a>
                            <hr/>

                            <a href="#" class="user-menu-item">Home</a>
                            <a href="#" class="user-menu-item">Search</a>
                        </div>
                    </div>

                </div>

            </div>
            <p class="number">1 / 3</p>
            <p class="exit">X</p>
        </div>
        <div class="body">
            <div class="container">
                <!-- Hamburger button -->
                <div class="hamburger" id="hamburgerBtn">
                    <!-- Using Google Material Icons you already load -->
                    <span class="material-symbols-rounded">menu</span>
                </div>

                <!-- Sidebar / Drawer -->
                <nav class="sidebar" id="sidebar">
                    <div class="sidebar-header">
                        <h3>List of quiz</h3>
                        <span class="material-symbols-rounded close-btn" id="closeBtn">close</span>
                    </div>
                    <ul>
                        <li><a href="#">1</a></li>
                        <li><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li><a href="#">4</a></li>
                    </ul>
                </nav>
                <h2 class="headline">Don't give up now! Trust the process.</h2>

                <div class="stats">
                    <div class="time">Your time: <strong>1 min.</strong></div>
                    <div class="score">
                        <div class="circle">
                            <span class="percent">0%</span>
                        </div>
                        <div class="score-details">
                            <p class="correct">Correct <span>0</span></p>
                            <p class="incorrect">Incorrect <span>20</span></p>
                        </div>
                    </div>
                </div>

                <h3 class="next-steps">Next steps</h3>

                <div class="actions">
                    <div class="card">
                        <span class="badge">20 missed terms</span>
                        <h4>Practise terms in Learn</h4>
                        <p>Practise your missed terms more until you get them right.</p>
                    </div>
                    <div class="card locked">
                        <span class="lock-icon">🔒</span>
                        <h4>Take a new test</h4>
                        <p>Take another test to boost your confidence.</p>
                    </div>
                </div>
            </div>
        </div>
        <!-- Linking SwiperJS script -->
        <!--        <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>-->
        <script src="test_result.js"></script>
    </body>
</html>
