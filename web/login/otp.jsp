<%-- 
    Document   : otp
    Created on : Feb 18, 2025, 8:57:07 PM
    Author     : DUCA
--%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>OTP Verification</title>
        <script src="https://cdn.tailwindcss.com"></script>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet"/>
    </head>
    <body class="flex items-center justify-center min-h-screen" style="background: linear-gradient(#fff, #ffbcfb);">
        <div class="bg-white p-8 rounded-lg shadow-lg w-full max-w-md">
            <div class="flex justify-center mb-4">
                <img src="https://atpcare.vn/wp-content/uploads/2021/10/mb.jpg" alt="Not found" class="w-30 h-24" height="100" width="150"/>
            </div>
            <h2 class="text-center text-xl font-semibold mb-2">
                OTP AUTHENTICATION
            </h2>
            <p class="text-center text-gray-600 mb-6">
                Please enter the code we sent you via email ${sessionScope.email}. The verification code is valid for <span id="countdown">120</span> seconds.
            </p>
            <form action="verifyotp" method="post" class="flex flex-col items-center">
                <div class="flex justify-center space-x-2 mb-6">
                    <input class="w-12 h-12 border border-gray-300 rounded text-center text-xl otp-input" maxlength="1" type="text" name="otp1" id="otp1" required="">
                    <input class="w-12 h-12 border border-gray-300 rounded text-center text-xl otp-input" maxlength="1" type="text" name="otp2" id="otp2" required="">
                    <input class="w-12 h-12 border border-gray-300 rounded text-center text-xl otp-input" maxlength="1" type="text" name="otp3" id="otp3" required="">
                    <input class="w-12 h-12 border border-gray-300 rounded text-center text-xl otp-input" maxlength="1" type="text" name="otp4" id="otp4" required="">
                    <input class="w-12 h-12 border border-gray-300 rounded text-center text-xl otp-input" maxlength="1" type="text" name="otp5" id="otp5" required="">
                    <input class="w-12 h-12 border border-gray-300 rounded text-center text-xl otp-input" maxlength="1" type="text" name="otp6" id="otp6" required="">
                </div>
                <button class="w-full text-white py-3 rounded-lg text-lg font-semibold mb-4" style="background-color: #640D5F;" type="submit">
                    Continue
                </button>
            </form>
            <p class="text-center text-gray-600">
                Haven't received code yet?
                <a class="text-blue-500" href="verifyotp">
                    Resend
                </a>
            </p>
        </div>

        <script>
            const otpInputs = document.querySelectorAll('.otp-input');

            otpInputs.forEach((input, index) => {
                input.addEventListener('input', (event) => {
                    const value = input.value;
                    if (value.length === 1 && /^[0-9]$/.test(value) && index < otpInputs.length - 1) {
                        otpInputs[index + 1].focus();
                    }
                });

                input.addEventListener('keydown', (event) => {
                    if (event.key === 'Backspace' && input.value.length === 0 && index > 0) {
                        otpInputs[index - 1].focus();
                    }
                });

                input.addEventListener('input', function (event) {
                    // Remove non-numeric characters immediately
                    this.value = this.value.replace(/[^0-9]/g, '');

                    // Manually trigger the input event to check if it's a number
                    if (this.value.length === 1 && /^[0-9]$/.test(this.value) && index < otpInputs.length - 1) {
                        otpInputs[index + 1].focus();
                    }
                });
            });

            let countdownElement = document.getElementById("countdown");
            let timeLeft = 120;

            function updateCountdown() {
                countdownElement.textContent = timeLeft;
                timeLeft--;

                if (timeLeft < 0) {
                    clearInterval(countdownInterval);
                    countdownElement.textContent = "0";
                }
            }

            updateCountdown();
            let countdownInterval = setInterval(updateCountdown, 1000);

        </script>
    </body>
</html>