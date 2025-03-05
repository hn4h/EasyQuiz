document.addEventListener("DOMContentLoaded", function () {
    const learnMenuBtn = document.querySelector(".learn-icon button"); // Nút Learn
    const learnMenu = document.querySelector(".learn-menu"); // Menu Learn

    learnMenuBtn.addEventListener("click", function (event) {
        learnMenu.classList.toggle("show"); // Hiện/ẩn menu khi click

        // Ngăn sự kiện click lan ra ngoài
        event.stopPropagation();
    });

    // Ẩn menu khi click ra ngoài
    document.addEventListener("click", function (event) {
        if (!learnMenuBtn.contains(event.target) && !learnMenu.contains(event.target)) {
            learnMenu.classList.remove("show");
        }
    });
});
