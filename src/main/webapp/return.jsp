<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="util.VNPayUtils" %>

<html>
<head>
    <title>Kết quả thanh toán</title>
</head>
<body>
<%
    // Lấy các tham số trả về từ VNPay
    Map<String, String> vnp_Params = new HashMap<>();
    for (Map.Entry<String, String[]> param : request.getParameterMap().entrySet()) {
        vnp_Params.put(param.getKey(), param.getValue()[0]);
    }

    // Lấy chữ ký từ VNPay
    String vnp_SecureHash = request.getParameter("vnp_SecureHash");

    // Chuỗi bí mật tạo checksum (vnp_HashSecret)
    String vnp_HashSecret = "2ANZRIFQZ4DYGC47N7VYPYF6FKD8G4TP"; //

    // Xác thực chữ ký
    boolean checkSignature = VNPayUtils.validateSignature(vnp_Params, vnp_SecureHash, vnp_HashSecret);
    if (checkSignature) {
        // Giao dịch thành công
        String vnp_Amount = request.getParameter("vnp_Amount");
        String vnp_OrderInfo = request.getParameter("vnp_OrderInfo");
        System.out.println("<h2>Giao dịch thành công!</h2>");
        System.out.println("<p>Số tiền: " + vnp_Amount + "</p>");
        System.out.println("<p>Thông tin đơn hàng: " + vnp_OrderInfo + "</p>");
    } else {
        // Giao dịch thất bại do chữ ký không hợp lệ
        System.out.println("<h2>Giao dịch thất bại!</h2>");
        System.out.println("<p>Chữ ký không hợp lệ!</p>");
    }
%>
</body>
</html>
