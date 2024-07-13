package database;

import model.TheLoai;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TheLoaiDAO implements DAOInterface<TheLoai> {

	private ArrayList<TheLoai> data = new ArrayList<>();

	
	public ArrayList<TheLoai> selectAll() {
		ArrayList<TheLoai> ketQua = new ArrayList<TheLoai>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM theloai";
			PreparedStatement st = con.prepareStatement(sql);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				String maTheLoai = rs.getString("maTheLoai");
				String tenTheLoai = rs.getString("tenTheLoai");

				TheLoai tl = new TheLoai(maTheLoai, tenTheLoai);
				ketQua.add(tl);
			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}

	
	public TheLoai selectById(TheLoai t) {
		TheLoai ketQua = null;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM theloai WHERE maTheLoai=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaTheLoai());

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				String maTheLoai = rs.getString("maTheLoai");
				String tenTheLoai = rs.getString("tenTheLoai");

				ketQua = new TheLoai(maTheLoai, tenTheLoai);
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

	
	public int insert(TheLoai t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "INSERT INTO theloai (maTheLoai, tenTheLoai) "+
					" VALUES (?,?)";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaTheLoai());
			st.setString(2, t.getTenTheLoai());

			// Bước 3: thực thi câu lệnh SQL
			ketQua = st.executeUpdate();

			// Bước 4:
			System.out.println("Bạn đã thực thi: "+ sql);
			System.out.println("Có "+ ketQua+" dòng bị thay đổi!");

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}

	
	public int insertAll(ArrayList<TheLoai> arr) {
		int dem = 0;
		for (TheLoai theLoai : arr) {
			dem+=this.insert(theLoai);
		}
		return dem;
	}

	
	public int delete(TheLoai t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "DELETE from theloai "+
					" WHERE maTheLoai=?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaTheLoai());

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ketQua = st.executeUpdate();

			// Bước 4:
			System.out.println("Bạn đã thực thi: "+ sql);
			System.out.println("Có "+ ketQua+" dòng bị thay đổi!");

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}

	
	public int deleteAll(ArrayList<TheLoai> arr) {
		int dem = 0;
		for (TheLoai TheLoai : arr) {
			dem += this.delete(TheLoai);
		}
		return dem;
	}

	
	public int update(TheLoai t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "UPDATE theloai "+
					" SET " +
					" tenTheLoai=?"+
					" WHERE maTheLoai=?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getTenTheLoai());
			st.setString(2, t.getMaTheLoai());
			// Bước 3: thực thi câu lệnh SQL

			System.out.println(sql);
			ketQua = st.executeUpdate();

			// Bước 4:
			System.out.println("Bạn đã thực thi: "+ sql);
			System.out.println("Có "+ ketQua+" dòng bị thay đổi!");

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}
//	public ArrayList<TheLoai> selectAll() {
//		ArrayList<TheLoai> theLoaiList = new ArrayList<>();
//		String query = "SELECT * FROM TheLoai";
//
//		try (Connection connection = JDBCUtil.getConnection();
//			 PreparedStatement preparedStatement = connection.prepareStatement(query);
//			 ResultSet resultSet = preparedStatement.executeQuery()) {
//
//			while (resultSet.next()) {
//				TheLoai theLoai = new TheLoai();
//				theLoai.setMaTheLoai(resultSet.getString("maTheLoai"));
//				theLoai.setTenTheLoai(resultSet.getString("tenTheLoai"));
//				theLoaiList.add(theLoai);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return theLoaiList;
//	}
//
//	
//	public TheLoai selectById(TheLoai theLoai) {
//		TheLoai result = null;
//		String query = "SELECT * FROM theloai WHERE maTheLoai = ?";
//
//		try (Connection connection = JDBCUtil.getConnection();
//			 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//
//			preparedStatement.setString(1, theLoai.getMaTheLoai());
//			ResultSet resultSet = preparedStatement.executeQuery();
//
//			if (resultSet.next()) {
//				result = new TheLoai();
//				result.setMaTheLoai(resultSet.getString("maTheLoai"));
//				result.setTenTheLoai(resultSet.getString("tenTheLoai"));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return result;
//	}
//	
//	public int insert(TheLoai theLoai) {
//		int rowsAffected = 0;
//		String query = "INSERT INTO TheLoai (maTheLoai, tenTheLoai) VALUES (?, ?)";
//
//		try (Connection connection = JDBCUtil.getConnection();
//			 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//
//			preparedStatement.setString(1, theLoai.getMaTheLoai());
//			preparedStatement.setString(2, theLoai.getTenTheLoai());
//
//			rowsAffected = preparedStatement.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return rowsAffected;
//	}
//
//	
//	public int insertAll(ArrayList<TheLoai> arr) {
//		int rowsAffected = 0;
//		String query = "INSERT INTO TheLoai (maTheLoai, tenTheLoai) VALUES (?, ?)";
//
//		try (Connection connection = JDBCUtil.getConnection();
//			 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//
//			for (TheLoai theLoai : arr) {
//				preparedStatement.setString(1, theLoai.getMaTheLoai());
//				preparedStatement.setString(2, theLoai.getTenTheLoai());
//				preparedStatement.addBatch();
//			}
//
//			int[] results = preparedStatement.executeBatch();
//			for (int result : results) {
//				rowsAffected += result;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return rowsAffected;
//	}
//
//	
//	public int delete(TheLoai theLoai) {
//		int rowsAffected = 0;
//		String query = "DELETE FROM TheLoai WHERE maTheLoai = ?";
//
//		try (Connection connection = JDBCUtil.getConnection();
//			 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//
//			preparedStatement.setString(1, theLoai.getMaTheLoai());
//
//			rowsAffected = preparedStatement.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return rowsAffected;
//	}
//
//	
//	public int deleteAll(ArrayList<TheLoai> arr) {
//		int rowsAffected = 0;
//		String query = "DELETE FROM TheLoai WHERE maTheLoai = ?";
//
//		try (Connection connection = JDBCUtil.getConnection();
//			 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//
//			for (TheLoai theLoai : arr) {
//				preparedStatement.setString(1, theLoai.getMaTheLoai());
//				preparedStatement.addBatch();
//			}
//
//			int[] results = preparedStatement.executeBatch();
//			for (int result : results) {
//				rowsAffected += result;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return rowsAffected;
//	}
//
//	
//	public int update(TheLoai theLoai) {
//		int rowsAffected = 0;
//		String query = "UPDATE TheLoai SET tenTheLoai = ? WHERE maTheLoai = ?";
//
//		try (Connection connection = JDBCUtil.getConnection();
//			 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//
//			preparedStatement.setString(1, theLoai.getTenTheLoai());
//			preparedStatement.setString(2, theLoai.getMaTheLoai());
//
//			rowsAffected = preparedStatement.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return rowsAffected;
//	}
//
//	public static void main(String[] args) {
//		TheLoaiDAO theLoaiDAO = new TheLoaiDAO();
//
//		// Lấy danh sách thể loại từ CSDL
//		ArrayList<TheLoai> theLoaiList = theLoaiDAO.selectAll();
//
//		// In danh sách thể loại
//		System.out.println("Danh sách thể loại:");
//		for (TheLoai theLoai : theLoaiList) {
//			System.out.println(theLoai);
//		}
//
//	}
}
