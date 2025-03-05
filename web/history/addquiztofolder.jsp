<%-- 
    Document   : addquiztofolder
    Created on : Mar 5, 2025, 12:28:08 AM
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
        <link rel="stylesheet" href="addquiztofolder.css">
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
                <div class="content">
                    <h1 class="folder-title">Folder title</h1>
                    <div class="btn-area">
                        <button class="add-btn"><i class="fa-solid fa-plus"></i></button>
                        <button class="option-btn"><i class="fa-solid fa-ellipsis"></i></button>
                    </div>
                </div>
                <div class="empty-folder">
                    Thư mục này trống.
                </div>
                <div class=" quiz-card">
                    <div class="quiz-title flex">
                        <div>
                            <span class="text-sm text-gray-600">10 questions</span>
                            <span class="text-sm text-gray-600 ml-2">|</span>
                            <span class="text-sm text-gray-600 ml-2">denvau</span>
                        </div>
                        <span class="title-text text-gray-600 mt-1">Quiz 1</span>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
        <script src="history.js"></script>
    </body>
</html>
