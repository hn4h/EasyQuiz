document.addEventListener("DOMContentLoaded", function () {
    const flashcardMenuBtn = document.querySelector(".flashcard-icon button"); // Nút Learn
    const flashcardMenu = document.querySelector(".flashcard-menu"); // Menu Learn

    flashcardMenuBtn.addEventListener("click", function (event) {
        flashcardMenu.classList.toggle("show"); // Hiện/ẩn menu khi click

        // Ngăn sự kiện click lan ra ngoài
        event.stopPropagation();
    });

    // Ẩn menu khi click ra ngoài
    document.addEventListener("click", function (event) {
        if (!flashcardMenuBtn.contains(event.target) && !flashcardMenu.contains(event.target)) {
            flashcardMenu.classList.remove("show");
        }
    });
});

//----------------------------Flip card 
document.addEventListener("DOMContentLoaded", function () {
    const flashcards = document.querySelectorAll(".flashcard");
    const pageIndicator = document.getElementById("pageIndicator");
    const prevBtn = document.getElementById("prevBtn");
    const nextBtn = document.getElementById("nextBtn");

    let currentIndex = 0;
    let isAnimating = false;

    function showFlashcard(newIndex, direction) {
        if (isAnimating || newIndex < 0 || newIndex >= flashcards.length)
            return;
        isAnimating = true;

        const currentCard = flashcards[currentIndex];
        const nextCard = flashcards[newIndex];

        // **Reset về mặt trước**
        currentCard.classList.remove("flip");
        nextCard.classList.remove("flip");

        currentCard.classList.add(direction === "next" ? "slide-out-left" : "slide-out-right");

        setTimeout(() => {
            currentCard.classList.add("hidden");
            currentCard.classList.remove("active", "slide-out-left", "slide-out-right");

            nextCard.classList.remove("hidden");
            nextCard.classList.add(direction === "next" ? "slide-in-right" : "slide-in-left");

            setTimeout(() => {
                nextCard.classList.add("active");
                nextCard.classList.remove("slide-in-right", "slide-in-left");

                // **Đảm bảo thẻ mới luôn ở mặt trước**
                nextCard.classList.remove("flip");

                currentIndex = newIndex;
                pageIndicator.textContent = `${currentIndex + 1} / ${flashcards.length}`;
                isAnimating = false;
            }, 200);
        }, 200);
    }

    nextBtn.addEventListener("click", function () {
        showFlashcard(currentIndex + 1, "next");
    });

    prevBtn.addEventListener("click", function () {
        showFlashcard(currentIndex - 1, "prev");
    });

    // Ẩn tất cả thẻ trừ thẻ đầu tiên
    flashcards.forEach((card, index) => {
        if (index !== 0) {
            card.classList.add("hidden");
        }
    });

    flashcards[currentIndex].classList.add("active");
});
//-------------------Flip card
document.addEventListener("DOMContentLoaded", function () {
    document.querySelectorAll(".flashcard").forEach(card => {
        card.addEventListener("click", function () {
            this.classList.toggle("flip");
        });
    });
});
//---------------Shuffle card 
document.addEventListener("DOMContentLoaded", function () {
    const shuffleBtn = document.getElementById("shuffleBtn");
    const flashcards = document.querySelectorAll(".flashcard-inner");

    let originalData = []; // Lưu trạng thái ban đầu
    let isShuffled = false; // Kiểm tra Shuffle đang bật hay tắt

    flashcards.forEach(card => {
        originalData.push({
            front: card.querySelector(".flashcard-front").innerHTML,
            back: card.querySelector(".flashcard-back").innerHTML
        });
    });

    function shuffleFlashcards() {
        if (isShuffled) {
            flashcards.forEach((card, index) => {
                card.querySelector(".flashcard-front").innerHTML = originalData[index].front;
                card.querySelector(".flashcard-back").innerHTML = originalData[index].back;
            });
            shuffleBtn.classList.remove("active"); // Đổi màu nút Shuffle
        } else {
            let shuffledData = [...originalData]; // Copy dữ liệu gốc
            shuffledData.sort(() => Math.random() - 0.5); // Xáo trộn

            flashcards.forEach((card, index) => {
                card.querySelector(".flashcard-front").innerHTML = shuffledData[index].front;
                card.querySelector(".flashcard-back").innerHTML = shuffledData[index].back;
            });
            shuffleBtn.classList.add("active"); // Đổi màu nút Shuffle
        }

        isShuffled = !isShuffled; // Đảo trạng thái Shuffle
    }

    shuffleBtn.addEventListener("click", shuffleFlashcards);
});

//---------------Swap face card 
document.addEventListener("DOMContentLoaded", function () {
    const swapFaceBtn = document.getElementById("swapFaceBtn");
    let isSwapped = false; // Trạng thái của nút

    swapFaceBtn.addEventListener("click", function () {
        isSwapped = !isSwapped; // Đảo trạng thái

        // Lấy tất cả flashcard và thêm/xóa class 'flip'
        document.querySelectorAll(".flashcard").forEach(card => {
            if (isSwapped) {
                card.classList.add("flip");
            } else {
                card.classList.remove("flip");
            }
        });

        // Toggle trạng thái active
        swapFaceBtn.classList.toggle("active", isSwapped);
    });
});

//----------------------------------Complete flash card
document.addEventListener("DOMContentLoaded", function () {
    const flashcards = document.querySelectorAll(".flashcard");
    const flashcardSlide = document.getElementById("flashcardSlide");
    const flashcardComplete = document.getElementById("flashcardComplete");
    const nextButton = document.getElementById("nextBtn");
    const prevButton = document.getElementById("prevBtn");
    const pageIndicator = document.getElementById("pageIndicator");
    const extraControls = document.querySelector(".extra-controls"); // Nhóm nút chức năng
    const navControls = document.querySelector(".nav-controls"); // Nhóm nút điều hướng
    const backToLastBtn = document.getElementById("backToLast");

    let currentCard = 0;
    let totalCards = flashcards.length;

    function showCard(index) {
        if (index < totalCards) {
            flashcards.forEach(card => card.style.display = "none");
            flashcards[index].style.display = "block";
            pageIndicator.textContent = `${index + 1} / ${totalCards}`;

            // Hiển thị lại giao diện khi chưa hoàn thành
            flashcardSlide.style.display = "block";
            navControls.style.display = "flex";
            extraControls.style.display = "flex";
            pageIndicator.style.display = "inline-block";
            flashcardComplete.style.display = "none";
        } else {

            // Thêm hiệu ứng fade-out
            flashcardSlide.classList.add("fade-out");

            setTimeout(() => {
                // Ẩn giao diện cũ
                flashcardSlide.style.display = "none";
                navControls.style.display = "none";
                extraControls.style.display = "none";
                pageIndicator.style.display = "none";

                // Hiển thị trang hoàn thành với hiệu ứng fade-in
                flashcardComplete.style.display = "block";
                flashcardComplete.classList.add("fade-in");

                // Hiển thị số thẻ đã hoàn thành
                document.getElementById("completedCount").innerText = totalCards;
            }, 500); // Đợi hiệu ứng fade-out hoàn thành (0.5s)
        }
    }

    nextButton.addEventListener("click", function () {
        currentCard++;
        showCard(currentCard);
    });

    prevButton.addEventListener("click", function () {
        if (currentCard > 0) {
            currentCard--;
            showCard(currentCard);
        }
    });

    // Nút "Back to the last question" -> Quay lại thẻ cuối cùng
    backToLastBtn.addEventListener("click", function () {
        flashcardComplete.classList.add("fade-out");

        setTimeout(() => {
            // Ẩn trang hoàn thành
            flashcardComplete.style.display = "none";

            // Hiển thị lại flashcard cuối cùng và giao diện điều khiển
            flashcardSlide.style.display = "block";
            navControls.style.display = "flex";
            extraControls.style.display = "flex";
            pageIndicator.style.display = "inline-block";

            // Hiển thị flashcard cuối cùng
            currentCard = totalCards - 1;
            showCard(currentCard);

            // Hiệu ứng xuất hiện mượt mà
            flashcardSlide.classList.remove("fade-out");
            flashcardSlide.classList.add("fade-in");
        }, 500); // Chờ hiệu ứng fade-out hoàn tất
    });


    // Hiển thị thẻ đầu tiên khi tải trang
    showCard(currentCard);
});
//------------------------Pop up option
const optionItem = document.querySelector('.optionBtn');
const optionPopup = document.getElementById('optionPopup');
const closeBtn = document.querySelector('.close-btn');

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

//------------------------Keyboard shortcut view
document.getElementById("viewKeyboardButton").addEventListener("click", function () {
    let shortcutMenu = document.getElementById("shortcutMenu");
    let buttonText = document.getElementById("buttonText");
    let arrowIcon = document.getElementById("arrowIcon");

    if (shortcutMenu.classList.contains("show")) {
        shortcutMenu.classList.remove("show");
        setTimeout(() => (shortcutMenu.style.display = "none"), 300);
        buttonText.textContent = "View";
        arrowIcon.classList.remove("rotate");
    } else {
        shortcutMenu.style.display = "flex";
        setTimeout(() => shortcutMenu.classList.add("show"), 10);
        buttonText.textContent = "Hide";
        arrowIcon.classList.add("rotate");
    }
});

//-----------------------Front và show 2 side mode
document.addEventListener("DOMContentLoaded", function () {
    const frontSelect = document.querySelector(".option-item select");
    const toggleSwitch = document.getElementById("toggleSwitch");
    const flashcards = document.querySelectorAll(".flashcard");

    let frontSide = localStorage.getItem("frontSide") || "definition";
    let showBothSides = localStorage.getItem("showBothSides") === "true";

    // 🛠 Hàm tự động điều chỉnh kích thước font chữ
    function adjustFontSize() {
        let termCards = document.querySelectorAll(".term-text");

        termCards.forEach((term) => {
            let textLength = term.innerHTML.split(/\s+/).length; // Giữ nguyên HTML (không đổi thành plain text)

            if (textLength <= 10) {
                term.style.fontSize = "36px"; // Nội dung ngắn -> Font to
            } else if (textLength <= 20) {
                term.style.fontSize = "30px"; // Nội dung trung bình -> Font vừa
            } else {
                term.style.fontSize = "24px"; // Nội dung dài -> Font nhỏ hơn
            }
        });
    }

    // 🛠 Hàm cập nhật mặt trước của thẻ
    function updateFrontSide() {
        flashcards.forEach(flashcard => {
            let front = flashcard.querySelector(".flashcard-front");
            let back = flashcard.querySelector(".flashcard-back");

            let term = back.dataset.term || front.querySelector(".term-text").innerHTML;
            let definition = front.dataset.definition || back.querySelector(".term-text").innerHTML;

            if (frontSide === "term") {
                front.innerHTML = `<p class="term-text">${term}</p>`;
                back.innerHTML = `<p class="term-text">${definition}</p>`;
            } else {
                front.innerHTML = `<p class="term-text">${definition}</p>`;
                back.innerHTML = `<p class="term-text">${term}</p>`;
            }
        });

        adjustFontSize(); // Gọi lại để giữ định dạng font chữ
    }

    // 🛠 Cập nhật hiển thị khi bật/tắt "Show both sides"
    function updateShowBothSides() {
        flashcards.forEach(flashcard => {
            flashcard.classList.toggle("show-both", showBothSides);
        });

        // Cập nhật trạng thái của select
        updateSelectState();
    }

    // 🛠 Cập nhật trạng thái select
    function updateSelectState() {
        if (showBothSides) {
            frontSelect.disabled = true; // Vô hiệu hóa select
            frontSelect.innerHTML = '<option selected>Both</option>'; // Thay đổi nội dung
        } else {
            frontSelect.disabled = false; // Bật lại select
            frontSelect.innerHTML = `
                <option value="definition" ${frontSide === "definition" ? "selected" : ""}>Definition</option>
                <option value="term" ${frontSide === "term" ? "selected" : ""}>Term</option>
            `; // Khôi phục tùy chọn
        }
    }

    // 🛠 Xử lý sự kiện thay đổi "Front"
    frontSelect.addEventListener("change", function () {
        frontSide = frontSelect.value.toLowerCase();
        localStorage.setItem("frontSide", frontSide);
        updateFrontSide();
    });

    // 🛠 Xử lý sự kiện bật/tắt "Show both sides"
    toggleSwitch.addEventListener("change", function () {
        showBothSides = toggleSwitch.checked;
        localStorage.setItem("showBothSides", showBothSides);
        updateShowBothSides();
    });

    // 🛠 Khôi phục cài đặt khi tải trang
    function loadSettings() {
        frontSelect.value = frontSide.charAt(0).toUpperCase() + frontSide.slice(1);
        toggleSwitch.checked = showBothSides;

        // Gán giá trị gốc cho mỗi flashcard
        flashcards.forEach(flashcard => {
            let front = flashcard.querySelector(".flashcard-front p");
            let back = flashcard.querySelector(".flashcard-back p");

            flashcard.querySelector(".flashcard-front").dataset.definition = front.innerHTML;
            flashcard.querySelector(".flashcard-back").dataset.term = back.innerHTML;
        });

        updateFrontSide();
        updateShowBothSides();
        updateSelectState(); // Cập nhật trạng thái select ngay khi tải trang
    }

    loadSettings();
});

//------------------------Pop up option
const testItem = document.querySelector('.test-btn');
const testPopup = document.getElementById('testPopup');
const closeTestBtn = document.querySelector('.closetest-btn');
const cancelBtn = document.querySelector('.cancel-btn');

// Hiển thị popup
testItem.addEventListener('click', (e) => {
    testPopup.style.display = "block";
    testPopup.classList.remove("hide");
    e.stopPropagation();
});

// Ẩn popup với animation fadeOut
closeTestBtn.addEventListener('click', () => {
    testPopup.classList.add("hide");
    setTimeout(() => {
       testPopup.style.display = "none";
    }, 200); // Thời gian khớp với animation fadeOut
});

// Ẩn popup với animation fadeOut
cancelBtn.addEventListener('click', () => {
    testPopup.classList.add("hide");
    setTimeout(() => {
        testPopup.style.display = "none";
    }, 200); // Thời gian khớp với animation fadeOut
});