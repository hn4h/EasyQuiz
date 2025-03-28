<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Test</title>
        <link rel="stylesheet" href="./quiz/testquiz.css">
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
                        <li class="nav-item remove-session" onclick="window.location.href = 'flashcard?id=${requestScope.quizDetail.qs.quizSetId}'">
                            <img src="./images/icon/flashcard_icon.png" alt="">
                            <a class="nav-link">Flashcards</a>
                        </li>
                        <li class="nav-item remove-session"  onclick="window.location.href = 'learnquiz?quizSetID=${requestScope.quizDetail.qs.quizSetId}'">
                            <img src="./images/icon/test_icon.png" alt="">
                            <a class="nav-link">Learn</a>
                        </li>
                        <hr>
                        <li class="nav-item remove-session">
                            <a href="home" class="nav-link">Home</a>
                        </li>
                    </ul>
                </div>
            </div>
            <input id="time-left" type="hidden" value="${sessionScope.testSession.timeLimit}">
            <div class="title">
                <span class="page-number" id="pageIndicator">0 / ${testSession.totalQuestions}</span>
                <span>${quizDetail.qs.quizSetName}</span>
            </div>
            <div class="close">
                <div id="timer" style="font-weight: bold; color: red;">Time remaining: 00:00</div>
                <button class="optionBtn"><span class="material-symbols-rounded">settings</span></button>
                <button id="cancel-test" onclick="window.location.href = 'quizz?id=${requestScope.quizDetail.qs.quizSetId}'"><span class="material-symbols-rounded">close</span></button>
            </div>
        </div>
        <form action="testquiz">
            <div class="optionPopup-container">
                <div id="optionPopup" class="test-popup">
                    <div class="option-popup-content">
                        <input name="quizSetID" type="hidden" value="${requestScope.quizDetail.qs.quizSetId}">
                        <h2>Options</h2>
                        <div class="option-list">
                            <div class="test-item">
                                <span class="test-name">Questions (max ${requestScope.quizDetail.qs.numberOfQuiz})</span>
                                <input name="numberQuiz" min="1" max="${requestScope.quizDetail.qs.numberOfQuiz}" type="number" value="${requestScope.quizDetail.qs.numberOfQuiz}"/>
                                <span class="test-name">Time (minutes)</span>
                                <input name="timeLimit" min="1" max="${requestScope.quizDetail.qs.numberOfQuiz + 20}" type="number" value="${requestScope.quizDetail.qs.numberOfQuiz + 10}"/>
                            </div>
                        </div>
                        <div class="option-btn">
                            <button type="button" class="cancel-btn"><span>Cancel</span></button>
                            <button type="submit" class="create-btn remove-session"><span>Create new test</span></button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
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
                            <p class="answered" onclick="scrollToQuestion(${i})">${i}</p>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <form id="quizForm" action="testresult" method="post">
                <div class="test-container">
                    <c:forEach var="quiz" items="${quizzes}" varStatus="quizLoop">
                        <div class="quiz" id="question-${quizLoop.index + 1}">
                            <div class="definition">
                                <div class="definition-header">
                                    <h4>Definition</h4>
                                    <span class="question-index">${quizLoop.index + 1} of ${fn:length(quizzes)}</span>
                                </div>
                                <div class="definition-content">
                                    <p>${quiz.content}</p>
                                </div>
                            </div>
                            <div class="term">
                                <h4>Select the correct term</h4>
                                <div class="term-content">
                                    <c:forEach var="answer" items="${quiz.answers}" varStatus="answerLoop" >
                                        <div class="answer" data-correct="${answer.correct}" data-quiz-id="${quiz.quizID}" data-answer-id="${answer.answerID}">
                                            <span class="answer-number">${answerLoop.count}</span>
                                            <p>${answer.content}</p>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                            <input type="hidden" name="userAnswer_${quiz.quizID}" id="answerInput_${quiz.quizID}" value="">
                        </div>
                    </c:forEach>
                    <div class="submit-btn">
                        <img src="./images/icon/submit_icon.png" alt="alt"/>
                        <span>All done! Ready to submit your test?</span>
                        <button type="button" class="submitBtn">Submit test</button>
                    </div>
                    <div class="confirmSubmitPopup-container">
                        <div id="confirmSubmitPopup" class="confirmSubmit-popup">
                            <div class="confirmSubmit-popup-content">
                                <h2>Are you sure to submit your test?</h2>
                                <div class="number-quiz-complete">
                                    <p><span>Completed: </span><span id="completed-num" style="color:#000;">0</span></p>
                                    <p><span>Not completed: </span><span id="incompleted-num" style="color:#000;">${testSession.totalQuestions}</span></p>
                                </div>
                                <div class="confirmSubmit-btn">
                                    <button type="button" class="cancelSubmit-btn"><span>Cancel</span></button>
                                    <button type="submit" class="submitTest-btn" id="submitQuiz"><span>Submit test</span></button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>

        </div>
        <script src="./quiz/testquiz.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    </body>
</html>
