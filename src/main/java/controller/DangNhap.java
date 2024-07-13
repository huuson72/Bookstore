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

@WebServlet(name = "DangNhap", value = "/dang-nhap")
public class DangNhap extends HttpServlet {
    public DangNhap() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           response.getWriter().append("Server ar : ").append(request.getContextPath());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tenDangNhap = request.getParameter("tenDangNhap");
        String matKhau = request.getParameter("matKhau");
        matKhau = maHoaMatKhau.toSHA1(matKhau);
        KhachHang kh = new KhachHang();
        kh.setTenDangNhap(tenDangNhap);
        kh.setMatKhau(matKhau);

        KhachHangDAO khd = new KhachHangDAO();
        KhachHang khachHang = khd.selectByUsernameAndPassWord(kh);
        String url = "";
        if(khachHang!=null) {
            HttpSession	session = request.getSession();
            session.setAttribute("khachHang", khachHang);
            url = "/index.jsp";
        }else {
            request.setAttribute("baoLoi", "Tên đăng nhập hoặc mật khẩu không đúng!");
            url = "/dangnhap.jsp";
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);

    }
}