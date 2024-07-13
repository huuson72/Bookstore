package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/ThemVaoGioHang")
public class ThemVaoGioHang extends HttpServlet {

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("cart");

    if (cart == null) {
        cart = new HashMap<>();
    }

    String maSanPham = request.getParameter("maSanPham");
    int quantity = Integer.parseInt(request.getParameter("quantity"));

    if (maSanPham != null) {
        if (cart.containsKey(maSanPham)) {
            cart.put(maSanPham, cart.get(maSanPham) + quantity);
        } else {
            cart.put(maSanPham, quantity);
        }
        session.setAttribute("cart", cart);
    }

    // Trả về phản hồi thành công mà không chuyển hướng
    response.setContentType("text/plain");
    response.getWriter().write("Thêm vào giỏ hàng thành công");
}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
