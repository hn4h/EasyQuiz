<%-- 
    Document   : checkout
    Created on : Feb 24, 2025, 4:16:31 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Checkout | EasyQuiz</title>
        <link rel="stylesheet" href="upgrade.css"/>
        <link rel="shortcut icon" href="./images/logo/Easyquiz_logo.png">
    </head>
    <body>
        <div class="header">
            <div class="logo">
                <a href="home"><span>EasyQuiz</span></a>
            </div>
        </div>
        <div class="checkout-container">
            <h1>
                Check out to get EasyQuiz superpowers
            </h1>
            <div class="order">
                <h2>
                    Your order
                </h2>
                <div class="plan">
                    <div class="plan-content-best-deal">
                        <input checked id="monthly" name="plan" type="radio"/>
                        <label for="monthly">
                            Monthly
                        </label>
                        <span style="text-align: right">Best deal</span>
                    </div>
                    <span class="price">
                        49,000 VND /month
                    </span>
                </div>
                <div class="plan">
                    <div class="plan-content">
                        <input id="quarterly" name="plan" type="radio"/>
                        <label for="quarterly">
                            Quarterly
                        </label>
                    </div>
                    <span class="price">
                        129,000 VND /quarter
                    </span>
                </div>
                <div class="plan">
                    <div class="plan-content">
                        <input id="annual" name="plan" type="radio"/>
                        <label for="annual">
                            Annual
                        </label>
                    </div>
                    <span class="price">
                        449,000 VND /year
                    </span>
                </div>
                <div class="total" id="total-monthly">
                    <span>
                        One month of EasyQuiz Premium
                    </span>
                    <span>
                        49,000 VND
                    </span>
                </div>
                <div class="total" id="total-quarterly">
                    <span>
                        One quarter of EasyQuiz Premium
                    </span>
                    <span>
                        129,000 VND/quarter
                    </span>
                </div>
                <div class="total" id="total-annual">
                    <span>
                        One year of EasyQuiz Premium
                    </span>
                    <span>
                        449,000 VND/year
                    </span>
                </div>
            </div>
            <div class="payment">
                <h2>
                    QR checkout
                </h2>
                <img src="./images/qr/qr.png" alt="Not found"/>
                <button class="complete-purchase">
                    Complete purchase
                </button>
                <p class="note">
                    Your package subscription to EasyQuiz Premium will start today and will be until cancelled automatically until expired.
                </p>
            </div>
        </div>
        <script src="upgrade.js"></script>
    </body>
</html>
