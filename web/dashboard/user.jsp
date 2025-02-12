<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard | User</title>
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
                <p>Dashboard | User</p>
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
                <div class="dashboard-item">
                    <i class="fa-solid fa-sliders"></i>
                    <a href="">
                        <p>Dashboard</p>
                    </a>
                </div>
                <hr>
            </div>
            <div class="other-dashboard">
                <p style="font-size: 12px; font-weight: 300; padding: 5px;">OTHER</p>
                <div class="dashboard-item-actived">
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
            <div class="user">
                <div class="title2">
                    <div style="display: flex;">
                        <div class="vertical-line"></div>
                        <p>Users</p>
                    </div>
                    <div>
                        <label for="search">Search:</label>
                        <input id="search" type="text">
                    </div>
                </div>
            </div>
            <div class="user-table">
                <table>
                    <thead>
                        <tr>
                            <th>ID <i class="fas fa-sort"></i></th>
                            <th>Name <i class="fas fa-sort"></i></th>
                            <th>Avatar <i class="fas fa-sort"></i></th>
                            <th>Email <i class="fas fa-sort"></i></th>
                            <th>Number of quiz <i class="fas fa-sort"></i></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>1</td>
                            <td>Airi Satou</td>
                            <td><img src="avt1.jpg" alt="Not found"></td>
                            <td>test1@email.com</td>
                            <td>10</td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>Angelica Ramos</td>
                            <td><img src="avt2.jpg" alt="Not found"></td>
                            <td>test2@email.com</td>
                            <td>5</td>
                        </tr>
                        <tr>
                            <td>3</td>
                            <td>Ashton Cox</td>
                            <td><img src="avt3.jpg" alt="Not found"></td>
                            <td>test3@email.com</td>
                            <td>7</td>
                        </tr>
                        <tr>
                            <td>4</td>
                            <td>Bradley Greer</td>
                            <td><img src="avt4.jpg" alt="Not found"></td>
                            <td>test4@email.com</td>
                            <td>3</td>
                        </tr>
                        <tr>
                            <td>5</td>
                            <td>Brenden Wagner</td>
                            <td><img src="avt5.jpg" alt="Not found"></td>
                            <td>test5@email.com</td>
                            <td>4</td>
                        </tr>
                    </tbody>
                </table>
                <div class="pagination">
                    <div>
                        <nav>
                            <a href="#">Previous</a>
                            <a href="#">1</a>
                            <a href="#">2</a>
                            <a href="#">Next</a>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="dashboard.js"></script>
</body>

</html>