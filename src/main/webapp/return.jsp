<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="controller.VNPayReturnServlet"%>
<%@ page import="util.VNPayConfig"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Xử lý kết quả thanh toán</title>
</head>
<body>
<h1>Xử lý kết quả thanh toán</h1>
<%-- Lấy thông tin kết quả từ VNPay --%>
<%
    VNPayReturnServlet returnServlet = new VNPayReturnServlet();
    returnServlet.doGet(request, response);
%>
</body>
</html>
