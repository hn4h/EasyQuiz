<%-- 
    Document   : cancel
    Created on : Mar 9, 2025, 1:43:15 AM
    Author     : 11
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Payment Failed</title>
        <style>
            body {
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                background: linear-gradient(#e3f3fe, #ffbcfb);
                margin: 0;
                font-family: Arial, sans-serif;
            }
            .main-box {
                background: white;
                padding: 30px;
                border-radius: 10px;
                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
                text-align: center;
                max-width: 400px;
            }
            .error-icon {
                width: 50px;
                height: 50px;
                background: red;
                color: white;
                font-size: 32px;
                font-weight: bold;
                display: flex;
                align-items: center;
                justify-content: center;
                border-radius: 50%;
                margin: 0 auto 15px;
            }
            .payment-title {
                font-size: 24px;
                font-weight: bold;
                color: #7d197d;
            }
            p {
                font-size: 16px;
                color: #333;
            }
            a {
                color: #7d197d;
                text-decoration: none;
                font-weight: bold;
            }
            #return-page-btn {
                display: inline-block;
                margin-top: 20px;
                padding: 10px 20px;
                background: #7d197d;
                color: white;
                border-radius: 5px;
                text-decoration: none;
                font-size: 16px;
            }
            #return-page-btn:hover {
                background: #5c125c;
            }
        </style>
    </head>
    <body>
        <div class="main-box">
            <div class="error-icon">&times;</div>
            <h4 class="payment-title">Payment Failed</h4>
            <p>
                If you have any questions, please send an email to
                <a href="mailto:easiquiz@gmail.com">easiquiz@gmail.com</a>
            </p>
            <a href="upgrade" id="return-page-btn">Return to Payment Link Creation</a>
        </div>
    </body>
</html>

