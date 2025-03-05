<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Learn</title>
    <link rel="stylesheet" href="learnquiz.css">
    <link rel="shortcut icon" href="../images/logo/Easyquiz_logo.png">
    <link rel="stylesheet"
        href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
</head>

<body>
    <div class="header">
        <div class="learn-icon">
            <button>
                <img src="../images/icon/learn_icon.png" alt="">
                <span class="learn-title">Learn</span>
                <span class="material-symbols-rounded">keyboard_arrow_down</span>
            </button>
            <div class="learn-menu">
                <ul class="learn-menu-nav">
                    <li class="nav-item">
                        <img src="flashcard_icon.png" alt="">
                        <a href="" class="nav-link">Flashcards</a>
                    </li>
                    <li class="nav-item">
                        <img src="test_icon.png" alt="">
                        <a href="" class="nav-link">Test</a>
                    </li>
                    <hr>
                    <li class="nav-item">
                        <a href="" class="nav-link">Home</a>
                    </li>
                    <li class="nav-item">
                        <a href="" class="nav-link">Search</a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="title">
            <span>Title</span>
        </div>
        <div class="close">
            <button><span class="material-symbols-rounded">settings</span></button>
            <button><span class="material-symbols-rounded">close</span></button>
        </div>
    </div>
    <div class="body">
        <div class="progress">
            <div class="progress-start">
                <p>0</p>
            </div>
            <div class="progress-line">
                <div class="line-1">

                </div>
                <div class="line-2">

                </div>
                <div class="line-3">

                </div>
            </div>
            <div class="progress-end">
                <p>6</p>
            </div>
        </div>
        <div class="learn-container">
            <div class="definition">
                <h4>Definition</h4>
                <div class="definition-content">
                    <p>1 + 1</p>
                </div>
            </div>
            <div class="term">
                <h4>Select the correct term</h4>
                <div class="term-content">
                    <div class="answer">
                        <span class="answer-number">1</span>
                        <p>2</p>
                    </div>
                    <div class="answer">
                        <span class="answer-number">2</span>
                        <p>2</p>
                    </div>
                    <div class="answer">
                        <span class="answer-number">3</span>
                        <p>2</p>
                    </div>
                    <div class="answer">
                        <span class="answer-number">4</span>
                        <p>2</p>
                    </div>
                </div>
            </div>
            <div class="suggest-btn">
                <button>
                    <span>Don't know?</span>
                </button>
            </div>
        </div>
        <div class="continue-container">
            <div class="continue-content">
                <p>Select the correc answer or press any key to continue</p>
                <button>
                    <span>Continue</span>
                </button>
            </div>
        </div>
    </div>
    <script src="learnquiz.js"></script>
</body>

</html>