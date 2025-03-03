<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard | Feedback</title>
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
                <p>Dashboard | Feedback</p>
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
        <aside class="sidebar">
            <nav class="sidebar-nav">
                <!--Top nav-->
                <ul class="nav-list primary-nav">
                    <li class="nav-item">
                        <a href="#" class="nav-link">
                            <span class="material-symbols-rounded">dashboard</span>
                            <span class="nav-label">Dashboard</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="#" class="nav-link">
                            <span class="material-symbols-rounded">person</span>
                            <span class="nav-label">User</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="#" class="nav-link">
                            <span class="material-symbols-rounded">receipt_long</span>
                            <span class="nav-label">Transaction</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="#" class="nav-link">
                            <span class="material-symbols-rounded">package</span>
                            <span class="nav-label">Package</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="#" class="nav-link-actived">
                            <span class="material-symbols-rounded">feedback</span>
                            <span class="nav-label">Feedback</span>
                        </a>
                    </li>
                </ul>
                <!--Bottom nav-->
                <ul class="nav-list secondary-nav">
                    <li class="nav-item">
                        <a href="#" class="nav-link">
                            <span class="material-symbols-rounded">logout</span>
                            <span class="nav-label">Logout</span>
                        </a>
                    </li>
                </ul>
            </nav>
        </aside>
<%@ page import="dal.FeedbackDAO" %>
<%@ page import="model.Feedback" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    FeedbackDAO feedbackDAO = new FeedbackDAO();

    int currentPage = 1;
    int recordsPerPage = 10;
    int totalRecords = feedbackDAO.getTotalFeedbackCount();
    int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);
    int startIndex = (currentPage - 1) * recordsPerPage;

    if (request.getParameter("page") != null) {
        currentPage = Integer.parseInt(request.getParameter("page"));
    }

    List<Feedback> feedbacks = feedbackDAO.getPagedFeedbacks(startIndex, recordsPerPage);
%>

<div class="main-content">
    <div class="user">
                <div class="title2">
                    <div style="display: flex;">
                        <div class="vertical-line"></div>
                        <p>Feedback</p>
                    </div>
                </div>
            </div>
    <div class="user-table">
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name of user</th>
                    <th>Content</th>
                    <th>Date</th>
                </tr>
            </thead>
            <tbody>
                <%
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    for (Feedback f : feedbacks) {
                %>
                <tr>
                    <td><%= f.getFeedbackId() %></td>
                    <td><%= f.getUserName() %></td>
                    <td><%= f.getFeedbackContent() %></td>
                    <td><%= dateFormat.format(f.getFeedbackDate()) %></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <%-- Pagination --%>
    </div>
</div>
    </div>
    <script src="./dashboard/dashboard.js"></script>
</body>

</html>