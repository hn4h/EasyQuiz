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
            <div class="title">
                <span class="page-number" id="pageIndicator">0 / ${quizDetail.flashCards.size()}</span>
                <span>${quizDetail.qs.quizSetName}</span>
            </div>
            <div class="close">
                <button class="printBtn"><span>Print test</span></button>
                <button class="optionBtn"><span class="material-symbols-rounded">settings</span></button>
                <button onclick="window.location.href = 'quizz?id=${requestScope.quizDetail.qs.quizSetId}'"><span class="material-symbols-rounded">close</span></button>
            </div>
        </div>
        <div class="optionPopup-container">
            <div id="optionPopup" class="option-popup">
                <div class="option-popup-content">
                    <span class="close-btn material-symbols-rounded">close</span>
                    <h2>Options</h2>
                    <div class="option-list">
                        <div class="option-item">
                            <span class="option-name">Questions (max ?)</span>
                            <input type="number"/>
                        </div>
                    </div>
                    <div class="option-btn">
                        <button class="cancel-btn"><span>Cancel</span></button>
                        <button class="create-btn"><span>Create new test</span></button>
                    </div>
                </div>
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
                        <c:forEach begin="1" end="${quizDetail.flashCards.size()}" var="i">
                            <p class="answered" onclick="scrollToQuestion(${i})">${i}</p>
                        </c:forEach>
                    </div>
                </div>
            </div>
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
                    </div>
                </c:forEach>
                <div class="submit-btn">
                    <img src="./images/icon/submit_icon.png" alt="alt"/>
                    <span>All done! Ready to submit your test?</span>
                    <button class="submitBtn">Submit test</button>
                </div>
                <div class="confirmSubmitPopup-container">
                    <div id="confirmSubmitPopup" class="confirmSubmit-popup">
                        <div class="confirmSubmit-popup-content">
                            <h2>Are you sure to submit your test?</h2>
                            <p>Number of questions you completed: ?</p>
                            <p>Number of questions you have not completed: ?</p>
                            <div class="confirmSubmit-btn">
                                <button class="cancelSubmit-btn"><span>Cancel</span></button>
                                <button class="submitTest-btn" id="submitQuiz"><span>Submit test</span></button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="./quiz/testquiz.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script>
                                $(document).ready(function () {
                                    let userAnswers = [];

                                    // Khi chọn đáp án
                                    $(".answer").click(function () {
                                        let quizID = $(this).data("quiz-id"); // Lấy ID câu hỏi
                                        let answerID = $(this).data("answer-id"); // Lấy ID đáp án
                                        console.log("Clicked answer:", {quizID, answerID});

                                        // Cập nhật danh sách lựa chọn
                                        let found = false;
                                        for (let i = 0; i < userAnswers.length; i++) {
                                            if (userAnswers[i].quiz.id === quizID) {
                                                userAnswers[i].userAnswer.id = answerID; // Cập nhật đáp án mới
                                                found = true;
                                                break;
                                            }
                                        }
                                        if (!found) {
                                            userAnswers.push({
                                                quiz: {id: quizID}, // Định dạng đúng
                                                userAnswer: {id: answerID} // Định dạng đúng
                                            });
                                        }
                                        console.log("Updated userAnswers:", userAnswers);
                                    });

                                    // Khi ấn "Nộp bài"
                                    $("#submitQuiz").click(function () {
                                        if (userAnswers.length < $(".question").length) {
                                            alert("Bạn chưa trả lời hết tất cả các câu hỏi!");
                                            return;
                                        }

                                        // Gửi dữ liệu qua Servlet
                                        $.ajax({
                                            url: "testquiz",
                                            type: "POST",
                                            contentType: "application/json",
                                            data: JSON.stringify(userAnswers), // Dữ liệu đã đúng format
                                            success: function (response) {
                                                console.log("✅ Success:", response);
                                                if (response.trim() === "Dữ liệu đã được lưu thành công!") {
                                                    window.location.href = "testresult";
                                                } else {
                                                    alert("Phản hồi không hợp lệ từ server: " + response);
                                                }
                                            },
                                            error: function (xhr, status, error) {
                                                console.log("❌ Error Status:", xhr.status);
                                                console.log("❌ Error Response:", xhr.responseText);
                                                alert("Có lỗi xảy ra khi gửi bài! Chi tiết: " + xhr.responseText);
                                            }
                                        });
                                    });
                                });

        </script>
    </body>
</html>
