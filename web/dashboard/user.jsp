<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Dashboard | User</title>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <link rel="stylesheet" href="./dashboard/dashboard.css">
        <link rel="shortcut icon" href="./images/logo/Easyquiz_logo.png">
        <link rel="stylesheet"
              href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
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
                            <a href="manageuser" class="nav-link-actived">
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
                <div class="user">
                    <div class="title2">
                        <div style="display: flex;">
                            <div class="vertical-line"></div>
                            <p>Users</p>
                        </div>
                        <div class="search">
                            <label for="search">Search:</label>
                            <input id="search" type="text">
                        </div>
                    </div>
                </div>
                <div class="user-table">
                    <table>
                        <thead>
                            <tr>
                                <th><span class="th-content">Avatar</span></th>
                                <th onclick="sortTable(1)"><span class="th-content">User Name <i class="fas fa-sort" id="icon-1"></i></span></th>
                                <th onclick="sortTable(2)"><span class="th-content">Email <i class="fas fa-sort" id="icon-2"></i></span></th>
                                <th style="text-align: center;">Actions</th>
                                <th style="text-align: center;">Details</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.users}" var="c" varStatus="status">
                                <tr class="user-row ${status.index % 2 == 0 ? 'even' : 'odd'}" id="row-${status.index}">
                                    <!--<td>1</td>-->
                                    <td style="text-align: center;"><img src="${c.imageProfile}" alt="Not found"></td>
                                    <td>${c.userName}</td>
                                    <td>${c.email}</td>
                                    <td style="text-align: center;">
                                        <select onchange="changeRowColor(this)" class="select-active">
                                            <option value="Active" selected>Active</option>
                                            <option value="Ban">Ban</option>
                                        </select>
                                    </td>
                                    <td style="text-align: center;">
                                        <span class="dropdown-icon material-symbols-rounded" id="icon-${status.index}" onclick="toggleDetails(${status.index})" data-id="${status.index}">expand_more</span>
                                    </td>
                                </tr>
                                <tr class="user-details" id="details-${status.index}">
                                    <td colspan="5">
                                        <div class="user-content">
                                            <div class="column">
                                                <!--                                        <p><strong>ID:</strong> 1</p>-->
                                                <p><strong>Name:</strong> ${c.userName}</p>
                                                <p><strong>Email:</strong> ${c.email}</p>
                                            </div>
                                            <div class="column">
                                                <p><strong>Folder:</strong> ${c.numFolder}</p>
                                                <p><strong>Quiz set:</strong> ${c.numQuizSet}</p>
                                                <!--<p><strong>Quiz test completed:</strong> 5</p>-->
                                            </div>
                                            <div class="column">
                                                <p><strong>Blog:</strong> ${c.numBlog}</p>
                                                <p><strong>Comment:</strong> ${c.numComment}</p>

                                            </div>
                                            <div class="column">
                                                <p><strong>Feedback:</strong> ${c.numFeedBack}</p>

                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div class="pagination">
                        <div class="pagination-options">
                            <select>
                                <option>5</option>
                                <option>10</option>
                                <option>15</option>
                                <option>20</option>
                                <option>25</option>
                            </select>
                            <span>entries per page</span>
                        </div>
                        <span class="pagination-text">Showing ? to ?? of ?? entries</span>
                        <div class="pagination-number">
                            <button id="prev-page" class="disabled">Previous</button>
                            <div id="page-numbers" class=".page-btn"></div>
                            <button id="next-page" class="disabled">Next</button>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <script src="./dashboard/dashboard.js"></script>
    </body>

</html>