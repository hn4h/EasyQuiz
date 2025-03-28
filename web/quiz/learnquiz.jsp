<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Learn</title>
        <link rel="stylesheet" href="./quiz/learnquiz.css?v=1.1"> <!-- Thêm query string -->
        <link rel="shortcut icon" href="./images/logo/Easyquiz_logo.png">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    </head>
    <body>
        <div class="header">
            <div class="learn-icon">
                <button>
                    <img src="./images/icon/learn_icon.png" alt="">
                    <span class="learn-title">Learn</span>
                    <span class="material-symbols-rounded">keyboard_arrow_down</span>
                </button>
                <div class="learn-menu">
                    <ul class="learn-menu-nav">
                        <li class="nav-item" onclick="window.location.href = 'flashcard?id=${requestScope.quizDetail.qs.quizSetId}'">
                            <img src="./images/icon/flashcard_icon.png" alt="">
                            <a class="nav-link">Flashcards</a>
                        </li>
                        <li class="nav-item test-btn">
                            <img src="./images/icon/test_icon.png" alt="">
                            <a class="nav-link">Test</a>
                        </li>
                        <hr>
                        <li class="nav-item">
                            <a href="home" class="nav-link">Home</a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="title">
                <span>Title</span>
            </div>
            <div class="close">
                <button onclick="window.location.href = 'quizz?id=${requestScope.quizDetail.qs.quizSetId}'"><span class="material-symbols-rounded">close</span></button>
            </div>
        </div>
        <form action="testquiz">
            <div class="testPopup-container">
                <div id="testPopup" class="test-popup">
                    <div class="test-popup-content">
                        <input name="quizSetID" type="hidden" value="${requestScope.quizDetail.qs.quizSetId}">
                        <span class="closetest-btn material-symbols-rounded">close</span>
                        <h2>Options</h2>
                        <div class="test-list">
                            <div class="test-item">
                                <span class="test-name">Questions (max ${requestScope.quizDetail.qs.numberOfQuiz})</span>
                                <input name="numberQuiz" min="1" max="${requestScope.quizDetail.qs.numberOfQuiz}" type="number" value="${requestScope.quizDetail.qs.numberOfQuiz >= 10 ? 10 : requestScope.quizDetail.qs.numberOfQuiz}"/>
                                <span class="test-name">Time (minutes)</span>
                                <input name="timeLimit" min="1" max="90" type="number" value="10"/>
                            </div>
                        </div>
                        <div class="test-submit-btn">
                            <button type="button" class="cancel-btn"><span>Cancel</span></button>
                            <button type="submit" class="create-btn"><span>Create new test</span></button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
        <div class="body">
            <!-- Màn hình hoàn thành -->
            <div class="learn-complete" id="learnComplete" style="display: none;">
                <div class="learn-complete-header">
                    <div class="cup-icon">
                        <img src="./images/icon/cup_icon.png" alt="alt"/>
                    </div>
                    <h1>Congratulations! You've reviewed all the cards.</h1>
                    <p>Take a test for practicing the difficult questions.</p>
                </div>
                <div class="learn-complete-btn">
                    <button class="btn" onclick="window.location.href = 'learnquiz?quizSetID=${requestScope.quizDetail.qs.quizSetId}'">Restart Learn mode</button>
                    <button class="btn test-btn">Take a test</button>
                </div>
            </div>
            <div class="progress">
                <div class="progress-start">
                    <p>0</p>
                </div>
                <div class="progress-line">
                    <div class="line-1"></div>
                    <div class="line-2"></div>
                    <div class="line-3"></div>
                </div>
                <div class="progress-end">
                    <p>${quizzes.size()}</p>
                </div>
            </div>
            <div class="learn-container">
                <c:if test="${not empty quizzes}">
                    <c:forEach var="quiz" items="${quizzes}" varStatus="loop">
                        <div class="quiz" data-index="${loop.index}" style="display: ${loop.index == 0 ? 'block' : 'none'};">
                            <div class="definition">
                                <h4>Definition</h4>
                                <div class="definition-content">
                                    <p>${quiz.content}</p>
                                </div>
                            </div>
                            <div class="term">
                                <h4>Select the correct term</h4>
                                <div class="term-content">
                                    <c:forEach var="answer" items="${quiz.answers}" varStatus="answerLoop">
                                        <div class="answer" data-correct="${answer.correct}">
                                            <span class="answer-number">${answerLoop.count}</span>
                                            <p>${answer.content}</p>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="suggest-btn">
                                <button class="dont-know">
                                    <span>Don't know?</span>
                                </button>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
                <c:if test="${empty quizzes}">
                    <div class="definition">
                        <h4>Definition</h4>
                        <div class="definition-content">
                            <p>No quizzes available.</p>
                        </div>
                    </div>
                </c:if>
            </div>
            <div class="continue-container">
                <div class="continue-content">
                    <p>Press continue button to next question</p>
                    <button class="continue-btn">
                        <span>Continue</span>
                    </button>
                </div>
            </div>
        </div>
        <script src="./quiz/learnquiz.js?v=1.1"></script>
    </body>
</html>