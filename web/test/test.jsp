<%-- 
    Document   : test
    Created on : Feb 27, 2025, 3:28:57 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Test</title>
        <link rel="stylesheet" href="test.css">

        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css" />

    </head>
    <body>
        <div class="header">
            <div class="popup-overlay-show" id="popupOverlay1" onclick="closePopup1()"></div>
            <div class="popup-show" id="popup1">
                <span class="close-button1" onclick="closePopup1()">×</span>
                <h3 style="color: #000">Test</h3>
                <h2>Set up your test</h2>
                
                
                <label for="questionCount">Questions (max ???)</label>
                <input class="questionCount" type="number" id="questionCount" value="20" min="1" max="999">
                <br>
                <button class="start-test" onclick="closePopup1()">Start test</button>
            </div>
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
                <div class="container1">
                    <h3>Which way should be followed if you want to reduce integration and interface errors?</h3>
                    <p>Select the correct definition</p>
                    <div class="options1">
                        <button>Allowing teams to define their own data standards</button>
                        <button>Using outdated data definitions for legacy systems</button>
                        <button>Implementing multiple data formats for flexibility</button>
                        <button class="correct1">Using consistent data definitions across the enterprise</button>
                    </div>
                </div>
                <div class="container1">
                    <h3>Which way should be followed if you want to reduce integration and interface errors?</h3>
                    <p>Select the correct definition</p>
                    <div class="options1">
                        <button>Allowing teams to define their own data standards</button>
                        <button>Using outdated data definitions for legacy systems</button>
                        <button>Implementing multiple data formats for flexibility</button>
                        <button class="correct1">Using consistent data definitions across the enterprise</button>
                    </div>
                </div>
                <div class="container1">
                    <h3>Which way should be followed if you want to reduce integration and interface errors?</h3>
                    <p>Select the correct definition</p>
                    <div class="options1">
                        <button>Allowing teams to define their own data standards</button>
                        <button>Using outdated data definitions for legacy systems</button>
                        <button>Implementing multiple data formats for flexibility</button>
                        <button class="correct1">Using consistent data definitions across the enterprise</button>
                    </div>
                </div>
                <button class="submit" onclick="openPopup()">Submit test</button>
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
            </div>
            <div class="popup-overlay" id="popupOverlay" onclick="closePopup()"></div>
            <div class="popup" id="popup">
                <span class="close-button" onclick="closePopup()">×</span>
                <h2>It looks like you skipped some questions</h2>
                <h3>Do you want to review the skipped questions, or submit the test now?</h3>
                <div class="popup-buttons">
                    <button class="review-btn" onclick="closePopup()">Review skipped questions</button>
                    <button class="submit-btn" onclick="submitFinalTest()">Submit test</button>
                </div>
            </div>
        </div>
        <!-- Linking SwiperJS script -->
        <!--        <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>-->
        <script src="test.js"></script>
    </body>
</html>