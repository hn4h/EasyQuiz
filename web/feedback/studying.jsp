<%-- 
    Document   : studying
    Created on : Feb 25, 2025, 9:43:05 PM
    Author     : DUCA
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Help Center - Studying</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="shortcut icon" href="./images/logo/Easyquiz_logo.png">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet"/>
    <link rel="stylesheet" href="styles.css">
    <script src="studying.js"></script>
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
        <span><a href="feedback.jsp">Studying</a></span>
    </div>
    <div class="mt-4 flex items-center space-x-4">
        <h2 class="text-3xl font-bold text-gray-800">Studying</h2>
    </div>
    <section class="mt-8">
        <div class="mt-4 grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4">
            <div class="studying-item" onclick="showDetails('stu1')">
                <h4 class="text-lg font-semibold text-gray-800">Becoming a top creator on EasyQuiz</h4>
                <p class="text-gray-600 mt-2">Top creators on EasyQuiz are determined by criteria like the number...</p>
            </div>
            <div class="studying-item" onclick="showDetails('stu2')">
                <h4 class="text-lg font-semibold text-gray-800">Organizing study content with folders</h4>
                <p class="text-gray-600 mt-2">You can add all kinds of study content to folders to help you stay organized...</p>
            </div>
            <div class="studying-item" onclick="showDetails('stu3')">
                <h4 class="text-lg font-semibold text-gray-800">What are flashcard sets?</h4>
                <p class="text-gray-600 mt-2">A flashcard set is a set of questions paired with their matching answers. You...</p>
            </div>
            <div class="studying-item" onclick="showDetails('stu4')">
                <h4 class="text-lg font-semibold text-gray-800">Creating quiz sets</h4>
                <p class="text-gray-600 mt-2">Creating your own quiz set lets you focus on exactly what you want to learn...</p>
            </div>
            <div class="studying-item" onclick="showDetails('stu5')">
                <h4 class="text-lg font-semibold text-gray-800">Copying and editing a set</h4>
                <p class="text-gray-600 mt-2">You can modify someone else's set or make a copy of one of your own to make...</p>
            </div>
            <div class="studying-item" onclick="showDetails('stu6')">
                <h4 class="text-lg font-semibold text-gray-800">Editing sets</h4>
                <p class="text-gray-600 mt-2">You can easily edit your flashcard sets. You can also copy someone else's...</p>
            </div>
            <div class="studying-item" onclick="showDetails('stu7')">
                <h4 class="text-lg font-semibold text-gray-800">Deleting a set</h4>
                <p class="text-gray-600 mt-2">To delete a set: 1. Log in to your account. 2. Go to the set. 3. Select More...</p>
            </div>
            <div class="studying-item" onclick="showDetails('stu8')">
                <h4 class="text-lg font-semibold text-gray-800">Finding quiz sets</h4>
                <p class="text-gray-600 mt-2">To find a quiz set: 1. Go to EasyQuiz web page. 2. Select Search. 3. Type in... </p>
            </div>
            <div class="studying-item" onclick="showDetails('stu9')">
                <h4 class="text-lg font-semibold text-gray-800">Sharing sets and folders</h4>
                <p class="text-gray-600 mt-2">Once you've created a quiz set or folder, or if you've found helpful content...</p>
            </div>
            <div class="studying-item" onclick="showDetails('stu10')">
                <h4 class="text-lg font-semibold text-gray-800">Studying with Flashcards</h4>
                <p class="text-gray-600 mt-2">Test your knowledge with Flashcards as you review your questions to work...</p>
            </div>
            <div class="studying-item" onclick="showDetails('stu11')">
                <h4 class="text-lg font-semibold text-gray-800">Studying with Test</h4>
                <p class="text-gray-600 mt-2">Test gives you the chance to see how you'll perform on an exam. To study... </p>
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