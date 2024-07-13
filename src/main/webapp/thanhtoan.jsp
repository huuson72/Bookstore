<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.TimeZone" %>
<%@ page import="java.util.Date" %>

<%--
  Created by IntelliJ IDEA.
  User: hzson72
  Date: 7/10/2024
  Time: 11:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: hzson72
  Date: 7/10/2024
  Time: 11:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: hzson72
  Date: 7/10/2024
  Time: 11:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: hzson72
  Date: 7/10/2024
  Time: 11:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form action="vnpay_payment" method="post">

    <div class="form-group">
        <label for="amount">Số tiền (VND):</label>
        <input type="text" class="form-control" id="amount" name="amount" required>
    </div>
    <div class="form-group">
        <label for="orderDescription">Mô tả thanh toán:</label>
        <input type="text" class="form-control" id="orderDescription" name="orderDescription" required>
    </div>
    <div class="form-group">
        <label for="bankCode">Mã ngân hàng:</label>
        <input type="text" class="form-control" id="bankCode" name="bankCode">
    </div>
    <div class="form-group">
        <label for="language">Ngôn ngữ:</label>
        <input type="text" class="form-control" id="language" name="language">
    </div>
    <div class="form-group">
        <label for="orderInfo">Thông tin hóa đơn:</label>
        <input type="text" class="form-control" id="orderInfo" name="orderInfo" required>
    </div>
    <button type="submit" class="btn btn-primary">Thanh toán</button>
</form>
