<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Dashboard | Transaction</title>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <link rel="stylesheet" href="./dashboard/dashboard.css">
        <link rel="shortcut icon" href="./images/logo/Easyquiz_logo.png">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    </head>

    <body>
        <div class="header">
            <div class="logo">
                <span onclick="window.location.href = 'home'">EasyQuiz</span>
                <div class="vertical-line"></div>
                <div class="name-dashboard">
                    <p>Dashboard | Transaction</p>
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
                            <a href="managetransaction" class="nav-link-actived">
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
                            <p>Transactions</p>
                        </div>
                        <div>
                            <label for="search">Search:</label>
                            <input id="search" type="text" onkeyup="searchTable()">
                        </div>
                    </div>
                </div>
                <div class="user-table">
                    <table>
                        <thead>
                            <tr>
                                <th onclick="sortTable(0)"><span class="th-content">Order code <i class="fas fa-sort" id="icon-0"></i></span></th>
                                <th>Name of user</th>
                                <th>Name of package</th>
                                <th onclick="sortTable(3)"><span class="th-content">Total <i class="fas fa-sort" id="icon-3"></i></span></th>
                                <th onclick="sortTable(4)"><span class="th-content">Status <i class="fas fa-sort" id="icon-4"></i></span></th>
                                <th onclick="sortTable(5)"><span class="th-content">Purchasing date <i class="fas fa-sort" id="icon-5"></i></span></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.transactions}" var="t" varStatus="status">
                                <tr class="user-row ${status.index % 2 == 0 ? 'even' : 'odd'}" id="row-${status.index}">
                                    <c:choose>
                                        <c:when test="${t.description == 'Annual Package'}">
                                            <td>
                                                <span class="package3">
                                                    ${t.orderCode}
                                                </span>
                                            </td>
                                            <td>${t.userName}</td>
                                            <td>
                                                <span class="package3">
                                                    ${t.description}
                                                </span>
                                            </td>
                                            <td>
                                                <span class="package3">
                                                    ${t.amount}
                                                </span>
                                            </td>
                                        </c:when>
                                        <c:when test="${t.description == 'Monthly Package'}">
                                            <td>
                                                <span class="package1">
                                                    ${t.orderCode}
                                                </span>
                                            </td>
                                            <td>${t.userName}</td>
                                            <td>
                                                <span class="package1">
                                                    ${t.description}
                                                </span>
                                            </td>
                                            <td>
                                                <span class="package1">
                                                    ${t.amount}
                                                </span>
                                            </td>
                                        </c:when>
                                        <c:when test="${t.description == 'Quarterly Package'}">
                                            <td>
                                                <span class="package2">
                                                    ${t.orderCode}
                                                </span>
                                            </td>
                                            <td>${t.userName}</td>
                                            <td>
                                                <span class="package2">
                                                    ${t.description}
                                                </span>
                                            </td>
                                            <td>
                                                <span class="package2">
                                                    ${t.amount}
                                                </span>
                                            </td>
                                        </c:when>
                                        <c:otherwise>
                                            <td>
                                                <span class="package0">
                                                    ${t.orderCode}
                                                </span>
                                            </td>
                                            <td>${t.userName}</td>
                                            <td>
                                                <span class="package0">
                                                    ${t.description}
                                                </span>
                                            </td>
                                            <td>
                                                <span class="package0">
                                                    ${t.amount}
                                                </span>
                                            </td>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${t.status == 'CANCELLED'}">
                                            <td>
                                                <span class="status1">
                                                    ${t.status}
                                                </span>
                                            </td>
                                        </c:when>
                                        <c:when test="${t.status == 'PAID'}">
                                            <td>
                                                <span class="status2">
                                                    ${t.status}
                                                </span>
                                            </td>
                                        </c:when>
                                        <c:otherwise>
                                            <td>
                                                <span class="status0">
                                                    ${t.status}
                                                </span>
                                            </td>
                                        </c:otherwise>
                                    </c:choose>
                                    <td>${t.createdDate}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div class="pagination">
                        <div class="pagination-options">
                            <select>
                                <option>5</option>
                                <option selected>10</option>
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