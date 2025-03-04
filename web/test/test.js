document.addEventListener("DOMContentLoaded", function () {
    // Lấy phần tử "test" và menu
    let TestBtn = document.querySelector(".test-word");
    let testImg = document.querySelector(".test-img");
    let userMenu = document.querySelector(".user-menu");

    // Kiểm tra nếu phần tử tồn tại
    if (TestBtn && testImg && userMenu) {
        // Bắt sự kiện click vào "test"
        TestBtn.addEventListener("click", function (event) {
            event.stopPropagation(); // Ngăn chặn sự kiện lan ra ngoài
            userMenu.classList.toggle("show"); // Hiện hoặc ẩn menu
        });
        testImg.addEventListener("click", function (event) {
            event.stopPropagation(); // Ngăn chặn sự kiện lan ra ngoài
            userMenu.classList.toggle("show"); // Hiện hoặc ẩn menu
        });
    }

    // Ẩn menu khi click ra ngoài
    document.addEventListener("click", function (event) {
        if (!userMenu.contains(event.target) && event.target !== TestBtn) {
            userMenu.classList.remove("show");
        }
    });
    // HAMBURGER SIDEBAR TOGGLE
    const hamburgerBtn = document.getElementById("hamburgerBtn");
    const sidebar = document.getElementById("sidebar");
    const closeBtn = document.getElementById("closeBtn");

    if (hamburgerBtn && sidebar && closeBtn) {
        // When clicking the hamburger, toggle the 'show' class
        hamburgerBtn.addEventListener("click", function (e) {
            e.stopPropagation();
            sidebar.classList.toggle("show");
        });

        // When clicking the close icon in the sidebar
        closeBtn.addEventListener("click", function (e) {
            e.stopPropagation();
            sidebar.classList.remove("show");
        });

        // Optional: close the sidebar if user clicks outside of it
        document.addEventListener("click", function (event) {
            // if the click is NOT inside the sidebar AND not on the hamburger
            if (!sidebar.contains(event.target) && !hamburgerBtn.contains(event.target)) {
                sidebar.classList.remove("show");
            }
        });
    }
    function openPopup() {
        document.getElementById("popup").classList.add("show");
        document.getElementById("popupOverlay").classList.add("show");
        document.querySelector(".header").classList.add("darken"); // Làm tối topbar
    }

    function closePopup() {
        document.getElementById("popup").classList.remove("show");
        document.getElementById("popupOverlay").classList.remove("show");
        document.querySelector(".header").classList.remove("darken"); // Bỏ làm tối topbar
    }
//    function submitFinalTest() {
//        alert("Test submitted successfully!");
//        closePopup();
//    }
    window.openPopup = openPopup;
    window.closePopup = closePopup;
//    window.submitFinalTest = submitFinalTest;
    function closePopup1() {
        let popup1 = document.getElementById("popup1");
        let overlay1 = document.getElementById("popupOverlay1");

        if (popup1 && overlay1) {
            popup1.classList.add("hide");
            overlay1.classList.add("hide");
        } else {
            console.error("Popup1 or Overlay1 not found!");
        }
    }
    // Gán vào window để gọi từ HTML
    window.closePopup1 = closePopup1;
    document.getElementById("submit").addEventListener("click", function () {
        document.body.innerHTML = `
<body>
    <div class="container3">
        <div class="title">Don't give up now! Trust the process.</div>
        <div class="stats">
            <div class="time-steps">
                <p>Your time: 1 min.</p>
                <p class="next-steps">Next steps</p>
        </div>
            <div class="circle"><span></span></div>
            <p class="correct">Correct: 0</p>
            <p class="incorrect">Incorrect: 20</p>
        </div>
        <div class="next-steps">
            <div class="card">📚 Practise terms in Learn</div>
            <div class="card">📝 Take a new test</div>
        </div>
    </div>
</body>
            `;
        document.body.classList.add("*");
        document.body.classList.add("body");

    });
});