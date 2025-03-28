<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.util.List, java.util.Map" %>
<%@ page import="model.Account" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Settings</title>
        <link rel="stylesheet" href="./setting/setting.css">
        <!--        <link rel="stylesheet" href="<!--%=request.getContextPath()%>/all.css">-->
        <link rel="shortcut icon" href="./images/logo/Easyquiz_logo.png">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    </head>
    <body>
        <div class="header">
            <div class="logo">
                <div class="menu-btn">
                    <button class="sidebar-toggler" id="menuToggle"><i class="fa-solid fa-bars"></i></button>
                </div>
                <a href="home"><span>EasyQuiz</span></a>
            </div>
            <div class="create-login">
                <c:if test="${sessionScope.account != null}">
                    <fmt:formatDate value="<%= new java.util.Date() %>" pattern="yyyy-MM-dd" var="today" />
                    <div class="avatar-user"  id="avatarUser">
                        <img src="${sessionScope.account.profileImage}" alt="Not found">
                        <div class="user-menu" id="userMenu">
                            <div class="user-info">
                                <img src="${sessionScope.account.profileImage}" alt="Not found"/>
                                <div>
                                    <div>
                                        <p>${sessionScope.account.userName}</p>
                                        <c:if test="${sessionScope.account.expiredDate > today}">
                                            <span class="premium-icon material-symbols-rounded">crown</span>
                                        </c:if>
                                    </div>
                                    <p>
                                        <c:choose>
                                            <c:when test="${fn:length(sessionScope.account.email) > 15}">
                                                ${fn:substring(sessionScope.account.email, 0, 15)}...
                                            </c:when>
                                            <c:otherwise>
                                                ${sessionScope.account.email}
                                            </c:otherwise>
                                        </c:choose>
                                    </p>
                                </div>
                            </div>
                            <hr/>
                            <a href="setting" class="user-menu-item"><i class="fa-solid fa-user"></i> Profile</a>
                            <a href="feedback" class="user-menu-item"><i class="fa-solid fa-circle-info"></i> Help and feedback</a>
                            <a href="upgrade" class="user-menu-item"><i class="fa-solid fa-crown"></i> Upgrades</a>
                            <hr/>
                            <a href="logout" class="user-menu-item"><i class="fa-solid fa-arrow-right-from-bracket"></i> Logout</a>
                        </div>
                    </div>
                </c:if>
                <c:if test="${sessionScope.account == null}">
                    <div class="login-btn">
                        <a href="login"><button>Log in</button></a>
                    </div>
                </c:if>
            </div>
        </div>
        <%
    String successMessage = (String) session.getAttribute("successMessage");
    if (successMessage != null) {
        %>
        <div id="toastMessage1">
            <span class="material-symbols-rounded">check</span>
            <span><%= successMessage %></span>
        </div>
        <script>
            setTimeout(function () {
                let toast1 = document.getElementById("toastMessage1");
                toast1.style.opacity = "0";
                setTimeout(() => {
                    toast1.style.display = "none";
                }, 500); // Ẩn hoàn toàn sau 0.5 giây sau khi mờ
            }, 3000);
        </script>
        <%
            session.removeAttribute("successMessage"); // Xóa sau khi hiển thị
            }
        %>


        <%
    String errorMessage = (String) request.getAttribute("error");
    if (errorMessage == null) {
        errorMessage = (String) session.getAttribute("error");
    }
    if (errorMessage != null) {
        %>
        <div id="toastMessage2">
            <span class="material-symbols-rounded">close</span>
            <span><%= errorMessage %></span>
        </div>
        <script>
            setTimeout(function () {
                let toast2 = document.getElementById("toastMessage2");
                toast2.style.opacity = "0";
                setTimeout(() => {
                    toast2.style.display = "none";
                }, 500);
            }, 3000);
        </script>
        <%
            session.removeAttribute("error");
            }
        %>
        <div class="body">
            <aside class="sidebar">
                <nav class="sidebar-nav">
                    <ul class="nav-list primary-nav">
                        <li class="nav-item">
                            <a href="home" class="nav-link">
                                <span class="material-symbols-rounded">home</span>
                                <span class="nav-label">Home</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="quizhistory" class="nav-link">
                                <span class="material-symbols-rounded">history</span>
                                <span class="nav-label">History</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="blog" class="nav-link">
                                <span class="material-symbols-rounded">rss_feed</span>
                                <span class="nav-label">Blog</span>
                            </a>
                        </li>
                        <c:if test="${sessionScope.account.isAdmin}">
                            <li class="nav-item">
                                <a href="dashboard" class="nav-link">
                                    <span class="material-symbols-rounded">dashboard</span>
                                    <span class="nav-label">Dashboard</span>
                                </a>
                            </li>
                        </c:if>
                    </ul>
                    <ul class="nav-list secondary-nav">
                        <li class="nav-item">
                            <a href="setting" class="nav-link-actived">
                                <span class="material-symbols-rounded">settings</span>
                                <span class="nav-label">Setting</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="logout" class="nav-link">
                                <span class="material-symbols-rounded">logout</span>
                                <span class="nav-label">Logout</span>
                            </a>
                        </li>
                    </ul>
                </nav>
            </aside>
            <div class="body-container">
                <h1>Settings</h1>
                <div class="personal-setting">
                    <h2>Personal information</h2>
                    <div class="personal-setting-box">
                        <div style="border-bottom: 2px solid #f3f3f3;">
                            <h3>Profile picture</h3>
                            <div class="profile-picture">
                                <img src="${sessionScope.account.profileImage}" alt="Not found">
                                <div class="avt-list">
                                    <c:forEach begin="1" end="14" var="i">
                                        <div class="avt-item">
                                            <form action="setting" method="post" style="display: inline;">
                                                <input type="hidden" name="action" value="updateAvatar">
                                                <input type="hidden" name="avatar" value="./images/avatar/avt${i}.jpg">
                                                <button type="submit">
                                                    <img src="./images/avatar/avt${i}.jpg" alt="Not found">
                                                </button>
                                            </form>
                                        </div>
                                    </c:forEach>
                                    <!-- Hiển thị ảnh đã upload -->
                                    <% 
                                        Account acc = (Account) session.getAttribute("account");
                                        Map<String, List<String>> userAvatars = (Map<String, List<String>>) session.getAttribute("userAvatars");
                                        List<String> avatarList = (userAvatars != null && acc.getUserName() != null) ? userAvatars.get(acc.getUserName()) : null;
                                    %>

                                    <% if (avatarList != null && !avatarList.isEmpty()) { %>
                                    <% for (String avatar : avatarList) { %>
                                    <div class="avt-item">
                                        <form action="setting" method="post" style="display: inline;">
                                            <input type="hidden" name="action" value="updateAvatar">
                                            <input type="hidden" name="avatar" value="<%= avatar %>">
                                            <button type="submit">
                                                <img src="<%= avatar %>" alt="Avatar">
                                            </button>
                                        </form>
                                    </div>
                                    <% } %>
                                    <% } %>

                                    <div class="avt-item">
                                        <form id="uploadForm" action="uploadavatar" method="post" enctype="multipart/form-data" style="display: inline;">
                                            <input type="file" name="avatar" id="avatarInput" accept="image/*" style="display: none;" onchange="uploadFile()">
                                            <button type="button" onclick="document.getElementById('avatarInput').click();">
                                                <span class="material-symbols-rounded">add</span>
                                            </button>
                                        </form>
                                    </div>


                                </div>
                            </div>
                        </div>
                        <div class="attribute-box">
                            <div class="attribute-box-item">
                                <h3>Username</h3>
                                <p>${sessionScope.account.userName}</p>
                            </div>
                            <button type="button" onclick="showUsernamePopup()">Edit</button>
                        </div>
                        <div class="attribute-box">
                            <div class="attribute-box-item">
                                <h3>Email</h3>
                                <p>${sessionScope.account.email}</p>
                            </div>
                        </div>
                        <fmt:formatDate value="<%= new java.util.Date() %>" pattern="yyyy-MM-dd" var="today" />
                        <c:if test="${sessionScope.account.expiredDate > today}">
                            <div class="attribute-box">
                                <div class="attribute-box-item">
                                    <div>
                                        <h3>Upgrade package time</h3>
                                        <span class="premium-icon material-symbols-rounded">crown</span>
                                    </div>
                                    <p>Your package will be expired at <strong style="color: #000;">${sessionScope.account.expiredDate}</strong></p>
                                </div>
                                <button type="button" onclick="window.location.href = 'upgrade'">Add extension</button>
                            </div>
                        </c:if>
                    </div>
                </div>
                <div class="personal-setting">
                    <h2>Account</h2>
                    <div class="personal-setting-box">
                        <c:if test="${!isGoogleAccount}">
                            <div class="attribute-box">
                                <h3>Change password</h3>
                                <button onclick="showChangePasswordPopup()">Change</button>
                            </div>
                        </c:if>
                        <c:if test="${!sessionScope.account.isAdmin}">
                            <div class="attribute-box">
                                <div>
                                    <h3>Delete your account</h3>
                                    <p>This will delete all your data and cannot be undone.</p>
                                </div>
                                <button onclick="showDeleteAccountPopup()" style="color: #fff; background-color: #970000;">Delete account</button>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        <!-- Username Edit Popup -->
        <div class="folder-popup" id="usernamePopup" style="display: none;">
            <div class="folder-popup-content">
                <span class="close-btn" onclick="hideUsernamePopup()">×</span>
                <h2>Enter your new username</h2>
                <form action="setting" method="post">
                    <input type="hidden" name="action" value="updateUsername">
                    <input type="text" name="newUsername" class="folder-input" value="${sessionScope.account.userName}" required>
                    <div class="create-folder-btn">
                        <button type="submit">Change</button>
                    </div>
                </form>
            </div>
        </div>
        <!-- Change Password Popup -->
        <div id="changePasswordPopup" class="popup" style="display: none;">
            <div class="popup-content">
                <h2>Change Password</h2>
                <form id="changePasswordForm" action="changepassword" method="post">
                    <label for="oldPassword">Old Password:</label>
                    <div class="input-wrapper">
                        <input id="oldPassword" type="password" name="oldpassword" required>
                        <button type="button" class="icon-eye" id="togglePassword1"><i class="fa-regular fa-eye"></i></button>
                    </div>
                    <div class="error-message" id="passwordError1"></div>
                    <label for="newPassword">New Password:</label>
                    <div class="input-wrapper">
                        <input id="newPassword" type="password" name="newpassword" required>
                        <button type="button" class="icon-eye" id="togglePassword2"><i class="fa-regular fa-eye"></i></button>
                    </div>
                    <div class="error-message" id="passwordError2"></div>
                    <label for="confirmPassword">Confirm New Password:</label>
                    <div class="input-wrapper">
                        <input id="confirmPassword" type="password" name="confirmPassword" required>
                        <button type="button" class="icon-eye" id="togglePassword3"><i class="fa-regular fa-eye"></i></button>
                    </div>
                    <div class="error-message" id="passwordError3"></div>
                    <div style="display: flex; justify-content: center;">
                        <button type="submit" class="change-btn">Change</button>
                        <button type="button" class="cancel-btn" onclick="hideChangePasswordPopup()">Cancel</button>
                    </div>
                </form>
            </div>
        </div>
        <form action="deleteaccount" method="post">
            <div id="deleteAccountPopup" class="popup" style="display: none;">
                <div class="popup-content">
                    <span class="close-btn" onclick="hideDeleteAccountPopup()">×</span>
                    <h2>Are you sure?</h2>
                    <p class="popup-message">This action will delete all information about your account and cannot be recovered.</p>
                    <div class="button-container">
                        <button class="delete-btn">Delete</button>
                    </div>
                </div>
            </div>
        </form>
        <script src="./setting/setting.js"></script>
        <script>
                        function uploadFile() {
                            document.getElementById('uploadForm').submit();
                        }
        </script>
        <script>
            window.onload = function () {
                const urlParams = new URLSearchParams(window.location.search);
                if (urlParams.has("uploaded")) {
                    window.location.href = "setting";
                }
            };
        </script>
        <script>
            function showUsernamePopup() {
                document.getElementById("usernamePopup").style.display = "block";
            }

            function hideUsernamePopup() {
                document.getElementById("usernamePopup").style.display = "none";
            }
        </script>
        <script>
            function showChangePasswordPopup() {
                document.getElementById("changePasswordPopup").style.display = "flex";
                document.getElementById("errorMessage").style.display = "none"; // Ẩn thông báo lỗi khi mở popup
            }

            function hideChangePasswordPopup() {
                document.getElementById("changePasswordPopup").style.display = "none";
                document.getElementById("changePasswordForm").reset(); // Xóa dữ liệu form khi đóng
            }

            // Kiểm tra xác nhận mật khẩu trước khi submit
            document.getElementById("changePasswordForm").onsubmit = function (event) {
                const newPassword = document.getElementById("newPassword").value;
                const confirmPassword = document.getElementById("confirmPassword").value;
                const errorMessage = document.getElementById("errorMessage");

                if (newPassword !== confirmPassword) {
                    event.preventDefault();
                    errorMessage.textContent = "New password and confirmation do not match!";
                    errorMessage.style.display = "block";
                    return false;
                }
            };

            function showDeleteAccountPopup() {
                document.getElementById("deleteAccountPopup").style.display = "flex";
            }

            function hideDeleteAccountPopup() {
                document.getElementById("deleteAccountPopup").style.display = "none";
            }

            function deleteAccount() {
                // Gửi yêu cầu xóa tài khoản (có thể gọi servlet)
                window.location.href = "deleteaccount"; // Giả sử bạn có servlet để xử lý xóa tài khoản
            }
        </script>
    </body>
</html>