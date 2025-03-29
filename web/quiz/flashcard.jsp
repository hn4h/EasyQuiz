
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Flashcards</title>
        <link rel="shortcut icon" href="./images/logo/Easyquiz_logo.png">
        <link rel="stylesheet" href="./quiz/flashcard.css">
        <link rel="stylesheet"
              href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    </head>
    <body>
        <div class="header">
            <div class="flashcard-icon">
                <button>
                    <img src="./images/icon/flashcard_icon.png" alt="">
                    <span class="learn-title">Flashcards</span>
                    <span class="material-symbols-rounded">keyboard_arrow_down</span>
                </button>
                <div class="flashcard-menu">
                    <ul class="flashcard-menu-nav">
                        <li class="nav-item" onclick="window.location.href = 'learnquiz?quizSetID=${requestScope.quizDetail.qs.quizSetId}'">
                            <img src="./images/icon/learn_icon.png" alt="">
                            <a class="nav-link">Learn</a>
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
                <span class="page-number" id="pageIndicator">? / ?</span>
                <span>${requestScope.quizDetail.qs.quizSetName}</span>
            </div>
            <div class="close">
                <button class="optionBtn"><span class="material-symbols-rounded">settings</span></button>
                <button onclick="window.location.href = 'quizz?id=${requestScope.quizDetail.qs.quizSetId}'"><span class="material-symbols-rounded">close</span></button>
            </div>
        </div>
        <div class="optionPopup-container" onclose="saveSettings()">
            <div id="optionPopup" class="option-popup">
                <div class="option-popup-content">
                    <span class="close-btn material-symbols-rounded">close</span>
                    <h2>Options</h2>
                    <div class="option-list">
                        <div class="option-item">
                            <span class="option-name">Front</span>
                            <select>
                                <option>Definition</option>
                                <option>Term</option>
                            </select>
                        </div>
                        <div class="option-item">
                            <span class="option-name">Show both sides of cards</span>
                            <label class="switch">
                                <input type="checkbox" id="toggleSwitch">
                                <span class="slider"></span>
                            </label>
                        </div>
                        <hr/>
                        <div class="option-item">
                            <span class="option-name">Keyboard shortcuts</span>
                            <button id="viewKeyboardButton">
                                <span id="buttonText">View</span>
                                <span class="material-symbols-rounded" id="arrowIcon">keyboard_arrow_down</span>
                            </button>
                        </div>
                        <div class="keyboard-shortcut" id="shortcutMenu" style="display: none;">
                            <div class="column">
                                <div class="column-item">
                                    <span>Previous</span>
                                    <span><kbd>&larr;</kbd></span>
                                </div>
                                <div class="column-item">
                                    <span>Next</span>
                                    <span><kbd>&rarr;</kbd></span>
                                </div>
                            </div>
                            <div class="column">
                                <div class="column-item">
                                    <span>Shuffle</span>
                                    <span><kbd>S</kbd></span>
                                </div>
                                <div class="column-item">
                                    <span>Flip</span>
                                    <span><kbd>Space</kbd></span>
                                </div>
                            </div>
                        </div>
                        <hr/>
                        <div class="option-item" onclick="window.location.href = 'flashcard?id=${requestScope.quizDetail.qs.quizSetId}'">
                            <span class="option-name restart">Restart Flashcards</span>
                        </div>
                    </div>
                </div>
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
                            <button id="nextSteps"><a onclick="window.location.href = 'learnquiz?quizSetID=${requestScope.quizDetail.qs.quizSetId}'">Practice with questions</a></button>
                            <button id="restartFlashcards"><a onclick="window.location.href = 'flashcard?id=${requestScope.quizDetail.qs.quizSetId}'">Restart flashcards</a></button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="flashcard-container">
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

                            <div class="flashcard-inner2">
                                <div class="flashcard-front2">
                                    <p class="term-text">${q.definition}</p>
                                </div>
                                <div class="flashcard-back2">
                                    <p class="term-text">${q.term}</p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <div class="previous-next">
                    <div class="nav-controls">
                        <button class="btn" id="prevBtn">
                            <span class="material-symbols-rounded">arrow_back</span>
                        </button>
                        <button class="btn" id="nextBtn">
                            <span class="material-symbols-rounded">arrow_forward</span>
                        </button>
                    </div>
                    <div class="extra-controls">
                        <button class="extra-btn" id="shuffleBtn">
                            <span class="material-symbols-rounded">shuffle</span>
                            <span class="tooltip">Shuffle</span>
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <script src="./quiz/flashcard.js"></script>
    </body>
</html>