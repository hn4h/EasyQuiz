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
    const shuffleBtn = document.getElementById("shuffleBtn");

    let currentIndex = 0;
    let isAnimating = false;
    let originalData = []; // Lưu dữ liệu gốc để khôi phục khi cần
    let isShuffled = false;

    function showFlashcard(newIndex, direction) {
        if (isAnimating || newIndex < 0 || newIndex >= flashcards.length)
            return;
        isAnimating = true;

        const currentCard = flashcards[currentIndex];
        const nextCard = flashcards[newIndex];

        console.log("Chuyển từ", currentIndex, "sang", newIndex);

        // Xóa hidden trước khi thêm active
        nextCard.classList.remove("hidden");
        nextCard.classList.add("active", direction === "next" ? "slide-in-right" : "slide-in-left");

        // Ẩn thẻ hiện tại bằng animation
        currentCard.classList.add(direction === "next" ? "slide-out-left" : "slide-out-right");

        setTimeout(() => {
            console.log("Ẩn thẻ cũ", currentIndex);
            currentCard.classList.remove("active", "slide-out-left", "slide-out-right");
            currentCard.classList.add("hidden"); // Ẩn hẳn thẻ hiện tại

            console.log("Hiện thẻ mới", newIndex);
            nextCard.classList.remove("slide-in-right", "slide-in-left");

            currentIndex = newIndex;
            pageIndicator.textContent = `${currentIndex + 1} / ${flashcards.length}`;
            isAnimating = false;
        }, 200);
    }

    function flipCurrentCard() {
        if (flashcards[currentIndex]) {
            flashcards[currentIndex].classList.toggle("flip");
        }
    }

    function showFlashcard(newIndex, direction) {
        if (isAnimating || newIndex < 0 || newIndex >= flashcards.length)
            return;
        isAnimating = true;

        const currentCard = flashcards[currentIndex];
        const nextCard = flashcards[newIndex];

        console.log("Chuyển từ", currentIndex, "sang", newIndex);

        // Bỏ class hidden trước khi thêm active
        nextCard.classList.remove("hidden");
        nextCard.classList.add("active", direction === "next" ? "slide-in-right" : "slide-in-left");

        // Thực hiện animation ẩn thẻ hiện tại
        currentCard.classList.add(direction === "next" ? "slide-out-left" : "slide-out-right");

        setTimeout(() => {
            console.log("Ẩn thẻ cũ", currentIndex);
            currentCard.classList.remove("active", "slide-out-left", "slide-out-right");
            currentCard.classList.add("hidden"); // Ẩn hoàn toàn thẻ cũ

            console.log("Hiện thẻ mới", newIndex);
            nextCard.classList.remove("slide-in-right", "slide-in-left");

            currentIndex = newIndex;
            pageIndicator.textContent = `${currentIndex + 1} / ${flashcards.length}`;
            isAnimating = false;
        }, 200);
    }

    function shuffleFlashcards() {
        // Nếu originalData chưa được lưu, lấy dữ liệu từ thẻ hiện tại
        if (originalData.length === 0) {
            flashcards.forEach(card => {
                originalData.push({
                    front: card.querySelector(".flashcard-front").innerHTML,
                    back: card.querySelector(".flashcard-back").innerHTML
                });
            });
        }

        if (isShuffled) {
            // Trả về thứ tự ban đầu
            flashcards.forEach((card, index) => {
                card.querySelector(".flashcard-front").innerHTML = originalData[index].front;
                card.querySelector(".flashcard-back").innerHTML = originalData[index].back;
            });
            shuffleBtn.classList.remove("active");
        } else {
            // Xáo trộn dữ liệu
            let shuffledData = [...originalData].sort(() => Math.random() - 0.5);

            flashcards.forEach((card, index) => {
                card.querySelector(".flashcard-front").innerHTML = shuffledData[index].front;
                card.querySelector(".flashcard-back").innerHTML = shuffledData[index].back;
            });
            shuffleBtn.classList.add("active");
        }

        isShuffled = !isShuffled;
    }


    nextBtn.addEventListener("click", () => showFlashcard(currentIndex + 1, "next"));
    prevBtn.addEventListener("click", () => showFlashcard(currentIndex - 1, "prev"));
    shuffleBtn.addEventListener("click", shuffleFlashcards);

    document.querySelectorAll(".flashcard").forEach(card => {
        card.addEventListener("click", flipCurrentCard);
    });

    // ✅ **Xử lý sự kiện bàn phím**
    document.addEventListener("keydown", function (event) {
        console.log("Phím nhấn:", event.key); // Kiểm tra xem phím có nhận không

        switch (event.key) {
            case "ArrowRight":
                console.log("Next flashcard");
                showFlashcard(currentIndex + 1, "next");
                break;
            case "ArrowLeft":
                console.log("Previous flashcard");
                showFlashcard(currentIndex - 1, "prev");
                break;
            case " ":
                event.preventDefault(); // Ngăn trang cuộn xuống
                console.log("Lật thẻ");
                document.querySelector(".flashcard.active").classList.toggle("flip");
                break;
            case "s":
                console.log("Xáo trộn thẻ");
                document.getElementById("shuffleBtn").click();
                break;
        }
    });

    // Ẩn tất cả thẻ trừ thẻ đầu tiên
    flashcards.forEach((card, index) => {
        if (index !== 0)
            card.classList.add("hidden");
    });
    flashcards[currentIndex].classList.add("active");
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
            }, 100);
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
        }, 100); // Chờ hiệu ứng fade-out hoàn tất
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
    let showBothSides = JSON.parse(localStorage.getItem("showBothSides") || "false");

    // 🛠 Hàm tự động điều chỉnh kích thước font chữ
    function adjustFontSize() {
        let termCards = document.querySelectorAll(".term-text");

        termCards.forEach((term) => {
            if (!term)
                return; // Kiểm tra tránh lỗi

            let textLength = term.innerText.trim().split(/\s+/).length;

            if (textLength <= 10) {
                term.style.fontSize = "36px";
            } else if (textLength <= 20) {
                term.style.fontSize = "30px";
            } else {
                term.style.fontSize = "24px";
            }
        });
    }

    // 🛠 Hàm cập nhật mặt trước của flashcard
    function updateFrontSide() {
        flashcards.forEach(flashcard => {
            let front = flashcard.querySelector(".flashcard-front");
            let back = flashcard.querySelector(".flashcard-back");

            if (!front || !back)
                return; // Kiểm tra tránh lỗi

            let term = back.dataset.term || (back.querySelector(".term-text")?.innerText || "");
            let definition = front.dataset.definition || (front.querySelector(".term-text")?.innerText || "");

            if (frontSide === "term") {
                front.innerHTML = `<p class="term-text">${term}</p>`;
                back.innerHTML = `<p class="term-text">${definition}</p>`;
            } else {
                front.innerHTML = `<p class="term-text">${definition}</p>`;
                back.innerHTML = `<p class="term-text">${term}</p>`;
            }
        });

        adjustFontSize();
    }

    // 🛠 Cập nhật hiển thị khi bật/tắt "Show both sides"
    function updateShowBothSides() {
        showBothSides = toggleSwitch.checked;
        localStorage.setItem("showBothSides", JSON.stringify(showBothSides));

        flashcards.forEach(flashcard => {
            flashcard.classList.toggle("show-both", showBothSides);
        });

        updateSelectState();
    }

    // 🛠 Cập nhật trạng thái select
    function updateSelectState() {
        if (showBothSides) {
            frontSelect.disabled = true;
            frontSelect.innerHTML = '<option selected>Both</option>';
        } else {
            frontSelect.disabled = false;
            frontSelect.innerHTML = `
                <option value="definition" ${frontSide === "definition" ? "selected" : ""}>Definition</option>
                <option value="term" ${frontSide === "term" ? "selected" : ""}>Term</option>
            `;
        }
    }

    // 🛠 Xử lý sự kiện thay đổi "Front"
    frontSelect.addEventListener("change", function () {
        frontSide = frontSelect.value.toLowerCase();
        localStorage.setItem("frontSide", frontSide);
        updateFrontSide();
    });

    // 🛠 Xử lý sự kiện bật/tắt "Show both sides"
    toggleSwitch.addEventListener("change", updateShowBothSides);

    // 🛠 Khôi phục cài đặt khi tải trang
    function loadSettings() {
        frontSelect.value = frontSide.charAt(0).toUpperCase() + frontSide.slice(1);
        toggleSwitch.checked = showBothSides;

        flashcards.forEach(flashcard => {
            let front = flashcard.querySelector(".flashcard-front p");
            let back = flashcard.querySelector(".flashcard-back p");

            if (front && back) {
                flashcard.querySelector(".flashcard-front").dataset.definition = front.innerText;
                flashcard.querySelector(".flashcard-back").dataset.term = back.innerText;
            }
        });

        updateFrontSide();
        updateShowBothSides();
        updateSelectState();
    }

    loadSettings();
});


//------------------------Pop up option test
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