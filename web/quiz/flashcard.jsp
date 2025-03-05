<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Flashcards</title>
    <link rel="shortcut icon" href="../images/logo/Easyquiz_logo.png">
    <link rel="stylesheet" href="flashcard.css">
    <link rel="stylesheet"
        href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
    <div class="header">
        <div class="flashcard-icon">
            <button>
                <img src="../images/icon/flashcard_icon.png" alt="">
                <span class="learn-title">Flashcards</span>
                <span class="material-symbols-rounded">keyboard_arrow_down</span>
            </button>
            <div class="flashcard-menu">
                <ul class="flashcard-menu-nav">
                    <li class="nav-item">
                        <img src="../images/icon/learn_icon.png" alt="">
                        <a href="" class="nav-link">Learn</a>
                    </li>
                    <li class="nav-item">
                        <img src="../images/icon/test_icon.png" alt="">
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
            <span class="page-number" id="pageIndicator">1 / 5</span>
            <span>Title</span>
        </div>
        <div class="close">
            <button><span class="material-symbols-rounded">settings</span></button>
            <button><span class="material-symbols-rounded">close</span></button>
        </div>
    </div>
    <div class="body">
        <div class="flashcard-container">
            <div class="flashcard-slide" id="flashcardSlide">
                <div class="flashcard">
                    <div class="flashcard-inner">
                        <div class="flashcard-front">
                            <p>1 + 1</p>
                        </div>
                        <div class="flashcard-back">
                            <p>2</p>
                        </div>
                    </div>
                </div>
                <div class="flashcard">
                    <div class="flashcard-inner">
                        <div class="flashcard-front">
                            <p>2 + 1</p>
                        </div>
                        <div class="flashcard-back">
                            <p>3</p>
                        </div>
                    </div>
                </div>
                <div class="flashcard">
                    <div class="flashcard-inner">
                        <div class="flashcard-front">
                            <p>3 + 1</p>
                        </div>
                        <div class="flashcard-back">
                            <p>4</p>
                        </div>
                    </div>
                </div>
                <div class="flashcard">
                    <div class="flashcard-inner">
                        <div class="flashcard-front">
                            <p>4 + 1</p>
                        </div>
                        <div class="flashcard-back">
                            <p>5</p>
                        </div>
                    </div>
                </div>
                <div class="flashcard">
                    <div class="flashcard-inner">
                        <div class="flashcard-front">
                            <p>5 + 1</p>
                        </div>
                        <div class="flashcard-back">
                            <p>6</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="previous-next">
            <div class="nav-controls">
                <button class="btn" id="prevBtn">
                    <span class="material-symbols-rounded">arrow_back</span>
                </button>
                <button class="btn" id="nextBtn">
                    <span class="material-symbols-rounded">arrow_forward</span>
                </button>
            </div>
            <div class="extra-controls">
                <button class="extra-btn" id="swapFaceBtn">
                    <span class="material-symbols-rounded">swap_horiz</span>
                    <span class="tooltip">Swap face</span>
                </button>
                <button class="extra-btn" id="shuffleBtn">
                    <span class="material-symbols-rounded">shuffle</span>
                    <span class="tooltip">Shuffle</span>
                </button>
            </div>
        </div>
        <script src="flashcard.js"></script>
    </div>
</body>
</html>