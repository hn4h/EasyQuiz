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

//---------------------------------More option header
document.addEventListener("DOMContentLoaded", function () {
    const moreBtn = document.querySelector(".header-btn .btn:nth-child(2)"); // Nút More
    const moreOption = document.querySelector(".more-option");

    moreBtn.addEventListener("click", function (event) {
        moreOption.classList.toggle("show"); // Bật tắt menu

        // Ngăn sự kiện click lan ra ngoài
        event.stopPropagation();
    });

    // Ẩn menu khi click ra ngoài
    document.addEventListener("click", function (event) {
        if (!moreBtn.contains(event.target) && !moreOption.contains(event.target)) {
            moreOption.classList.remove("show");
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

//------------------------------Sort terms 
document.addEventListener("DOMContentLoaded", function () {
    const selectSort = document.querySelector(".term-header select");
    const termContainer = document.querySelector(".term-content");

    selectSort.addEventListener("change", function () {
        let termCards = Array.from(document.querySelectorAll(".term-card"));
        let sortType = selectSort.value; // Lấy giá trị được chọn

        if (sortType === "alphabetical") {
            termCards.sort((a, b) => {
                let defA = a.querySelector(".definition p").textContent.toLowerCase();
                let defB = b.querySelector(".definition p").textContent.toLowerCase();
                return defA.localeCompare(defB);
            });
        } else {
            termCards.sort((a, b) => a.dataset.index - b.dataset.index);
        }

        // Xóa nội dung cũ và thêm lại theo thứ tự đã sắp xếp
        termContainer.innerHTML = "";
        termCards.forEach(card => termContainer.appendChild(card));
    });

    // Gán chỉ số ban đầu để khi chọn "Original" có thể khôi phục đúng thứ tự
    document.querySelectorAll(".term-card").forEach((card, index) => {
        card.dataset.index = index;
    });
});


//-----------------------Study mode button
document.addEventListener("DOMContentLoaded", function () {
    const studyMode = document.querySelector(".study-mode");
    const moreMode = document.querySelector(".more-mode");

    studyMode.addEventListener("click", function (event) {
        moreMode.style.display = (moreMode.style.display === "block") ? "none" : "block";
        event.stopPropagation(); // Ngăn chặn sự kiện click lan ra ngoài
    });

    // Ẩn menu khi click ra ngoài
    document.addEventListener("click", function (event) {
        if (!studyMode.contains(event.target) && !moreMode.contains(event.target)) {
            moreMode.style.display = "none";
        }
    });
});

document.addEventListener("DOMContentLoaded", function () {
    const toggleBtn = document.querySelector(".mode-content"); // Nút Hide/Show definitions hoặc terms
    const swapBtn = document.querySelector(".swapBtn"); // Nút đổi giữa term & definition
    const verticalLine = document.querySelector(".vertical-line-mode"); // Đường kẻ
    const definitions = document.querySelectorAll(".definition");
    const terms = document.querySelectorAll(".term");

    let isDefinitionMode = true; // Ban đầu hiển thị definitions
    let isFirstClick = true; // Chưa bấm lần nào
    let isHidden = false; // Trạng thái ẩn hiện

    // Ẩn hoặc hiện definitions hoặc terms khi click vào "Hide definitions"
    toggleBtn.addEventListener("click", function () {
        if (isDefinitionMode) {
            // Chế độ definitions -> chỉ ẩn hoặc hiện definitions
            definitions.forEach(def => def.classList.toggle("hidden"));
            isHidden = !isHidden;
            toggleBtn.textContent = isHidden ? "Show definitions" : "Hide definitions";
        } else {
            // Chế độ terms -> chỉ ẩn hoặc hiện terms
            terms.forEach(term => term.classList.toggle("hidden"));
            isHidden = !isHidden;
            toggleBtn.textContent = isHidden ? "Show terms" : "Hide terms";
        }

        // Chỉ hiển thị swapBtn & vertical-line-mode khi nút được bấm lần đầu tiên
        if (isFirstClick) {
            swapBtn.style.display = "flex";
            verticalLine.style.display = "block";
            isFirstClick = false;
        }
    });

    // Chuyển đổi giữa hiển thị term <-> definition
    swapBtn.addEventListener("click", function () {
        isDefinitionMode = !isDefinitionMode; // Đảo trạng thái hiển thị

        // Đổi chữ của toggleBtn theo chế độ hiện tại
        if (toggleBtn.textContent.includes("definitions")) {
            toggleBtn.textContent = toggleBtn.textContent.replace("definitions", "terms");
        } else {
            toggleBtn.textContent = toggleBtn.textContent.replace("terms", "definitions");
        }
    });
});


//------------------------Arrow button move on header
document.addEventListener("DOMContentLoaded", function () {
    const scrollBtn = document.getElementById("scrollToTop");

    // Ẩn/Hiện nút khi cuộn trang
    window.addEventListener("scroll", function () {
        if (window.scrollY > 300) {
            scrollBtn.classList.add("show");
        } else {
            scrollBtn.classList.remove("show");
        }
    });

    // Xử lý sự kiện khi bấm vào nút
    scrollBtn.addEventListener("click", function () {
        window.scrollTo({top: 0, behavior: "smooth"});
    });
});




