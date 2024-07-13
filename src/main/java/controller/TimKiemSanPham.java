package controller;

import database.SanPhamDAO;
import model.SanPham;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TimKiemSanPham", value = "/TimKiemSanPham")
public class TimKiemSanPham extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");

        // Gọi phương thức tìm kiếm sản phẩm từ SanPhamDAO
        List<SanPham> sanPhamList = SanPhamDAO.timKiem(query);

        // Debug: In ra console
        System.out.println("Số lượng sản phẩm tìm được: " + sanPhamList.size());

        // Đẩy danh sách sản phẩm tìm được vào request để hiển thị trang kết quả
        request.setAttribute("sanPhamList", sanPhamList);
        request.getRequestDispatcher("ketquatimkiem.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
