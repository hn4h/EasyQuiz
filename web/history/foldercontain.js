// foldercontain.js
document.addEventListener('DOMContentLoaded', () => {
    const popup = document.getElementById("popup-folder");
    const openBtn = document.querySelector(".add-btn");
    const closeFolderBtn = document.getElementById("closePopup");
    const completeBtn = document.querySelector(".submit-button");
    const items = document.querySelectorAll(".item");
    const folderId = document.getElementById("folderId").value;

    // Show popup
    openBtn.addEventListener("click", (e) => {
        e.preventDefault();
        popup.style.display = "flex";
    });

    // Close popup when clicking X
    closeFolderBtn.addEventListener("click", () => {
        popup.style.display = "none";
    });

    // Close popup when clicking Complete
//    completeBtn.addEventListener("click", () => {
//        popup.style.display = "none";
//    });

    // Close popup when clicking outside
    window.addEventListener("click", (e) => {
        if (e.target === popup) {
            popup.style.display = "none";
        }
    });

    // Handle quiz set selection
    items.forEach(item => {
        item.addEventListener("click", (e) => {
            e.preventDefault();
            const quizSetId = item.getAttribute("data-id");
            const isSelected = item.classList.contains("selected");

            if (!isSelected) {
                // Add to folder
                fetch(`addtofolder?folderId=${folderId}&quizSetId=${quizSetId}`, {
                    method: 'GET'
                })
                        .then(response => {
                            if (response.ok) {
                                item.classList.add("selected");
                                item.querySelector(".ticked").style.display = "block";
                            }
                        })
                        .catch(error => console.error('Error:', error));
            } else {
                // Remove from folder
                fetch(`deletefromfolder?folderId=${folderId}&quizSetId=${quizSetId}`, {
                    method: 'GET'
                })
                        .then(response => {
                            if (response.ok) {
                                item.classList.remove("selected");
                                item.querySelector(".ticked").style.display = "none";
                            }
                        })
                        .catch(error => console.error('Error:', error));
            }
        });
    });
});