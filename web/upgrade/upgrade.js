document.addEventListener("DOMContentLoaded", function () {
    // Ẩn tất cả các total khi trang load
    function hideAllTotals() {
        document.getElementById("total-monthly").style.display = "none";
        document.getElementById("total-annual").style.display = "none";
        document.getElementById("total-unlimited").style.display = "none";
    }

    // Hàm cập nhật total khi chọn radio
    function updateTotal() {
        hideAllTotals(); // Ẩn tất cả trước khi hiển thị cái mới
        let selectedPlan = document.querySelector('input[name="plan"]:checked').id;
        document.getElementById(`total-${selectedPlan}`).style.display = "flex";
    }

    // Gán sự kiện khi thay đổi lựa chọn
    document.querySelectorAll('input[name="plan"]').forEach(radio => {
        radio.addEventListener("change", updateTotal);
    });

    // Gọi lần đầu để hiển thị đúng total ban đầu
    updateTotal();
});
