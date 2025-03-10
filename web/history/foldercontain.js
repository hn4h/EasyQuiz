/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */


const popup = document.getElementById("popup-folder");
const openBtn = document.querySelector(".add-btn");
const closeFolderBtn = document.getElementById("closePopup");

// Hiện popup
openBtn.addEventListener("click", () => {
    popup.style.display = "flex";
});

// Đóng popup khi nhấn nút X
closeFolderBtn.addEventListener("click", () => {
    popup.style.display = "none";
});

// Đóng popup khi nhấn ra ngoài
window.addEventListener("click", (e) => {
    if (e.target === popup) {
        popup.style.display = "none";
    }
});

document.addEventListener("DOMContentLoaded", function () {
    const items = document.querySelectorAll(".item");

    items.forEach(item => {
        item.addEventListener("click", function () {
            this.classList.toggle("selected");
        });
    });
});