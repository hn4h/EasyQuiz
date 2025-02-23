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
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet"/>
</head>
<body class="bg-white text-gray-800">
    <div class="container mx-auto p-4">
        <div class="flex flex-col md:flex-row justify-between items-start md:items-center mb-8">
            <div class="flex items-center mb-4 md:mb-0">
                <div class="text-2xl font-bold text-blue-600 mr-4">
                    EasyQuiz
                </div>
                <div class="text-lg text-blue-600">
                    Help center
                </div>
            </div>
            <div class="relative w-full md:w-auto">
                <input class="border rounded-full py-2 px-4 w-full md:w-64" placeholder="Search how-to and more" type="text"/>
                <i class="fas fa-search absolute right-4 top-3 text-gray-400"></i>
            </div>
        </div>
        <div class="mb-8">
            <h2 class="text-2xl font-bold mb-4">
                How can we help?
            </h2>
            <div class="flex space-x-4">
                <div class="flex flex-col items-center p-4 border rounded-lg flex-1">
                    <i class="fas fa-user fa-2x mb-2"></i> 
                    <span>Account</span>
                </div>
                <div class="flex flex-col items-center p-4 border rounded-lg flex-1">
                    <i class="fas fa-credit-card fa-2x mb-2"></i> 
                    <span>Billing</span>
                </div>
                <div class="flex flex-col items-center p-4 border rounded-lg flex-1">
                    <i class="fas fa-graduation-cap fa-2x mb-2"></i> 
                    <span>Studying</span>
                </div>
            </div>
        </div>
        <div>
            <h2 class="text-2xl font-bold mb-4">
                Feedback
            </h2>
            <textarea class="w-full border rounded-lg p-4 mb-4" placeholder="Write your feedback here..." rows="5"></textarea>
            <button class="bg-blue-600 text-white py-2 px-6 rounded-full">
                Send
            </button>
        </div>
    </div>
</body>
</html>
