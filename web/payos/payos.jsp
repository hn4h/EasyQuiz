<%-- 
    Document   : payos
    Created on : Feb 23, 2025, 3:20:34 PM
    Author     : 11
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Tạo Link thanh toán</title>
    <link rel="stylesheet" href="style.css" />
    <link rel="shortcut icon" href="./images/logo/Easyquiz_logo.png">
  </head>
  <body>
    <div class="main-box">
      <div class="checkout">
        <div class="product">
          <p><strong>Tên sản phẩm:</strong> Trial Package</p>
          <p><strong>Giá tiền:</strong> 2000 VNĐ</p>
          <p><strong>Số lượng:</strong> 1</p>
        </div>

        <form action="pay" method="post">
          <button type="submit" id="create-payment-link-btn">
            Tạo Link thanh toán
          </button>
        </form>
      </div>
    </div>
  </body>
</html>
