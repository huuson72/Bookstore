package database;

import model.KhachHang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class KhachHangDAO implements DAOInterface<KhachHang> {
    public static void main(String[] args) {
        // Tạo một đối tượng KhachHang mới để insert
        String maKhachHang = "KH001"; // Thay đổi mã khách hàng tùy ý
        String tenDangNhap = "user123";
        String matKhau = "password";
        String hoVaTen = "Nguyễn Văn A";
        String gioiTinh = "Nam";
        String diaChi = "123 Đường ABC, Quận XYZ, TP HCM";
        String diaChiNhanHang = "456 Đường XYZ, Quận ABC, TP HCM";
        String diaChiMuaHang = "789 Đường DEF, Quận GHI, TP HCM";
        Date ngaySinh = new Date(); // Ngày sinh tùy ý, ví dụ new Date() là ngày hiện tại
        String soDienThoai = "0901234567";
        String email = "user123@example.com";
        boolean dangKyNhanBangTin = true;

        KhachHang kh = new KhachHang(maKhachHang, tenDangNhap, matKhau, hoVaTen, gioiTinh, diaChi,
                diaChiNhanHang, diaChiMuaHang, ngaySinh, soDienThoai, email, dangKyNhanBangTin);

        // Thực hiện insert vào CSDL
        KhachHangDAO khachHangDAO = new KhachHangDAO();
        int result = khachHangDAO.insert(kh);

        if (result > 0) {
            System.out.println("Thêm khách hàng thành công!");
        } else {
            System.out.println("Thêm khách hàng thất bại!");
        }


        // Lấy danh sách khách hàng từ cơ sở dữ liệu
        ArrayList<KhachHang> khachHangList = khachHangDAO.selectAll();

        // In thông tin của từng khách hàng ra màn hình bằng phương thức toString
        for (KhachHang khachHang : khachHangList) {
            System.out.println(khachHang.toString());
            System.out.println("----------------------------------");
        }
    }

    public ArrayList<KhachHang> data = new ArrayList<>();

    @Override
    public ArrayList<KhachHang> selectAll() {
        ArrayList<KhachHang> ketQua = new ArrayList<KhachHang>();
        try {
            // Bước 1: tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            // Bước 2: tạo ra đối tượng statement
            String sql = "SELECT * FROM khachhang";
            PreparedStatement st = con.prepareStatement(sql);

            // Bước 3: thực thi câu lệnh SQL
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            // Bước 4:
            while (rs.next()) {
                String maKhacHang = rs.getString("maKhachHang");
                String tenDangNhap = rs.getString("tendangnhap");
                String matKhau = rs.getString("matkhau");
                String hoVaTen = rs.getString("hovaten");
                String gioiTinh = rs.getString("gioitinh");
                String diaChi = rs.getString("diachi");
                String diaChiNhanHang = rs.getString("diachinhanhang");
                String diaChiMuaHang = rs.getString("diachimuahang");
                Date ngaySinh = rs.getDate("ngaysinh");
                String soDienThoai = rs.getString("sodienthoai");
                String email = rs.getString("email");
                boolean dangKyNhanBangTin = rs.getBoolean("dangkynhanbangtin");

                KhachHang kh = new KhachHang(maKhacHang, tenDangNhap, matKhau, hoVaTen, gioiTinh, diaChi,
                        diaChiNhanHang, diaChiMuaHang, ngaySinh, soDienThoai, email, dangKyNhanBangTin);
                ketQua.add(kh);
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
    public KhachHang selectById(KhachHang t) {
        KhachHang ketQua = null;
        try {
            // Bước 1: tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            // Bước 2: tạo ra đối tượng statement
            String sql = "SELECT * FROM khachhang WHERE makhachhang=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getMaKhacHang());

            // Bước 3: thực thi câu lệnh SQL
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            // Bước 4:
            while (rs.next()) {
                String maKhacHang = rs.getString("maKhachHang");
                String tenDangNhap = rs.getString("tenDangNhap");
                String matKhau = rs.getString("matKhau");
                String hoVaTen = rs.getString("hoVaTen");
                String gioiTinh = rs.getString("gioiTinh");
                String diaChi = rs.getString("diaChi");
                String diaChiNhanHang = rs.getString("diaChiNhanHang");
                String diaChiMuaHang = rs.getString("diaChiMuaHang");
                Date ngaySinh = rs.getDate("ngaySinh");
                String soDienThoai = rs.getString("soDienThoai");
                String email = rs.getString("email");
                boolean dangKyNhanBangTin = rs.getBoolean("dangKyNhanBangTin");

                ketQua = new KhachHang(maKhacHang, tenDangNhap, matKhau, hoVaTen, gioiTinh, diaChi, diaChiNhanHang,
                        diaChiMuaHang, ngaySinh, soDienThoai, email, dangKyNhanBangTin);
            }

            // Bước 5:
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ketQua;
    }
public KhachHang selectByUsernameAndPassWord(KhachHang t){
        KhachHang ketQua = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql =  "SELECT * FROM khachhang WHERE tenDangNhap =? and matKhau=?";
            PreparedStatement st = con.prepareStatement(sql);
            System.out.println(t.getTenDangNhap()+"/"+t.getMatKhau());
            st.setString(1,t.getTenDangNhap());
            st.setString(2,t.getMatKhau());
            System.out.println(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                String maKhacHang = rs.getString("maKhachHang");
                String tenDangNhap = rs.getString("tenDangNhap");
                String matKhau = rs.getString("matKhau");
                String hoVaTen = rs.getString("hoVaTen");
                String gioiTinh = rs.getString("gioiTinh");
                String diaChi = rs.getString("diaChi");
                String diaChiNhanHang = rs.getString("diaChiNhanHang");
                String diaChiMuaHang = rs.getString("diaChiMuaHang");
                Date ngaySinh = rs.getDate("ngaySinh");
                String soDienThoai = rs.getString("soDienThoai");
                String email = rs.getString("email");
                boolean dangKyNhanBangTin = rs.getBoolean("dangKyNhanBangTin");
                ketQua = new KhachHang(maKhacHang, tenDangNhap, matKhau, hoVaTen, gioiTinh, diaChi, diaChiNhanHang,
                        diaChiMuaHang, ngaySinh, soDienThoai, email, dangKyNhanBangTin);

            }
            JDBCUtil.closeConnection(con);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    return ketQua;
}
    @Override
    public int insert(KhachHang t) {
        int ketQua = 0;
        try {
            // Bước 1: tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            // Bước 2: tạo ra đối tượng statement
            String sql = "INSERT INTO khachhang (maKhachHang, tenDangNhap, matKhau, hoVaTen, gioiTinh, diaChi, diaChiNhanHang, diaChiMuaHang, ngaySinh, soDienThoai, email, dangKyNhanBangTin) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getMaKhacHang());
            st.setString(2, t.getTenDangNhap());
            st.setString(3, t.getMatKhau());
            st.setString(4, t.getHoVaTen());
            st.setString(5, t.getGioiTinh());
            st.setString(6, t.getDiaChi());
            st.setString(7, t.getDiaChiNhanHang());
            st.setString(8, t.getDiaChiMuaHang());
            st.setDate(9, new java.sql.Date(t.getNgaySinh().getTime()));
            st.setString(10, t.getSoDienThoai());
            st.setString(11, t.getEmail());
            st.setBoolean(12, t.isDangKyNhanBangTin());

            // Bước 3: thực thi câu lệnh SQL
            ketQua = st.executeUpdate();

            // Bước 4: thông báo kết quả
            System.out.println("Bạn đã thực thi: " + sql);
            System.out.println("Có " + ketQua + " dòng bị thay đổi!");

            // Bước 5: đóng kết nối
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ketQua;
    }


    @Override
    public int insertAll(ArrayList<KhachHang> arr) {
        int dem = 0;
        for (KhachHang KhachHang : arr) {
            dem += this.insert(KhachHang);
        }
        return dem;
    }
    @Override
    public int delete(KhachHang t) {
        int ketQua = 0;
        try {
            // Bước 1: tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            // Bước 2: tạo ra đối tượng statement
            String sql = "DELETE FROM khachhang WHERE makhachhang=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getMaKhacHang());

            // Bước 3: thực thi câu lệnh SQL
            ketQua = st.executeUpdate();

            // Bước 4: thông báo kết quả
            System.out.println("Bạn đã thực thi: " + sql);
            System.out.println("Có " + ketQua + " dòng bị thay đổi!");

            // Bước 5: đóng kết nối
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ketQua;
    }
    @Override
    public int deleteAll(ArrayList<KhachHang> arr) {
        int dem = 0;
        for (KhachHang kh : arr) {
            dem += this.delete(kh);
        }
        return dem;
    }
    @Override
    public int update(KhachHang t) {
        int ketQua = 0;
        try {
            // Bước 1: tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            // Bước 2: tạo ra đối tượng statement
            String sql = "UPDATE khachhang SET tendangnhap=?, matkhau=?, hovaten=?, gioitinh=?, diachi=?, diachinhanhang=?, diachimuahang=?, ngaysinh=?, sodienthoai=?, email=?, dangkynhanbangtin=? WHERE makhachhang=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getTenDangNhap());
            st.setString(2, t.getMatKhau());
            st.setString(3, t.getHoVaTen());
            st.setString(4, t.getGioiTinh());
            st.setString(5, t.getDiaChi());
            st.setString(6, t.getDiaChiNhanHang());
            st.setString(7, t.getDiaChiMuaHang());
            st.setDate(8, new java.sql.Date(t.getNgaySinh().getTime()));
            st.setString(9, t.getSoDienThoai());
            st.setString(10, t.getEmail());
            st.setBoolean(11, t.isDangKyNhanBangTin());
            st.setString(12, t.getMaKhacHang());

            // Bước 3: thực thi câu lệnh SQL
            ketQua = st.executeUpdate();

            // Bước 4: thông báo kết quả
            System.out.println("Bạn đã thực thi: " + sql);
            System.out.println("Có " + ketQua + " dòng bị thay đổi!");

            // Bước 5: đóng kết nối
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ketQua;
    }
    public boolean kiemTraTenDangNhap(String tenDangNhap) {
        boolean ketQua = false;
        try {
            // Bước 1: tạo kết nối đến CSDL
            Connection con = JDBCUtil.getConnection();

            // Bước 2: tạo ra đối tượng statement
            String sql = "SELECT * FROM khachhang WHERE tendangnhap=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, tenDangNhap);

            // Bước 3: thực thi câu lệnh SQL
            ResultSet rs = st.executeQuery();

            // Bước 4: kiểm tra kết quả
            if (rs.next()) {
                ketQua = true;
            }

            // Bước 5: đóng kết nối
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ketQua;
    }



}
