package database;

import model.TacGia;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class TacGiaDAO implements DAOInterface<TacGia> {
	public ArrayList<TacGia> selectAll() {
		ArrayList<TacGia> ketQua = new ArrayList<TacGia>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM tacgia";
			PreparedStatement st = con.prepareStatement(sql);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while(rs.next()) {
				String maTacGia = rs.getString("maTacGia");
				String hoVaTen = rs.getString("hoVaTen");
				Date ngaySinh = rs.getDate("ngaySinh");
				String tieuSu = rs.getString("tieuSu");

				TacGia tg = new TacGia(maTacGia, hoVaTen, (java.sql.Date) ngaySinh, tieuSu);
				ketQua.add(tg);
			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}


	public TacGia selectById(TacGia t) {
		TacGia ketQua = null;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM tacgia WHERE maTacGia=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaTacGia());

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while(rs.next()) {
				String maTacGia = rs.getString("maTacGia");
				String hoVaTen = rs.getString("hoVaTen");
				Date ngaySinh = rs.getDate("ngaySinh");
				String tieuSu = rs.getString("tieuSu");

				ketQua = new TacGia(maTacGia, hoVaTen, (java.sql.Date) ngaySinh, tieuSu);
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


	
	public int insert(TacGia t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "INSERT INTO tacgia (maTacGia, hoVaTen, ngaySinh, tieuSu) "+
					" VALUES (?,?,?,?)";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaTacGia());
			st.setString(2, t.getHoVaTen());
			st.setDate(3, t.getNgaySinh());
			st.setString(4, t.getTieuSu());

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

	
	public int insertAll(ArrayList<TacGia> arr) {
		int dem = 0;
		for (TacGia tacGia : arr) {
			dem+=this.insert(tacGia);
		}
		return dem;
	}

	
	public int delete(TacGia t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "DELETE from tacgia "+
					" WHERE maTacGia=?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaTacGia());

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

	
	public int deleteAll(ArrayList<TacGia> arr) {
		int dem = 0;
		for (TacGia tacGia : arr) {
			dem+=this.delete(tacGia);
		}
		return dem;
	}

	
	public int update(TacGia t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "UPDATE tacgia "+
					" SET " +
					" hoVaTen=?"+
					", ngaySinh=?"+
					", tieuSu=?"+
					" WHERE maTacGia=?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getHoVaTen());
			st.setDate(2, t.getNgaySinh());
			st.setString(3, t.getTieuSu());
			st.setString(4, t.getMaTacGia());
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



	public static void main(String[] args) {
		TacGiaDAO tgd = new TacGiaDAO();
//		ArrayList<TacGia> kq = tgd.selectAll();
//		for (TacGia tacGia : kq) {
//			System.out.println(tacGia.toString());
//		}
//
//
//		TacGia tg = tgd.selectById(new TacGia("TG1", "", null, ""));
//		System.out.println(tg);
//

//		TacGia tg_new = new TacGia("TG10", "David", new Date(2000-1900, 10, 15), "");
//		tgd.insert(tg_new);

//		TacGia tg_new = new TacGia("TG10", "David", new Date(2000-1900, 10, 15), "");
//		tgd.delete(tg_new);


		TacGia tg = tgd.selectById(new TacGia("TG01", "", null, ""));
		System.out.println(tg);
		tg.setTieuSu("TIỂU SỬ ĐÃ BỊ THAY ĐỔI");

		tgd.update(tg);
	}

//	
//	public ArrayList<TacGia> selectAll() {
//		ArrayList<TacGia> tacGiaList = new ArrayList<>();
//        Connection connection = null;
//        try {
//            connection = JDBCUtil.getConnection();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        String query = "SELECT * FROM TacGia";
//		try {
//			Statement statement = connection.createStatement();
//			ResultSet resultSet = statement.executeQuery(query);
//			while (resultSet.next()) {
//				String maTacGia = resultSet.getString("maTacGia");
//				String hoVaTen = resultSet.getString("hoVaTen");
//				Date ngaySinh = resultSet.getDate("ngaySinh");
//				String tieuSu = resultSet.getString("tieuSu");
//
//				TacGia tacGia = new TacGia(maTacGia, hoVaTen, (java.sql.Date) ngaySinh, tieuSu);
//				tacGiaList.add(tacGia);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			JDBCUtil.close(connection);
//		}
//		return tacGiaList;
//	}
//
//	
//
//
//	public TacGia selectById(TacGia t) {
//		TacGia tacGia = null;
//		String query = "SELECT * FROM tacgia WHERE maTacGia = ?";
//
//		try (Connection connection = JDBCUtil.getConnection();
//			 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//
//			preparedStatement.setString(1, t.getMaTacGia());
//			ResultSet resultSet = preparedStatement.executeQuery();
//
//			if (resultSet.next()) {
//				tacGia = new TacGia();
//				tacGia.setMaTacGia(resultSet.getString("maTacGia"));
//				tacGia.setHoVaTen(resultSet.getString("hoVaTen"));
//				tacGia.setNgaySinh(resultSet.getDate("ngaySinh"));
//				tacGia.setTieuSu(resultSet.getString("tieuSu"));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return tacGia;
//	}
//	
//	public int insert(TacGia t) {
//		int result = 0;
//        Connection connection = null;
//        try {
//            connection = JDBCUtil.getConnection();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        String query = "INSERT INTO TacGia (maTacGia, hoVaTen, ngaySinh, tieuSu) VALUES (?, ?, ?, ?)";
//		try {
//			PreparedStatement preparedStatement = connection.prepareStatement(query);
//			preparedStatement.setString(1, t.getMaTacGia());
//			preparedStatement.setString(2, t.getHoVaTen());
//			preparedStatement.setDate(3, new java.sql.Date(t.getNgaySinh().getTime()));
//			preparedStatement.setString(4, t.getTieuSu());
//
//			result = preparedStatement.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			JDBCUtil.close(connection);
//		}
//		return result;
//	}
//
//	
//	public int insertAll(ArrayList<TacGia> arr) {
//		int result = 0;
//		for (TacGia tacGia : arr) {
//			result += insert(tacGia);
//		}
//		return result;
//	}
//
//	
//	public int delete(TacGia t) {
//		int result = 0;
//        Connection connection = null;
//        try {
//            connection = JDBCUtil.getConnection();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        String query = "DELETE FROM TacGia WHERE maTacGia = ?";
//		try {
//			PreparedStatement preparedStatement = connection.prepareStatement(query);
//			preparedStatement.setString(1, t.getMaTacGia());
//
//			result = preparedStatement.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			JDBCUtil.close(connection);
//		}
//		return result;
//	}
//
//	
//	public int deleteAll(ArrayList<TacGia> arr) {
//		int result = 0;
//		for (TacGia tacGia : arr) {
//			result += delete(tacGia);
//		}
//		return result;
//	}
//
//	
//	public int update(TacGia t) {
//		int result = 0;
//        Connection connection = null;
//        try {
//            connection = JDBCUtil.getConnection();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        String query = "UPDATE TacGia SET hoVaTen = ?, ngaySinh = ?, tieuSu = ? WHERE maTacGia = ?";
//		try {
//			PreparedStatement preparedStatement = connection.prepareStatement(query);
//			preparedStatement.setString(1, t.getHoVaTen());
//			preparedStatement.setDate(2, new java.sql.Date(t.getNgaySinh().getTime()));
//			preparedStatement.setString(3, t.getTieuSu());
//			preparedStatement.setString(4, t.getMaTacGia());
//
//			result = preparedStatement.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			JDBCUtil.close(connection);
//		}
//		return result;
//	}
//
//	public static void main(String[] args) {
//		TacGiaDAO tacGiaDAO = new TacGiaDAO();
//
//		// Lấy danh sách tất cả các tác giả từ cơ sở dữ liệu
//		ArrayList<TacGia> tacGiaList = tacGiaDAO.selectAll();
//
//		// In ra thông tin của từng tác giả sử dụng phương thức toString
//		System.out.println("Danh sách tác giả:");
//		for (TacGia tacGia : tacGiaList) {
//			System.out.println(tacGia.toString());
//			System.out.println("----------------------");
//		}
//	}


}