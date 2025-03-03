<%-- 
    Document   : folders
    Created on : Mar 1, 2025, 4:09:37 AM
    Author     : Lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Folder</title>
        <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <link rel="stylesheet" href="history.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <script src="https://cdn.tailwindcss.com"></script>
        <link rel="shortcut icon" href="./images/logo/Easyquiz_logo.png">
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
                        <a href="#" class="folders-button">Quiz</a>
                        <a href="#" class="quiz-button">Folders</a>
                    </div>
                    <div style="margin-top: 20px;" class="flex justify-between items-center mb-4">
                        <select class="border border-gray-300 px-4 py-2 rounded">
                            <option>Recent</option>
                            <option>Created</option>
                        </select>
                    </div>
                    <div>
                        <h2 class="text-lg font-bold mb-2">TODAY</h2>
                        <div class="folder-card bg-white-100 rounded mb-4">
                            <div class=" quiz-title flex">
                                <div>
                                    
                                </div>
                                <span class="title-text text-gray-600 mt-1"><i class="fa-solid fa-folder"></i>&nbsp;Folder 1</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
        <script src="history.js"></script>
    </body>
</html>
