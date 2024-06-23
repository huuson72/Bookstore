package database;

import model.SanPham;
import model.TacGia;
import model.TheLoai;

import java.sql.*;
import java.util.ArrayList;

public class SanPhamDAO implements DAOInterface<SanPham> {
	public ArrayList<SanPham> selectAll() {
		ArrayList<SanPham> ketQua = new ArrayList<SanPham>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM sanpham";
			PreparedStatement st = con.prepareStatement(sql);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				String masanpham = rs.getString("masanpham");
				String tensanpham = rs.getString("tensanpham");
				String matacgia = rs.getString("matacgia");
				int namxuatban = rs.getInt("namxuatban");
				double gianhap = rs.getDouble("gianhap");
				double giagoc = rs.getDouble("giagoc");
				double giaban = rs.getDouble("giaban");
				int soluong = (int) rs.getDouble("soluong");
				String matheloai = rs.getString("matheloai");
				String ngonngu = rs.getString("ngonngu");
				String mota = rs.getString("mota");

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

	@Override
	public SanPham selectById(SanPham t) {

		SanPham ketQua = null;
		try {
			Connection con = JDBCUtil.getConnection();

			String sql = "SELECT * FROM sanpham WHERE masanpham=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaSanPham());

			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String masanpham = rs.getString("masanpham");
				String tensanpham = rs.getString("tensanpham");
				String matacgia = rs.getString("matacgia");
				int namxuatban = rs.getInt("namxuatban");
				double gianhap = rs.getDouble("gianhap");
				double giagoc = rs.getDouble("giagoc");
				double giaban = rs.getDouble("giaban");
				int soluong = (int) rs.getDouble("soluong");
				String matheloai = rs.getString("matheloai");
				String ngonngu = rs.getString("ngonngu");
				String mota = rs.getString("mota");

				TacGia tacGia = (new TacGiaDAO().selectById(new TacGia(matacgia, "", null, "")));
				TheLoai theLoai = (new TheLoaiDAO().selectById(new TheLoai(matheloai, "")));

				ketQua = new SanPham(masanpham, tensanpham, tacGia, namxuatban, gianhap, giagoc, giaban, soluong,
						theLoai, ngonngu, mota);
				break;
			}

			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}

	@Override
	public int insert(SanPham t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "INSERT INTO sanpham (masanpham,tensanpham, matacgia, namxuatban,"
					+ " gianhap, giagoc, giaban, soluong, matheloai, ngonngu, mota) "
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

	@Override
	public int insertAll(ArrayList<SanPham> arr) {
		int dem = 0;
		for (SanPham SanPham : arr) {
			dem += this.insert(SanPham);
		}
		return dem;
	}

	@Override
	public int delete(SanPham t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "DELETE from sanpham " + " WHERE masanpham=?";

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

	@Override
	public int deleteAll(ArrayList<SanPham> arr) {
		int dem = 0;
		for (SanPham SanPham : arr) {
			dem += this.delete(SanPham);
		}
		return dem;
	}

	@Override
	public int update(SanPham t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "UPDATE sanpham " + " SET " + "tensanpham=?, matacgia=?, namxuatban=?, gianhap=?, giagoc=?, "
					+ "giaban=?, soluong=?, matheloai=?, ngonngu=?, mota=?" + " WHERE masanpham=?";

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


//	@Override
//
//	public ArrayList<SanPham> selectAll() {
//		ArrayList<SanPham> sanPhamList = new ArrayList<>();
//		String query = "SELECT * FROM sanpham";
//
//		try (Connection conn = JDBCUtil.getConnection();
//			 Statement stmt = conn.createStatement();
//			 ResultSet rs = stmt.executeQuery(query)) {
//
//			while (rs.next()) {
//				String masanpham = rs.getString("masanpham");
//				String tensanpham = rs.getString("tensanpham");
//				String matacgia = rs.getString("matacgia");
//				int namxuatban = rs.getInt("namxuatban");
//				double gianhap = rs.getDouble("gianhap");
//				double giagoc = rs.getDouble("giagoc");
//				double giaban = rs.getDouble("giaban");
//				int soluong = (int) rs.getDouble("soluong");
//				String ngonngu = rs.getString("ngonngu");
//				String mota = rs.getString("mota");
//
//				TacGia tacGia = (new TacGiaDAO().selectById(new TacGia(matacgia, "", null, "")));
//				String matheloai = rs.getString("matheloai");
//				TheLoai theLoai = (new TheLoaiDAO().selectById(new TheLoai(matheloai, "")));
//
//				SanPham sp = new SanPham(masanpham, tensanpham, tacGia, namxuatban, gianhap, giagoc, giaban, soluong,
//						theLoai, ngonngu, mota);
//				sanPhamList.add(sp);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return sanPhamList;
//	}
//
//
//
//	@Override
//	public SanPham selectById(SanPham sanPham) {
//		SanPham result = null;
//		String query = "SELECT * FROM sanpham WHERE maSanPham = ?";
//
//		try (Connection conn = JDBCUtil.getConnection();
//			 PreparedStatement stmt = conn.prepareStatement(query)) {
//
//			stmt.setString(1, sanPham.getMaSanPham());
//			ResultSet rs = stmt.executeQuery();
//
//			if (rs.next()) {
//				result = new SanPham();
//				result.setMaSanPham(rs.getString("maSanPham"));
//				result.setTenSanPham(rs.getString("tenSanPham"));
//
//				// Truy vấn thông tin của TacGia từ maTacGia
//				String maTacGia = rs.getString("maTacGia");
//				TacGia tacGia = new TacGiaDAO().selectById(new TacGia(maTacGia)); // Tạo đối tượng TacGia từ maTacGia
//				result.setTacGia(tacGia);
//
//				result.setNamXuatBan(rs.getInt("namXuatBan"));
//				result.setGiaNhap(rs.getDouble("giaNhap"));
//				result.setGiaGoc(rs.getDouble("giaGoc"));
//				result.setGiaBan(rs.getDouble("giaBan"));
//				result.setSoLuong(rs.getInt("soLuong"));
//
//				// Truy vấn thông tin của TheLoai từ maTheLoai
//				String maTheLoai = rs.getString("maTheLoai");
//				TheLoai theLoai = new TheLoaiDAO().selectById(new TheLoai(maTheLoai)); // Tạo đối tượng TheLoai từ maTheLoai
//				result.setTheLoai(theLoai);
//
//				result.setNgonNgu(rs.getString("ngonNgu"));
//				result.setMoTa(rs.getString("moTa"));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return result;
//	}
//
//	@Override
//	public int insert(SanPham sanPham) {
//		try (Connection conn = JDBCUtil.getConnection();
//			 PreparedStatement pstmt = conn.prepareStatement("INSERT INTO sanpham VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
//			pstmt.setString(1, sanPham.getMaSanPham());
//			pstmt.setString(2, sanPham.getTenSanPham());
//			pstmt.setString(3, sanPham.getTacGia().getMaTacGia()); // Hoàn thiện khi có TacGiaDAO
//			pstmt.setInt(4, sanPham.getNamXuatBan());
//			pstmt.setDouble(5, sanPham.getGiaNhap());
//			pstmt.setDouble(6, sanPham.getGiaGoc());
//			pstmt.setDouble(7, sanPham.getGiaBan());
//			pstmt.setInt(8, sanPham.getSoLuong());
//			pstmt.setString(9, sanPham.getTheLoai().getMaTheLoai()); // Hoàn thiện khi có TheLoaiDAO
//			pstmt.setString(10, sanPham.getNgonNgu());
//			pstmt.setString(11, sanPham.getMoTa());
//			return pstmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return 0;
//		}
//	}
//
//	@Override
//	public int insertAll(ArrayList<SanPham> arr) {
//		// Implement insertAll method
//		return 0;
//	}
//
//	@Override
//	public int delete(SanPham sanPham) {
//		// Implement delete method
//		return 0;
//	}
//
//	@Override
//	public int deleteAll(ArrayList<SanPham> arr) {
//		// Implement deleteAll method
//		return 0;
//	}
//
//	@Override
//	public int update(SanPham sanPham) {
//		// Implement update method
//		return 0;
//	}
//
//	public static void main(String[] args) {
//		SanPhamDAO sanPhamDAO = new SanPhamDAO();
//		ArrayList<SanPham> spLst = sanPhamDAO.selectAll();
//		for(SanPham sp :spLst){
//			System.out.println(sp);
//		}
//	}
}
