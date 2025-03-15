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

// Ẩn popup với animation fadeOut
closeBtn.addEventListener('click', () => {
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

    let userAnswers = []; // Mảng lưu câu trả lời của user
    let answeredCount = 0; // Đếm số câu đã trả lời

    answers.forEach(answer => {
        answer.addEventListener("click", function () {
            const parentQuestion = this.closest(".quiz"); // Tìm câu hỏi cha
            const questionIndex = [...document.querySelectorAll(".quiz")].indexOf(parentQuestion); // Xác định vị trí câu hỏi
            const quizID = this.getAttribute("data-quiz-id");
            const answerID = this.getAttribute("data-answer-id");

            // Nếu đã chọn trước đó => Hủy chọn
            if (this.classList.contains("selected")) {
                this.classList.remove("selected");

                // Xóa đáp án khỏi mảng userAnswers
                userAnswers = userAnswers.filter(ans => ans.quizID !== quizID);

                // Nếu tất cả các đáp án của câu đó đều bị bỏ chọn, hủy trạng thái "answered"
                if (!parentQuestion.querySelector(".answer.selected")) {
                    answeredCount--;
                    pageIndicator.innerText = `${answeredCount} / ${questionNumbers.length}`;
                    parentQuestion.classList.remove("answered");
                    questionNumbers[questionIndex].classList.remove("answered-question");
                }
                return;
            }

            // Nếu chưa chọn thì lưu vào mảng
            userAnswers = userAnswers.filter(ans => ans.quizID !== quizID); // Xóa đáp án cũ nếu có
            userAnswers.push({ quizID: quizID, answerID: answerID }); // Thêm đáp án mới

            console.log("User Answers:", userAnswers); // Kiểm tra dữ liệu

            // Nếu chưa chọn câu hỏi này, đánh dấu là đã chọn
            if (!parentQuestion.classList.contains("answered")) {
                answeredCount++;
                pageIndicator.innerText = `${answeredCount} / ${questionNumbers.length}`;
                parentQuestion.classList.add("answered");
                questionNumbers[questionIndex].classList.add("answered-question");
            }

            // Xóa lớp 'selected' khỏi các đáp án khác trong cùng câu hỏi
            parentQuestion.querySelectorAll(".answer").forEach(ans => ans.classList.remove("selected"));

            // Thêm class 'selected' vào đáp án vừa chọn
            this.classList.add("selected");
        });
    });

    // Khi ấn "Nộp bài"
    document.getElementById("submitQuiz").addEventListener("click", function () {
        if (userAnswers.length < document.querySelectorAll(".quiz").length) {
            alert("Bạn chưa trả lời hết tất cả các câu hỏi!");
            return;
        }

        // Gửi dữ liệu qua Servlet
        fetch("testquiz", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(userAnswers),
        })
        .then(response => response.json())
        .then(data => {
            window.location.href = "testresult";
        })
        .catch(error => {
            console.error("Lỗi khi gửi bài:", error);
            alert("Có lỗi xảy ra khi gửi bài!");
        });
    });
});


function scrollToQuestion(questionNumber) {
    let targetQuestion = document.getElementById(`question-${questionNumber}`);
    if (targetQuestion) {
        targetQuestion.scrollIntoView({behavior: 'smooth', block: 'center'});
    }
}

//-----------------------------Chuyen data nhung lua chon cua nguoi dung sang be






