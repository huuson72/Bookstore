package controller;



import util.VnPayUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/vnpay_payment")
public class VnPayServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String vnp_TmnCode = "ROQNN7QK";
        String vnp_HashSecret = "2ANZRIFQZ4DYGC47N7VYPYF6FKD8G4TP";
        String vnp_Url = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";

        // Lấy các tham số từ form
        String amountStr = request.getParameter("amount");
        String orderDescription = request.getParameter("orderDescription");
        String bankCode = request.getParameter("bankCode");
        String language = request.getParameter("language");
        String orderInfo = request.getParameter("orderInfo");

        // Chuyển đổi số tiền sang đơn vị VNPay yêu cầu (VND * 100)
        int amount = Integer.parseInt(amountStr) * 100;

        // Khởi tạo các tham số VNPay
        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", "2.1.0"); // Đảm bảo phiên bản là 2.1.0
        vnp_Params.put("vnp_Command", "pay");
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_TxnRef", VnPayUtils.getRandomNumber(8));
        vnp_Params.put("vnp_OrderInfo", orderInfo);
        vnp_Params.put("vnp_OrderType", "other");
        vnp_Params.put("vnp_Locale", language != null && !language.isEmpty() ? language : "vn");
        vnp_Params.put("vnp_BankCode", bankCode != null && !bankCode.isEmpty() ? bankCode : "NCB");
        vnp_Params.put("vnp_CreateDate", VnPayUtils.getCurrentDate());

        // Tạo chữ ký
        String vnp_SecureHash = VnPayUtils.hashAllFields(vnp_Params, vnp_HashSecret);
        vnp_Params.put("vnp_SecureHash", vnp_SecureHash);

        // In các tham số để kiểm tra
        System.out.println("vnp_Params: " + vnp_Params);
        System.out.println("vnp_SecureHash: " + vnp_SecureHash);

        // Tạo URL thanh toán
        String paymentUrl = VnPayUtils.buildPaymentUrl(vnp_Url, vnp_Params);

        // Chuyển hướng người dùng đến trang thanh toán của VNPay
        response.sendRedirect(paymentUrl);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
