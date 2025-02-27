<%-- 
    Document   : account
    Created on : Feb 24, 2025, 10:38:47 PM
    Author     : DUCA
--%>

<!DOCTYPE html>
<html>
    <head>
        <title>Help Center - Account</title>
        <script src="https://cdn.tailwindcss.com"></script>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet"/>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet"/>
        <script src="account.js"></script>
    </head>
    <body class="bg-gray-50">
        <header class="bg-white shadow-sm">
            <div class="flex flex-col md:flex-row justify-between items-start md:items-center mb-8">
                <div class="flex items-center mb-4 md:mb-0">
                    <div class="logo" style="display: flex; justify-content: center; padding: 10px; height: 100%; align-self: center; font-weight: bold; font-size: 25px;">
                        <a href="home" style="color: #640D5F; text-decoration: none; padding: 0 15px; align-self: center;"><span>EasyQuiz |</span></a>
                    </div>
                    <div style="color: #640D5F; margin-left: -15px; font-size: 23px">
                        Help center
                    </div>
                </div>
            </div>
        </header>
        <main class="container mx-auto px-4 py-8">
            <div class="flex items-center space-x-2 text-gray-600">
                <span>EasyQuiz Help Center</span>
                <i class="fas fa-chevron-right"></i>
                <span><a href="feedback.jsp">Account</a></span>
            </div>
            <div class="mt-4 flex items-center space-x-4">
                <h2 class="text-3xl font-bold text-gray-800">Account</h2>
            </div>
            <section class="mt-8">
                <div class="mt-4 grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4">
                    <div class="bg-white p-4 rounded-lg shadow-sm cursor-pointer" onclick="showDetails('signup')">
                        <h4 class="text-lg font-semibold text-gray-800">Signing up for a free account</h4>
                        <p class="text-gray-600 mt-2">You can create a free account using your email address,or Google account...</p>
                    </div>
                    <div class="bg-white p-4 rounded-lg shadow-sm cursor-pointer" onclick="showDetails('login')">
                        <h4 class="text-lg font-semibold text-gray-800">Trouble logging in</h4>
                        <p class="text-gray-600 mt-2">We're sorry to hear that you're having trouble logging in! Below, find the...</p>
                    </div>
                    <div class="bg-white p-4 rounded-lg shadow-sm cursor-pointer" onclick="showDetails('username')">
                        <h4 class="text-lg font-semibold text-gray-800">Changing your username</h4>
                        <p class="text-gray-600 mt-2">To change your username: 1. Log in to your account. 2. Select your profile image...</p>
                    </div>
                    <div class="bg-white p-4 rounded-lg shadow-sm cursor-pointer" onclick="showDetails('password')">
                        <h4 class="text-lg font-semibold text-gray-800">Changing your password</h4>
                        <p class="text-gray-600 mt-2">You can change your password from your Settings. To change your password Log in to...</p>
                    </div>
                    <div class="bg-white p-4 rounded-lg shadow-sm cursor-pointer" onclick="showDetails('forgot')">
                        <h4 class="text-lg font-semibold text-gray-800">Forgotten password</h4>
                        <p class="text-gray-600 mt-2">To reset your password: 1. Go to the Forgot password page. 2. Enter your username...</p>
                    </div>
                    <div class="bg-white p-4 rounded-lg shadow-sm cursor-pointer" onclick="showDetails('logout')">
                        <h4 class="text-lg font-semibold text-gray-800">Logging out</h4>
                        <p class="text-gray-600 mt-2">When you log in to your EasyQuiz account on the website, you'll stay logged in...</p>
                    </div>
                    <div class="bg-white p-4 rounded-lg shadow-sm cursor-pointer" onclick="showDetails('delete')">
                        <h4 class="text-lg font-semibold text-gray-800">Deleting your account</h4>
                        <p class="text-gray-600 mt-2">To delete your account: 1. Log in to your account on the EasyQuiz website. 2. Go to...</p>
                    </div>
                </div>
            </section>
            <div id="detailView" class="mt-8 hidden">
                <div class="bg-white p-6 rounded-lg shadow-md">
                    <h3 id="detailTitle" class="text-2xl font-semibold text-gray-800 mb-4"></h3>
                    <p id="detailContent" class="text-gray-700"></p>
                    <button onclick="hideDetails()" class="mt-4 text-gray-800 font-semibold py-2 px-4 rounded" style="color: #640D5F;">Click to hide</button>
                </div>
            </div>
        </main>
    </body>
</html>