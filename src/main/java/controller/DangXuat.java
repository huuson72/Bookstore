package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "DangXuat", urlPatterns = "/dang-xuat")
public class DangXuat extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate(); // Hủy session hiện tại
        response.sendRedirect("dangnhap.jsp"); // Chuyển hướng về trang đăng nhập
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response); // Xử lý yêu cầu GET và chuyển hướng về trang đăng nhập
    }
}
