<%-- 
    Document   : premium.jsp
    Created on : Feb 22, 2025, 7:01:04 PM
    Author     : DUCA
--%>

<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>EasyQuiz Premium</title>
        <link rel="shortcut icon" href="./images/logo/Easyquiz_logo.png">
        <script src="https://cdn.tailwindcss.com"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@700&display=swap" rel="stylesheet">
        <style>
            body {
                font-family: 'Montserrat', sans-serif;
            }

            @keyframes blink {
                50% {
                    color: black;
                }
            }

            .blink {
                animation: blink 0.5s step-end infinite alternate;
                color: red;
            }
        </style>
    </style>
</head>
<body class="text-black" style="background: linear-gradient(#fff, #ffbcfb);">
    <div class="text-center py-16">
        <h1 class="text-5xl font-bold">EasyQuiz Premium</h1>
        <p class="text-3xl mt-4">Get better results with the number one learning platform</p>
    </div>
    <div class="flex flex-col md:flex-row justify-center items-center gap-8 px-4">
        <div class="bg-white text-black rounded-lg p-6 w-full md:w-1/4 shadow-lg flex flex-col justify-between">
            <div>
                <div class="flex justify-between items-center">
                    <h2 class="text-xl font-bold">Monthly</h2>
                    <span class="bg-yellow-400 text-xs font-bold px-2 py-1 rounded-full blink">Best deal</span>
                </div>
                <p class="mt-2 text-sm">Billed annually at US$35.99, that's like</p>
                <p class="text-3xl font-bold mt-2">US$1.99 <span class="text-lg font-normal">/month</span></p>
                <p class="text-sm mt-2">Start with a 7-day free trial.</p>
            </div>
            <button class="bg-yellow-400 text-black font-bold py-2 px-4 rounded mt-4 w-full">Start your free trial</button>
        </div>
        <div class="bg-white text-black rounded-lg p-6 w-full md:w-1/4 shadow-lg flex flex-col justify-between">
            <div>
                <h2 class="text-xl font-bold">Annual</h2>
                <p class="mt-2 text-sm">Amount billed today</p>
                <p class="text-3xl font-bold mt-2">US$16.99 <span class="text-lg font-normal">/year</span></p>
                <p class="text-sm mt-2">Recurring billing. Cancel any time.</p>
            </div>
            <button class="bg-gray-200 text-black font-bold py-2 px-4 rounded mt-4 w-full" style="background-color: #640D5F; color: white">Get EasyQuiz Premium</button>
        </div>
        <div class="bg-white text-black rounded-lg p-6 w-full md:w-1/4 shadow-lg flex flex-col justify-between">
            <div>
                <h2 class="text-xl font-bold">Unlimited</h2>
                <p class="mt-2 text-sm">Amount billed today</p>
                <p class="text-3xl font-bold mt-2">US$129.99</p>
                <p class="text-sm mt-2">Recurring billing. Cancel any time.</p>
            </div>
            <button class="bg-gray-200 text-black font-bold py-2 px-4 rounded mt-4 w-full" style="background-color: #640D5F; color: white">Get EasyQuiz Premium</button>
        </div>
    </div>
</body>
</html>
