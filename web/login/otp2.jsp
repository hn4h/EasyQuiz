<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>OTP Verification</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <form class="form-otp">
        <span class="otp-title">Enter OTP</span>
        <p class="otp-text">
            We have sent a verification code to your email: <b>name@email.com</b>
        </p>
        <div class="otp-input">
            <input type="text" maxlength="1" required>
            <input type="text" maxlength="1" required disabled>
            <input type="text" maxlength="1" required disabled>
            <input type="text" maxlength="1" required disabled>
            <input type="text" maxlength="1" required disabled>
            <input type="text" maxlength="1" required disabled>
        </div>
        <button class="verifyBtn" type="submit" disabled>Verify</button>
    </form>

    <script src="script.js"></script>
</body>
</html>
