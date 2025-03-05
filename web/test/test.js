document.addEventListener("DOMContentLoaded", function () {
    // Lấy phần tử "test" và menu
    let TestBtn = document.querySelector(".test-word");
    let testImg = document.querySelector(".test-img");
    let userMenu = document.querySelector(".user-menu");

    if (TestBtn && testImg && userMenu) {
        TestBtn.addEventListener("click", toggleUserMenu);
        testImg.addEventListener("click", toggleUserMenu);
    }

    function toggleUserMenu(event) {
        event.stopPropagation(); 
        userMenu.classList.toggle("show");
    }

    // Ẩn menu khi click ra ngoài
    document.addEventListener("click", function (event) {
        if (userMenu && !userMenu.contains(event.target) && event.target !== TestBtn && event.target !== testImg) {
            userMenu.classList.remove("show");
        }
    });

    // HAMBURGER SIDEBAR TOGGLE
    const hamburgerBtn = document.getElementById("hamburgerBtn");
    const sidebar = document.getElementById("sidebar");
    const closeBtn = document.getElementById("closeBtn");

    if (hamburgerBtn && sidebar && closeBtn) {
        hamburgerBtn.addEventListener("click", function (e) {
            e.stopPropagation();
            toggleSidebar();
        });

        closeBtn.addEventListener("click", function (e) {
            e.stopPropagation();
            closeSidebar();
        });

        // Đóng sidebar khi click ra ngoài
        document.addEventListener("click", function (event) {
            if (!sidebar.contains(event.target) && !hamburgerBtn.contains(event.target)) {
                closeSidebar();
            }
        });
    }

    function toggleSidebar() {
        sidebar.classList.toggle("show");
    }

    function closeSidebar() {
        sidebar.classList.remove("show");
    }

    // Popup
    function openPopup() {
        document.getElementById("popup").classList.add("show");
        document.getElementById("popupOverlay").classList.add("show");
        document.querySelector(".header").classList.add("darken");
    }

    function closePopup() {
        document.getElementById("popup").classList.remove("show");
        document.getElementById("popupOverlay").classList.remove("show");
        document.querySelector(".header").classList.remove("darken");
    }

    window.openPopup = openPopup;
    window.closePopup = closePopup;

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

    window.closePopup1 = closePopup1;

    // Nút submit cập nhật giao diện
    document.getElementById("submit").addEventListener("click", function () {
        document.body.innerHTML = `
            <div class="header">
                <div class="test">
                    <div class="avatar-user" id="avatarUser">
                        <img class="test-img" src="../images/icon/test_icon.png">
                        <span class="test-word">Test</span>
                        <div class="user-menu" id="userMenu">
                            <hr/>
                            <a href="#" class="user-menu-item">Learn</a>
                            <a href="#" class="user-menu-item">Flashcard</a>
                            <hr/>
                            <a href="#" class="user-menu-item">Home</a>
                            <a href="#" class="user-menu-item">Search</a>
                        </div>
                    </div>
                </div>
                <p class="number">1 / 3</p>
                <p class="exit">X</p>
            </div>
            <div class="flashcard-complete" id="flashcardComplete">
                <h2>Don't give up now! Trust the process.</h2>
                <div class="progress-actions">
                    <div class="progress">
                        <h3>Your time: 1 min.</h3>
                        <div class="progress-info">
                            <span class="circle"></span>
                            <div class="progress-info-complete">
                                <p class="correct">Correct:<span id="correctCount">0</span></p>
                                <p class="incorrect">Incorrect:<span id="incorrectCount">0</span></p>
                            </div>
                        </div>
                    </div>
                    <div class="actions">
                        <h3>Next steps</h3>
                        <div class="actions-info">
                            <button id="nextSteps"><a href="#">Practice terms in Learn</a></button>
                            <button id="restartFlashcards"><a href="#">Take a new test</a></button>
                        </div>
                    </div>
                </div>
            </div>
            <h3 class="Your-answers">Your answers</h3>
            <div class="container">
                        <div class="container1">
                    <h3>Which way should be followed if you want to reduce integration and interface errors?</h3>
                    <p>Select the correct definition</p>
                    <div class="options1">
                        <button>Allowing teams to define their own data standards</button>
                        <button>Using outdated data definitions for legacy systems</button>
                        <button>Implementing multiple data formats for flexibility</button>
                        <button class="correct1">Using consistent data definitions across the enterprise</button>
                    </div>
                </div>
                <div class="container1">
                    <h3>Which way should be followed if you want to reduce integration and interface errors?</h3>
                    <p>Select the correct definition</p>
                    <div class="options1">
                        <button>Allowing teams to define their own data standards</button>
                        <button>Using outdated data definitions for legacy systems</button>
                        <button>Implementing multiple data formats for flexibility</button>
                        <button class="correct1">Using consistent data definitions across the enterprise</button>
                    </div>
                </div>
                <div class="container1">
                    <h3>Which way should be followed if you want to reduce integration and interface errors?</h3>
                    <p>Select the correct definition</p>
                    <div class="options1">
                        <button>Allowing teams to define their own data standards</button>
                        <button>Using outdated data definitions for legacy systems</button>
                        <button>Implementing multiple data formats for flexibility</button>
                        <button class="correct1">Using consistent data definitions across the enterprise</button>
                    </div>
                </div>
                <div class="hamburger" id="hamburgerBtn">
                    <span class="material-symbols-rounded">menu</span>
                </div>
                <nav class="sidebar" id="sidebar">
                    <div class="sidebar-header">
                        <h3>List of quiz</h3>
                        <span class="material-symbols-rounded close-btn" id="closeBtn">close</span>
                    </div>
                    <ul>
                        <li><a href="#">1</a></li>
                        <li><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li><a href="#">4</a></li>
                    </ul>
                </nav>
            </div>
        `;

        document.body.classList.add("*");
        document.body.classList.add("body");

        // Gọi lại hàm để gắn sự kiện cho phần tử mới
        initEventListeners();
    });

    function initEventListeners() {
         // ========== USER MENU ==========
        const testBtn = document.querySelector(".test-word");
        const testImg = document.querySelector(".test-img");
        const userMenu = document.querySelector(".user-menu");

        function toggleUserMenu(event) {
            event.stopPropagation();
            userMenu.classList.toggle("show");
        }

        function closeUserMenu() {
            userMenu.classList.remove("show");
        }

        if (testBtn && testImg && userMenu) {
            testBtn.addEventListener("click", toggleUserMenu);
            testImg.addEventListener("click", toggleUserMenu);

            document.addEventListener("click", function (event) {
                if (!userMenu.contains(event.target) && event.target !== testBtn && event.target !== testImg) {
                    closeUserMenu();
                }
            });
        }
        
        // ========== SIDEBAR ==========
        const newHamburgerBtn = document.getElementById("hamburgerBtn");
        const newSidebar = document.getElementById("sidebar");
        const newCloseBtn = document.getElementById("closeBtn");

        if (newHamburgerBtn && newSidebar && newCloseBtn) {
            newHamburgerBtn.addEventListener("click", function (e) {
                e.stopPropagation();
                newSidebar.classList.toggle("show");
            });

            newCloseBtn.addEventListener("click", function (e) {
                e.stopPropagation();
                newSidebar.classList.remove("show");
            });

            document.addEventListener("click", function (event) {
                if (!newSidebar.contains(event.target) && !newHamburgerBtn.contains(event.target)) {
                    newSidebar.classList.remove("show");
                }
            });
        }
    }

    initEventListeners(); // Gọi lần đầu tiên
});