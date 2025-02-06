//------------------------------------------->
document.getElementById("menuToggle").addEventListener("click", function () {
    console.log("Menu button clicked!");
    const sidebar = document.getElementById("sidebar");
    sidebar.classList.toggle("minimized"); // Thêm/xóa lớp minimized
});
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

