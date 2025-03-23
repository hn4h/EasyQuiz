
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
                ? aText.localeCompare(bText, undefined, {numeric: true})
                : bText.localeCompare(aText, undefined, {numeric: true});
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
    } else if (value === "Disable") {
        selectElement.style.backgroundColor = "#C62300";
        selectElement.style.color = "#fff";
    }
}

//-----------------Search
function searchTable() {
    let input = document.getElementById("search").value.toLowerCase();
    let rows = document.querySelectorAll("tbody tr.user-row");

    rows.forEach(row => {
        let cells = row.getElementsByTagName("td");
        let found = false;

        for (let cell of cells) {
            if (cell.textContent.toLowerCase().includes(input)) {
                found = true;
                break;
            }
        }

        row.style.display = found ? "table-row" : "none";
    });
}


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
            if (i === currentPage)
                pageBtn.classList.add("active");

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



//--------------------------Pop up edit package
document.querySelectorAll('.edit-btn').forEach(button => {
    button.addEventListener('click', function () {
        console.log("Nút Edit đã được nhấn!");
        // Lấy hàng chứa nút vừa click
        let row = this.closest('tr'); 

        // Lấy thông tin từ hàng
        let id = row.cells[0].textContent.trim();
        let name = row.cells[1].textContent.trim();
        let value = row.cells[2].textContent.trim();
        let price = row.cells[3].textContent.trim();
        let description = row.cells[4].textContent.trim();

        // Đổ dữ liệu vào modal
        document.getElementById("editPackageId").value = id;
        document.getElementById("editPackageName").value = name;
        document.getElementById("editPackageValue").value = value;
        document.getElementById("editPackagePrice").value = price;
        document.getElementById("editPackageDescription").value = description;

        // Hiển thị modal
        document.getElementById("editPackageModal").style.display = "block";
    });
});

document.querySelector('.close-edit').addEventListener('click', function () {
    document.getElementById("editPackageModal").style.display = "none";
});

//--------------------------Pop up new package
// Lấy modal và nút mở modal
const newPackageBtn = document.getElementById('newPackageBtn');
const modal = document.getElementById('newPackageModal');
const closeBtn = document.querySelector(".close");
const addPackageForm = document.getElementById("addPackageForm");

// Mở modal khi ấn nút "New Package"
newPackageBtn.addEventListener('click', () => {
    modal.style.display = "block";
});

// Đóng modal khi ấn nút "X"
closeBtn.addEventListener('click', () => {
    modal.style.display = "none";
});

// Xử lý khi submit form
addPackageForm.addEventListener('submit', (event) => {
    const packageName = document.getElementById('packageName').value.trim();
    const packageValue = document.getElementById('packageValue').value.trim();
    const packagePrice = document.getElementById('packagePrice').value.trim();
    const packageDescription = document.getElementById('packageDescription').value.trim();

    if (!packageName || !packageValue || !packagePrice || !packageDescription) {
        event.preventDefault(); // Ngăn chặn submit nếu có ô trống
        alert("Please fill in all fields.");
        return;
    }

    // Nếu tất cả hợp lệ, form sẽ được submit
});


