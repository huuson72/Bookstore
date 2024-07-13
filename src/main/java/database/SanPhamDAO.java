package database;

import model.SanPham;
import model.TacGia;
import model.TheLoai;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SanPhamDAO implements DAOInterface<SanPham> {
	public ArrayList<SanPham> selectAll() throws SQLException {
		ArrayList<SanPham> ketQua = new ArrayList<SanPham>();

		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "select * from sanpham";
			PreparedStatement st = con.prepareStatement(sql);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				String masanpham = rs.getString("maSanPham");
				String tensanpham = rs.getString("tenSanPham");
				String matacgia = rs.getString("maTacGia");
				int namxuatban = rs.getInt("namXuatBan");
				double gianhap = rs.getDouble("giaNhap");
				double giagoc = rs.getDouble("giaGoc");
				double giaban = rs.getDouble("giaBan");
				int soluong = (int) rs.getDouble("soLuong");
				String matheloai = rs.getString("maTheLoai");
				String ngonngu = rs.getString("ngonNgu");
				String mota = rs.getString("moTa");

				TacGia tacGia = (new TacGiaDAO().selectById(new TacGia(matacgia, "", null, "")));
				TheLoai theLoai = (new TheLoaiDAO().selectById(new TheLoai(matheloai, "")));

				SanPham sp = new SanPham(masanpham, tensanpham, tacGia, namxuatban, gianhap, giagoc, giaban, soluong,
						theLoai, ngonngu, mota);
				ketQua.add(sp);
			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}

	public static SanPham selectById(SanPham t) {
		SanPham ketQua = null;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM sanpham WHERE maSanPham=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaSanPham());

			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				String masanpham = rs.getString("maSanPham");
				String tensanpham = rs.getString("tenSanPham");
				String matacgia = rs.getString("maTacGia");
				int namxuatban = rs.getInt("namXuatBan");
				double gianhap = rs.getDouble("giaNhap");
				double giagoc = rs.getDouble("giaGoc");
				double giaban = rs.getDouble("giaBan");
				int soluong = rs.getInt("soLuong");
				String matheloai = rs.getString("maTheLoai");
				String ngonngu = rs.getString("ngonNgu");
				String mota = rs.getString("moTa");

				TacGia tacGia = new TacGiaDAO().selectById(new TacGia(matacgia, "", null, ""));
				TheLoai theLoai = new TheLoaiDAO().selectById(new TheLoai(matheloai, ""));
				ketQua = new SanPham(masanpham, tensanpham, tacGia, namxuatban, gianhap, giagoc, giaban, soluong,
						theLoai, ngonngu, mota);
			}

			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}
	public int count() {
		int total = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT COUNT(*) FROM sanpham";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				total = rs.getInt(1);
			}
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}

	public List<SanPham> selectWithPagination(int page, int pageSize) {
		List<SanPham> list = new ArrayList<>();
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM sanpham LIMIT ? OFFSET ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, pageSize);
			pst.setInt(2, (page - 1) * pageSize);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				SanPham sp = new SanPham();
				sp.setMaSanPham(rs.getString("maSanPham"));
				sp.setTenSanPham(rs.getString("tenSanPham"));

				String maTacGia = rs.getString("maTacGia");
				TacGia tacGia = new TacGiaDAO().selectById(new TacGia(maTacGia, "", null, ""));
				sp.setTacGia(tacGia);

				sp.setNamXuatBan(rs.getInt("namXuatBan"));
				sp.setGiaNhap(rs.getDouble("giaNhap"));
				sp.setGiaGoc(rs.getDouble("giaGoc"));
				sp.setGiaBan(rs.getDouble("giaBan"));
				sp.setSoLuong(rs.getInt("soLuong"));

				String maTheLoai = rs.getString("maTheLoai");
				TheLoai theLoai = new TheLoaiDAO().selectById(new TheLoai(maTheLoai, ""));
				sp.setTheLoai(theLoai);

				sp.setNgonNgu(rs.getString("ngonNgu"));
				sp.setMoTa(rs.getString("moTa"));

				list.add(sp);
			}
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	public int insert(SanPham t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "INSERT INTO sanpham (maSanPham,tenSanPham, maTacGia, namXuatBan,"
					+ " giaNhap, giaGoc, giaBan, soLuong, maTheLoai, ngonNgu, moTa) "
					+ " VALUES (?,?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaSanPham());
			st.setString(2, t.getTenSanPham());
			st.setString(3, t.getTacGia().getMaTacGia());
			st.setInt(4, t.getNamXuatBan());
			st.setDouble(5, t.getGiaNhap());
			st.setDouble(6, t.getGiaGoc());
			st.setDouble(7, t.getGiaBan());
			st.setInt(8, t.getSoLuong());
			st.setString(9, t.getTheLoai().getMaTheLoai());
			st.setString(10, t.getNgonNgu());
			st.setString(11, t.getMoTa());

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


	public int insertAll(ArrayList<SanPham> arr) {
		int dem = 0;
		for (SanPham SanPham : arr) {
			dem += this.insert(SanPham);
		}
		return dem;
	}


	public int delete(SanPham t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "DELETE from sanpham " + " WHERE maSanPham=?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaSanPham());

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


	public int deleteAll(ArrayList<SanPham> arr) {
		int dem = 0;
		for (SanPham SanPham : arr) {
			dem += this.delete(SanPham);
		}
		return dem;
	}


	public int update(SanPham t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "UPDATE sanpham " + " SET " + "tenSanPham=?, maTacGia=?, namXuatBan=?, giaNhap=?, giaGoc=?, "
					+ "giaBan=?, soLuong=?, maTheLoai=?, ngonNgu=?, moTa=?" + " WHERE maSanPham=?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getTenSanPham());
			st.setString(2, t.getTacGia().getMaTacGia());
			st.setInt(3, t.getNamXuatBan());
			st.setDouble(4, t.getGiaNhap());
			st.setDouble(5, t.getGiaGoc());
			st.setDouble(6, t.getGiaBan());
			st.setInt(7, t.getSoLuong());
			st.setString(8, t.getTheLoai().getMaTheLoai());
			st.setString(9, t.getNgonNgu());
			st.setString(10, t.getMoTa());
			st.setString(11, t.getMaSanPham());
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

	public  static  List<SanPham> timKiem(String tuKhoa) {
		List<SanPham> ketQua = new ArrayList<>();

		try {
			Connection conn = JDBCUtil.getConnection();
			String sql = "SELECT * FROM sanpham WHERE tenSanPham LIKE ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + tuKhoa + "%");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				SanPham sanPham = new SanPham();
				sanPham.setMaSanPham(rs.getString("maSanPham"));
				sanPham.setTenSanPham(rs.getString("tenSanPham"));
				sanPham.setNamXuatBan(rs.getInt("namXuatBan"));
				sanPham.setGiaNhap(rs.getDouble("giaNhap"));
				sanPham.setGiaGoc(rs.getDouble("giaGoc"));
				sanPham.setGiaBan(rs.getDouble("giaBan"));
				sanPham.setSoLuong(rs.getInt("soLuong"));
				sanPham.setNgonNgu(rs.getString("ngonNgu"));
				sanPham.setMoTa(rs.getString("moTa"));

				// Lấy thông tin Tác giả
				String maTacGia = rs.getString("maTacGia");
				TacGia tacGia = (new TacGiaDAO().selectById(new TacGia(maTacGia, "", null, "")));
				sanPham.setTacGia(tacGia);


				// Lấy thông tin Thể loại
				String maTheLoai = rs.getString("maTheLoai");
				TheLoai theLoai = (new TheLoaiDAO().selectById(new TheLoai(maTheLoai, "")));
				sanPham.setTheLoai(theLoai);
				ketQua.add(sanPham);
			}

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ketQua;
	}

//	public static void main(String[] args) {
//		// Khởi tạo đối tượng SanPham với mã sản phẩm cần tìm
//		SanPham sanPhamTimKiem = new SanPham();
//		sanPhamTimKiem.setMaSanPham("SP001");
//
//		// Tạo đối tượng DAO để gọi phương thức selectById
//		SanPhamDAO sanPhamDAO = new SanPhamDAO();
//
//		// Gọi phương thức selectById và nhận kết quả
//		SanPham ketQua = sanPhamDAO.selectById(sanPhamTimKiem);
//
//		// Kiểm tra và hiển thị kết quả
//		if (ketQua != null) {
//			System.out.println("Thông tin sản phẩm:");
//			System.out.println("Mã sản phẩm: " + ketQua.getMaSanPham());
//			System.out.println("Tên sản phẩm: " + ketQua.getTenSanPham());
//			System.out.println("Tác giả: " + ketQua.getTacGia().getHoVaTen());
//			System.out.println("Năm xuất bản: " + ketQua.getNamXuatBan());
//			System.out.println("Giá nhập: " + ketQua.getGiaNhap());
//			System.out.println("Giá gốc: " + ketQua.getGiaGoc());
//			System.out.println("Giá bán: " + ketQua.getGiaBan());
//			System.out.println("Số lượng: " + ketQua.getSoLuong());
//			System.out.println("Thể loại: " + ketQua.getTheLoai().getTenTheLoai());
//			System.out.println("Ngôn ngữ: " + ketQua.getNgonNgu());
//			System.out.println("Mô tả: " + ketQua.getMoTa());
//		} else {
//			System.out.println("Không tìm thấy sản phẩm với mã sản phẩm đã cho.");
//		}
//	}
//
//	public static void testSelectAll() throws SQLException {
//		SanPhamDAO sanPhamDAO = new SanPhamDAO();
//		ArrayList<SanPham> sanPhams = sanPhamDAO.selectAll();
//
//		System.out.println("Danh sách sản phẩm từ selectAll:");
//		for (SanPham sp : sanPhams) {
//			System.out.println(sp);
//		}
//	}
//
//	public static void testSelectById() throws SQLException {
//		SanPhamDAO sanPhamDAO = new SanPhamDAO();
//		String maSanPham = "SP01";
//		SanPham sanPham = sanPhamDAO.selectById(new SanPham(maSanPham));
//
//		if (sanPham != null) {
//			System.out.println("Sản phẩm với mã " + maSanPham + ":");
//			System.out.println(sanPham);
//		} else {
//			System.out.println("Không tìm thấy sản phẩm với mã " + maSanPham);
//		}
//	}
//}

	public static void main(String[] args) {
		// Khởi tạo từ khóa tìm kiếm
		String tuKhoa = "Harry Potter"; // Đổi thành từ khóa tìm kiếm thực tế của bạn

		// Tạo đối tượng DAO
		SanPhamDAO sanPhamDAO = new SanPhamDAO();

		// Gọi phương thức tìm kiếm
		List<SanPham> ketQua = sanPhamDAO.timKiem(tuKhoa);

		// Hiển thị kết quả tìm kiếm
		if (!ketQua.isEmpty()) {
			System.out.println("Kết quả tìm kiếm cho từ khóa '" + tuKhoa + "':");
			for (SanPham sanPham : ketQua) {
				System.out.println("Mã sản phẩm: " + sanPham.getMaSanPham());
				System.out.println("Tên sản phẩm: " + sanPham.getTenSanPham());

				// Kiểm tra và hiển thị tên tác giả
				TacGia tacGia = sanPham.getTacGia();
				if (tacGia != null) {
					System.out.println("Tác giả: " + tacGia.getHoVaTen());
				} else {
					System.out.println("Tác giả: Không có thông tin");
				}

				System.out.println("Thể loại: " + sanPham.getTheLoai().getTenTheLoai());
				System.out.println("Năm xuất bản: " + sanPham.getNamXuatBan());
				System.out.println("Giá bán: " + sanPham.getGiaBan());
				System.out.println("Số lượng: " + sanPham.getSoLuong());
				System.out.println("--------------------------------");
			}
		} else {
			System.out.println("Không tìm thấy sản phẩm nào cho từ khóa '" + tuKhoa + "'.");
		}
	}
}
