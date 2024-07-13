<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Kết quả tìm kiếm</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2>Kết quả tìm kiếm</h2>

    <div class="row mt-4">
        <c:if test="${empty sanPhamList}">
            <p>Không tìm thấy sản phẩm nào phù hợp với yêu cầu.</p>
        </c:if>

        <c:forEach items="${sanPhamList}" var="sanPham">
            <div class="col-md-4 mb-4">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">${sanPham.tenSanPham}</h5>
                        <p class="card-text">Giá: ${sanPham.giaBan} VNĐ</p>
                        <a href="ChiTietSanPham?maSanPham=${sanPham.maSanPham}" class="btn btn-primary">Chi tiết</a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
