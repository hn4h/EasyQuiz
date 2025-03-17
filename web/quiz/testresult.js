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

//---------------------------Circle percentage
function updateCircle(correct, incorrect) {
    let total = correct + incorrect;
    let percentage = total === 0 ? 0 : Math.round((correct / total) * 100);
    let circle = document.querySelector('.circle');
    let percentageText = document.querySelector('.percentage-text');

    let dashArrayValue = `${percentage}, 100`;
    circle.setAttribute('stroke-dasharray', dashArrayValue);
    percentageText.textContent = `${percentage}%`;

    document.getElementById("correct").textContent = correct;
    document.getElementById("incorrect").textContent = incorrect;
}

function scrollToQuestion(questionNumber) {
    let targetQuestion = document.getElementById(`question-${questionNumber}`);
    if (targetQuestion) {
        targetQuestion.scrollIntoView({behavior: 'smooth', block: 'center'});
    }
}
let duration = document.querySelector(".duration").value * 60;
let timeLeft = sessionStorage.getItem("quizTimeLeft");
const timerDisplay = document.getElementById("timer");
const timeRemain = document.getElementById("time-remain");
function updateTimerDisplay() {
    let minutes = Math.floor(timeLeft / 60);
    let seconds = timeLeft % 60;
    let minute = Math.floor(duration / 60);
    let second = duration % 60;
    timerDisplay.textContent = `Your time: ${minute.toString().padStart(2, '0')}:${second.toString().padStart(2, '0')}`;
    timeRemain.textContent = `Time remaining: ${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}`;
}

updateTimerDisplay();

document.querySelectorAll(".remove-session").forEach(element => {
    element.addEventListener("click", function () {
        sessionStorage.removeItem("quizTimeLeft");
    });
});

//------------------------Pop up option
const optionItem = document.querySelector('.test-btn');
const optionPopup = document.getElementById('optionPopup');
const closeTestBtn = document.querySelector('.closetest-btn');
const cancelBtn = document.querySelector('.cancel-btn');

// Hiển thị popup
optionItem.addEventListener('click', (e) => {
    optionPopup.style.display = "block";
    optionPopup.classList.remove("hide");
    e.stopPropagation();
});

// Ẩn popup với animation fadeOut
closeTestBtn.addEventListener('click', () => {
    optionPopup.classList.add("hide");
    setTimeout(() => {
        optionPopup.style.display = "none";
    }, 200); // Thời gian khớp với animation fadeOut
});

// Ẩn popup với animation fadeOut
cancelBtn.addEventListener('click', () => {
    optionPopup.classList.add("hide");
    setTimeout(() => {
        optionPopup.style.display = "none";
    }, 200); // Thời gian khớp với animation fadeOut
});