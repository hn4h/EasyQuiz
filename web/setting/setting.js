
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

function changeTheme(theme) {
    if (theme === "dark") {
        document.body.classList.add("dark-theme");
    } else {
        document.body.classList.remove("dark-theme");
    }
    // Lưu theme vào localStorage để áp dụng trên toàn hệ thống
    localStorage.setItem("theme", theme);
}

window.onload = function () {
    const savedTheme = localStorage.getItem("theme");
    if (savedTheme) {
        document.getElementById("theme").value = savedTheme;
        changeTheme(savedTheme);
    }
};
//---------------------Look password
const togglePassword1 = document.getElementById("togglePassword1");
const togglePassword2 = document.getElementById("togglePassword2");
const togglePassword3 = document.getElementById("togglePassword3");
const oldPassword = document.getElementById("oldPassword");
const newPassword = document.getElementById("newPassword");
const confirmPassword = document.getElementById("confirmPassword");

function togglePasswordVisibility(input, button) {
    button.addEventListener("click", function () {
        const isVisible = input.type === "password";
        input.type = isVisible ? "text" : "password";
        this.innerHTML = isVisible
                ? '<i class="fa-regular fa-eye-slash"></i>'
                : '<i class="fa-regular fa-eye"></i>';
    });
}

togglePasswordVisibility(oldPassword, togglePassword1);
togglePasswordVisibility(newPassword, togglePassword2);
togglePasswordVisibility(confirmPassword, togglePassword3);
//-----------------------Validate form--------
document.addEventListener("DOMContentLoaded", function () {
    const oldPassword = document.getElementById("oldPassword");
    const newPassword = document.getElementById("newPassword");
    const confirmPassword = document.getElementById("confirmPassword");

    const passwordError1 = document.getElementById("passwordError1");
    const passwordError2 = document.getElementById("passwordError2");
    const passwordError3 = document.getElementById("passwordError3");

    let passwordTouched1 = false;
    let passwordTouched2 = false;
    let passwordTouched3 = false;

    function setError(input, message, errorElement) {
        errorElement.textContent = message;
        input.classList.add("input-error");
    }

    function clearError(input, errorElement) {
        errorElement.textContent = "";
        input.classList.remove("input-error");
    }

    function validatePassword1() {
        if (!passwordTouched1) return false; // Chưa nhập password thì không kiểm tra

        if (oldPassword.value.length < 8) {
            setError(oldPassword, "Your password is too short. The minimum length is 8 characters.", passwordError1);
            return false;
        } else {
            clearError(oldPassword, passwordError1);
            return true;
        }
    }

    function validatePassword2() {
        if (!passwordTouched2) return false;

        if (newPassword.value.length < 8) {
            setError(newPassword, "Your password is too short. The minimum length is 8 characters.", passwordError2);
            return false;
        } else {
            clearError(newPassword, passwordError2);
            return true;
        }
    }

    function validatePassword3() {
        if (!passwordTouched3) return false;

        if (confirmPassword.value !== newPassword.value) {
            setError(confirmPassword, "Passwords do not match.", passwordError3);
            return false;
        } else {
            clearError(confirmPassword, passwordError3);
            return true;
        }
    }

    oldPassword.addEventListener("input", function () {
        passwordTouched1 = true;
        validatePassword1();
    });

    newPassword.addEventListener("input", function () {
        passwordTouched2 = true;
        validatePassword2();
    });

    confirmPassword.addEventListener("input", function () {
        passwordTouched3 = true;
        validatePassword3();
    });
    
    document.getElementById("changePasswordForm").addEventListener("submit", function (event) {
        let valid1 = validatePassword1();
        let valid2 = validatePassword2();
        let valid3 = validatePassword3();

        if (!valid1 || !valid2 || !valid3) {
            event.preventDefault(); // Chặn form submit nếu có lỗi
        }
    });
});