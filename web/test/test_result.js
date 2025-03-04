/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
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
});
