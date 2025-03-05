<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <link rel="stylesheet" href="./dashboard/dashboard.css">
    <link rel="shortcut icon" href="./images/logo/Easyquiz_logo.png">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>

<body>
    <div class="header">
        <div class="logo">
            <span>EasyQuiz</span>
            <div class="vertical-line"></div>
            <div class="name-dashboard">
                <p>Dashboard</p>
            </div>
        </div>

        <div class="admin-icon">
            <div>
                <img src="./images/avatar/default.png" alt="Not found">
            </div>
            <div class="admin-info">
                <p>Admin</p>
                <p>Website Owner</p>
            </div>
        </div>
    </div>
    <div class="body">
        <aside class="sidebar">
            <nav class="sidebar-nav">
                <!--Top nav-->
                <ul class="nav-list primary-nav">
                    <li class="nav-item">
                        <a href="dashboard" class="nav-link-actived">
                            <span class="material-symbols-rounded">dashboard</span>
                            <span class="nav-label">Dashboard</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="manageuser" class="nav-link">
                            <span class="material-symbols-rounded">person</span>
                            <span class="nav-label">User</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="managetransaction" class="nav-link">
                            <span class="material-symbols-rounded">receipt_long</span>
                            <span class="nav-label">Transaction</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="managepackage" class="nav-link">
                            <span class="material-symbols-rounded">package</span>
                            <span class="nav-label">Package</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="managefeedback" class="nav-link">
                            <span class="material-symbols-rounded">feedback</span>
                            <span class="nav-label">Feedback</span>
                        </a>
                    </li>
                </ul>
                <!--Bottom nav-->
                <ul class="nav-list secondary-nav">
                    <li class="nav-item">
                        <a href="logout" class="nav-link">
                            <span class="material-symbols-rounded">logout</span>
                            <span class="nav-label">Logout</span>
                        </a>
                    </li>
                </ul>
            </nav>
        </aside>
        <div class="main-content">
            <div class="summary">
                <div class="title-summary">
                    <div style="display: flex;">
                        <div class="vertical-line"></div>
                        <p>Summary</p>
                    </div>
                </div>
                <div class="summary-list">
                    <div class="summary-item" style="border-bottom: 4px solid #EFB6C8;">
                        <span class="material-symbols-rounded">payments</span>
                        <div>
                            <p style="font-weight: 600;">Total Revenue</p>
                            <p style="font-weight: bold; font-size: 30px;" id="revenue">${requestScope.totalRevenue}</p>
                        </div>
                    </div>
                    <div class="summary-item" style="border-bottom: 4px solid #A888B5;">
                        <span class="material-symbols-rounded">person</span>
                        <div>
                            <p style="font-weight: 600;">Total User</p>
                            <p style="font-weight: bold; font-size: 30px;" id="users">${requestScope.numOfUser}</p>
                        </div>
                    </div>
                    <div class="summary-item" style="border-bottom: 4px solid #8174A0;">
                        <span class="material-symbols-rounded">quiz</span>
                        <div>
                            <p style="font-weight: 600;">Total Quiz</p>
                            <p style="font-weight: bold; font-size: 30px;" id="quizzes">${requestScope.numOfQuizSet}</p>
                        </div>
                    </div>
                    <div class="summary-item" style="border-bottom: 4px solid #441752;">
                        <span class="material-symbols-rounded">receipt_long</span>
                        <div>
                            <p style="font-weight: 600;">Total Transaction</p>
                            <p style="font-weight: bold; font-size: 30px;" id="transactions">${requestScope.numOfQuizSet}</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="chart-user">
                <div class="chart-card">
                    <div class="title-summary">
                        <div style="display: flex;">
                            <div class="vertical-line"></div>
                            <p>Chart</p>
                        </div>
                        <div>

                        </div>
                        <div class="filter-summary">
                            <select id="chartFilter" onchange="updateChart()">
                                <option value="today">Today</option>
                                <option value="week">This Week</option>
                                <option value="month">This Month</option>
                                <option value="year">This Year</option>
                            </select>
                        </div>
                    </div>
                    <div class="area-chart">
                        <canvas id="revenueChart"></canvas>
                    </div>
                </div>
                <div class="user-card">
                    <div class="title">
                        <div class="vertical-line"></div>
                        <p>Recently user</p>
                    </div>
                    <div class="card-content">
                        <c:forEach items="${requestScope.newUser}" var="c">
                            <div class="notify">
                            <img src="${c.profileImage}" alt="Not found">
                            <p class="notify-content"><b>${c.userName}</b> has just registered an account successfully</p>
                        </div>
                        </c:forEach>
                        
                    </div>
                </div>
            </div>
            <div class="transaction-card">
                <div class="title">
                    <div class="vertical-line"></div>
                    <p>Recently transaction</p>
                </div>
                <div class="card-content">
                    <table>
                        <thead>
                            <tr>
                                <th>Name of user <i class="fas fa-sort"></i></th>
                                <th>Name of package <i class="fas fa-sort"></i></th>
                                <th>Price <i class="fas fa-sort"></i></th>
                                <th>Payments <i class="fas fa-sort"></i></th>
                                <th>Status <i class="fas fa-sort"></i></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Airi Satou</td>
                                <td><span
                                        style="padding: 5px 10px; color: #fff; background: #A64D79; border-radius: 20px; font-size: 14px;">Monthly</span>
                                </td>
                                <td><span
                                        style="padding: 5px 10px; color: #fff; background: #A64D79; border-radius: 20px; font-size: 14px;">1.99$</span>
                                </td>
                                <td><span style="color: #C62300;">Due</span></td>
                                <td><span style="color: #ce29c6;">Pending</span></td>
                            </tr>
                            <tr>
                                <td>Airi Satou</td>
                                <td><span
                                        style="padding: 5px 10px; color: #fff; background: #6A1E55; border-radius: 20px; font-size: 14px;">Annual</span>
                                </td>
                                <td><span
                                        style="padding: 5px 10px; color: #fff; background: #6A1E55; border-radius: 20px; font-size: 14px;">16.99$</span>
                                </td>
                                <td><span style="color: #C62300;">Due</span></td>
                                <td><span style="color: #ce29c6;">Pending</span></td>
                            </tr>
                            <tr>
                                <td>Airi Satou</td>
                                <td><span
                                        style="padding: 5px 10px; color: #fff; background: #3B1C32; border-radius: 20px; font-size: 14px;">Unlimited</span>
                                </td>
                                <td><span
                                        style="padding: 5px 10px; color: #fff; background: #3B1C32; border-radius: 20px; font-size: 14px;">129.99$</span>
                                </td>
                                <td><span style="color: #C62300;">Due</span></td>
                                <td><span style="color: #ce29c6;">Pending</span></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    </div>
    <script src="./dashboard/dashboard.js"></script>
</body>

</html>