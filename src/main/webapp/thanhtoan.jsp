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
<form action="VNPayServlet" method="post">
    <label for="amount">Số tiền:</label>
    <input type="text" id="amount" name="amount" value="10000" />

    <label for="orderId">Mã đơn hàng:</label>
    <input type="text" id="orderId" name="orderId" value="123456" />

    <input type="hidden" name="orderType" value="billpayment" />

    <button type="submit">Thanh toán qua VNPay</button>
</form>
