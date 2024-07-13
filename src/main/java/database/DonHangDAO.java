package  database;

import model.DonHang;
import model.KhachHang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class DonHangDAO implements DAOInterface<DonHang> {

    
    public ArrayList<DonHang> selectAll() throws SQLException {
        ArrayList<DonHang> ketQua = new ArrayList<DonHang>();
        Connection con = JDBCUtil.getConnection();
        String sql = "SELECT * FROM donhang";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String maDH = rs.getString(1);
                String maKH = rs.getString(2);
                String diaChiNguoiMua = rs.getString(3);
                String diaChiNhanHang = rs.getString(4);
                String trangThai = rs.getString(5);
                String hinhThucThanhToan = rs.getString(6);
                double soTienDaThanhToan = rs.getDouble(7);
                double soTienConThieu = rs.getDouble(8);
                Date ngayDatHang = rs.getDate(9);
                Date ngayGiaoHang = rs.getDate(10);

                KhachHang khachHang = new KhachHangDAO()
                        .selectById(new KhachHang(maKH, "", "", "", "", "", "", "", null, "", "", false));
                DonHang dh = new DonHang(maDH, khachHang, diaChiNguoiMua, diaChiNhanHang, trangThai, hinhThucThanhToan,
                        soTienDaThanhToan, soTienConThieu, (java.sql.Date) ngayDatHang, (java.sql.Date) ngayGiaoHang);

                ketQua.add(dh);
            }
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ketQua;
    }

    
    public DonHang selectById(DonHang t) throws SQLException {
        DonHang ketQua = null;
        Connection con = JDBCUtil.getConnection();
        String sql = "SELECT * FROM donhang WHERE maDonHang = ?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getMaDonHang());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String maDH = rs.getString(1);
                String maKH = rs.getString(2);
                String diaChiNguoiMua = rs.getString(3);
                String diaChiNhanHang = rs.getString(4);
                String trangThai = rs.getString(5);
                String hinhThucThanhToan = rs.getString(6);
                double soTienDaThanhToan = rs.getDouble(7);
                double soTienConThieu = rs.getDouble(8);
                Date ngayDatHang = rs.getDate(9);
                Date ngayGiaoHang = rs.getDate(10);

                KhachHang khachHang = new KhachHangDAO()
                        .selectById(new KhachHang(maKH, "", "", "", "", "", "", "", null, "", "", false));
                DonHang dh = new DonHang(maDH, khachHang, diaChiNguoiMua, diaChiNhanHang, trangThai, hinhThucThanhToan,
                        soTienDaThanhToan, soTienConThieu, (java.sql.Date) ngayDatHang, (java.sql.Date) ngayGiaoHang);

                ketQua = dh;
            }
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ketQua;
    }

    
    public int insert(DonHang t) throws SQLException {
        int kq = 0;
        Connection con = JDBCUtil.getConnection();
        String sql = "INSERT INTO donhang(maDonHang, maKhachHang, diaChiMuaHang, diaChiNhanHang, trangThai, hinhThucThanhToan,soTienDaThanhToan, soTienConThieu,ngayDatHang,ngayGiaoHang)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getMaDonHang());
            st.setString(2, t.getKhachHang().getMaKhacHang());
            st.setString(3, t.getDiaChiMuaHang());
            st.setString(4, t.getDiaChiNhanHang());
            st.setString(5, t.getTrangThai());
            st.setString(6, t.getHinhThucThanhToan());
            st.setDouble(7, t.getSoTienDaThanhToan());
            st.setDouble(8, t.getSoTienConThieu());
            st.setDate(9, t.getNgayDatHang());
            st.setDate(10, t.getNgayGiaoHang());

            kq = st.executeUpdate();
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return kq;
    }

    
    public int insertAll(ArrayList<DonHang> arr) throws SQLException {
        int kq = 0;
        for (DonHang donHang : arr) {
            kq += this.insert(donHang);
        }
        return kq;
    }

    
    public int delete(DonHang t) throws SQLException {
        int kq = 0;
        Connection con = JDBCUtil.getConnection();
        String sql = "DELETE FROM donhang WHERE maDonHang = ?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getMaDonHang());
            kq = st.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kq;
    }

    
    public int deleteAll(ArrayList<DonHang> arr) throws SQLException {
        int kq = 0;
        for (DonHang t : arr) {
            kq += this.delete(t);
        }
        return kq;
    }

    
    public int update(DonHang t) throws SQLException {
        int kq = 0;
        Connection con = JDBCUtil.getConnection();

        String sql = "UPDATE donhang" + " SET " + "maKhachHang=?" + ", diaChiMuaHang=?" + ",diaChiNhanHang=?"
                + ",trangThai=?" + ",hinhThucThanhToan=?" + ",trangThaiThanhToan=?" + ", soTienDaThanhToan =?"+",tienThieu=?" + ",ngayDatHang=?"
                + ",ngayGiaoHang=?" + " WHERE maDonHang=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getKhachHang().getMaKhacHang());
            st.setString(2, t.getDiaChiMuaHang());
            st.setString(3, t.getDiaChiNhanHang());
            st.setString(4, t.getTrangThai());
            st.setString(5, t.getHinhThucThanhToan());
            st.setDouble(6, t.getSoTienDaThanhToan());
            st.setDouble(7, t.getSoTienConThieu());
            st.setDate(8, t.getNgayDatHang());
            st.setDate(9, t.getNgayGiaoHang());
            st.setString(10, t.getMaDonHang());

            kq = st.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kq;
    }

    public static void main(String[] args) throws SQLException {
        DonHangDAO donHangDAO = new DonHangDAO();
        KhachHangDAO khachHangDAO = new KhachHangDAO();
        KhachHang khachHang = new KhachHang();
        khachHang.setMaKhacHang("KH006");
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
        DonHang donHang = new DonHang();
        donHang.setMaDonHang("DH06");
        donHang.setKhachHang(khachHang);
        donHang.setDiaChiMuaHang("123 Test St");
        donHang.setDiaChiNhanHang("456 Test Ave");
        donHang.setTrangThai("Pending");
        donHang.setHinhThucThanhToan("Credit Card");
        donHang.setSoTienDaThanhToan(100.0);
        donHang.setSoTienConThieu(50.0);
        donHang.setNgayDatHang((java.sql.Date) new Date(System.currentTimeMillis()));
        donHang.setNgayGiaoHang((java.sql.Date) new Date(System.currentTimeMillis() + 86400000L)); // Ngày giao hàng là ngày mai

        // Thêm đơn hàng vào cơ sở dữ liệu
        donHangDAO.insert(donHang);
    }

}