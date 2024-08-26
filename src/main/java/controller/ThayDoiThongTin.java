package controller;

import database.KhachHangDAO;
import model.KhachHang;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

/**
 * Servlet implementation class ThayDoiThongTin
 */
@WebServlet("/thay-doi-thong-tin")
public class ThayDoiThongTin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThayDoiThongTin() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String hoVaTen = request.getParameter("hoVaTen");
        String gioiTinh = request.getParameter("gioiTinh");
        String ngaySinh = request.getParameter("ngaySinh");
        String diaChiKhachHang = request.getParameter("diaChiKhachHang");
        String diaChiMuaHang = request.getParameter("diaChiMuaHang");
        String diaChiNhanHang = request.getParameter("diaChiNhanHang");
        String dienThoai = request.getParameter("dienThoai");
        String email = request.getParameter("email");
        String dongYNhanMail = request.getParameter("dongYNhanMail");
        request.setAttribute("hoVaTen", hoVaTen);
        request.setAttribute("gioiTinh", gioiTinh);
        request.setAttribute("ngaySinh", ngaySinh);
        request.setAttribute("diaChiKhachHang", diaChiKhachHang);
        request.setAttribute("diaChiMuaHang", diaChiMuaHang);
        request.setAttribute("diaChiNhanHang", diaChiNhanHang);
        request.setAttribute("dienThoai", dienThoai);
        request.setAttribute("dongYNhanMail", dongYNhanMail);

        String url = "";

        String baoLoi = "";
        KhachHangDAO khachHangDAO = new KhachHangDAO();

        request.setAttribute("baoLoi", baoLoi);


        if(baoLoi.length()>0) {
            url = "/dangky.jsp";
        }else {
            Object obj = request.getSession().getAttribute("khachHang");
            KhachHang khachHang = null;
            if (obj!=null)
                khachHang = (KhachHang)obj;
            if(khachHang!=null){
                String maKhachHang = khachHang.getMaKhacHang();
                KhachHang kh = new KhachHang(maKhachHang, "", "", hoVaTen, gioiTinh, diaChiKhachHang, diaChiNhanHang, diaChiMuaHang,
                        Date.valueOf(ngaySinh), dienThoai, email, dongYNhanMail!=null);
                khachHangDAO.updateInfo(kh);
                KhachHang kh2 = khachHangDAO.selectById(kh);
                request.getSession().setAttribute("khachHang", kh2);
                request.setAttribute("successMessage", "Thông tin của bạn đã được thay đổi thành công!");
                url = "/changeInfo.jsp";
            }

        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        System.out.println("Ho va ten: " + hoVaTen);
        System.out.println("Gioi tinh: " + gioiTinh);


        rd.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
