package controller;


import util.VNPayUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "VNPayServlet", urlPatterns = {"/VNPayServlet"})
public class VNPayServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Lấy các tham số trả về từ VNPay
        Map<String, String> vnp_Params = new HashMap<>();
        for (Map.Entry<String, String[]> param : request.getParameterMap().entrySet()) {
            vnp_Params.put(param.getKey(), param.getValue()[0]);
        }

        // Lấy chữ ký từ VNPay
        String vnp_SecureHash = request.getParameter("vnp_SecureHash");

        // Chuỗi bí mật tạo checksum (vnp_HashSecret)
        String vnp_HashSecret = "2ANZRIFQZ4DYGC47N7VYPYF6FKD8G4TP"; // Thay bằng giá trị thực tế của bạn

        // Xác thực chữ ký
        boolean checkSignature = VNPayUtils.validateSignature(vnp_Params, vnp_SecureHash, vnp_HashSecret);
        if (checkSignature) {
            // Giao dịch thành công
            String vnp_Amount = request.getParameter("vnp_Amount");
            String vnp_OrderInfo = request.getParameter("vnp_OrderInfo");
            response.getWriter().println("<h2>Giao dịch thành công!</h2>");
            response.getWriter().println("<p>Số tiền: " + vnp_Amount + "</p>");
            response.getWriter().println("<p>Thông tin đơn hàng: " + vnp_OrderInfo + "</p>");
        } else {
            // Giao dịch thất bại do chữ ký không hợp lệ
            response.getWriter().println("<h2>Giao dịch thất bại!</h2>");
            response.getWriter().println("<p>Chữ ký không hợp lệ!</p>");
        }
    }
}
