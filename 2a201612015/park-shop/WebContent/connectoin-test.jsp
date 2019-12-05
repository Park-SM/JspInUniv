<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="java.sql.*"%> 
<html>
<head>
<title>ConnectionTest</title>
</head>
<body>
	<%
		Connection conn = null;
		try {
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "a201612015";
			String password = "park#godqhr1";

			// Loading driver
			// "mysql-connector-java-8.0.18.jar" is in WEB-INF/lib or %CATALINA_HOME%\bin
			// "com.mysql.jdbc.Driver" will be changed to "com.mysql.cj.jdbc.Driver" when upper 5.7 version.
			Class.forName("oracle.jdbc.OracleDriver");
			
			// Create connection instance using connetion information.
			conn = DriverManager.getConnection(url, user, password);
			
			out.println("데이터베이스 연결이 성공했습니다.");
			
			// Write query.
			String query = "insert into ma_201612015 (id, pw, name) values ('admin999', 'amidn1212', 'park_SM')";
			
			// Get statement instance from connection instance.
			Statement stmt = conn.createStatement();
			
			// Execute insert query.
			stmt.executeUpdate(query);
			
		} catch (SQLException ex) {
			out.println("데이터베이스 연결이 실패했습니다.<br>");
			out.println("SQLException: " + ex.getMessage());
		} finally {
			// Close the conn instance.
			if (conn != null) conn.close();
		}
	%>
</body>
</html>