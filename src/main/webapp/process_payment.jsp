<%--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>--%>
<%--<%@ page import="java.util.Map"%>--%>
<%--<%@ page import="database.SanPhamDAO"%>--%>
<%--<%@ page import="model.SanPham"%>--%>
<%--<%@ page import="java.text.DecimalFormat" %>--%>
<%--<%--%>
<%--    String fullname = request.getParameter("fullname");--%>
<%--    String email = request.getParameter("email");--%>
<%--    String phone = request.getParameter("phone");--%>

<%--    Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("cart");--%>
<%--    SanPhamDAO sanPhamDAO = new SanPhamDAO();--%>

<%--    double total = 0;--%>
<%--    DecimalFormat df = new DecimalFormat("#,###.##");--%>
<%--    if (cart != null && !cart.isEmpty()) {--%>
<%--        for (Map.Entry<String, Integer> entry : cart.entrySet()) {--%>
<%--            String maSanPham = entry.getKey();--%>
<%--            SanPham sanPham = sanPhamDAO.selectById(new SanPham(maSanPham, null, null, 0, 0, 0, 0, 0, null, null, null));--%>
<%--            if (sanPham != null) {--%>
<%--                int quantity = entry.getValue();--%>
<%--                double price = sanPham.getGiaBan();--%>
<%--                double subtotal = price * quantity;--%>
<%--                total += subtotal;--%>
<%--            }--%>
<%--        }--%>
<%--    }--%>

<%--    // Thanh toán thành công, xóa giỏ hàng--%>
<%--    session.removeAttribute("cart");--%>

<%--    // Chuyển hướng tới trang confirm_payment.jsp--%>
<%--    response.sendRedirect("confirm_payment.jsp?fullname=" + fullname + "&email=" + email + "&phone=" + phone + "&total=" + df.format(total));--%>
<%--%>--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="database.SanPhamDAO"%>
<%@ page import="model.SanPham"%>
<%@ page import="java.text.DecimalFormat" %>

<%
    Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("cart");
    SanPhamDAO sanPhamDAO = new SanPhamDAO();
    DecimalFormat df = new DecimalFormat("#,###.##");
    double total = 0;
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thanh toán VNPay</title>
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
    <a href="thanhtoan.jsp" class="btn btn-primary mt-3">Thanh toán</a>
    <a href="index.jsp" class="btn btn-primary mt-3">Tiếp tục mua sắm</a>
</div>
</body>
</html>
