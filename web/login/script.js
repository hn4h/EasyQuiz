const togglePassword = document.getElementById("togglePassword");
const passwordInput = document.getElementById("passwordInput");
const confirmPasswordInput = document.getElementById("confirmPasswordInput");
const toggleConfirmPassword = document.getElementById("toggleConfirmPassword");

function togglePasswordVisibility(input, button) {
    button.addEventListener("click", function () {
        const isVisible = input.type === "password";
        input.type = isVisible ? "text" : "password";
        this.innerHTML = isVisible
                ? '<i class="fa-regular fa-eye-slash"></i>'
                : '<i class="fa-regular fa-eye"></i>';
    });
}

togglePasswordVisibility(passwordInput, togglePassword);
togglePasswordVisibility(confirmPasswordInput, toggleConfirmPassword);

//----------------------------------OTP--

document.addEventListener("DOMContentLoaded", function () {
    const inputs = document.querySelectorAll(".otp-input input");
    const verifyBtn = document.querySelector(".verifyBtn");

    // Focus vào ô đầu tiên khi trang tải
    inputs[0].disabled = false;
    inputs[0].focus();

    inputs.forEach((input, index) => {
        input.addEventListener("input", (event) => {
            if (input.value.length === 1) {
                if (index < inputs.length - 1) {
                    inputs[index + 1].disabled = false;
                    setTimeout(() => inputs[index + 1].focus(), 50);
                }
            }
            checkAllInputsFilled();
        });

        input.addEventListener("keydown", (event) => {
            if (event.key === "Backspace") {
                if (input.value === "" && index > 0) {
                    inputs[index].disabled = true;
                    inputs[index - 1].focus();
                }
            }
        });
    });

    function checkAllInputsFilled() {
        const allFilled = [...inputs].every(input => input.value.length === 1);
        verifyBtn.disabled = !allFilled;
    }
});

//-----------------------Validate form--------
document.addEventListener("DOMContentLoaded", function () {
    const emailInput = document.getElementById("emailInput");
    const passwordInput = document.getElementById("passwordInput");
    const confirmPasswordInput = document.getElementById("confirmPasswordInput");
    const submitButton = document.getElementById("submitButton");

    const emailError = document.getElementById("emailError");
    const passwordError = document.getElementById("passwordError");
    const confirmPasswordError = document.getElementById("confirmPasswordError");

    let emailTouched = false;
    let passwordTouched = false;
    let confirmPasswordTouched = false;

    function setError(input, message, errorElement) {
        errorElement.textContent = message;
        input.classList.add("input-error");
    }

    function clearError(input, errorElement) {
        errorElement.textContent = "";
        input.classList.remove("input-error");
    }

    function validateEmail() {
        if (!emailTouched) return false; // Chưa chạm vào thì không kiểm tra

        const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        if (emailInput.value.trim() === "") {
            setError(emailInput, "Please enter your email.", emailError);
            return false;
        } else if (!emailPattern.test(emailInput.value.trim())) {
            setError(emailInput, "Invalid email address.", emailError);
            return false;
        } else {
            clearError(emailInput, emailError);
            return true;
        }
    }

    function validatePassword() {
        if (!passwordTouched) return false; // Chưa nhập password thì không kiểm tra

        if (passwordInput.value.length < 8) {
            setError(passwordInput, "Your password is too short. The minimum length is 8 characters.", passwordError);
            return false;
        } else {
            clearError(passwordInput, passwordError);
            return true;
        }
    }

    function validateConfirmPassword() {
        if (!confirmPasswordTouched) return false; // Chưa nhập confirm password thì không kiểm tra

        if (confirmPasswordInput.value !== passwordInput.value) {
            setError(confirmPasswordInput, "Your password does not match.", confirmPasswordError);
            return false;
        } else {
            clearError(confirmPasswordInput, confirmPasswordError);
            return true;
        }
    }

    function validateForm() {
        const isEmailValid = validateEmail();
        const isPasswordValid = validatePassword();
        const isConfirmPasswordValid = validateConfirmPassword();

        submitButton.disabled = !(isEmailValid && isPasswordValid && isConfirmPasswordValid);
    }

    emailInput.addEventListener("input", function () {
        emailTouched = true;
        validateEmail();
        validateForm();
    });

    passwordInput.addEventListener("input", function () {
        passwordTouched = true;
        validatePassword();
        validateForm();
    });

    confirmPasswordInput.addEventListener("input", function () {
        confirmPasswordTouched = true;
        validateConfirmPassword();
        validateForm();
    });
});












