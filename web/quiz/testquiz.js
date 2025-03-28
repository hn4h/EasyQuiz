document.addEventListener("DOMContentLoaded", function () {
    const testMenuBtn = document.querySelector(".test-icon button"); // Nút Learn
    const testMenu = document.querySelector(".test-menu"); // Menu Learn

    testMenuBtn.addEventListener("click", function (event) {
        testMenu.classList.toggle("show"); // Hiện/ẩn menu khi click

        // Ngăn sự kiện click lan ra ngoài
        event.stopPropagation();
    });

    // Ẩn menu khi click ra ngoài
    document.addEventListener("click", function (event) {
        if (!testMenuBtn.contains(event.target) && !testMenu.contains(event.target)) {
            testMenu.classList.remove("show");
        }
    });
});
//---------------------Menu list question
document.addEventListener("DOMContentLoaded", function () {
    const menuButton = document.getElementById("menuButton");
    const hideButton = document.getElementById("hideButton");
    const questionList = document.getElementById("questionList");

    menuButton.addEventListener("click", function () {
        menuButton.style.display = "none";
        questionList.classList.add("show");
    });

    hideButton.addEventListener("click", function () {
        menuButton.style.display = "inline-block";
        questionList.classList.remove("show");
    });
});
//------------------------Pop up option
const optionItem = document.querySelector('.optionBtn');
const optionPopup = document.getElementById('optionPopup');
const closeBtn = document.querySelector('.close-btn');
const cancelBtn = document.querySelector('.cancel-btn');

// Hiển thị popup
optionItem.addEventListener('click', (e) => {
    optionPopup.style.display = "block";
    optionPopup.classList.remove("hide");
    e.stopPropagation();
});




//---------------Pop up confirm submit
const submitItem = document.querySelector('.submitBtn');
const confirmSubmitPopup = document.getElementById('confirmSubmitPopup');
const cancelConfirmBtn = document.querySelector('.cancelSubmit-btn');

// Hiển thị popup
submitItem.addEventListener('click', (e) => {
    confirmSubmitPopup.style.display = "block";
    confirmSubmitPopup.classList.remove("hide");
    e.stopPropagation();
});

// Ẩn popup với animation fadeOut
cancelConfirmBtn.addEventListener('click', () => {
    confirmSubmitPopup.classList.add("hide");
    setTimeout(() => {
        confirmSubmitPopup.style.display = "none";
    }, 200); // Thời gian khớp với animation fadeOut
});

//-----------------------------Answer hover
document.addEventListener("DOMContentLoaded", function () {
    const answers = document.querySelectorAll(".answer");
    const pageIndicator = document.getElementById("pageIndicator");
    const questionNumbers = document.querySelectorAll(".answered");
    const quizForm = document.getElementById("quizForm");
    const submitButton = document.getElementById("submitQuiz");
    const numCompleted = document.getElementById("completed-num");
    const numInCompleted = document.getElementById("incompleted-num");
    let answeredCount = 0;
    let totalQuestions = document.querySelectorAll(".quiz").length;
    let timeLeft = sessionStorage.getItem("quizTimeLeft");
    // Xử lý khi chọn đáp án
    answers.forEach(answer => {
        answer.addEventListener("click", function () {
            const parentQuestion = this.closest(".quiz");
            const questionIndex = [...document.querySelectorAll(".quiz")].indexOf(parentQuestion);
            const quizID = this.getAttribute("data-quiz-id");
            const answerID = this.getAttribute("data-answer-id");

            // Xóa lựa chọn cũ và thêm lựa chọn mới
            parentQuestion.querySelectorAll(".answer").forEach(ans => ans.classList.remove("selected"));
            this.classList.add("selected");

            // Cập nhật input ẩn
            document.getElementById(`answerInput_${quizID}`).value = answerID;

            // Cập nhật số câu đã trả lời
            if (!parentQuestion.classList.contains("answered")) {
                answeredCount++;
                pageIndicator.innerText = `${answeredCount} / ${totalQuestions}`;
                numCompleted.innerText = `${answeredCount}`;
                numInCompleted.innerText = `${totalQuestions - answeredCount}`;
                parentQuestion.classList.add("answered");
                questionNumbers[questionIndex].classList.add("answered-question");
            }
            if (totalQuestions - answeredCount > 0) {
                numInCompleted.style.color = "red";  // Chưa hoàn thành -> màu đỏ
            } else {
                numInCompleted.style.color = "black"; // Hoàn thành hết -> màu đen
            }
        });
    });

    if (!timeLeft) {
        timeLeft = document.getElementById("time-left").value * 60; // Chuyển từ phút sang giây
    } else {
        timeLeft = parseInt(timeLeft);
    }

    const timerDisplay = document.getElementById("timer");

    function updateTimerDisplay() {
        let minutes = Math.floor(timeLeft / 60);
        let seconds = timeLeft % 60;
        timerDisplay.textContent = `Time remaining: ${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}`;
    }

    updateTimerDisplay();

    const countdown = setInterval(() => {
        if (timeLeft > 0) {
            timeLeft--;
            sessionStorage.setItem("quizTimeLeft", timeLeft);
            updateTimerDisplay();
        } else {
            clearInterval(countdown);
            sessionStorage.removeItem("quizTimeLeft");
            alert("Time's up! Submitting test...");
            document.getElementById("quizForm").submit();
        }
    }, 1000);

    document.getElementById("cancel-test").addEventListener("click", function () {
        sessionStorage.removeItem("quizTimeLeft");
    });

    submitButton.addEventListener("click", function (event) {
        event.preventDefault(); // Ngăn form submit ngay lập tức

        //ssessionStorage.removeItem("quizTimeLeft"); // Xóa sessionStorage

        setTimeout(() => {
            quizForm.submit(); // Chờ 100ms trước khi submit
        }, 0);
    });

});

document.querySelectorAll(".remove-session").forEach(element => {
    element.addEventListener("click", function () {
        sessionStorage.removeItem("quizTimeLeft");
    });
});




function scrollToQuestion(questionNumber) {
    let targetQuestion = document.getElementById(`question-${questionNumber}`);
    if (targetQuestion) {
        targetQuestion.scrollIntoView({behavior: 'smooth', block: 'center'});
    }
}


// Ẩn popup với animation fadeOut
cancelBtn.addEventListener('click', () => {
    optionPopup.classList.add("hide");
    setTimeout(() => {
        optionPopup.style.display = "none";
    }, 200); // Thời gian khớp với animation fadeOut
});

//-----------------------------Chuyen data nhung lua chon cua nguoi dung sang be


