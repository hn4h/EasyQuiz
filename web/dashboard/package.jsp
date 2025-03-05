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
            width: 50%;
        }

        .close {
            color: #aaa;
            float: right;
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
            background-color: #5D8736;
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
                </div>
            </div>
            <div class="user-table">
                <table id="packageTable">
                    <thead>
                        <tr>
                            <th>ID <i class="fas fa-sort"></i></th>
                            <th>Name of package <i class="fas fa-sort"></i></th>
                            <th>Value <i class="fas fa-sort"></i></th>
                            <th>Price <i class="fas fa-sort"></i></th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>1</td>
                            <td>Monthly</td>
                            <td>30 days</td>
                            <td>49,000 VND</td>
                            <td>
                                <button class="edit-btn">Edit</button>
                                <button class="delete-btn">Delete</button>
                            </td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>Quarterly</td>
                            <td>90 days</td>
                            <td>129,000 VND</td>
                            <td>
                                <button class="edit-btn">Edit</button>
                                <button class="delete-btn">Delete</button>
                            </td>
                        </tr>
                        <tr>
                            <td>3</td>
                            <td>Annual</td>
                            <td>360 days</td>
                            <td>449,000 VND</td>
                            <td>
                                <button class="edit-btn">Edit</button>
                                <button class="delete-btn">Delete</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <div class="pagination">
                    <div>
                        <button id="newPackageBtn" style="font-size: 14px; font-weight: bold;"><i
                                class="fa-solid fa-plus"></i> New package</button>
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
    <div id="newPackageModal" class="modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <h2>New Package</h2>
            <label for="packageName">Package Name:</label><br>
            <input type="text" id="packageName" name="packageName"><br>
            <label for="packageValue">Package Duration:</label><br>
            <input type="text" id="packageValue" name="packageValue"><br>
            <label for="packagePrice">Package Price:</label><br>
            <input type="text" id="packagePrice" name="packagePrice"><br>
            <label for="packageDescription">Package Description:</label><br>
            <input type="text" id="packageDescription" name="packageDescription"><br><br>
            <button id="modalSaveBtn" class="save-btn">Save</button>
            <button id="modalCancelBtn" class="cancel-btn">Cancel</button>
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