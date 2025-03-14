// SwiperJS pagination
const swiper = new Swiper('.card-wrapper', {
    loop: true,
    spaceBetween: 20,

    // Pagination bullets
    pagination: {
        el: '.swiper-pagination',
        clickable: true,
        dynamicBullets: true
    },

    // Navigation arrows
    navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
    },

    breakpoints: {
        0: {
            slidesPerView: 1
        },
        768: {
            slidesPerView: 2
        },
        1024: {
            slidesPerView: 3
        },
    }
});
//Toggle the visibility of a dropdown menu
const toggleDropdown = (dropdown, menu, isOpen) => {
    dropdown.classList.toggle("open", isOpen);
    menu.style.height = isOpen ? `${menu.scrollHeight}px` : 0;
}

const closeAllDropdowns = () => {
    document.querySelectorAll(".dropdown-container.open").forEach(openDropdown => {
        toggleDropdown(openDropdown, openDropdown.querySelector(".dropdown-subject"), false);
    });
}
//Attach click event to all dropdown toggles
document.querySelectorAll(".dropdown-toggle").forEach(dropdownToggle => {
    dropdownToggle.addEventListener("click", e => {
        e.preventDefault();

        const dropdown = e.target.closest(".dropdown-container");
        const menu = dropdown.querySelector(".dropdown-subject");
        const isOpen = dropdown.classList.contains("open");

        closeAllDropdowns();//Close all open dropdowns

        toggleDropdown(dropdown, menu, !isOpen); //Toggle current dropdown visibility
    });
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
