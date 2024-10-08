package database;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DAOInterface<T> {

	public ArrayList<T> selectAll() throws SQLException;

	static <T> T selectById(T t) {

		return null;
	}

	public int insert(T t) throws SQLException;

	public int insertAll(ArrayList<T> arr) throws SQLException;

	public int delete(T t) throws SQLException;

	public int deleteAll(ArrayList<T> arr) throws SQLException;

	public int update(T t) throws SQLException;
}
