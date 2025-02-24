

// Lấy thẻ canvas
var ctx = document.getElementById('revenueChart').getContext('2d');

var summaryData = {
    today: { revenue: "$500", users: "10", quizzes: "5", transactions: "3" },
    week: { revenue: "$5000", users: "120", quizzes: "45", transactions: "25" },
    month: { revenue: "$20,000", users: "500", quizzes: "200", transactions: "100" },
    year: { revenue: "$250,000", users: "6000", quizzes: "2500", transactions: "1300" }
};

var chartData = {
    today: { labels: ['0h', '6h', '12h', '18h', '24h'], data: [5, 6, 3, 0, 4] },
    week: { labels: ['T2', 'T3', 'T4', 'T5', 'T6', 'T7', 'CN'], data: [500, 700, 800, 650, 900, 0, 0] },
    month: { labels: ['Tuần 1', 'Tuần 2', 'Tuần 3', 'Tuần 4'], data: [4500, 5200, 5800, 6200] },
    year: { labels: ['Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4'], data: [1500, 1800, 2000, 2500] }
};

var revenueChart = new Chart(ctx, {
    type: 'line',
    data: {
        labels: chartData.week.labels,
        datasets: [{
            label: 'Revenue ($)',
            data: chartData.week.data,
            borderColor: '#640D5F',
            backgroundColor: 'rgba(255, 188, 251, 0.5)',
            fill: true,
            tension: 0.4
        }]
    },
    options: {
        responsive: true,
        plugins: {
            legend: { display: true }
        }
    }
});

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

//User-dropdown-details
function toggleDetails(id) {
    const detailsRow = document.getElementById("details-" + id);
    const row = document.getElementById("row-" + id);
    const icon = document.getElementById("icon-" + id);

    if (detailsRow.style.display === "table-row") {
        detailsRow.style.display = "none";
        row.classList.remove("selected");
        icon.classList.remove("rotate");
    } else {
        // Ẩn tất cả các hàng chi tiết khác (chỉ mở 1 hàng duy nhất)
        document.querySelectorAll(".user-details").forEach((el) => el.style.display = "none");
        document.querySelectorAll(".user-row").forEach((el) => el.classList.remove("selected"));
        document.querySelectorAll(".dropdown-icon").forEach((el) => el.classList.remove("rotate"));

        // Hiển thị hàng chi tiết mới
        detailsRow.style.display = "table-row";
        row.classList.add("selected");
        icon.classList.add("rotate");
    }
}   



