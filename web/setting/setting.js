
//------------------------------------------->
document.getElementById("menuToggle").addEventListener("click", function () {
    console.log("Menu button clicked!");
    const sidebar = document.getElementById("sidebar");
    sidebar.classList.toggle("minimized"); // Thêm/xóa lớp minimized
});