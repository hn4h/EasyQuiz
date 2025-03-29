
document.querySelector(".sidebar-toggler").addEventListener("click", () => {
    // Toggle collapsed class on sidebar
    document.querySelector(".sidebar").classList.toggle("collapsed");
});

document.addEventListener('DOMContentLoaded', function () {
    document.querySelector(".sidebar").classList.toggle("collapsed");
});
//------------------------Menu of avatar
// Get elements
const avatarUser = document.getElementById('avatarUser');
const dropdownMenu = document.getElementById('userMenu');

// Toggle dropdown menu on click
avatarUser.addEventListener('click', (e) => {
    dropdownMenu.classList.toggle('show');
    e.stopPropagation(); // Prevent click from closing immediately
});

// Close menu when clicking outside
document.addEventListener('click', () => {
    if (dropdownMenu.classList.contains('show')) {
        dropdownMenu.classList.remove('show');
    }
});
//----------------------Menu of create button
// Get elements
const createButton = document.getElementById('createButton');
const dropdownCreateMenu = document.getElementById('createMenu');
const createFolderItem = document.querySelector('.create-menu-item:nth-child(2)'); // Chọn mục "Folder"
const folderPopup = document.getElementById('folderPopup');
const closeBtn = document.querySelector('.close-btn');


// Toggle dropdown menu on click
createButton.addEventListener('click', (e) => {
    dropdownCreateMenu.classList.toggle('show');
    e.stopPropagation(); // Prevent click from closing immediately
});

// Close menu when clicking outside
document.addEventListener('click', () => {
    if (dropdownCreateMenu.classList.contains('show')) {
        dropdownCreateMenu.classList.remove('show');
    }
});


document.addEventListener("DOMContentLoaded", function () {
    const quizForm = document.getElementById("quizForm");
    const addButton = document.querySelector(".add-btn");
    const questionCountInput = document.getElementById("questionCount");
    let questionCount = parseInt(questionCountInput.value, 10) || 1;
    let draggedItem = null;
   

    function updateQuestionNames() {
        const quizItems = quizForm.querySelectorAll(".quiz-item");
        questionCount = quizItems.length;
        questionCountInput.value = questionCount;

        quizItems.forEach((item, index) => {
            const questionNumber = index + 1;
            item.querySelector(".question-text").name = `question${questionNumber}`;
            item.querySelector(".question-id").name = `quizId${questionNumber}`;
            item.querySelectorAll(".form-radio").forEach(button => {
                button.name = `correct${questionNumber}`;
            });
            item.querySelectorAll(".option-input").forEach((answer, answerIndex) => {
                answer.name = `answer${questionNumber}.${answerIndex + 1}`;
            });
        });

        toggleDeleteButtons();
    }

    function createNewQuizItem() {
        const newQuizItem = document.createElement("div");
        newQuizItem.className = "quiz-item";
        const newQuestionNum = questionCount + 1;

        newQuizItem.innerHTML = `
            <div class="quiz-content">
                <i class="fas fa-grip-vertical drag-handle" draggable="true"></i>
                <div class="question">
                    <div class="question-header">
                        <input type="hidden" name="quizId${newQuestionNum}" value="new" class="question-id">
                        <input class="question-text input" type="text" name="question${newQuestionNum}" placeholder="Enter question here">
                        <div class="error-message error${newQuestionNum} question-error"></div>
                        <div class="actions">
                            <i class="fas fa-trash"></i>
                        </div>
                    </div>
                    <p class="text-gray">Answer</p>
                    <div class="options">
                        ${[1, 2, 3, 4].map(i => `
                        <div class="option">
                            <input type="radio" name="correct${newQuestionNum}" value="${i}" class="form-radio">
                            <input type="text" placeholder="Option ${i}" name="answer${newQuestionNum}.${i}" class="option-input">
                        </div>`).join('')}
                    </div>
                    <div class="error-message error${newQuestionNum} option-radio-error"></div>
                </div>
            </div>
        `;
        return newQuizItem;
    }

    addButton.addEventListener("click", function () {
        const newQuizItem = createNewQuizItem();
        const centerBtn = document.querySelector(".center-btn");
        quizForm.appendChild(newQuizItem);
        questionCount++;
        questionCountInput.value = questionCount;
        updateQuestionNames();
    });
    
    function toggleDeleteButtons() {
        const deleteButtons = quizForm.querySelectorAll(".fa-trash");
        if (questionCount <= 1) {
            deleteButtons.forEach(btn => btn.classList.add("disabled"));
        } else {
            deleteButtons.forEach(btn => btn.classList.remove("disabled"));
        }
    }

    quizForm.addEventListener("click", function (event) {
        if (event.target.classList.contains("fa-trash") && !event.target.classList.contains("disabled")) {
            const quizItem = event.target.closest(".quiz-item");
            if (quizItem) {
                quizItem.remove();
                updateQuestionNames();
            }
        }
    });
    
    
    
    let selectedAnswers = new Map(); // Lưu trạng thái của các câu hỏi

    quizForm.addEventListener("dragstart", function (event) {
        if (event.target.classList.contains("drag-handle")) {
            draggedItem = event.target.closest(".quiz-item");
            draggedItem.classList.add("dragging");

            // Lưu trạng thái radio theo quizID
            selectedAnswers.clear();
            document.querySelectorAll(".quiz-item").forEach(item => {
                const quizID = item.querySelector(".question-id").value;
                const checkedRadio = item.querySelector("input[type='radio']:checked");
                if (checkedRadio) {
                    selectedAnswers.set(quizID, checkedRadio.value); // Lưu theo quizID
                }
            });

            event.dataTransfer.effectAllowed = "move";
        }
    });

    quizForm.addEventListener("dragend", function () {
        if (draggedItem) {
            draggedItem.classList.remove("dragging");
            draggedItem = null;
        }
    });

    quizForm.addEventListener("dragover", function (event) {
        event.preventDefault();
        event.dataTransfer.dropEffect = "move";
    });

    quizForm.addEventListener("drop", function (event) {
        event.preventDefault();
        if (draggedItem) {
            const targetItem = event.target.closest(".quiz-item");
            if (targetItem && targetItem !== draggedItem) {
                const allItems = [...quizForm.querySelectorAll(".quiz-item")];
                const draggedIndex = allItems.indexOf(draggedItem);
                const targetIndex = allItems.indexOf(targetItem);

                targetIndex > draggedIndex
                        ? targetItem.after(draggedItem)
                        : targetItem.before(draggedItem);

                updateQuestionNames(); // Cập nhật lại name

                // Khôi phục trạng thái radio theo quizID
                document.querySelectorAll(".quiz-item").forEach(item => {
                    const quizID = item.querySelector(".question-id").value;
                    const selectedValue = selectedAnswers.get(quizID); // Lấy giá trị radio từ quizID
                    if (selectedValue) {
                        const optionsContainer = item.querySelector(`.options${quizID}`); // Lấy đúng options
                        if (optionsContainer) {
                            optionsContainer.querySelectorAll("input[type='radio']").forEach(radio => {
                                if (radio.value === selectedValue) {
                                    radio.checked = true; // Gán lại radio đúng câu hỏi
                                }
                            });
                        }
                    }
                });
            }
        }
    });

    document.querySelectorAll(".drag-handle").forEach(handle => handle.setAttribute("draggable", "true"));
    updateQuestionNames();
});


//------------------------Pop up create folder
// Hiển thị popup
createFolderItem.addEventListener('click', (e) => {
    folderPopup.style.display = "block";
    folderPopup.classList.remove("hide");
    e.stopPropagation();
});

// Ẩn popup với animation fadeOut
closeBtn.addEventListener('click', () => {
    folderPopup.classList.add("hide");
    setTimeout(() => {
        folderPopup.style.display = "none";
    }, 200); // Thời gian khớp với animation fadeOut
});

document.addEventListener("DOMContentLoaded", function () {
    const quizForm = document.getElementById("quizForm");
    const createButton = document.querySelector(".create-btn");
    const globalError = document.getElementById("globalError");

    function validateQuizTitle() {
        const quizTitle = document.querySelector("input[name='quizTitle']");
        const quizTitleError = document.getElementById("quizTitleError");
        if (!quizTitle.value.trim()) {
            quizTitleError.textContent = "Title cannot be empty.";
            quizTitle.classList.add("input-error");
            return false;
        } else {
            quizTitleError.textContent = "";
            quizTitle.classList.remove("input-error");
            return true;
        }
    }

    function validateQuizItem(item) {
        let isValid = true;
        const questionText = item.querySelector(".question-text");
        const options = item.querySelectorAll(".option-input");
        const quizID = item.querySelector(".question-id").value; // Lấy quizID
        const correctAnswer = item.querySelector(`input[type='radio']:checked`);

        if (!questionText.value.trim()) {
            isValid = false;
        }

        options.forEach(option => {
            if (!option.value.trim()) {
                isValid = false;
            }
        });

        if (!correctAnswer) {
            isValid = false;
        }

        return isValid;
    }

    createButton.addEventListener("click", function (event) {
        event.preventDefault();
        let isValid = validateQuizTitle();

        const quizItems = document.querySelectorAll(".quiz-item");
        quizItems.forEach(item => {
            if (!validateQuizItem(item)) { // Không cần index
                isValid = false;
            }
        });

        if (!isValid) {
            globalError.textContent = "All inputs must be filled.";
        } else {
            globalError.textContent = "";
            quizForm.submit();
        }
    });

    document.querySelector("input[name='quizTitle']").addEventListener("input", validateQuizTitle);

    function attachValidationEvents() {
        document.querySelectorAll(".quiz-item").forEach(item => {
            item.querySelector(".question-text").addEventListener("input", () => validateQuizItem(item));
            item.querySelectorAll(".option-input").forEach(option => {
                option.addEventListener("input", () => validateQuizItem(item));
            });
            item.querySelectorAll(".form-radio").forEach(radio => {
                radio.addEventListener("change", () => validateQuizItem(item));
            });
        });
    }

    attachValidationEvents();
});
