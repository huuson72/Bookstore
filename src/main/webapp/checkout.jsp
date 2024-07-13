<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="database.SanPhamDAO"%>
<%@ page import="model.SanPham"%>
<%@ page import="java.text.DecimalFormat" %>

<%
    Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("cart");
    SanPhamDAO sanPhamDAO = new SanPhamDAO();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thanh toán</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<div class="container mt-4">
    <h1>Thông tin đơn hàng</h1>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Sản phẩm</th>
            <th>Số lượng</th>
            <th>Đơn giá</th>
            <th>Thành tiền</th>
        </tr>
        </thead>
        <tbody>
        <%
            if (cart != null && !cart.isEmpty()) {
                double total = 0;
                DecimalFormat df = new DecimalFormat("#,###.##");
                for (Map.Entry<String, Integer> entry : cart.entrySet()) {
                    String maSanPham = entry.getKey();
                    SanPham sanPham = sanPhamDAO.selectById(new SanPham(maSanPham, null, null, 0, 0, 0, 0, 0, null, null, null));
                    if (sanPham != null) {
                        int quantity = entry.getValue();
                        double price = sanPham.getGiaBan();
                        double subtotal = price * quantity;
                        total += subtotal;
        %>
        <tr>
            <td><%= sanPham.getTenSanPham() %></td>
            <td><%= quantity %></td>
            <td><%= df.format(price) %></td>
            <td><%= df.format(subtotal) %></td>
        </tr>
        <%
                }
            }
        %>
        <tr>
            <td colspan="3" class="text-right">Tổng cộng:</td>
            <td><%= df.format(total) %></td>
        </tr>
        <% } else { %>
        <tr>
            <td colspan="4" class="text-center">Giỏ hàng trống</td>
        </tr>
        <% } %>
        </tbody>
    </table>
    <h2>Thông tin thanh toán</h2>
    <form action="process_payment.jsp" method="post">
        <div class="mb-3">
            <label for="fullname" class="form-label">Họ và tên:</label>
            <input type="text" class="form-control" id="fullname" name="fullname" required>
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Email:</label>
            <input type="email" class="form-control" id="email" name="email" required>
        </div>
        <div class="mb-3">
            <label for="phone" class="form-label">Số điện thoại:</label>
            <input type="tel" class="form-control" id="phone" name="phone" required>
        </div>
        <button type="submit" class="btn btn-success">Xác nhận thanh toán</button>
    </form>
    <a href="index.jsp" class="btn btn-primary mt-3">Tiếp tục mua sắm</a>
</div>
</body>
</html>
