const togglePassword = document.getElementById("togglePassword");
const passwordInput = document.getElementById("passwordInput");
togglePassword.addEventListener("click", function () {
    const isPasswordVisible = passwordInput.type === "password";
    passwordInput.type = isPasswordVisible ? "text" : "password";
    this.innerHTML = isPasswordVisible
            ? '<i class="fa-regular fa-eye-slash"></i>'
            : '<i class="fa-regular fa-eye"></i>';
});

document.getElementById("registerForm").addEventListener("submit", function (event) {
    const acceptTerms = document.getElementById("acceptTerms");
    const errorMessage = document.getElementById("errorMessage");

    if (!acceptTerms.checked) {
        event.preventDefault();
        errorMessage.textContent = "Please accept EasyQuiz's terms of service and privacy policy to continue.";
        errorMessage.style.display = "block";
    } else {
        errorMessage.style.display = "none";
    }
});
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





