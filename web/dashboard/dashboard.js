//
//
//// Lấy thẻ canvas
//var ctx = document.getElementById('revenueChart').getContext('2d');
//
//var summaryData = {
//    today: { revenue: "$500", users: "10", quizzes: "5", transactions: "3" },
//    week: { revenue: "$5000", users: "120", quizzes: "45", transactions: "25" },
//    month: { revenue: "$20,000", users: "500", quizzes: "200", transactions: "100" },
//    year: { revenue: "$250,000", users: "6000", quizzes: "2500", transactions: "1300" }
//};
//
//var chartData = {
//    today: { labels: ['0h', '6h', '12h', '18h', '24h'], data: [5, 6, 3, 0, 4] },
//    week: { labels: ['T2', 'T3', 'T4', 'T5', 'T6', 'T7', 'CN'], data: [500, 700, 800, 650, 900, 0, 0] },
//    month: { labels: ['Tuần 1', 'Tuần 2', 'Tuần 3', 'Tuần 4'], data: [4500, 5200, 5800, 6200] },
//    year: { labels: ['Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4'], data: [1500, 1800, 2000, 2500] }
//};
//
//var revenueChart = new Chart(ctx, {
//    type: 'line',
//    data: {
//        labels: chartData.week.labels,
//        datasets: [{
//            label: 'Revenue ($)',
//            data: chartData.week.data,
//            borderColor: '#640D5F',
//            backgroundColor: 'rgba(255, 188, 251, 0.5)',
//            fill: true,
//            tension: 0.4
//        }]
//    },
//    options: {
//        responsive: true,
//        plugins: {
//            legend: { display: true }
//        }
//    }
//});

function updateSummary() {
    var timeRange = document.getElementById("summaryFilter").value;
    document.getElementById("revenue").innerText = summaryData[timeRange].revenue;
    document.getElementById("users").innerText = summaryData[timeRange].users;
    document.getElementById("quizzes").innerText = summaryData[timeRange].quizzes;
    document.getElementById("transactions").innerText = summaryData[timeRange].transactions;
}

function updateChart() {
    var timeRange = document.getElementById("chartFilter").value;
    revenueChart.data.labels = chartData[timeRange].labels;
    revenueChart.data.datasets[0].data = chartData[timeRange].data;
    revenueChart.update();
}

// Trạng thái sắp xếp và dữ liệu gốc
var sortStates = {}; 
var originalOrder = []; 

document.addEventListener("DOMContentLoaded", function () {
    let table = document.querySelector("tbody");

    // Lưu trữ thứ tự ban đầu của bảng
    originalOrder = Array.from(table.querySelectorAll("tr.user-row")).map(row => {
        let id = row.id.split("-")[1];
        return {
            row: row.cloneNode(true),
            detailsRow: document.getElementById("details-" + id)?.cloneNode(true) || null
        };
    });

    attachDropdownEvents(); // Gán sự kiện dropdown ban đầu
});

// Hàm Toggle hiển thị chi tiết user
function toggleDetails(id) {
    const detailsRow = document.getElementById("details-" + id);
    const row = document.getElementById("row-" + id);
    const icon = document.getElementById("icon-" + id);

    if (!detailsRow) {
        console.error("❌ Không tìm thấy hàng chi tiết:", `details-${id}`);
        return;
    }

    if (detailsRow.style.display === "table-row") {
        detailsRow.style.display = "none";
        row.classList.remove("selected");
        icon.classList.remove("rotate");
    } else {
        document.querySelectorAll(".user-details").forEach(el => el.style.display = "none");
        document.querySelectorAll(".user-row").forEach(el => el.classList.remove("selected"));
        document.querySelectorAll(".dropdown-icon").forEach(el => el.classList.remove("rotate"));

        detailsRow.style.display = "table-row";
        row.classList.add("selected");
        icon.classList.add("rotate");
    }
}

// Khôi phục bảng về trạng thái ban đầu
function resetTable() {
    let table = document.querySelector("tbody");
    table.innerHTML = "";

    originalOrder.forEach(({ row, detailsRow }) => {
        let id = row.id.split("-")[1];
        let clonedRow = row.cloneNode(true);
        let clonedDetailsRow = detailsRow ? detailsRow.cloneNode(true) : null;

        table.appendChild(clonedRow);
        if (clonedDetailsRow) {
            table.appendChild(clonedDetailsRow);
        }
    });

    attachDropdownEvents(); // Gán lại sự kiện dropdown sau khi reset
}

// Hàm sắp xếp bảng
function sortTable(colIndex) {
    let table = document.querySelector("tbody");
    let rows = Array.from(table.querySelectorAll("tr.user-row"));
    let icon = document.getElementById(`icon-${colIndex}`);

    // Xác định trạng thái sắp xếp
    if (!sortStates[colIndex]) {
        sortStates[colIndex] = "asc";
    } else if (sortStates[colIndex] === "asc") {
        sortStates[colIndex] = "desc";
    } else {
        sortStates[colIndex] = null;
    }

    // Reset icon trước khi cập nhật icon mới
    document.querySelectorAll(".fa-sort").forEach(el => el.classList.remove("fa-sort-up", "fa-sort-down"));
    if (sortStates[colIndex] === "asc") {
        icon.classList.add("fa-sort-up");
    } else if (sortStates[colIndex] === "desc") {
        icon.classList.add("fa-sort-down");
    }

    if (!sortStates[colIndex]) {
        resetTable();
        return;
    }

    // 🔥 Gom hàng chính và hàng chi tiết thành một cặp
    let rowPairs = rows.map(row => {
        let id = row.id.split("-")[1];
        let detailsRow = document.getElementById("details-" + id);
        return [row, detailsRow];
    });

    // Sắp xếp theo cột được chọn
    rowPairs.sort((a, b) => {
        let aText = a[0].children[colIndex].innerText.trim().toLowerCase();
        let bText = b[0].children[colIndex].innerText.trim().toLowerCase();
        return sortStates[colIndex] === "asc"
            ? aText.localeCompare(bText, undefined, { numeric: true })
            : bText.localeCompare(aText, undefined, { numeric: true });
    });

    // Xóa bảng cũ và thêm lại các cặp hàng đã sắp xếp
    table.innerHTML = "";
    rowPairs.forEach(([row, detailsRow]) => {
        table.appendChild(row);
        if (detailsRow) {
            table.appendChild(detailsRow);
        }
    });

    // Gán lại sự kiện dropdown sau khi sort
    attachDropdownEvents();
}

// Gán lại sự kiện dropdown sau khi sort
function attachDropdownEvents() {
    document.querySelectorAll(".dropdown-icon").forEach(icon => {
        icon.onclick = function () {
            let id = this.getAttribute("data-id");
            if (!id) {
                console.error("Dropdown ID không hợp lệ!");
                return;
            }
            toggleDetails(id);
        };
    });
}

document.addEventListener("DOMContentLoaded", function () {
    document.querySelectorAll(".select-active").forEach(select => {
        changeRowColor(select); 
        select.addEventListener("change", function () {
            changeRowColor(this);
        });
    });
});

function changeRowColor(selectElement) {
    let value = selectElement.value.trim();

    if (value === "Active") {
        selectElement.style.backgroundColor = "";
        selectElement.style.color = "";
    } else if (value === "Ban") {
        selectElement.style.backgroundColor = "#C62300";
        selectElement.style.color = "#fff";
    }
}

document.addEventListener("DOMContentLoaded", function () {
    let searchInput = document.getElementById("search");

    searchInput.addEventListener("input", function () {
        let filter = this.value.trim().toLowerCase();
        let rows = document.querySelectorAll("tr.user-row");

        rows.forEach(row => {
            let username = row.children[1].innerText.toLowerCase(); // Cột username
            let email = row.children[2].innerText.toLowerCase();    // Cột email

            if (username.includes(filter) || email.includes(filter)) {
                row.style.display = ""; // Hiển thị nếu khớp
            } else {
                row.style.display = "none"; // Ẩn nếu không khớp
            }
        });
    });
});

//-----------------Phân trang
document.addEventListener("DOMContentLoaded", function () {
    let table = document.querySelector("tbody");
    let rows = Array.from(table.querySelectorAll("tr.user-row"));
    let rowsPerPage = 10; // Mặc định 10 hàng trên mỗi trang
    let currentPage = 1;
    let totalPages = Math.ceil(rows.length / rowsPerPage);
    let paginationContainer = document.getElementById("page-numbers");

    function displayPage(page) {
        let start = (page - 1) * rowsPerPage;
        let end = start + rowsPerPage;

        rows.forEach((row, index) => {
            row.style.display = index >= start && index < end ? "table-row" : "none";
        });

        document.querySelector(".pagination-text").textContent = 
            `Showing ${start + 1} to ${Math.min(end, rows.length)} of ${rows.length} entries`;

        updatePaginationButtons();
        generatePageNumbers();
    }

    function updatePaginationButtons() {
        let prevBtn = document.getElementById("prev-page");
        let nextBtn = document.getElementById("next-page");

        prevBtn.classList.toggle("disabled", currentPage === 1);
        nextBtn.classList.toggle("disabled", currentPage === totalPages);
    }

    function generatePageNumbers() {
        paginationContainer.innerHTML = "";
        for (let i = 1; i <= totalPages; i++) {
            let pageBtn = document.createElement("button");
            pageBtn.textContent = i;
            pageBtn.classList.add("page-btn");
            if (i === currentPage) pageBtn.classList.add("active");

            pageBtn.addEventListener("click", function () {
                currentPage = i;
                displayPage(currentPage);
            });

            paginationContainer.appendChild(pageBtn);
        }
    }

    document.getElementById("prev-page").addEventListener("click", function () {
        if (currentPage > 1) {
            currentPage--;
            displayPage(currentPage);
        }
    });

    document.getElementById("next-page").addEventListener("click", function () {
        if (currentPage < totalPages) {
            currentPage++;
            displayPage(currentPage);
        }
    });

    document.querySelector(".pagination-options select").addEventListener("change", function (event) {
        rowsPerPage = parseInt(event.target.value);
        totalPages = Math.ceil(rows.length / rowsPerPage);
        currentPage = 1;
        displayPage(currentPage);
    });

    displayPage(currentPage);
});

