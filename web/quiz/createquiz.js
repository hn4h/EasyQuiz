document.addEventListener('DOMContentLoaded', function() {
    const addButton = document.querySelector('.add-btn');
    const bodyContainer = document.querySelector('.body-container');
    const questionCountInput = document.getElementById('questionCount');
    let questionCount = parseInt(questionCountInput.value, 10) || 1;

    function updateQuestionNames() {
        const quizItems = bodyContainer.querySelectorAll('.quiz-item');
        questionCount = quizItems.length;
        questionCountInput.value = questionCount;

        quizItems.forEach((item, index) => {
            const questionNumber = index + 1;
            item.querySelector('.question-text').name = `question${questionNumber}`;

            item.querySelectorAll('.form-radio').forEach(button => {
                button.name = `correct${questionNumber}`;
            });

            item.querySelectorAll('.option-input').forEach((answer, answerIndex) => {
                answer.name = `answer${questionNumber}.${answerIndex + 1}`;
            });
        });
    }

    function createNewQuizItem() {
        const newQuizItem = document.createElement('div');
        newQuizItem.className = 'quiz-item';
        const newQuestionNum = questionCount + 1;

        newQuizItem.innerHTML = `
            <div class="quiz-content">
                <i class="fas fa-grip-vertical drag-handle" draggable="true"></i>
                <div class="question">
                    <div class="question-header">
                        <input class="question-text input" type="text" name="question${newQuestionNum}" placeholder="Enter question here">
                        <div class="actions">
                            <i class="fas fa-trash"></i>
                        </div>
                    </div>
                    <p class="text-gray">Answer</p>
                    <div class="options">
                        ${[1, 2, 3, 4].map(i => `
                        <div class="option">
                            <input type="radio" name="correct${newQuestionNum}" value="option${i}" class="form-radio">
                            <input type="text" placeholder="Option ${i}" name="answer${newQuestionNum}.${i}" class="option-input">
                        </div>`).join('')}
                    </div>
                </div>
            </div>
        `;
        return newQuizItem;
    }

    addButton.addEventListener('click', function() {
        const newQuizItem = createNewQuizItem();
        const centerBtn = document.querySelector('.center-btn');
        bodyContainer.insertBefore(newQuizItem, centerBtn);
        questionCount++;
        questionCountInput.value = questionCount;
        updateQuestionNames();
    });

    bodyContainer.addEventListener('click', function(event) {
        if (event.target.classList.contains('fa-trash')) {
            const quizItem = event.target.closest('.quiz-item');
            if (quizItem) {
                quizItem.remove();
                updateQuestionNames();
            }
        }
    });

    let draggedItem = null;

    bodyContainer.addEventListener('dragstart', function(event) {
        if (event.target.classList.contains('drag-handle')) {
            draggedItem = event.target.closest('.quiz-item');
            draggedItem.classList.add('dragging');
            event.dataTransfer.effectAllowed = 'move';
        }
    });

    bodyContainer.addEventListener('dragend', function() {
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
                const allItems = [...bodyContainer.querySelectorAll('.quiz-item')];
                const draggedIndex = allItems.indexOf(draggedItem);
                const targetIndex = allItems.indexOf(targetItem);
                targetIndex > draggedIndex ? targetItem.after(draggedItem) : targetItem.before(draggedItem);
                updateQuestionNames();
            }
        }
    });

    document.querySelectorAll('.drag-handle').forEach(handle => handle.setAttribute('draggable', 'true'));
    updateQuestionNames();
});