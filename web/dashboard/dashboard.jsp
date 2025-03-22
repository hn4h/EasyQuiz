<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Dashboard</title>
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
                                <p style="font-weight: bold; font-size: 30px;" id="transactions">${requestScope.numOfTransaction}</p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="chart-card">
                    <div class="title-summary">
                        <div style="display: flex;">
                            <div class="vertical-line"></div>
                            <p>Chart</p>
                        </div>

                        <div class="filter-summary">
                            <form action="dashboard" method="get" >
                                <select name="chartOption" class="form-select" onchange="this.form.submit()">
                                    <option value="1" ${requestScope.chartOption == '1' ? 'selected' : ''}>Last 7 days</option>
                                    <option value="2" ${requestScope.chartOption == '2' ? 'selected' : ''} >Last 30 days</option>
                                    <option value="3"${requestScope.chartOption == '3' ? 'selected' : ''}>Last 90 days</option>
                                    <option value="4"${requestScope.chartOption == '4' ? 'selected' : ''}>This Year</option>
                                </select>
                            </form>

                        </div>
                    </div>
                    <div id="revenue-chart">
                        <script>
                            document.addEventListener("DOMContentLoaded", function () {
                            var options = {
                            series: [{
                            name: 'Revenue',
                                    data: [<c:forEach items="${requestScope.chart}" var="c" varStatus="status">
                                ${c.value}<c:if test="${!status.last}">,</c:if>
                            </c:forEach>
                                    ]
                            }],
                                    chart: {
                                    height: 350,
                                            type: 'line',
                                            zoom: {
                                            enabled: true
                                            }
                                    },
                                    dataLabels: {
                                    enabled: false
                                    },
                                    stroke: {
                                    curve: 'smooth'
                                    },
                                    grid: {
                                    row: {
                                    colors: ['#f3f3f3', 'transparent'], // takes an array which will be repeated on columns
                                            opacity: 0.5
                                    },
                                    },
                                    xaxis: {
                                    categories: [<c:forEach items="${requestScope.chart}" var="c" varStatus="status">
                                    '${c.columnName}'<c:if test="${!status.last}">,</c:if>
                            </c:forEach>],
                                    }
                            };
                            var chart = new ApexCharts(document.querySelector("#revenue-chart"), options);
                            chart.render();
                            });
                        </script>
                    </div>
                </div>
                <div class="chart-user">

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
                                        <th>Date <i class="fas fa-sort"></i></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${requestScope.newTransaction}" var="t">
                                        <tr>
                                            <td>${t.userName}</td>
                                            <c:choose>
                                                <c:when test="${t.description == 'Annual Package'}">
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
                                            <td><span style="color: #000;">${t.createdDate}</span></td>
                                        </tr>
                                    </c:forEach>

                                </tbody>
                            </table>
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
            </div>
        </div>
    </div>
</div>
<script src="./dashboard/dashboard.js"></script>
<script src="https://cdn.jsdelivr.net/npm/apexcharts"></script>
</body>

</html>