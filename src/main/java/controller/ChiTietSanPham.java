package controller;

import database.SanPhamDAO;
import model.SanPham;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ChiTietSanPham", value = "/ChiTietSanPham")
public class ChiTietSanPham extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String maSanPham = request.getParameter("maSanPham");
        String tenTacGia = request.getParameter("hoVaTen");
        String tenTheLoai = request.getParameter("tenTheLoai");



        // Gọi phương thức lấy chi tiết sản phẩm từ SanPhamDAO
        SanPham sanPham = new SanPhamDAO().selectById(new SanPham(maSanPham, "", null, 0, 0, 0, 0, 0, null, "", ""));


        // Debugging: In ra console để kiểm tra
        if (sanPham != null) {
            System.out.println("SanPham: " + sanPham.getTenSanPham());
            System.out.println("TacGia: " + sanPham.getTacGia().getHoVaTen());
            System.out.println("TheLoai: " + sanPham.getTheLoai().getTenTheLoai());
        } else {
            System.out.println("SanPham không tồn tại");
        }

        // Đẩy thông tin sản phẩm vào request để hiển thị trang chi tiết
        request.setAttribute("sanPham", sanPham);
        request.getRequestDispatcher("chitietsanpham.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
