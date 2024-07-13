<%@ page import="model.KhachHang" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bookstore</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js"
            integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT"
            crossorigin="anonymous"></script>
    <style>
        .message {
            font-size: 24px;
            text-align: center;
            margin-top: 20px;
        }
        .success {
            color: green;
        }
        .error {
            color: red;
        }
    </style>
</head>
<body>
<%
    Object obj = session.getAttribute("khachHang");
    KhachHang khachHang = null;
    if (obj != null)
        khachHang = (KhachHang) obj;
    if (khachHang == null) {
%>
<h1>Bạn chưa đăng nhập vào hệ thống. Vui lòng quay lại trang chủ!</h1>
<%
} else {
    String baoLoi = request.getAttribute("baoLoi") + "";
    if (baoLoi.equals("null")) {
        baoLoi = "";
    }
    boolean thayDoiThanhCong = false;
    Object thayDoiThanhCongObj = request.getAttribute("thayDoiThanhCong");
    if (thayDoiThanhCongObj != null) {
        thayDoiThanhCong = (boolean) thayDoiThanhCongObj;
    }
%>
<div class="container">
    <h1>ĐỔI MẬT KHẨU</h1>
    <c:if test="${!empty baoLoi}">
        <span class="message <%= thayDoiThanhCong ? "success" : "error" %>">
            <%= baoLoi %>
        </span>
        <br>
        <c:if test="${thayDoiThanhCong}">
            <a href="index.jsp" class="btn btn-primary mt-3">Quay về trang chủ</a>
        </c:if>
    </c:if>
    <form action="doi-mat-khau" method="POST">
        <div class="mb-3">
            <label for="matKhauHienTai" class="form-label">Mật khẩu hiện tại</label>
            <input type="password" class="form-control" id="matKhauHienTai" name="matKhauHienTai">
        </div>
        <div class="mb-3">
            <label for="matKhauMoi" class="form-label">Mật khẩu mới</label>
            <input type="password" class="form-control" id="matKhauMoi" name="matKhauMoi">
        </div>
        <div class="mb-3">
            <label for="matKhauMoiNhapLai" class="form-label">Nhập lại mật khẩu mới</label>
            <input type="password" class="form-control" id="matKhauMoiNhapLai" name="matKhauMoiNhapLai">
        </div>
        <button type="submit" class="btn btn-primary">Lưu mật khẩu</button>
    </form>
</div>
<% } %>
</body>
</html>
