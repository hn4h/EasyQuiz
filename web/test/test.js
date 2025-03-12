document.addEventListener("DOMContentLoaded", function () {
    document.querySelector(".test-word").addEventListener("click", toggleUserMenu);
    document.querySelector(".test-img").addEventListener("click", toggleUserMenu);
    document.getElementById("hamburgerBtn").addEventListener("click", toggleSidebar);
    document.getElementById("closeBtn").addEventListener("click", closeSidebar);
    document.getElementById("submit").addEventListener("click", submitTest);
    document.querySelector(".open-popup")?.addEventListener("click", openPopup);
    document.querySelector(".close-popup")?.addEventListener("click", closePopup);
    document.getElementById("popupOverlay")?.addEventListener("click", closePopup);

    document.addEventListener("click", function (event) {
        let sidebar = document.getElementById("sidebar");
        let userMenu = document.querySelector(".user-menu");

        if (!sidebar.contains(event.target) && !event.target.closest("#hamburgerBtn")) {
            closeSidebar();
        }

        if (userMenu && !userMenu.contains(event.target) &&
                !event.target.classList.contains("test-word") &&
                !event.target.classList.contains("test-img")) {
            userMenu.classList.remove("show");
        }
    });
    
    // Lấy tất cả các nhóm câu hỏi
    const questions = document.querySelectorAll(".container1");
    let userAnswers = {}; // Lưu trạng thái lựa chọn của người dùng
    questions.forEach((question, index) => {
        const answers = question.querySelectorAll(".options1 button");

        answers.forEach(answer => {
            answer.addEventListener("click", function () {
                // Loại bỏ lớp 'selected' khỏi tất cả đáp án trong câu hỏi hiện tại
                answers.forEach(btn => btn.classList.remove("selected"));
                
                // Thêm lớp 'selected' vào đáp án được chọn
                this.classList.add("selected");
                 // Lưu kết quả của câu hỏi
                const isCorrect = this.getAttribute("data-correct") === "true";
                userAnswers[index] = isCorrect;
                // Chuyển xuống câu hỏi tiếp theo (nếu có)
                const nextQuestion = questions[index + 1];
                if (nextQuestion) {
                    nextQuestion.scrollIntoView({ behavior: "smooth", block: "center" });
                }
            });
        });
    });
});
// Hàm toggle sidebar
function toggleSidebar() {
    let sidebar = document.getElementById("sidebar");
    if (sidebar) {
        sidebar.classList.toggle("show");
    }
}

// Hàm đóng sidebar
function closeSidebar() {
    let sidebar = document.getElementById("sidebar");
    if (sidebar) {
        sidebar.classList.remove("show");
    }
}


// Hàm toggle user menu
function toggleUserMenu(event) {
    event.stopPropagation();
    let userMenu = document.querySelector(".user-menu");
    if (userMenu) {
        userMenu.classList.toggle("show");
    }
}

// Hàm mở popup
function openPopup() {
    document.getElementById("popup").classList.add("show");
    document.getElementById("popupOverlay").classList.add("show");
}

// Hàm đóng popup
function closePopup() {
    document.getElementById("popup").classList.remove("show");
    document.getElementById("popupOverlay").classList.remove("show");
}
function closePopup1() {
    let popup1 = document.getElementById("popup1");
    let overlay1 = document.getElementById("popupOverlay1");

    if (popup1 && overlay1) {
        popup1.classList.add("hide");
        overlay1.classList.add("hide");
    } else {
        console.error("Popup1 or Overlay1 not found!");
    }
}

window.closePopup1 = closePopup1;


// Nút submit cập nhật giao diện
function submitTest() {
    // Lấy danh sách tất cả câu hỏi
    const questions = document.querySelectorAll(".container1");

    let correctCount = 0;
    let totalQuestions = questions.length;

    // Kiểm tra từng câu hỏi
    questions.forEach((question) => {
        let selectedAnswer = question.querySelector(".options1 .selected"); // Lấy đáp án người dùng chọn
        let correctAnswer = question.querySelector(".options1 .correct1"); // Lấy đáp án đúng

        if (selectedAnswer && correctAnswer) {
            if (selectedAnswer === correctAnswer) {
                correctCount++; // Nếu đúng, tăng số câu đúng
            }
        }
    });

    let incorrectCount = totalQuestions - correctCount;
    let correctPercentage = ((correctCount / totalQuestions) * 100).toFixed(1);
    document.body.innerHTML = `
            <div class="header">
                <div class="test">
                    <div class="avatar-user" id="avatarUser">
                        <img class="test-img" src="../images/icon/test_icon.png">
                        <span class="test-word">Test</span>
                        <div class="user-menu" id="userMenu">
                            <hr/>
                            <a href="#" class="user-menu-item">Learn</a>
                            <a href="#" class="user-menu-item">Flashcard</a>
                            <hr/>
                            <a href="#" class="user-menu-item">Home</a>
                            
                        </div>
                    </div>
                </div>
                <p class="number">1 / 3</p>
                <p class="exit">X</p>
            </div>
            <div class="flashcard-complete" id="flashcardComplete">
                <h2>Don't give up now! Trust the process.</h2>
                <div class="progress-actions">
                    <div class="progress">
                        <h3>Your time: 1 min.</h3>
                        <div class="progress-info">
                            <span class="circle">${correctPercentage}%</span>
                            <div class="progress-info-complete">
                                <p class="correct">Correct:<span id="correctCount">${correctCount}</span></p>
                                <p class="incorrect">Incorrect:<span id="incorrectCount">${incorrectCount}</span></p>
                            </div>
                        </div>
                    </div>
                    <div class="actions">
                        <h3>Next steps</h3>
                        <div class="actions-info">
                            <button id="nextSteps"><a href="#">Practice terms in Learn</a></button>
                            <button id="restartFlashcards"><a href="#">Take a new test</a></button>
                        </div>
                    </div>
                </div>
            </div>
            <h3 class="Your-answers">Your answers</h3>
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
                <div class="hamburger" id="hamburgerBtn">
                    <span class="material-symbols-rounded">menu</span>
                </div>
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
        `;

    document.body.classList.add("*");
    document.body.classList.add("body");

    // Gọi lại hàm để gắn sự kiện cho phần tử mới
    document.querySelector(".test-word")?.addEventListener("click", toggleUserMenu);
    document.querySelector(".test-img")?.addEventListener("click", toggleUserMenu);
    document.getElementById("hamburgerBtn")?.addEventListener("click", toggleSidebar);
    document.getElementById("closeBtn")?.addEventListener("click", closeSidebar);
}




