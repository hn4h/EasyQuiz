<%-- 
    Document   : history
    Created on : Feb 21, 2025, 2:14:10 PM
    Author     : DUCA
--%>
<%@ page import="java.util.*, model.QuizHistory" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Quizzes</title>
        <link rel="stylesheet" href="./history/history.css">
        <link rel="shortcut icon" href="./images/logo/Easyquiz_logo.png">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <script src="https://cdn.tailwindcss.com"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    </head>
    <body>
        <div class="header">
            <%@include file="../default/header.jsp" %>
        </div>
        <div class="body">
            <aside class="sidebar">
                <%@include file="../default/sidebar.jsp" %>
            </aside>   
            <div class="body-container">
                <h1 class="text-2xl font-bold mb-4" style="margin-top: 10px;">History</h1>
                <div class="history-card">
                    <div class="button2">
                        <a href="quiz" class="quiz-button">Quiz</a>
                        <a href="folderhistory" class="folders-button">Folders</a>
                    </div>
                    <div style="margin-top: 20px;" class="flex justify-between items-center mb-4">
                        <select class="border border-gray-300 px-4 py-2 rounded">
                            <option>Recent</option>
                            <option>Created</option>
                        </select>
                        <div class="relative w-1/3">
                            <input type="text" placeholder="Search for a quiz" class="w-full p-2 border border-gray-300 rounded pr-10">
                            <div class="absolute inset-y-0 right-0 pr-3 flex items-center pointer-events-none">
                                <svg class="h-5 w-5 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"></path>
                                </svg>
                            </div>
                        </div>
                    </div>
                    <div>
                        <!--Today-->
                        <c:if test="${not empty todayQuizzes}">
                            <h2 class="text-lg font-bold mb-2">TODAY</h2>
                            <c:forEach var="quiz" items="${todayQuizzes}">
                                <div class=" quiz-card bg-white-100 rounded mb-4">
                                    <div class=" quiz-title flex">
                                        <div>
                                            <span class="text-sm text-gray-600">${quiz.numberOfQuiz} terms</span>
                                            <span class="text-sm text-gray-600 ml-2">|</span>
                                            <span class="text-sm text-gray-600 ml-2">${quiz.author}</span>
                                        </div>
                                        <span class="title-text text-gray-600 mt-1">${quiz.quizSetName}</span>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:if>
                        <!--This week-->
                        <c:if test="${empty todayQuizzes and not empty weekQuizzes}">
                            <h2 class="text-lg font-bold mb-2">THIS WEEK</h2>
                            <c:forEach var="quiz" items="${weekQuizzes}">
                                <div class=" quiz-card bg-white-100 rounded mb-4">
                                    <div class=" quiz-title flex">
                                        <div>
                                            <span class="text-sm text-gray-600">${quiz.numberOfQuiz} terms</span>
                                            <span class="text-sm text-gray-600 ml-2">|</span>
                                            <span class="text-sm text-gray-600 ml-2">${quiz.author}</span>
                                        </div>
                                        <span class="title-text text-gray-600 mt-1">${quiz.quizSetName}</span>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:if>
                        <!--month-->
                        <c:if test="${empty todayQuizzes and empty weekQuizzes}">
                            <h2 class="text-lg font-bold mb-2">${fn:split(entry.key, '-')[0]} / ${fn:split(entry.key, '-')[1]}</h2>
                            <c:forEach var="quiz" items="${monthlyQuizzes}">
                                <div class=" quiz-card bg-white-100 rounded mb-4">
                                    <div class=" quiz-title flex">
                                        <div>
                                            <span class="text-sm text-gray-600">${quiz.numberOfQuiz} terms</span>
                                            <span class="text-sm text-gray-600 ml-2">|</span>
                                            <span class="text-sm text-gray-600 ml-2">${quiz.author}</span>
                                        </div>
                                        <span class="title-text text-gray-600 mt-1">${quiz.quizSetName}</span>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
        <script src="history.js"></script>
    </body>

</html>
