<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Dashboard | Package</title>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <link rel="stylesheet" href="./dashboard/dashboard.css">
        <link rel="shortcut icon" href="./images/logo/Easyquiz_logo.png">
        <link rel="stylesheet"
              href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <style>
            .modal {
                display: none;
                position: fixed;
                z-index: 1;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                overflow: auto;
                background-color: rgba(0, 0, 0, 0.4);
            }

            .modal-content {
                background-color: #fefefe;
                margin: 15% auto;
                padding: 20px;
                border: 1px solid #888;
                border-radius: 8px;
                width: 50%;
            }

            .modal-header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 20px;
            }

            .close {
                color: #aaa;
                font-size: 28px;
                font-weight: bold;
            }

            .close:hover,
            .close:focus {
                color: black;
                text-decoration: none;
                cursor: pointer;
            }

            .save-btn {
                background-color: #007f00;
                color: #fff;
                padding: 5px 15px;
                border-style: none;
                border-radius: 20px;
                cursor: pointer;
                font-weight: bold;
                font-size: 14px;
                margin-right: 5px;
            }

            .save-btn:hover {
                background-color: #314d18;
            }

            .cancel-btn {
                background-color: #C62300;
                color: #fff;
                padding: 5px 15px;
                border-style: none;
                border-radius: 20px;
                cursor: pointer;
                font-weight: bold;
                font-size: 14px;
                margin-right: 5px;
            }

            .cancel-btn:hover {
                background-color: #831901;
            }

            /* C?n ch?nh form */
            .form-group {
                display: flex;
                align-items: center; /* Canh label và input th?ng hàng */
                margin-bottom: 10px; /* T?o kho?ng cách gi?a các dòng */
            }

            .form-group label {
                width: 30%; /* ?i?u ch?nh ?? r?ng c?a label */
                font-weight: 500;
                margin-right: 10px;
            }

            .form-group input {
                flex: 1; /* Input m? r?ng h?t ph?n còn l?i */
                padding: 8px;
                border: 1px solid #ccc;
                border-radius: 5px;
                font-size: 14px;
            }

            /* Canh ch?nh nút Save và Cancel */
            .button-group {
                display: flex;
                justify-content: flex-end;
                gap: 10px; /* Kho?ng cách gi?a các nút */
                margin-top: 15px;
            }
        </style>
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
                        <div class="new-package">
                            <button id="newPackageBtn" style="font-size: 14px; font-weight: bold;"><i
                                    class="fa-solid fa-plus"></i> New package</button>
                        </div>
                    </div>
                </div>
                <div class="user-table">
                    <table id="packageTable">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name of package</th>
                                <th>Value</th>
                                <th>Price</th>
                                <th>Description</th>
                                <th style="text-align: center;">Status</th>
                                <th style="text-align: center;">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.packages}" var="p" varStatus="status">
                                <tr class="user-row ${status.index % 2 == 0 ? 'even' : 'odd'}" id="row-${status.index}">
                                    <td>${p.id}</td>
                                    <td>${p.name}</td>
                                    <td>${p.value} days</td>
                                    <td>${p.price} VND</td>
                                    <td>${p.description}</td>
                                    <td style="text-align: center;">
                                        <select onchange="changeRowColor(this)" class="select-active">
                                            <option value="Active" selected>Active</option>
                                            <option value="Ban">Ban</option>
                                        </select>
                                    </td>
                                    <td style="text-align: center;">
                                        <button class="edit-btn">Edit</button>
                                        <!--                                        <button class="delete-btn">Delete</button>-->
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
        <div id="newPackageModal" class="modal">
            <div class="modal-content">
                <div class="modal-header">
                    <h2>New Package</h2>
                    <span class="close material-symbols-rounded">close</span>
                </div>
                <div class="form-group">
                    <label for="packageName">Package Name:</label>
                    <input type="text" id="packageName" name="packageName">
                </div>

                <div class="form-group">
                    <label for="packageValue">Package Duration:</label>
                    <input type="text" id="packageValue" name="packageValue">
                </div>

                <div class="form-group">
                    <label for="packagePrice">Package Price:</label>
                    <input type="text" id="packagePrice" name="packagePrice">
                </div>

                <div class="form-group">
                    <label for="packageDescription">Package Description:</label>
                    <input type="text" id="packageDescription" name="packageDescription">
                </div>

                <div class="button-group">
                    <button id="modalSaveBtn" class="save-btn">Save</button>
                    <button id="modalCancelBtn" class="cancel-btn">Cancel</button>
                </div>
            </div>
        </div>

        <script>
            const newPackageBtn = document.getElementById('newPackageBtn');
            const modal = document.getElementById('newPackageModal');
            const span = document.getElementsByClassName("close")[0];
            const modalSaveBtn = document.getElementById('modalSaveBtn');
            const modalCancelBtn = document.getElementById('modalCancelBtn');
            const packageTable = document.getElementById('packageTable');

            newPackageBtn.addEventListener('click', () => {
                modal.style.display = "block";
            });

            span.addEventListener('click', () => {
                modal.style.display = "none";
            });

            modalCancelBtn.addEventListener('click', () => {
                modal.style.display = "none";
            });

            modalSaveBtn.addEventListener('click', () => {
                const packageName = document.getElementById('packageName').value;
                const packageValue = document.getElementById('packageValue').value;
                const packagePrice = document.getElementById('packagePrice').value;

                if (packageName && packageValue && packagePrice) {
                    const newRow = packageTable.insertRow(-1);
                    const idCell = newRow.insertCell();
                    const nameCell = newRow.insertCell();
                    const valueCell = newRow.insertCell();
                    const priceCell = newRow.insertCell();
                    const actionsCell = newRow.insertCell();

                    const nextId = packageTable.rows.length - 1;

                    idCell.textContent = nextId;
                    nameCell.textContent = packageName;
                    valueCell.textContent = packageValue;
                    priceCell.textContent = packagePrice;
                    actionsCell.innerHTML = `
                    <button class="edit-btn">Edit</button>
                    <button class="delete-btn">Delete</button>
                `;

                    modal.style.display = "none";

                    document.getElementById('packageName').value = '';
                    document.getElementById('packageValue').value = '';
                    document.getElementById('packagePrice').value = '';
                } else {
                    alert('Please fill in all fields.');
                }
            });
        </script>
        <script src="./dashboard/dashboard.js"></script>
    </body>

</html>