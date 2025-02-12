<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <link rel="stylesheet" href="dashboard.css">
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
                <img src="avt1.jpg" alt="Not found">
            </div>
            <div class="admin-info">
                <p>Admin</p>
                <p>Website Owner</p>
            </div>
        </div>
    </div>
    <div class="body">
        <div class="side-bar">
            <div class="main-dashboard">
                <p style="font-size: 12px; font-weight: 300; padding: 5px;">MAIN</p>
                <div class="dashboard-item-actived">
                    <i class="fa-solid fa-sliders"></i>
                    <a href="">
                        <p>Dashboard</p>
                    </a>
                </div>
                <hr>
            </div>
            <div class="other-dashboard">
                <p style="font-size: 12px; font-weight: 300; padding: 5px;">OTHER</p>
                <div class="dashboard-item">
                    <i class="fa-solid fa-user"></i>
                    <a href="">
                        <p>User</p>
                    </a>
                </div>
                <div class="dashboard-item">
                    <i class="fa-solid fa-right-left"></i>
                    <a href="">
                        <p>Transaction</p>
                    </a>
                </div>
                <div class="dashboard-item">
                    <i class="fa-solid fa-blog"></i>
                    <a href="">
                        <p>Blog</p>
                    </a>
                </div>
                <div class="dashboard-item">
                    <i class="fa-solid fa-box"></i>
                    <a href="">
                        <p>Package</p>
                    </a>
                </div>
                <div class="dashboard-item">
                    <i class="fa-solid fa-comment"></i>
                    <a href="">
                        <p>Feedback</p>
                    </a>
                </div>
                <div class="dashboard-item">
                    <i class="fa-solid fa-right-from-bracket"></i>
                    <a href="">
                        <p>Logout</p>
                    </a>
                </div>
            </div>
        </div>
        <div class="main-content">
            <div class="summary">
                <div class="title">
                    <div class="vertical-line"></div>
                    <p>Summary</p>
                </div>
                <div class="summary-list">
                    <div class="summary-item">
                        <i class="fa-solid fa-coins"></i>
                        <div>
                            <p style="font-weight: 500;">Total Revenue</p>
                            <p style="font-weight: bold; font-size: 30px;">$123</p>
                        </div>
                    </div>
                    <div class="summary-item">
                        <i class="fa-solid fa-user"></i>
                        <div>
                            <p style="font-weight: 500;">Total User</p>
                            <p style="font-weight: bold; font-size: 30px;">123</p>
                        </div>
                    </div>
                    <div class="summary-item">
                        <i class="fa-brands fa-accusoft"></i>
                        <div>
                            <p style="font-weight: 500;">Total Quiz</p>
                            <p style="font-weight: bold; font-size: 30px;">123</p>
                        </div>
                    </div>
                    <div class="summary-item">
                        <i class="fa-solid fa-right-left"></i>
                        <div>
                            <p style="font-weight: 500;">Total Transaction</p>
                            <p style="font-weight: bold; font-size: 30px;">123</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="chart">
                <div class="title">
                    <div class="vertical-line"></div>
                    <p>Chart</p>
                </div>
                <div class="area-chart">
                    <div class="time-filter">
                        <button onclick="updateChart('week')">1 Tu?n</button>
                        <button onclick="updateChart('month')">1 Tháng</button>
                        <button onclick="updateChart('year')">1 N?m</button>
                    </div>
                    <canvas id="revenueChart"></canvas>
                </div>
            </div>
            <div class="user-transaction">
                <div class="list-user">
                    <div class="title">
                        <div class="vertical-line"></div>
                        <p>List of new user</p>
                    </div>
                    <div class="card">
                        <table><thead>
                            <tr>
                                <th>Name <i class="fas fa-sort"></i></th>
                                <th>Avatar <i class="fas fa-sort"></i></th>
                                <th>Email <i class="fas fa-sort"></i></th>
                                <th>Number of quiz <i class="fas fa-sort"></i></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Ashton Cox</td>
                                <td><img src="avt3.jpg" alt="Not found"></td>
                                <td>test3@email.com</td>
                                <td>7</td>
                            </tr>
                            <tr>
                                <td>Bradley Greer</td>
                                <td><img src="avt4.jpg" alt="Not found"></td>
                                <td>test4@email.com</td>
                                <td>3</td>
                            </tr>
                            <tr>
                                <td>Brenden Wagner</td>
                                <td><img src="avt5.jpg" alt="Not found"></td>
                                <td>test5@email.com</td>
                                <td>4</td>
                            </tr>
                        </tbody>
                        </table>
                    </div>
                </div>
                <div class="list-transaction">
                    <div class="title">
                        <div class="vertical-line"></div>
                        <p>Recently transaction</p>
                    </div>
                    <div class="card">
                        <table>
                            <thead>
                                <tr>
                                    <th>Name of user <i class="fas fa-sort"></i></th>
                                    <th>Name of package <i class="fas fa-sort"></i></th>
                                    <th>Total <i class="fas fa-sort"></i></th>
                                    <th>Purchasing date <i class="fas fa-sort"></i></th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>Airi Satou</td>
                                    <td>Monthly</td>
                                    <td>1.99$</td>
                                    <td>2025-01-01</td>
                                </tr>
                                <tr>
                                    <td>Angelica Ramos</td>
                                    <td>Monthly</td>
                                    <td>1.99$</td>
                                    <td>2025-01-01</td>
                                </tr>
                                <tr>
                                    <td>Ashton Cox</td>
                                    <td>Annual</td>
                                    <td>16.99$</td>
                                    <td>2025-01-01</td>
                                </tr>
                                <tr>
                                    <td>Bradley Greer</td>
                                    <td>Annual</td>
                                    <td>16.99$</td>
                                    <td>2025-01-01</td>
                                </tr>
                                <tr>
                                    <td>Brenden Wagner</td>
                                    <td>Unlimited</td>
                                    <td>129.99$</td>
                                    <td>2025-01-01</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="dashboard.js"></script>
</body>

</html>