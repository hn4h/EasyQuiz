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
    <body class="bg-purple-200 flex items-center justify-center min-h-screen">
        <div class="bg-white p-8 rounded-lg shadow-lg w-full max-w-md">
            <div class="flex justify-center mb-4">
                <img src="https://atpcare.vn/wp-content/uploads/2021/10/mb.jpg" alt="Not found" class="w-30 h-24" height="100" width="150"/>
            </div>
            <h2 class="text-center text-xl font-semibold mb-2">
                OTP AUTHENTICATION
            </h2>
            <p class="text-center text-gray-600 mb-6">
                Please enter the code we sent you via email ?????@gmail.com. The verification code is valid for 120 seconds.
            </p>
            <div class="flex justify-center space-x-2 mb-6">
                <input class="w-12 h-12 border border-gray-300 rounded text-center text-xl" maxlength="1" type="text" id="otp1">
                <input class="w-12 h-12 border border-gray-300 rounded text-center text-xl" maxlength="1" type="text" id="otp2">
                <input class="w-12 h-12 border border-gray-300 rounded text-center text-xl" maxlength="1" type="text" id="otp3">
                <input class="w-12 h-12 border border-gray-300 rounded text-center text-xl" maxlength="1" type="text" id="otp4">
                <input class="w-12 h-12 border border-gray-300 rounded text-center text-xl" maxlength="1" type="text" id="otp5">
                <input class="w-12 h-12 border border-gray-300 rounded text-center text-xl" maxlength="1" type="text" id="otp6">
            </div>
            <button class="w-full bg-orange-500 text-white py-3 rounded-lg text-lg font-semibold" onclick="verifyOTP()">
                Continue
            </button>
            <p class="text-center text-gray-600 mt-4">
                Haven't received code yet?
                <a class="text-blue-500" href="#">
                    Resend
                </a>
            </p>
        </div>

        <script>
            function verifyOTP() {
                const otp1 = document.getElementById('otp1').value;
                const otp2 = document.getElementById('otp2').value;
                const otp3 = document.getElementById('otp3').value;
                const otp4 = document.getElementById('otp4').value;
                const otp5 = document.getElementById('otp5').value;
                const otp6 = document.getElementById('otp6').value;

                const otp = otp1 + otp2 + otp3 + otp4 + otp5 + otp6;

                fetch('/verifyOTP', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({otp: otp})
                })
                        .then(response => response.json())
                        .then(data => {
                            if (data.success) {
                                window.location.href = '/success';
                            } else {
                                alert('Invalid OTP code. Please try again.');
                            }
                        });
            }
        </script>
    </body>
</html>