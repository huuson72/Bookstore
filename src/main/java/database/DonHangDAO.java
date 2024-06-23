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
    @Override
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

    @Override
    public DonHang selectById(DonHang t) throws SQLException {
        DonHang ketQua = null;
        Connection con = JDBCUtil.getConnection();
        String sql = "SELECT * FROM donhang WHERE madonhang = ?";
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

    @Override
    public int insert(DonHang t) throws SQLException {
        int kq = 0;
        Connection con = JDBCUtil.getConnection();
        String sql = "INSERT INTO donhang(madonhang, khachhang, diachinguoimua, diachinguoinhan, trangthai, thanhtoan,tienthanhtoan, tienthieu,ngaydathang,ngaygiaohang)"
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

    @Override
    public int insertAll(ArrayList<DonHang> arr) throws SQLException {
        int kq = 0;
        for (DonHang donHang : arr) {
            kq += this.insert(donHang);
        }
        return kq;
    }

    @Override
    public int delete(DonHang t) throws SQLException {
        int kq = 0;
        Connection con = JDBCUtil.getConnection();
        String sql = "DELETE FROM donhang WHERE madonhang = ?";
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

    @Override
    public int deleteAll(ArrayList<DonHang> arr) throws SQLException {
        int kq = 0;
        for (DonHang t : arr) {
            kq += this.delete(t);
        }
        return kq;
    }

    @Override
    public int update(DonHang t) throws SQLException {
        int kq = 0;
        Connection con = JDBCUtil.getConnection();

        String sql = "UPDATE donhang" + " SET " + "khachhang=?" + ", diachinguoimua=?" + ",diachinguoinhan=?"
                + ",trangthai=?" + ",thanhtoan=?" + ",tienthanhtoan=?" + ",tienthieu=?" + ",ngaydathang=?"
                + ",ngaygiaohang=?" + " WHERE madonhang=?";
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


//    @Override
//    public ArrayList<DonHang> selectAll() {
//        ArrayList<DonHang> donHangList = new ArrayList<>();
//        try (Connection conn = JDBCUtil.getConnection();
//             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM donhang");
//             ResultSet rs = stmt.executeQuery()) {
//            while (rs.next()) {
//                DonHang donHang = new DonHang();
//                donHang.setMaDonHang(rs.getString("maDonHang"));
//
//                // Truy vấn thông tin của KhachHang từ maKhachHang
//                String maKhachHang = rs.getString("maKhachHang");
//                KhachHang khachHang = new KhachHangDAO()
//                        .selectById(new KhachHang(maKhachHang, "", "", "", "", "", "", "", null, "", "", false));
//                donHang.setKhachHang(khachHang);
//
//                donHang.setDiaChiMuaHang(rs.getString("diaChiMuaHang"));
//                donHang.setDiaChiNhanHang(rs.getString("diaChiNhanHang"));
//                donHang.setTrangThai(rs.getString("trangThai"));
//                donHang.setHinhThucThanhToan(rs.getString("hinhThucThanhToan"));
//                donHang.setTrangThaiThanhToan(rs.getString("trangThaiThanhToan"));
//                donHang.setSoTienDaThanhToan(rs.getDouble("soTienDaThanhToan"));
//                donHang.setSoTienConThieu(rs.getDouble("soTienConThieu"));
//                donHang.setNgayDatHang(rs.getDate("ngayDatHang"));
//                donHang.setNgayGiaoHang(rs.getDate("ngayGiaoHang"));
//
//                donHangList.add(donHang);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return donHangList;
//    }
//
//
//
//
//    public DonHang selectById(DonHang t) {
//        DonHang ketQua = null;
//        try {
//            Connection con = JDBCUtil.getConnection();
//
//            String sql = "SELECT * FROM donhang WHERE madonhang=?";
//            PreparedStatement st = con.prepareStatement(sql);
//            st.setString(1, t.getMaDonHang());
//
//            ResultSet rs = st.executeQuery();
//
//            if (rs.next()) {
//                String maDonHang = rs.getString("maDonHang");
//                String maKhachHang = rs.getString("maKhachHang");
//                String diaChiMuaHang = rs.getString("diaChiMuaHang");
//                String diaChiNhanHang = rs.getString("diaChiNhanHang");
//                String trangThai = rs.getString("trangThai");
//                String hinhThucThanhToan = rs.getString("hinhThucThanhToan");
//                String trangThaiThanhToan = rs.getString("trangThaiThanhToan");
//                double soTienDaThanhToan = rs.getDouble("soTienDaThanhToan");
//                double soTienConThieu = rs.getDouble("soTienConThieu");
//                String ngayDatHang = rs.getString("ngayDatHang");
//                String ngayGiaoHang = rs.getString("ngayGiaoHang");
//
//                ketQua = new DonHang(maDonHang, maKhachHang, diaChiMuaHang, diaChiNhanHang, trangThai,
//                        hinhThucThanhToan, trangThaiThanhToan, soTienDaThanhToan, soTienConThieu, ngayDatHang, ngayGiaoHang);
//            }
//            JDBCUtil.close(con);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return ketQua;
//    }
//
//    @Override
//    public int insert(DonHang donHang) {
//        return 0;
//    }
//
//    @Override
//    public int delete(DonHang donHang) {
//        return 0;
//    }
//
//    @Override
//    public int update(DonHang donHang) {
//        return 0;
//    }
//
//    @Override
//
//
//    public int insertAll(ArrayList arr) {
//        return 0;
//    }
//
//    @Override
//
//
//    public int deleteAll(ArrayList arr) {
//        return 0;
//    }
//
//    public static void main(String[] args) {
//        DonHangDAO donHangDAO = new DonHangDAO();
//
//        // Tạo đối tượng DonHang để truy vấn
//        DonHang donHang = new DonHang();
//        donHang.setMaDonHang("DH01"); // Thay đổi mã đơn hàng tùy theo nhu cầu kiểm tra
//
//        // Gọi phương thức selectById để lấy thông tin đơn hàng từ cơ sở dữ liệu
//        DonHang result = donHangDAO.selectById(donHang);
//
//        // In ra thông tin đơn hàng lấy được từ cơ sở dữ liệu
//        if (result != null) {
//            System.out.println("Thông tin Đơn hàng:");
//            System.out.println(result);
//        } else {
//            System.out.println("Không tìm thấy Đơn hàng với mã: " + donHang.getMaDonHang());
//        }
//    }

}