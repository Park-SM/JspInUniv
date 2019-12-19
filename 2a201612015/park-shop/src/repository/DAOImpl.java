package repository;

import java.sql.*;

public class DAOImpl implements DAO {
	
	private Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	Statement stmt = null;

	
	// Create connection instance and return created instance.
	@Override
	public Connection getConnection() {
		// TODO Auto-generated method stub

		try {
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "a201612015";
			//String user = "parksm";
			String password = "park#godqhr1";

			// Loading driver
			// "mysql-connector-java-8.0.18.jar" is in WEB-INF/lib or %CATALINA_HOME%\bin
			// "com.mysql.jdbc.Driver" will be changed to "com.mysql.cj.jdbc.Driver" when upper 5.7 version.
			Class.forName("oracle.jdbc.OracleDriver");
			
			// Create connection instance using connection driver
			conn = DriverManager.getConnection(url, user, password);
			
		} catch (SQLException | ClassNotFoundException e) {
			
			// conn variable has null value at here.
			e.printStackTrace();
		}
		
		return this.conn;
	}

	@Override
	public void closeResource(ResultSet rs, PreparedStatement pstmt, Statement stmt, Connection conn) {
		// TODO Auto-generated method stub
		
		if (rs != null) {
			try {
				// Return memory of ResultSet instance.
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (pstmt != null) {
			try {
				// Return memory of PreparedStatement instance.
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (stmt != null) {
			try {
				// Return memory of Statement instance.
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (conn != null) {
			try {
				// #1 Return to Connection pool. - Fast
				// #2 Remove memory of Connection instance.
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	
	}

}
