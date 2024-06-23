package database;

import model.ChiTietDonHang;
import model.DonHang;
import model.SanPham;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ChiTietDonHangDAO implements DAOInterface<ChiTietDonHang> {
    @Override
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
                String maChiTietDonHang = rs.getString("machitietdonhang");
                String donhang = rs.getString("donhang");
                String sanpham = rs.getString("sanpham");
                double soluong = rs.getDouble("soluong");
                double giagoc = rs.getDouble("giagoc");
                double giamgia = rs.getDouble("giamgia");
                double giaban = rs.getDouble("giaban");
                double thuevat = rs.getDouble("thuevat");
                double tongtien = rs.getDouble("tongtien");

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

    @Override
    public ChiTietDonHang selectById(ChiTietDonHang t) {
        ChiTietDonHang ketQua = null;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM chitietdonhang WHERE machitietdonhang=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getMaChiTietDonHang());

            // Bước 3: thực thi câu lệnh SQL
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String maChiTietDonHang = rs.getString("machitietdonhang");
                String donhang = rs.getString("donhang");// o
                String sanpham = rs.getString("sanpham");// o
                double soluong = rs.getDouble("soluong");
                double giagoc = rs.getDouble("giagoc");
                double giamgia = rs.getDouble("giamgia");
                double giaban = rs.getDouble("giaban");
                double thuevat = rs.getDouble("thuevat");
                double tongtien = rs.getDouble("tongtien");

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

    @Override
    public int insert(ChiTietDonHang t) {
        int ketQua = 0;
        try {
            // Bước 1: tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            // Bước 2: tạo ra đối tượng statement
            String sql = "INSERT INTO chitietdonhang (machitietdonhang, donhang,sanpham, soluong, giagoc,giamgia,giaban,thuevat,tongtien) "
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

    @Override
    public int insertAll(ArrayList<ChiTietDonHang> arr) {
        int dem = 0;
        for (ChiTietDonHang ChiTietDonHang : arr) {
            dem += this.insert(ChiTietDonHang);
        }
        return dem;
    }

    @Override
    public int delete(ChiTietDonHang t) {
        int ketQua = 0;
        try {
            // Bước 1: tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            // Bước 2: tạo ra đối tượng statement
            String sql = "DELETE from chitietdonhang " + " WHERE machitietdonhang=?";

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

    @Override
    public int deleteAll(ArrayList<ChiTietDonHang> arr) {
        int dem = 0;
        for (ChiTietDonHang ChiTietDonHang : arr) {
            dem += this.delete(ChiTietDonHang);
        }
        return dem;
    }

    @Override
    public int update(ChiTietDonHang t) {
        int ketQua = 0;
        try {
            // Bước 1: tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            // Bước 2: tạo ra đối tượng statement
            String sql = "UPDATE chitietdonhang SET donhang=?, sanpham=?, soluong=?, giagoc=?, giamgia=?, giaban=?, thuevat=?, tongtien=?"
                    + " WHERE machitietdonhang=?";

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


//    @Override
//    public ArrayList<ChiTietDonHang> selectAll() {
//        ArrayList<ChiTietDonHang> chiTietDonHangList = new ArrayList<>();
//        String query = "SELECT * FROM chitietdonhang";
//
//        try (Connection conn = JDBCUtil.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(query);
//             ResultSet rs = stmt.executeQuery()) {
//
//            while (rs.next()) {
//                String maChiTietDonHang = rs.getString("maChiTietDonHang");
//                String maDonHang = rs.getString("maDonHang");
//                String maSanPham = rs.getString("maSanPham");
//                double soLuong = rs.getDouble("soLuong");
//                double giaGoc = rs.getDouble("giaGoc");
//                double giamGia = rs.getDouble("giamGia");
//                double giaBan = rs.getDouble("giaBan");
//                double thueVAT = rs.getDouble("thueVAT");
//                double tongTien = rs.getDouble("tongTien");
//
//                DonHang donHang = new DonHangDAO().selectById(new DonHang(maDonHang, null, null, null, null, null, 0, 0, null, null));
//                if (donHang == null) {
//                    System.out.println("Không tìm thấy đơn hàng với ID: " + maDonHang);
//                    continue;  // Bỏ qua chi tiết đơn hàng này
//                }
//
//                SanPham sanPham = new SanPhamDAO().selectById(new SanPham(maSanPham, null, null, 0, 0, 0, 0, 0, null, null, null));
//                if (sanPham == null) {
//                    System.out.println("Không tìm thấy sản phẩm với ID: " + maSanPham);
//                    continue;  // Bỏ qua chi tiết đơn hàng này
//                }
//
//                ChiTietDonHang chiTietDonHang = new ChiTietDonHang(maChiTietDonHang, donHang, sanPham,
//                        soLuong, giaGoc, giamGia, giaBan, thueVAT, tongTien);
//                chiTietDonHangList.add(chiTietDonHang);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return chiTietDonHangList;
//    }
//
//
//    @Override
//    public ChiTietDonHang selectById(ChiTietDonHang t) {
//        ChiTietDonHang ketQua = null;
//        try {
//            Connection con = JDBCUtil.getConnection();
//
//            String sql = "SELECT * FROM chitietdonhang WHERE machitietdonhang=?";
//            PreparedStatement st = con.prepareStatement(sql);
//            st.setString(1, t.getMaChiTietDonHang());
//
//            // Bước 3: thực thi câu lệnh SQL
//            System.out.println(sql);
//            ResultSet rs = st.executeQuery();
//
//            while (rs.next()) {
//                String maChiTietDonHang = rs.getString("machitietdonhang");
//                String donhang = rs.getString("madonhang");// o
//                String sanpham = rs.getString("masanpham");// o
//                double soluong = rs.getDouble("soluong");
//                double giagoc = rs.getDouble("giagoc");
//                double giamgia = rs.getDouble("giamgia");
//                double giaban = rs.getDouble("giaban");
//                double thuevat = rs.getDouble("thuevat");
//                double tongtien = rs.getDouble("tongtien");
//
//                DonHang dh = new DonHangDAO().selectById(new DonHang(donhang, null, "", "", "", "", 0, 0, null, null));
//                SanPham sp = new SanPhamDAO().selectById(new SanPham(sanpham, "", null, 0, 0, 0, 0, 0, null, "", ""));
//
//                ketQua = new ChiTietDonHang(maChiTietDonHang, dh, sp, soluong, giagoc, giamgia, giaban, thuevat,
//                        tongtien);
//                break;
//            }
//            // Bước 5:
//            JDBCUtil.close(con);
//        } catch (SQLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        return ketQua;
//    }
//
//    @Override
//    public int insert(ChiTietDonHang chiTietDonHang) {
//        int rowsAffected = 0;
//        String query = "INSERT INTO chitietdonhang (maChiTietDonHang, ...) VALUES (?, ...)";
//
//        try (Connection conn = JDBCUtil.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//
//            stmt.setString(1, chiTietDonHang.getMaChiTietDonHang());
//            // Set các giá trị khác cho câu lệnh INSERT tại đây
//
//            rowsAffected = stmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return rowsAffected;
//    }
//
//    @Override
//    public int insertAll(ArrayList<ChiTietDonHang> arr) {
//        int rowsAffected = 0;
//        String query = "INSERT INTO chitietdonhang (maChiTietDonHang, ...) VALUES (?, ...)";
//
//        try (Connection conn = JDBCUtil.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//
//            for (ChiTietDonHang chiTietDonHang : arr) {
//                stmt.setString(1, chiTietDonHang.getMaChiTietDonHang());
//                // Set các giá trị khác cho câu lệnh INSERT tại đây
//
//                rowsAffected += stmt.executeUpdate();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return rowsAffected;
//    }
//
//    @Override
//    public int delete(ChiTietDonHang chiTietDonHang) {
//        int rowsAffected = 0;
//        String query = "DELETE FROM chitietdonhang WHERE maChiTietDonHang = ?";
//
//        try (Connection conn = JDBCUtil.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//
//            stmt.setString(1, chiTietDonHang.getMaChiTietDonHang());
//            rowsAffected = stmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return rowsAffected;
//    }
//
//    @Override
//    public int deleteAll(ArrayList<ChiTietDonHang> arr) {
//        int rowsAffected = 0;
//        String query = "DELETE FROM chitietdonhang WHERE maChiTietDonHang = ?";
//
//        try (Connection conn = JDBCUtil.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//
//            for (ChiTietDonHang chiTietDonHang : arr) {
//                stmt.setString(1, chiTietDonHang.getMaChiTietDonHang());
//                rowsAffected += stmt.executeUpdate();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return rowsAffected;
//    }
//
//    @Override
//    public int update(ChiTietDonHang chiTietDonHang) {
//        int rowsAffected = 0;
//        String query = "UPDATE chitietdonhang SET ... WHERE maChiTietDonHang = ?";
//
//        try (Connection conn = JDBCUtil.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//
//            // Set các giá trị mới cho câu lệnh UPDATE tại đây
//
//            stmt.setString(1, chiTietDonHang.getMaChiTietDonHang());
//
//            rowsAffected = stmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return rowsAffected;
//    }
//    public static void main(String[] args) {
//        ChiTietDonHangDAO chiTietDonHangDAO = new ChiTietDonHangDAO();
//
//        // Test selectAll
//        ArrayList<ChiTietDonHang> chiTietDonHangList = chiTietDonHangDAO.selectAll();
//
//        // In ra thông tin của các chi tiết đơn hàng
//        for (ChiTietDonHang chiTietDonHang : chiTietDonHangList) {
//            System.out.println(chiTietDonHang);
//        }
//    }



}

