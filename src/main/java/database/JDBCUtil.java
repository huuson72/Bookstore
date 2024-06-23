package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
    // Thông tin kết nối cơ sở dữ liệu
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=mywebsite;encrypt=false;trustServerCertificate=true;characterEncoding=UTF-8" ;

    private static final String USER = "sa";
    private static final String PASSWORD = "070203";

    static {
        try {
            // Đăng ký driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Phương thức để lấy kết nối đến cơ sở dữ liệu
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Phương thức để đóng kết nối, statement và result set
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
