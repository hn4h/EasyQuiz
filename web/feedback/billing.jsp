<%-- 
    Document   : billing
    Created on : Feb 25, 2025, 9:40:54 PM
    Author     : DUCA
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Help Center - Billing</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet"/>
    <link rel="stylesheet" href="styles.css">
    <script src="billing.js"></script>
    <link rel="shortcut icon" href="./images/logo/Easyquiz_logo.png">
</head>
<body class="bg-gray-50">
<header class="bg-white shadow-sm">
    <div class="flex flex-col md:flex-row justify-between items-start md:items-center mb-8">
        <div class="flex items-center mb-4 md:mb-0">
            <div class="logo">
                <a href="home"><span>EasyQuiz |</span></a>
            </div>
            <div class="help-center-title">
                <a href="feedback.jsp">Help center</a>
            </div>
        </div>
    </div>
</header>
<main class="container mx-auto px-4 py-8">
    <div class="flex items-center space-x-2 text-gray-600">
        <span>EasyQuiz Help Center</span>
        <i class="fas fa-chevron-right"></i>
        <span><a href="feedback.jsp">Billing</a></span>
    </div>
    <div class="mt-4 flex items-center space-x-4">
        <h2 class="text-3xl font-bold text-gray-800">Billing</h2>
    </div>
    <section class="mt-8">
        <div class="mt-4 grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4">
            <div class="billing-item" onclick="showDetails('ends')">
                <h4 class="text-lg font-semibold text-gray-800">What happens if my paid subscription ends?</h4>
                <p class="text-gray-600 mt-2">If your paid subscription ends, your account will revert to the free version...</p>
            </div>
            <div class="billing-item" onclick="showDetails('premium')">
                <h4 class="text-lg font-semibold text-gray-800">Buy a premium package</h4>
                <p class="text-gray-600 mt-2">To buy a premium package: 1. Log in to your account. 2. Select your profile image...</p>
            </div>
        </div>
    </section>
    <div id="detailView" class="mt-8 hidden">
        <div class="bg-white p-6 rounded-lg shadow-md">
            <h3 id="detailTitle" class="text-2xl font-semibold text-gray-800 mb-4"></h3>
            <p id="detailContent" class="text-gray-700"></p>
            <button onclick="hideDetails()" class="hide-detail-button">Click to hide</button>
        </div>
    </div>
</main>
</body>
</html>