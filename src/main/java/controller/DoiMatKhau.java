package controller;

import database.KhachHangDAO;
import model.KhachHang;
import util.maHoaMatKhau;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/doi-mat-khau")
public class DoiMatKhau extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String matKhauHienTai = request.getParameter("matKhauHienTai");
        String matKhauMoi = request.getParameter("matKhauMoi");
        String matKhauMoiNhapLai = request.getParameter("matKhauMoiNhapLai");

        String matKhauHienTai_Sha1 = maHoaMatKhau.toSHA1(matKhauHienTai);

        String baoLoi = "";
        String url = "/doimatkhau.jsp";

        // Kiểm tra mat khau cu co giong hay khong
        HttpSession session = request.getSession();
        KhachHang khachHang = (KhachHang) session.getAttribute("khachHang");

        if (khachHang == null) {
            baoLoi = "Bạn chưa đăng nhập vào hệ thống!";
        } else {
            if (!matKhauHienTai_Sha1.equals(khachHang.getMatKhau())) {
                baoLoi = "Mật khẩu hiện tại không chính xác!";
            } else if (!matKhauMoi.equals(matKhauMoiNhapLai)) {
                baoLoi = "Mật khẩu nhập lại không khớp!";
            } else {
                String matKhauMoi_Sha1 = maHoaMatKhau.toSHA1(matKhauMoi);
                khachHang.setMatKhau(matKhauMoi_Sha1);
                KhachHangDAO khachHangDAO = new KhachHangDAO();
                if (khachHangDAO.changePassword(khachHang)) {
                    baoLoi = "Mật khẩu đã thay đổi thành công!";
                    request.setAttribute("thayDoiThanhCong", true); // Thuộc tính để hiển thị thông báo thành công
//                    url = "/index.jsp"; // Không cần thiết khi không chuyển hướng ngay lập tức
                } else {
                    baoLoi = "Quá trình thay đổi mật khẩu không thành công!";
                }
            }
        }

        request.setAttribute("baoLoi", baoLoi);
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }
}
