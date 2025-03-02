<%-- 
    Document   : upgrade
    Created on : Feb 20, 2025, 12:49:10 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Upgrade package</title>
        <link rel="stylesheet" href="upgrade.css"/>
        <script src="https://cdn.tailwindcss.com"></script>
        <link rel="shortcut icon" href="./images/logo/Easyquiz_logo.png">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    </head>
    <body style="    background-image: url('../images/background/backgr.png'); 
    background-size: cover; 
    background-position: center; 
    background-repeat: no-repeat; 
    color: black;">
        <div class="container">
            <div class="header" style="position: fixed;
    background-color: #fff;
    display: flex;
    justify-content: space-between;
    width: 100%;
    top: 0;
    left: 0;
    z-index: 100;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);">
            <div class="logo" style="display: flex;
    justify-content: center;
    padding: 10px;
    height: 100%;
    align-self: center;
    font-weight: bold;
    font-size: 25px;">
                <a href="home"><span>EasyQuiz</span></a>
            </div>
        </div>
            <div class="py-8"></div>
            <h1 class="text-5xl font-bold text-center mb-8">EasyQuiz Premium</h1>
            <div class="py-4"></div>
            <h1 class="text-4xl font-bold text-center mb-8">Get better results with the <br/> number one learning platform</h1>
            <div class="plans">
                <div class="plan best-deal">
                    <div class="plan-title">Monthly</div>
                    <div class="plan-description">Billed annually today</div>
                    <div class="plan-price">50,000 VND <span>/month</span></div>
                    <div class="plan-description">Recurring billing. Cancel any time.</div>
                    <a href="#" class="plan-button secondary">Get EasyQuiz Premium</a>
                </div>
                <div class="plan">
                    <div class="plan-title">Annual</div>
                    <div class="plan-description">Amount billed today</div>
                    <div class="plan-price">450,000 VND <span>/year</span></div>
                    <div class="plan-description">Recurring billing. Cancel any time.</div>
                    <a href="#" class="plan-button secondary">Get EasyQuiz Premium</a>
                </div>
                <div class="plan">
                    <div class="plan-title">Unlimited</div>
                    <div class="plan-description">Amount billed today</div>
                    <div class="plan-price">3,500,000 VND </div>
                    <div class="plan-description">Recurring billing. Cancel any time.</div>
                    <a href="#" class="plan-button secondary">Get EasyQuiz Premium</a>
                </div>
            </div>
            <div class="py-8"></div>
            <div class="container mx-auto my-8 px-20 py-8">
                <h1 class="text-4xl font-bold text-center mb-8">Benefits of upgrading to EasyQuiz Premium</h1>

                <div class="flex flex-col lg:flex-row items-center lg:items-center justify-center lg:justify-between h-full gap-6">
                    <div class="text-left mb-6 lg:mb-0 lg:mr-6 lg:w-2/5 flex flex-col self-center">
                        <h2 class="text-4xl font-bold mb-3">Unlimited experience</h2>
                        <h2 class="text-4xl font-bold mb-3">with EasyQuiz</h2>
                        <p class="text-2xl">Unlimited number of quizzes you create and study.</p>
                        <p class="text-2xl">Unlimited number of tests you take.</p>
                    </div>

                    <div class="bg-yellow-400 rounded-lg p-6 lg:w-3/5 flex justify-center items-center h-full">
                        <div class="bg-white rounded-lg p-6 w-full">
                            <p class="text-gray-600 font-bold mb-2">Question 2/30</p>
                            <p class="text-gray-800 mb-4 text-lg">Organelle found only in plant cells that makes glucose?</p>
                            <div class="grid grid-cols-2 gap-4">
                                <button class="border border-gray-300 rounded-lg py-3 px-4 text-black text-lg">Nucleus</button>
                                <button class="border border-gray-300 rounded-lg py-3 px-4 text-black text-lg">Cytoplasm</button>
                                <button class="border border-gray-300 rounded-lg py-3 px-4 text-black text-lg">Mitochondrion</button>
                                <button class="border border-gray-300 rounded-lg py-3 px-4 text-black text-lg">Chloroplast</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="py-8"></div>

            <h1 class="text-4xl font-bold text-center mb-8">Experience it today at a great price!</h1>
            <div class="plans">
                <div class="plan best-deal">
                    <div class="plan-title">Monthly</div>
                    <div class="plan-description">Billed annually today</div>
                    <div class="plan-price">50,000 VND <span>/month</span></div>
                    <div class="plan-description">Recurring billing. Cancel any time.</div>
                    <a href="#" class="plan-button secondary">Get EasyQuiz Premium</a>
                </div>
                <div class="plan">
                    <div class="plan-title">Annual</div>
                    <div class="plan-description">Amount billed today</div>
                    <div class="plan-price">450,000 VND <span>/year</span></div>
                    <div class="plan-description">Recurring billing. Cancel any time.</div>
                    <a href="#" class="plan-button secondary">Get EasyQuiz Premium</a>
                </div>
                <div class="plan">
                    <div class="plan-title">Unlimited</div>
                    <div class="plan-description">Amount billed today</div>
                    <div class="plan-price">3,500,000 VND </div>
                    <div class="plan-description">Recurring billing. Cancel any time.</div>
                    <a href="#" class="plan-button secondary">Get EasyQuiz Premium</a>
                </div>
            </div>
        </div>
    </body>
</html>
