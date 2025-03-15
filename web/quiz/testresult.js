document.addEventListener("DOMContentLoaded", function () {
    const testMenuBtn = document.querySelector(".test-icon button"); // Nút Learn
    const testMenu = document.querySelector(".test-menu"); // Menu Learn

    testMenuBtn.addEventListener("click", function (event) {
        testMenu.classList.toggle("show"); // Hiện/ẩn menu khi click

        // Ngăn sự kiện click lan ra ngoài
        event.stopPropagation();
    });

    // Ẩn menu khi click ra ngoài
    document.addEventListener("click", function (event) {
        if (!testMenuBtn.contains(event.target) && !testMenu.contains(event.target)) {
            testMenu.classList.remove("show");
        }
    });
});
//---------------------Menu list question
document.addEventListener("DOMContentLoaded", function () {
    const menuButton = document.getElementById("menuButton");
    const hideButton = document.getElementById("hideButton");
    const questionList = document.getElementById("questionList");

    menuButton.addEventListener("click", function () {
        menuButton.style.display = "none"; 
        questionList.classList.add("show");
    });

    hideButton.addEventListener("click", function () {
        menuButton.style.display = "inline-block"; 
        questionList.classList.remove("show");
    });
});

//---------------------------Circle percentage
function updateCircle(correct, incorrect) {
    let total = correct + incorrect;
    let percentage = total === 0 ? 0 : Math.round((correct / total) * 100);
    let circle = document.querySelector('.circle');
    let percentageText = document.querySelector('.percentage-text');

    let dashArrayValue = `${percentage}, 100`;
    circle.setAttribute('stroke-dasharray', dashArrayValue);
    percentageText.textContent = `${percentage}%`;

    document.getElementById("correct").textContent = correct;
    document.getElementById("incorrect").textContent = incorrect;
}




