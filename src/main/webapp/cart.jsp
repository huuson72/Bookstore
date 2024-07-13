<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="database.SanPhamDAO"%>
<%@ page import="model.SanPham"%>
<%
    Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("cart");
    SanPhamDAO sanPhamDAO = new SanPhamDAO();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Giỏ hàng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="main.js"></script>
</head>
<body>
<div class="container mt-4">
    <h1>Giỏ hàng của bạn</h1>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Sản phẩm</th>
            <th>Số lượng</th>
            <th>Giá</th>
            <th>Tổng</th>
        </tr>
        </thead>
        <tbody>
        <%
            if (cart != null && !cart.isEmpty()) {
                double total = 0;
                for (Map.Entry<String, Integer> entry : cart.entrySet()) {
                    String maSanPham = entry.getKey();
                    if (maSanPham != null) {
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
            <td><%= price %></td>
            <td><%= subtotal %></td>
            <td>
                <form action="XoaSanPham" method="post">
                    <input type="hidden" name="maSanPham" value="<%= maSanPham %>">
                    <button type="submit" class="btn btn-danger">Xóa</button>
                </form>
            </td>
        </tr>
        <%
        } else {
        %>
        <tr>
            <td colspan="4" class="text-center">Không tìm thấy sản phẩm với mã: <%= maSanPham %></td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="4" class="text-center">Mã sản phẩm là null</td>
        </tr>
        <%
                }
            }
        %>
        <tr>
            <td colspan="3" class="text-right">Tổng cộng:</td>
            <td><%= total %></td>
        </tr>
        <% } else { %>
        <tr>
            <td colspan="4" class="text-center">Giỏ hàng trống</td>
        </tr>
        <% } %>
        </tbody>
    </table>
    <a href="index.jsp" class="btn btn-primary">Tiếp tục mua sắm</a>
    <!-- Form để chuyển tới trang thanh toán -->
    <form action="checkout.jsp" method="post" class="mt-3">
        <button type="submit" class="btn btn-success">Thanh toán</button>
    </form>
</div>
</body>
</html>
