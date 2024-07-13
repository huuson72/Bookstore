package controller;


import util.VnPayUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/VnPayReturnServlet")
public class VnPayReturnServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> fields = new HashMap<>();
        for (Enumeration<String> params = request.getParameterNames(); params.hasMoreElements();) {
            String paramName = params.nextElement();
            fields.put(paramName, request.getParameter(paramName));
        }

        String secureHash = fields.remove("vnp_SecureHash");
        String vnp_HashSecret = "2ANZRIFQZ4DYGC47N7VYPYF6FKD8G4TP";

        if (VnPayUtils.validateChecksum(fields, vnp_HashSecret, secureHash)) {
            // Xử lý kết quả giao dịch tại đây
            String responseCode = fields.get("vnp_ResponseCode");
            if ("00".equals(responseCode)) {
                // Giao dịch thành công
            } else {
                // Giao dịch thất bại
            }
        } else {
            // Chữ ký không hợp lệ
        }
    }
}
