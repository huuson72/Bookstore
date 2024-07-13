<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String fullname = request.getParameter("fullname");
    String email = request.getParameter("email");
    String phone = request.getParameter("phone");
    String total = request.getParameter("total");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Xác nhận thanh toán</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<div class="container mt-4">
    <h1>Xác nhận thanh toán</h1>
    <div class="alert alert-success" role="alert">
        <h4 class="alert-heading">Cảm ơn bạn, <%= fullname %>!</h4>
        <p>Đơn hàng của bạn đã được thanh toán thành công.</p>
        <hr>
        <p class="mb-0">Chi tiết đơn hàng đã được gửi tới email: <%= email %></p>
        <p class="mb-0">Số điện thoại: <%= phone %></p>
        <p class="mb-0">Tổng tiền: <%= total %> VND</p>
    </div>
    <a href="index.jsp" class="btn btn-primary">Quay về trang chủ</a>
</div>
</body>
</html>
