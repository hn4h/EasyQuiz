
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

document.querySelector(".sidebar-toggler").addEventListener("click", () => {
    closeAllDropdowns();
    // Toggle collapsed class on sidebar
    document.querySelector(".sidebar").classList.toggle("collapsed");
});

document.addEventListener('DOMContentLoaded', function () {
    document.querySelector(".sidebar").classList.toggle("collapsed");
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

    quizForm.addEventListener("click", function (event) {
        if (event.target.classList.contains("fa-trash")) {
            const quizItem = event.target.closest(".quiz-item");
            if (quizItem) {
                quizItem.remove();
                updateQuestionNames();
            }
        }
    });

    quizForm.addEventListener("dragstart", function (event) {
        if (event.target.classList.contains("drag-handle")) {
            draggedItem = event.target.closest(".quiz-item");
            draggedItem.classList.add("dragging");
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
                targetIndex > draggedIndex ? targetItem.after(draggedItem) : targetItem.before(draggedItem);
                updateQuestionNames();
            }
        }
    });

    document.querySelectorAll(".drag-handle").forEach(handle => handle.setAttribute("draggable", "true"));
    updateQuestionNames();
});

document.getElementById("quizForm").addEventListener("submit", function(event) {
    let quizItems = document.querySelectorAll(".quiz-item"); 
    let isValid = true; 

    quizItems.forEach((item) => {
        let checkedRadio = item.querySelector("input[type='radio']:checked"); 
        if (!checkedRadio) {
            isValid = false;
        }
    });

    if (!isValid) {
        alert(`You need to choose correct answer for all questions!`);
        event.preventDefault(); 
    }
});
