<%-- 
    Document   : feedback
    Created on : Feb 23, 2025, 8:13:50 PM
    Author     : DUCA
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8"/>
        <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
        <title>Help Center</title>
        <script src="https://cdn.tailwindcss.com"></script>
        <link rel="shortcut icon" href="./images/logo/Easyquiz_logo.png">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet"/>
        <link rel="stylesheet" href="./feedback/styles.css">
    </head>
    <body class="bg-white text-gray-800">
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
        <div class="container mx-auto p-4">
            <div class="mb-8">
                <h2 class="text-2xl font-bold mb-4">
                    How can we help?
                </h2>
                <div class="flex space-x-4">
                    <div class="help-option">
                        <i class="fas fa-user fa-2x mb-2"></i>
                        <span><a href="account.jsp">Account</a></span>
                    </div>
                    <div class="help-option">
                        <i class="fas fa-credit-card fa-2x mb-2"></i>
                        <span><a href="billing.jsp">Billing</a></span>
                    </div>
                    <div class="help-option">
                        <i class="fas fa-graduation-cap fa-2x mb-2"></i>
                        <span><a href="studying.jsp">Studying</a></span>
                    </div>
                </div>
            </div>
            <div>
                <h2 class="text-2xl font-bold mb-4">Feedback</h2>
                <form action="feedback" method="post">
                    <textarea class="w-full border rounded-lg p-4 mb-4" 
                              name="feedbackContent" 
                              placeholder="Write your feedback here..." 
                              rows="5"></textarea>
                    <button class="send-button" type="submit">Send</button>
                </form>
            </div>
        </div>
    </body>
</html>
