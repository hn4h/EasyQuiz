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