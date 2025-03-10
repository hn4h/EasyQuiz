document.addEventListener("DOMContentLoaded", function () {
    const learnMenuBtn = document.querySelector(".learn-icon button");
    const learnMenu = document.querySelector(".learn-menu");
    const learnComplete = document.getElementById("learnComplete"); // Thêm div hoàn thành

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

    if (!quizzes.length) {
        console.error("No quizzes found!");
        return;
    }
    if (!continueContainer || !progressStart || !line1 || !line2 || !line3) {
        console.error("Required elements not found!");
        return;
    }

    function updateProgress() {
        const totalQuizzes = quizzes.length;
        const correctPercentage = (correctAnswers / totalQuizzes) * 100;
        const wrongPercentage = (wrongAnswers / totalQuizzes) * 100;

        line1.style.width = `${correctPercentage}%`;
        line2.style.width = `${correctPercentage + wrongPercentage}%`;
        line3.style.width = "100%";
        progressStart.textContent = correctAnswers;
        console.log(`Progress updated: Correct: ${correctPercentage}%, Wrong: ${wrongPercentage}%`);
    }

    function showNextQuiz() {
        if (currentQuizIndex < quizzes.length - 1) {
            quizzes[currentQuizIndex].style.display = "none";
            currentQuizIndex++;
            quizzes[currentQuizIndex].style.display = "block";
            continueContainer.classList.remove("show"); // Ẩn với hiệu ứng

            const answers = quizzes[currentQuizIndex].querySelectorAll(".answer");
            answers.forEach(answer => {
                const answerNumber = answer.querySelector(".answer-number");
                answerNumber.innerHTML = answerNumber.textContent.replace(/✔|✖/, '');
                answerNumber.style.backgroundColor = "#e8e8e8";
            });
        } else {
            document.querySelector(".progress").style.display = "none"; // Ẩn phần body chính
            document.querySelector(".learn-container").style.display = "none";
//          // Kích hoạt hiệu ứng fade up cho màn hình hoàn thành
            learnComplete.style.display = "flex";
            setTimeout(() => {
                learnComplete.classList.add("show");
            }, 50);
        }
    }

    const answers = document.querySelectorAll(".answer");
    if (!answers.length) {
        console.error("No answers found!");
    }
    answers.forEach(answer => {
        answer.addEventListener("click", function () {
            console.log("Answer clicked:", this);
            if (continueContainer.classList.contains("show"))
                return;

            const isCorrect = this.getAttribute("data-correct") === "true";
            const answerNumber = this.querySelector(".answer-number");

            if (isCorrect) {
                answerNumber.innerHTML = "✔";
                answerNumber.style.backgroundColor = "#8BC34A";
                correctAnswers++;
            } else {
                answerNumber.innerHTML = "✖";
                answerNumber.style.backgroundColor = "#FF0000";
                wrongAnswers++;
                const correctAnswer = this.parentElement.querySelector("[data-correct='true']");
                correctAnswer.querySelector(".answer-number").innerHTML = "✔";
                correctAnswer.querySelector(".answer-number").style.backgroundColor = "#8BC34A";
            }

            updateProgress();
            continueContainer.classList.add("show"); // Thêm hiệu ứng fade-up
        });
    });

    const dontKnowButtons = document.querySelectorAll(".dont-know");
    if (!dontKnowButtons.length) {
        console.error("No 'Don't know' buttons found!");
    }
    dontKnowButtons.forEach(button => {
        button.addEventListener("click", function () {
            console.log("Don't know clicked:", this);
            if (continueContainer.classList.contains("show"))
                return;

            const correctAnswer = this.closest(".quiz").querySelector("[data-correct='true']");
            correctAnswer.querySelector(".answer-number").innerHTML = "✔";
            correctAnswer.querySelector(".answer-number").style.backgroundColor = "#8BC34A";
            wrongAnswers++;
            updateProgress();
            continueContainer.classList.add("show"); // Thêm hiệu ứng fade-up
        });
    });

    const continueBtn = document.querySelector(".continue-btn");
    if (!continueBtn) {
        console.error("Continue button not found!");
    }
    continueBtn.addEventListener("click", function () {
        continueContainer.classList.remove("show"); // Ẩn với hiệu ứng mượt
        showNextQuiz();
    });

    updateProgress();
});
