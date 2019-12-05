package repository;

// Import ResultSet, PreparedStatement, Statement, Connection class;
import java.sql.*;

public interface DAO {

	Connection getConnection();
	void closeResource(ResultSet rs, PreparedStatement pstmt, Statement stmt, Connection conn);
	
}
