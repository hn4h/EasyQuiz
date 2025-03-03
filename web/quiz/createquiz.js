/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */
// createquiz.js

document.addEventListener('DOMContentLoaded', function() {
    const addButton = document.querySelector('.add-btn');
    const bodyContainer = document.querySelector('.body-container');
    let questionCount = 1; // Initialize count with existing question

    // Function to update all question names and inputs based on their current position
    function updateQuestionNames() {
        const quizItems = bodyContainer.querySelectorAll('.quiz-item');
        questionCount = quizItems.length; // Update total count
        
        quizItems.forEach((item, index) => {
            const questionNumber = index + 1;
            
            // Update question input name
            const questionInput = item.querySelector('.question-text');
            questionInput.name = `question${questionNumber}`;
            
            // Update radio buttons name
            const radioButtons = item.querySelectorAll('.form-radio');
            radioButtons.forEach(button => {
                button.name = `correct${questionNumber}`;
            });
            
            // Update answer inputs name
            const answerInputs = item.querySelectorAll('.option-input');
            answerInputs.forEach((answer, answerIndex) => {
                answer.name = `answer${questionNumber}.${answerIndex + 1}`;
            });
        });
        
        // Update total questions counter (you can use this variable as needed)
        console.log(`Total questions: ${questionCount}`); // For debugging
    }

    // Function to create a new quiz item
    function createNewQuizItem() {
        const newQuizItem = document.createElement('div');
        newQuizItem.className = 'quiz-item';
        
        // Use questionCount + 1 for new item naming
        const newQuestionNum = questionCount + 1;
        
        newQuizItem.innerHTML = `
            <div class="quiz-content">
                <i class="fas fa-grip-vertical drag-handle"></i>
                <div class="question">
                    <div class="question-header">
                        <input class="question-text input" type="text" name="question${newQuestionNum}" placeholder="Enter question here">
                        <div class="actions">
                            <i class="fas fa-trash"></i>
                        </div>
                    </div>
                    <p class="text-gray">Answer</p>
                    <div class="options">
                        <div class="option">
                            <input type="radio" name="correct${newQuestionNum}" value="option1" class="form-radio">
                            <input type="text" placeholder = "Option 1" name="answer${newQuestionNum}.1" class="option-input">
                        </div>
                        <div class="option">
                            <input type="radio" name="correct${newQuestionNum}" value="option2" class="form-radio">
                            <input type="text" placeholder = "Option 2" name="answer${newQuestionNum}.2" class="option-input">
                        </div>
                        <div class="option">
                            <input type="radio" name="correct${newQuestionNum}" value="option3" class="form-radio">
                            <input type="text" placeholder = "Option 3" name="answer${newQuestionNum}.3" class="option-input">
                        </div>
                        <div class="option">
                            <input type="radio" name="correct${newQuestionNum}" value="option4" class="form-radio">
                            <input type="text" placeholder = "Option 4" name="answer${newQuestionNum}.4" class="option-input">
                        </div>
                    </div>
                </div>
            </div>
        `;

        const dragHandle = newQuizItem.querySelector('.drag-handle');
        dragHandle.setAttribute('draggable', 'true');
        
        return newQuizItem;
    }

    // Add new quiz item
    addButton.addEventListener('click', function() {
        const newQuizItem = createNewQuizItem();
        const centerBtn = document.querySelector('.center-btn');
        bodyContainer.insertBefore(newQuizItem, centerBtn);
        questionCount++; // Increment counter
        updateQuestionNames(); // Update all names
    });

    // Delete quiz item
    bodyContainer.addEventListener('click', function(event) {
        if (event.target.classList.contains('fa-trash')) {
            const quizItem = event.target.closest('.quiz-item');
            if (quizItem) {
                quizItem.remove();
                updateQuestionNames(); // Update names after deletion
            }
        }
    });

    // Drag and Drop functionality
    let draggedItem = null;

    bodyContainer.addEventListener('dragstart', function(event) {
        if (event.target.classList.contains('drag-handle')) {
            draggedItem = event.target.closest('.quiz-item');
            draggedItem.classList.add('dragging');
            event.dataTransfer.effectAllowed = 'move';
        } else {
            event.preventDefault();
        }
    });

    bodyContainer.addEventListener('dragend', function(event) {
        if (draggedItem) {
            draggedItem.classList.remove('dragging');
            draggedItem = null;
        }
    });

    bodyContainer.addEventListener('dragover', function(event) {
        event.preventDefault();
        event.dataTransfer.dropEffect = 'move';
    });

    bodyContainer.addEventListener('drop', function(event) {
        event.preventDefault();
        if (draggedItem) {
            const targetItem = event.target.closest('.quiz-item');
            if (targetItem && targetItem !== draggedItem) {
                const allItems = Array.from(bodyContainer.querySelectorAll('.quiz-item'));
                const draggedIndex = allItems.indexOf(draggedItem);
                const targetIndex = allItems.indexOf(targetItem);

                if (draggedIndex < targetIndex) {
                    targetItem.after(draggedItem);
                } else {
                    targetItem.before(draggedItem);
                }
                updateQuestionNames(); // Update names after reordering
            }
        }
    });

    // Make existing quiz items' handles draggable
    const existingHandles = document.querySelectorAll('.drag-handle');
    existingHandles.forEach(handle => {
        handle.setAttribute('draggable', 'true');
    });
    
    // Initial name update for existing items
    updateQuestionNames();
});

//Toggle the visibility of a dropdown menu
const toggleDropdown = (dropdown, menu, isOpen) => {
    dropdown.classList.toggle("open", isOpen);
    menu.style.height = isOpen ? `${menu.scrollHeight}px` : 0;
};

const closeAllDropdowns = () => {
    document.querySelectorAll(".dropdown-container.open").forEach(openDropdown => {
        toggleDropdown(openDropdown, openDropdown.querySelector(".dropdown-subject"), false);
    });
};
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


