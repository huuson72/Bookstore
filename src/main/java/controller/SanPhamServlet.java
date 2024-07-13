package controller;

import database.SanPhamDAO;
import model.SanPham;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/SanPhamServlet")
public class SanPhamServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Tạo đối tượng SanPhamDAO để lấy danh sách sản phẩm
        SanPhamDAO sanPhamDAO = new SanPhamDAO();
        List<SanPham> listSanPham = null;
        try {
            listSanPham = sanPhamDAO.selectAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Đưa danh sách sản phẩm vào request
        request.setAttribute("listSanPham", listSanPham);

        // Chuyển hướng tới trang JSP để hiển thị
        request.getRequestDispatcher("/sanpham.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
