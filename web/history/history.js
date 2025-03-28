// SwiperJS pagination
const swiper = new Swiper('.card-wrapper', {
    loop: true,
    spaceBetween: 20,

    // Pagination bullets
    pagination: {
        el: '.swiper-pagination',
        clickable: true,
        dynamicBullets: true
    },

    // Navigation arrows
    navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
    },

    breakpoints: {
        0: {
            slidesPerView: 1
        },
        768: {
            slidesPerView: 2
        },
        1024: {
            slidesPerView: 3
        }
    }
});

//Toggle the visibility of a dropdown menu
const toggleDropdown = (dropdown, menu, isOpen) => {
    dropdown.classList.toggle("open", isOpen);
    menu.style.height = isOpen ? `${menu.scrollHeight}px` : 0;
};

const closeAllDropdowns = () => {
    document.querySelectorAll(".dropdown-container.open").forEach(openDropdown => {
        toggleDropdown(openDropdown, openDropdown.querySelector(".dropdown-subject"), false);
    });
};
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

const createFolderItem = document.querySelector('.create-menu-item:nth-child(2)'); // Chọn mục "Folder"
const folderPopup = document.getElementById('folderPopup');
const closeBtn = document.querySelector('.close-btn');

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

document.addEventListener('DOMContentLoaded', function() {
    const searchInput = document.querySelector('input[placeholder="Search for a quiz"]');
    const quizCards = document.querySelectorAll('.quiz-box');
    const sectionHeaders = document.querySelectorAll('h2');

    searchInput.addEventListener('input', function(e) {
        const searchTerm = e.target.value.toLowerCase().trim();
        
        // If search is empty, show all cards and headers
        if (searchTerm === '') {
            quizCards.forEach(card => card.style.display = 'block');
            sectionHeaders.forEach(header => header.style.display = 'block');
            return;
        }

        // Filter quiz cards based on quiz set name
        quizCards.forEach(card => {
            const quizSetName = card.querySelector('.title-text').textContent.toLowerCase();
            const shouldShow = quizSetName.includes(searchTerm);
            card.style.display = shouldShow ? 'block' : 'none';
        });

        // Show/hide section headers based on visible cards in their section
        sectionHeaders.forEach(header => {
            const nextCards = getNextQuizCards(header);
            const hasVisibleSibling = nextCards.some(card => 
                card.style.display !== 'none'
            );
            header.style.display = hasVisibleSibling ? 'block' : 'none';
        });
    });

    // Helper function to get quiz cards that follow a header
    function getNextQuizCards(header) {
        const cards = [];
        let nextElement = header.nextElementSibling;
        
        while (nextElement && !nextElement.matches('h2')) {
            if (nextElement.matches('.quiz-card')) {
                cards.push(nextElement);
            }
            nextElement = nextElement.nextElementSibling;
        }
        
        return cards;
    }
});

document.addEventListener("DOMContentLoaded", function () {
    const selectElement = document.querySelector("select");
    const recentDiv = document.querySelector(".recent");
    const createdDiv = document.querySelector(".created");

    // Ẩn `created` mặc định, chỉ hiển thị `recent`
    createdDiv.style.display = "none";

    selectElement.addEventListener("change", function () {
        if (this.value === "Created") {
            recentDiv.style.display = "none";
            createdDiv.style.display = "block";
        } else {
            recentDiv.style.display = "block";
            createdDiv.style.display = "none";
        }
    });
});