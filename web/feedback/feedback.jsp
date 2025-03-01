<%-- 
    Document   : feedback
    Created on : Feb 23, 2025, 8:13:50 PM
    Author     : DUCA
--%>

<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8"/>
        <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
        <title>Help Center</title>
        <script src="https://cdn.tailwindcss.com"></script>
        <link rel="shortcut icon" href="./images/logo/Easyquiz_logo.png">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet"/>
    </head>
    <body class="bg-white text-gray-800">
        <div class="flex flex-col md:flex-row justify-between items-start md:items-center mb-8">
            <div class="flex items-center mb-4 md:mb-0">
                <div class="logo" style="display: flex; justify-content: center; padding: 10px; height: 100%; align-self: center;
                     font-weight: bold; font-size: 25px;">
                    <a href="home" style="color: #640D5F; text-decoration: none; padding: 0 15px; align-self: center;"><span>EasyQuiz |</span></a>
                </div>
                <div style="color: #640D5F; margin-left: -15px; font-size: 23px">
                    Help center
                </div>
            </div>
        </div>
        <div class="container mx-auto p-4">

            <div class="mb-8">
                <h2 class="text-2xl font-bold mb-4">
                    How can we help?
                </h2>
                <div class="flex space-x-4">
                    <div class="flex flex-col items-center p-4 border rounded-lg flex-1">
                        <i class="fas fa-user fa-2x mb-2"></i> 
                        <span><a href="account.jsp">Account</a></span>
                    </div>
                    <div class="flex flex-col items-center p-4 border rounded-lg flex-1">
                        <i class="fas fa-credit-card fa-2x mb-2"></i> 
                        <span><a href="billing.jsp">Billing</a></span>
                    </div>
                    <div class="flex flex-col items-center p-4 border rounded-lg flex-1">
                        <i class="fas fa-graduation-cap fa-2x mb-2"></i> 
                        <span><a href="studying.jsp">Studying</a></span>
                    </div>
                </div>
            </div>
            <div>
                <h2 class="text-2xl font-bold mb-4">
                    Feedback
                </h2>
                <textarea class="w-full border rounded-lg p-4 mb-4" placeholder="Write your feedback here..." rows="5"></textarea>
                <button class="text-white py-2 px-6 rounded-full" style="background-color: #640D5F;">
                    Send
                </button>
            </div>
        </div>
    </body>
</html>
