<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List" %>
<%@ page import="model.UserAnswer" %>
<%@ page import="model.Quiz" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Test</title>
        <link rel="stylesheet" href="./quiz/testresult.css">
        <link rel="shortcut icon" href="./images/logo/Easyquiz_logo.png">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css" />
    </head>
    <body>
        <div class="header">
            <div class="test-icon">
                <button>
                    <img src="./images/icon/test_icon.png" alt="">
                    <span class="test-title">Test</span>
                    <span class="material-symbols-rounded">keyboard_arrow_down</span>
                </button>
                <div class="test-menu">
                    <ul class="test-menu-nav">
                        <li class="nav-item" onclick="window.location.href = 'quizz?id=${requestScope.quizDetail.qs.quizSetId}'">
                            <img src="./images/icon/flashcard_icon.png" alt="">
                            <a href="" class="nav-link">Flashcards</a>
                        </li>
                        <li class="nav-item"  onclick="window.location.href = 'test?id=${requestScope.quizDetail.qs.quizSetId}'">
                            <img src="./images/icon/test_icon.png" alt="">
                            <a href="" class="nav-link">Learn</a>
                        </li>
                        <hr>
                        <li class="nav-item">
                            <a href="home" class="nav-link">Home</a>
                        </li>
                    </ul>
                </div>
            </div>
            <!--            <div class="title">
                            <span class="page-number" id="pageIndicator">0 / ${quizDetail.flashCards.size()}</span>
                            <span>${quizDetail.qs.quizSetName}</span>
                        </div>-->
            <div class="close">
                <!--                <button class="printBtn"><span>Print test</span></button>-->
                <button id="remove-session" onclick="window.location.href = 'quizz?id=${requestScope.quizDetail.qs.quizSetId}'"><span class="material-symbols-rounded">close</span></button>
            </div>
        </div>
        <div class="body">
            <div class="question-list">
                <span id="menuButton" class="menu-btn material-symbols-rounded">menu</span>
                <div id="questionList" class="list">
                    <div id="hideButton" class="hide-btn">
                        <span class="material-symbols-rounded">close</span>
                        <span>Hide question list</span>
                    </div>
                    <div class="question-number">
                        <h4>Multiple choice</h4>
                        <c:forEach begin="1" end="${testSession.totalQuestions}" var="i">
                            <p class="answered answered-question" onclick="scrollToQuestion(${i})">${i}</p>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <div class="result-container">
                <h1>Congratulations! You have completed your test.</h1>
                <div class="result-content">
                    <div class="result-percentage">
                        <input type="hidden" class="duration" value="${sessionScope.testSession.timeLimit}">
                        <h3 id="timer">Your time: ${sessionScope.testSession.timeLimit }</h3>
                        <h3 id="time-remain" >Time remaining: ${sessionScope.testSession.timeLimit}</h3>
                        <div class="result-percentage-content">
                            <div class="percentage-circle">
                                <svg viewBox="0 0 36 36" class="circular-chart">
                                <path class="circle-bg"
                                      d="M18 2.0845
                                      a 15.9155 15.9155 0 0 1 0 31.831
                                      a 15.9155 15.9155 0 0 1 0 -31.831" />
                                <path class="circle"
                                      stroke-dasharray="${percentage}, 100"
                                      d="M18 2.0845
                                      a 15.9155 15.9155 0 0 1 0 31.831
                                      a 15.9155 15.9155 0 0 1 0 -31.831" />
                                <text x="18" y="20.35" class="percentage-text">${percentage}%</text>
                                </svg>
                            </div>
                            <div class="numberQuestion-result">
                                <div class="numberQuestion-correct">
                                    <span>Correct</span>
                                    <span id="correct">${correctCount}</span>
                                </div>
                                <div class="numberQuestion-incorrect">
                                    <span>Incorrect</span>
                                    <span id="incorrect">${incorrectCount}</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="next-step">
                        <h3>Next steps</h3>
                        <div class="next-step-btn">
                            <div class="next-step-learn">
                                <div class="next-step-img">
                                    <img src="./images/icon/learn_icon.png" alt="alt"/>
                                </div>
                                <div class="next-step-btn-content">
                                    <p>Practise terms in Learn</p>
                                    <p>Learn these terms in a different way to build knowledge.</p>
                                </div>
                                <div>
                                    <span class="material-symbols-rounded">
                                        arrow_forward_ios
                                    </span>
                                </div>
                            </div>
                            <div class="next-step-test">
                                <div class="next-step-img">
                                    <img src="./images/icon/test_icon.png" alt="alt"/>
                                </div>
                                <div class="next-step-btn-content">
                                    <p>Take a new test</p>
                                    <p>Take another test to boost your confidence.</p>
                                </div>
                                <span class="material-symbols-rounded">
                                    arrow_forward_ios
                                </span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="test-container">
                <h3>Your answers</h3>

                <c:forEach var="quiz" items="${sessionScope.testSession.questions}" varStatus="quizLoop">
                    <div class="quiz" id="question-${quizLoop.index + 1}">
                        <div class="definition">
                            <div class="definition-header">
                                <h4>Definition</h4>
                                <span class="question-index">${quizLoop.index + 1} of ${fn:length(sessionScope.testSession.questions)}</span>
                            </div>
                            <div class="definition-content">
                                <p>${quiz.content}</p>
                            </div>
                        </div>

                        <div class="term">
                            <h4>Select the correct term</h4>
                            <div class="term-content">
                                <c:set var="userSelectedAnswerID" value="-1" />
                                <c:forEach var="userAnswer" items="${sessionScope.testSession.userAnswers}">
                                    <c:if test="${userAnswer.quizID eq quiz.quizID}">
                                        <c:set var="userSelectedAnswerID" value="${userAnswer.selectedAnswerID}" />
                                    </c:if>
                                </c:forEach>

                                <c:forEach var="answer" items="${quiz.answers}">
                                    <c:set var="answerClass" value="" />
                                    <c:if test="${answer.correct}">
                                        <c:set var="answerClass" value="correct-answer" />
                                    </c:if>
                                    <c:if test="${userSelectedAnswerID eq answer.answerID and not answer.correct}">
                                        <c:set var="answerClass" value="wrong-answer" />
                                    </c:if>

                                    <div class="answer ${answerClass}">
                                        <p class="answer-content">${answer.content}</p>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <script src="./quiz/testresult.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    </body>
</html>
