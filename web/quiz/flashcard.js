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
