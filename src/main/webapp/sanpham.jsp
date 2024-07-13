<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách Sách</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.3.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <!-- Custom CSS styles -->
    <style>
        /* Custom styles here */
    </style>
</head>
<body>
<div class="container">
    <h1>Danh sách Sách</h1>
    <div class="row">
        <!-- Loop through products and display each -->
        <c:forEach var="sanpham" items="${listSanPham}">
            <div class="col-md-4 mb-3">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">${sanpham.tenSanPham}</h5>
                        <p class="card-text">${sanpham.moTa}</p>
                        <p class="card-text">Giá: ${sanpham.giaBan}</p>
                        <div class="input-group">
                            <input type="number" id="quantity-${sanpham.maSanPham}" class="form-control" name="quantity" value="1" min="1">
                            <div class="input-group-append">
                                <button type="button" class="btn btn-primary" onclick="addToCart('${sanpham.maSanPham}')">Thêm vào giỏ hàng</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/5.3.3/js/bootstrap.bundle.min.js"
        integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8sh/jYSdxKRYc4BRFbIUJvOhZhNPs/Qt9PNIQ8"
        crossorigin="anonymous"></script>
</body>
</html>
