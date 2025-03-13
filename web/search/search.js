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


document.addEventListener("DOMContentLoaded", function () {
    const pageSpan = document.querySelector(".page-span");
    const prevBtn = document.querySelector(".previous-btn");
    const nextBtn = document.querySelector(".next-btn");
    const cardList = document.querySelectorAll(".card-item");
    const itemsPerPage = 6;

    let currentPage = 1;
    let totalPages = Math.ceil(cardList.length / itemsPerPage);

    function updatePageDisplay() {
        pageSpan.textContent = `Page ${currentPage} of ${totalPages}`;
        prevBtn.disabled = (currentPage <= 1);
        nextBtn.disabled = (currentPage >= totalPages);
    }

    function showPage(page) {
        let startIndex = (page - 1) * itemsPerPage;
        let endIndex = startIndex + itemsPerPage;

        cardList.forEach((card, index) => {
            card.style.display = (index >= startIndex && index < endIndex) ? "block" : "none";
        });
    }
    prevBtn.addEventListener("click", function () {
        if (currentPage > 1) {
            currentPage--;
            showPage(currentPage);
            updatePageDisplay();
        }
    });

    nextBtn.addEventListener("click", function () {
        if (currentPage < totalPages) {
            currentPage++;
            showPage(currentPage);
            updatePageDisplay();
        }
    });

    showPage(currentPage);
    updatePageDisplay();
});