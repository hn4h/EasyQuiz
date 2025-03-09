document.addEventListener("DOMContentLoaded", function () {
    const learnMenuBtn = document.querySelector(".learn-icon button");
    const learnMenu = document.querySelector(".learn-menu");

    if (!learnMenuBtn || !learnMenu) {
        console.error("Learn menu button or menu not found!");
    }

    learnMenuBtn.addEventListener("click", function (event) {
        learnMenu.classList.toggle("show");
        event.stopPropagation();
    });

    document.addEventListener("click", function (event) {
        if (!learnMenuBtn.contains(event.target) && !learnMenu.contains(event.target)) {
            learnMenu.classList.remove("show");
        }
    });

    const quizzes = document.querySelectorAll(".quiz");
    const continueContainer = document.querySelector(".continue-container");
    const progressStart = document.querySelector(".progress-start p");
    const line1 = document.querySelector(".line-1");
    const line2 = document.querySelector(".line-2");
    const line3 = document.querySelector(".line-3");
    let currentQuizIndex = 0;
    let correctAnswers = 0;
    let wrongAnswers = 0;

    // Kiểm tra các phần tử
    if (!quizzes.length) {
        console.error("No quizzes found!");
        return;
    }
    if (!continueContainer || !progressStart || !line1 || !line2 || !line3) {
        console.error("Required elements (continueContainer, progressStart, lines) not found!");
        return;
    }

    function updateProgress() {
        const totalQuizzes = quizzes.length;
        const correctPercentage = (correctAnswers / totalQuizzes) * 100;
        const wrongPercentage = (wrongAnswers / totalQuizzes) * 100;

        line1.style.width = `${correctPercentage}%`; // Phần xanh nhạt (câu đúng)
        line2.style.left = `${correctPercentage}%`;   // Dịch chuyển phần xanh đậm
        line2.style.width = `${wrongPercentage}%`;    // Phần xanh đậm (câu sai)
        line3.style.width = "100%";                   // Phần xám luôn full làm nền
        progressStart.textContent = correctAnswers;   // Cập nhật số câu đúng
        console.log(`Progress updated: Correct: ${correctPercentage}%, Wrong: ${wrongPercentage}%`);
    }

    function showNextQuiz() {
        if (currentQuizIndex < quizzes.length - 1) {
            quizzes[currentQuizIndex].style.display = "none";
            currentQuizIndex++;
            quizzes[currentQuizIndex].style.display = "block";
            continueContainer.style.display = "none";

            // Reset trạng thái các đáp án cho quiz mới
            const answers = quizzes[currentQuizIndex].querySelectorAll(".answer");
            answers.forEach(answer => {
                const answerNumber = answer.querySelector(".answer-number");
                answerNumber.innerHTML = answerNumber.textContent.replace(/✔|✖/, ''); // Reset về số ban đầu
                answerNumber.style.backgroundColor = "#e8e8e8";    // Reset màu nền
            });
        } else {
            alert("Quiz completed!");
        }
    }

    const answers = document.querySelectorAll(".answer");
    if (!answers.length) {
        console.error("No answers found!");
    }
    answers.forEach(answer => {
        answer.addEventListener("click", function () {
            console.log("Answer clicked:", this);
            if (continueContainer.style.display === "flex") return; // Ngăn click nhiều lần

            const isCorrect = this.getAttribute("data-correct") === "true";
            const answerNumber = this.querySelector(".answer-number");

            if (isCorrect) {
                answerNumber.innerHTML = "✔"; // Dấu tích xanh
                answerNumber.style.backgroundColor = "#8BC34A"; // Xanh nhạt
                correctAnswers++;
            } else {
                answerNumber.innerHTML = "✖"; // Dấu x đỏ
                answerNumber.style.backgroundColor = "#FF0000"; // Đỏ
                wrongAnswers++;
                const correctAnswer = this.parentElement.querySelector("[data-correct='true']");
                correctAnswer.querySelector(".answer-number").innerHTML = "✔"; // Hiển thị đáp án đúng
                correctAnswer.querySelector(".answer-number").style.backgroundColor = "#8BC34A"; // Xanh nhạt
            }

            updateProgress();
            continueContainer.style.display = "flex"; // Hiển thị nút Continue
        });
    });

    const dontKnowButtons = document.querySelectorAll(".dont-know");
    if (!dontKnowButtons.length) {
        console.error("No 'Don't know' buttons found!");
    }
    dontKnowButtons.forEach(button => {
        button.addEventListener("click", function () {
            console.log("Don't know clicked:", this);
            if (continueContainer.style.display === "flex") return; // Ngăn click nhiều lần

            const correctAnswer = this.closest(".quiz").querySelector("[data-correct='true']");
            correctAnswer.querySelector(".answer-number").innerHTML = "✔"; // Hiển thị đáp án đúng
            correctAnswer.querySelector(".answer-number").style.backgroundColor = "#8BC34A"; // Xanh nhạt
            wrongAnswers++;
            updateProgress();
            continueContainer.style.display = "flex"; // Hiển thị nút Continue
        });
    });

    const continueBtn = document.querySelector(".continue-btn");
    if (!continueBtn) {
        console.error("Continue button not found!");
    }
    continueBtn.addEventListener("click", showNextQuiz);

    // Khởi tạo thanh tiến độ
    updateProgress();
});