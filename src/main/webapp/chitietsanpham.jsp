<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chi tiết sản phẩm</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
          integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <script src="main.js"></script>
    <style>
        .cart-icon {

            text-decoration: none;
        }

        .cart-text {
            position: fixed;
            top: 50px;
            display: block; /* Hiển thị chữ "Giỏ hàng của bạn" dưới icon */
            text-align: center; /* Căn giữa văn bản */
            font-size: 1.1rem; /* Kích thước chữ */
            margin-top: 5px; /* Khoảng cách với icon */
            right: 20px;
            color: black;
        }

        .fa-shopping-cart {
            position: fixed;
            top: 20px;
            right: 60px;
            cursor: pointer;
            font-size: 2.5rem; /* Điều chỉnh kích thước theo ý muốn */
            color: black;
            display: block; /* Hiển thị icon dưới dạng khối để căn giữa */
            margin: 0 auto;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <h2>Chi tiết sản phẩm</h2>
    <a href="cart.jsp" class="cart-icon">

        <i class="fas fa-shopping-cart"></i>
        <span class="cart-text">Giỏ hàng của bạn</span>
    </a>

    <div class="card">
        <div class="card-body">
            <h5 class="card-title">${sanPham.tenSanPham}</h5>
            <p class="card-text">Giá bán: ${sanPham.giaBan} VNĐ</p>
            <p class="card-text">Tác giả: ${sanPham.tacGia.hoVaTen}</p>
            <p class="card-text">Thể loại: ${sanPham.theLoai.tenTheLoai}</p>
            <p class="card-text">Năm xuất bản: ${sanPham.namXuatBan}</p>
            <p class="card-text">Mô tả: ${sanPham.moTa}</p>

            <div class="row align-items-center">

                </div>
                <div class="col-md-3">
                    <div class="input-group">
                        <a href="index.jsp" class="btn btn-primary me-2">Quay lại</a>

                        <button type="button" class="btn btn-primary" onclick="addToCart('${sanPham.maSanPham}')">Thêm vào giỏ hàng</button>
                        <input type="number" id="quantity-${sanPham.maSanPham}" class="form-control" name="quantity" value="1" min="1">
                    </div>
                </div>

            </div>

        </div>
    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
