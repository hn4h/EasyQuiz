document.addEventListener("DOMContentLoaded", function () {
    // Lấy URL hiện tại
    let currentUrl = window.location.href;
    
    // Duyệt qua tất cả các mục menu
    document.querySelectorAll(".dashboard-item a").forEach(item => {
        // Nếu href của menu trùng với URL hiện tại thì thêm class active
        if (currentUrl.includes(item.getAttribute("href"))) {
            item.parentElement.classList.add("active");
        }
    });
});
 
 // Lấy thẻ canvas
 var ctx = document.getElementById('revenueChart').getContext('2d');

 // Dữ liệu mẫu
 var dataSets = {
     week: {
         labels: ['T2', 'T3', 'T4', 'T5', 'T6', 'T7', 'CN'],
         data: [500, 700, 800, 650, 900, 1200, 1500]
     },
     month: {
         labels: ['Tuần 1', 'Tuần 2', 'Tuần 3', 'Tuần 4'],
         data: [4500, 5200, 5800, 6200]
     },
     year: {
         labels: ['Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4', 'Tháng 5', 'Tháng 6', 'Tháng 7', 'Tháng 8', 'Tháng 9', 'Tháng 10', 'Tháng 11', 'Tháng 12'],
         data: [15000, 18000, 20000, 22000, 25000, 28000, 30000, 32000, 35000, 37000, 40000, 45000]
     }
 };

 // Vẽ biểu đồ mặc định (Tuần)
 var revenueChart = new Chart(ctx, {
     type: 'line', // Biểu đồ dạng đường (Area Chart)
     data: {
         labels: dataSets.week.labels,
         datasets: [{
             label: 'Doanh thu (VNĐ)',
             data: dataSets.week.data,
             borderColor: 'rgba(0, 123, 255, 1)',
             backgroundColor: 'rgba(0, 123, 255, 0.2)',
             fill: true,
             tension: 0.4 // Làm cong đường biểu đồ
         }]
     },
     options: {
         responsive: true,
         plugins: {
             legend: { display: true }
         }
     }
 });

 // Hàm cập nhật biểu đồ khi chọn thời gian
 function updateChart(timeRange) {
     revenueChart.data.labels = dataSets[timeRange].labels;
     revenueChart.data.datasets[0].data = dataSets[timeRange].data;
     revenueChart.update();
 }