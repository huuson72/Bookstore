package database;

import model.*;

import java.sql.*;
import java.util.ArrayList;

public class ChiTietDonHangDAO implements DAOInterface<ChiTietDonHang> {
    
    public ArrayList<ChiTietDonHang> selectAll() {
        ArrayList<ChiTietDonHang> ketQua = new ArrayList<ChiTietDonHang>();

        try {
            // Bước 1: tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            // Bước 2: tạo ra đối tượng statement
            String sql = "SELECT * FROM chitietdonhang";
            PreparedStatement st = con.prepareStatement(sql);

            // Bước 3: thực thi câu lệnh SQL
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            // Bước 4:

            while (rs.next()) {
                String maChiTietDonHang = rs.getString("maChiTietDonHang");
                String donhang = rs.getString("maDonHang");
                String sanpham = rs.getString("maSanPham");
                double soluong = rs.getDouble("soLuong");
                double giagoc = rs.getDouble("giaGoc");
                double giamgia = rs.getDouble("giamGia");
                double giaban = rs.getDouble("giaBan");
                double thuevat = rs.getDouble("thueVAT");
                double tongtien = rs.getDouble("tongTien");

                DonHang dh = new DonHangDAO().selectById(new DonHang(donhang, null, "", "", "", "", 0, 0, null, null));
                SanPham sp = new SanPhamDAO().selectById(new SanPham("", "", null, 0, 0, 0, 0, 0, null, "", ""));

                ChiTietDonHang ctdh = new ChiTietDonHang(maChiTietDonHang, dh, sp, soluong, giagoc, giamgia, giaban,
                        thuevat, tongtien);
                ketQua.add(ctdh);
            }

            // Bước 5:
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ketQua;
    }

    
    public ChiTietDonHang selectById(ChiTietDonHang t) {
        ChiTietDonHang ketQua = null;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM chitietdonhang WHERE maChiTietDonHang=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getMaChiTietDonHang());

            // Bước 3: thực thi câu lệnh SQL
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String maChiTietDonHang = rs.getString("maChiTietDonHang");
                String donhang = rs.getString("maDonHang");
                String sanpham = rs.getString("maSanPham");
                double soluong = rs.getDouble("soLuong");
                double giagoc = rs.getDouble("giaGoc");
                double giamgia = rs.getDouble("giamGia");
                double giaban = rs.getDouble("giaBan");
                double thuevat = rs.getDouble("thueVAT");
                double tongtien = rs.getDouble("tongTien");

                DonHang dh = new DonHangDAO().selectById(new DonHang(donhang, null, "", "", "", "", 0, 0, null, null));
                SanPham sp = new SanPhamDAO().selectById(new SanPham(sanpham, "", null, 0, 0, 0, 0, 0, null, "", ""));

                ketQua = new ChiTietDonHang(maChiTietDonHang, dh, sp, soluong, giagoc, giamgia, giaban, thuevat,
                        tongtien);
                break;
            }
            // Bước 5:
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ketQua;
    }

    
    public int insert(ChiTietDonHang t) {
        int ketQua = 0;
        try {
            // Bước 1: tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            // Bước 2: tạo ra đối tượng statement
            String sql = "INSERT INTO chitietdonhang (maChiTietDonHang, maDonHang,maSanPham, soLuong, giaGoc,giamGia,giaBan,thueVAT,tongTien) "
                    + " VALUES (?,?,?,?,?,?,?,?,?)";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getMaChiTietDonHang());
            st.setString(2, t.getDonHang().getMaDonHang());
            st.setString(3, t.getSanPham().getMaSanPham());
            st.setDouble(4, t.getSoLuong());
            st.setDouble(5, t.getGiaGoc());
            st.setDouble(7, t.getGiamGia());
            st.setDouble(6, t.getGiaBan());
            st.setDouble(8, t.getThueVAT());
            st.setDouble(9, t.getTongTien());
            // Bước 3: thực thi câu lệnh SQL
            ketQua = st.executeUpdate();

            // Bước 4:
            System.out.println("Bạn đã thực thi: " + sql);
            System.out.println("Có " + ketQua + " dòng bị thay đổi!");

            // Bước 5:
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ketQua;
    }

    
    public int insertAll(ArrayList<ChiTietDonHang> arr) {
        int dem = 0;
        for (ChiTietDonHang ChiTietDonHang : arr) {
            dem += this.insert(ChiTietDonHang);
        }
        return dem;
    }

    
    public int delete(ChiTietDonHang t) {
        int ketQua = 0;
        try {
            // Bước 1: tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            // Bước 2: tạo ra đối tượng statement
            String sql = "DELETE from chitietdonhang " + " WHERE maChiTietDonHang=?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getMaChiTietDonHang());

            // Bước 3: thực thi câu lệnh SQL
            System.out.println(sql);
            ketQua = st.executeUpdate();

            // Bước 4:
            System.out.println("Bạn đã thực thi: " + sql);
            System.out.println("Có " + ketQua + " dòng bị thay đổi!");

            // Bước 5:
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ketQua;
    }

    
    public int deleteAll(ArrayList<ChiTietDonHang> arr) {
        int dem = 0;
        for (ChiTietDonHang ChiTietDonHang : arr) {
            dem += this.delete(ChiTietDonHang);
        }
        return dem;
    }

    
    public int update(ChiTietDonHang t) {
        int ketQua = 0;
        try {
            // Bước 1: tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            // Bước 2: tạo ra đối tượng statement
            String sql = "UPDATE chitietdonhang SET maDonHang=?, maSanPham=?, soLuong=?, giaGoc=?, giamGia=?, giaBan=?, thueVAT=?, tongTien=?"
                    + " WHERE maChiTietDonHang=?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getDonHang().getMaDonHang());
            st.setString(2, t.getSanPham().getMaSanPham());
            st.setDouble(3, t.getSoLuong());
            st.setDouble(4, t.getGiaGoc());
            st.setDouble(5, t.getGiamGia());
            st.setDouble(6, t.getGiaBan());
            st.setDouble(7, t.getThueVAT());
            st.setDouble(8, t.getTongTien());
            st.setString(9, t.getMaChiTietDonHang());

            // Bước 3: thực thi câu lệnh SQL

            System.out.println(sql);
            ketQua = st.executeUpdate();

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ketQua;
    }



    public static void main(String[] args) throws SQLException {
        ChiTietDonHangDAO chiTietDonHangDAO = new ChiTietDonHangDAO();
        DonHangDAO donHangDAO = new DonHangDAO();
        KhachHangDAO khachHangDAO = new KhachHangDAO();
        SanPhamDAO sanPhamDAO = new SanPhamDAO();

        // Tạo một khách hàng mới để thêm vào đơn hàng
        KhachHang khachHang = new KhachHang();
        khachHang.setMaKhacHang("KH04");
        khachHang.setTenDangNhap("testuser");
        khachHang.setMatKhau("password");
        khachHang.setHoVaTen("Test User");
        khachHang.setGioiTinh("Nam");
        khachHang.setDiaChi("123 Test St");
        khachHang.setDiaChiMuaHang("123 Test St");
        khachHang.setDiaChiNhanHang("123 Test St");
        khachHang.setNgaySinh(java.sql.Date.valueOf("2000-01-01"));
        khachHang.setSoDienThoai("0987654321");
        khachHang.setEmail("testuser@example.com");
        khachHang.setDangKyNhanBangTin(true);

        // Insert the customer
        khachHangDAO.insert(khachHang);

        // Tạo một đơn hàng mới
        DonHang donHang = new DonHang();
        donHang.setMaDonHang("DH04");
        donHang.setKhachHang(khachHang);
        donHang.setDiaChiMuaHang("123 Test St");
        donHang.setDiaChiNhanHang("456 Test Ave");
        donHang.setTrangThai("Pending");
        donHang.setHinhThucThanhToan("Credit Card");
        donHang.setSoTienDaThanhToan(100.0);
        donHang.setSoTienConThieu(50.0);
        donHang.setNgayDatHang(new Date(System.currentTimeMillis()));
        donHang.setNgayGiaoHang(new Date(System.currentTimeMillis() + 86400000L));

        // Thêm đơn hàng vào cơ sở dữ liệu
        donHangDAO.insert(donHang);
        // Tạo tác giả và thể loại cho sản phẩm
        TacGia tacGia = new TacGia();
        tacGia.setMaTacGia("TG04");
        tacGia.setHoVaTen("Test Author");
        TheLoai theLoai = new TheLoai();
        theLoai.setMaTheLoai("TL04");
        theLoai.setTenTheLoai("Test Category");

        // Tạo một sản phẩm mới
        SanPham sanPham = new SanPham();
        sanPham.setMaSanPham("SP04");
        sanPham.setTenSanPham("Test Product");
        sanPham.setTacGia(tacGia);
        sanPham.setNamXuatBan(2023);
        sanPham.setGiaNhap(800.0);
        sanPham.setGiaGoc(1000.0);
        sanPham.setGiaBan(1200.0);
        sanPham.setSoLuong(100);
        sanPham.setTheLoai(theLoai);
        sanPham.setNgonNgu("Vietnamese");
        sanPham.setMoTa("This is a test product.");

        // Thêm sản phẩm vào cơ sở dữ liệu
        sanPhamDAO.insert(sanPham);

        // Tạo một chi tiết đơn hàng mới
        ChiTietDonHang chiTietDonHang = new ChiTietDonHang();
        chiTietDonHang.setMaChiTietDonHang("CTDH03");
        chiTietDonHang.setDonHang(donHang);
        chiTietDonHang.setSanPham(sanPham);
        chiTietDonHang.setSoLuong(2);
        chiTietDonHang.setGiaGoc(1000.0);
        chiTietDonHang.setGiamGia(0.1);
        chiTietDonHang.setGiaBan(1200.0);
        chiTietDonHang.setThueVAT(0.1);
        chiTietDonHang.setTongTien(2400.0);

        // Thêm chi tiết đơn hàng vào cơ sở dữ liệu
        chiTietDonHangDAO.insert(chiTietDonHang);

        // Lấy chi tiết đơn hàng vừa thêm và in thông tin ra
        ChiTietDonHang selectedChiTietDonHang = chiTietDonHangDAO.selectById(chiTietDonHang);
        if (selectedChiTietDonHang != null) {
            System.out.println("Selected ChiTietDonHang: " + selectedChiTietDonHang.getMaChiTietDonHang() + ", " + selectedChiTietDonHang.getTongTien());
        }

        // Cập nhật số lượng chi tiết đơn hàng
        selectedChiTietDonHang.setSoLuong(3);
        chiTietDonHangDAO.update(selectedChiTietDonHang);

        // Lấy tất cả các chi tiết đơn hàng và in ra thông tin từng chi tiết đơn hàng
        ArrayList<ChiTietDonHang> chiTietDonHangList = chiTietDonHangDAO.selectAll();
        for (ChiTietDonHang ctdh : chiTietDonHangList) {
            System.out.println(ctdh.getMaChiTietDonHang() + ", " + ctdh.getSoLuong());
        }

        // Xóa chi tiết đơn hàng vừa cập nhật
        chiTietDonHangDAO.delete(selectedChiTietDonHang);
    }


}

