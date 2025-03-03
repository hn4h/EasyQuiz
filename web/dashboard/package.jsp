<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard | Package</title>
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
                <p>Dashboard | Package</p>
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
                        <a href="dashboard" class="nav-link">
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
                        <a href="managepackage" class="nav-link-actived">
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
            <div class="user">
                <div class="title2">
                    <div style="display: flex;">
                        <div class="vertical-line"></div>
                        <p>Packages</p>
                    </div>
                </div>
            </div>
            <div class="user-table">
                <table>
                    <thead>
                        <tr>
                            <th>ID <i class="fas fa-sort"></i></th>
                            <th>Name of package <i class="fas fa-sort"></i></th>
                            <th>Price <i class="fas fa-sort"></i></th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>1</td>
                            <td>Monthly</td>
                            <td>1.99$</td>
                            <td>
                                <button class="edit-btn">Edit</button>
                                <button class="delete-btn">Delete</button>
                            </td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>Annual</td>
                            <td>16.99$</td>
                            <td>
                                <button class="edit-btn">Edit</button>
                                <button class="delete-btn">Delete</button>
                            </td>
                        </tr>
                        <tr>
                            <td>3</td>
                            <td>Unlimited</td>
                            <td>129.99$</td>
                            <td>
                                <button class="edit-btn">Edit</button>
                                <button class="delete-btn">Delete</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <div class="pagination">
                    <div>
                        <button style="font-size: 14px; font-weight: bold;"><i class="fa-solid fa-plus"></i> New package</button>
                    </div>
                    <div>
                        <nav>
                            <a href="#">Previous</a>
                            <a href="#">1</a>
                            <a href="#">Next</a>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="./dashboard/dashboard.js"></script>
</body>

</html>