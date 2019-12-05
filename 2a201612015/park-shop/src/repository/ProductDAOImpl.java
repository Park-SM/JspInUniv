package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import domain.Member;
import domain.Product;

public class ProductDAOImpl extends DAOImpl implements ProductDAO {

	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	/*
	 * PreparedStatement class can be cashing for result of SQL compile. Therefore when recycling, one SQL is fast better than one of SQL for Statement class.
	 * and possible using SQL bind variable. Since this class can be auto-parsing, better than Statement class when get user data.
	 */
	
	@Override
	public int create(Product p) {
		int ret = 0;
		
		try {
			// Get statement instance from connection instance.
			conn = this.getConnection();
			stmt = conn.createStatement();
			// Write query.
			String query = "insert into p_201612015 values ('" +
					p.getProductId() + "', '" +
					p.getPname() + "', " +
					p.getUnitPrice() + ", '" +
					p.getManufacturer() + "', '" +
					p.getDescription() + "', '" +
					p.getCategory() + "', " +
					p.getUnitsInStock() + ", '" +
					p.getCondition() + "', " +
					p.getQuantity() + ", '" +
					p.getFilename() + "')";

			// Execute insert query.
			ret = stmt.executeUpdate(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		
		
		return ret;
	}
	
	@Override
	public int update(Product p) {
		int ret = 0;
		
		try {
			// Get statement instance from connection instance.
			conn = this.getConnection();
			stmt = conn.createStatement();
			// Write query.
			String query = "update p_201612015 set pname=?, unitPrice=?, description=?, manufacturer=?"
					+ ", category=?, unitsInStock=?, condition=?, quantity=?, filename=? where productId=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, p.getPname());
			pstmt.setInt(2, p.getUnitPrice());
			pstmt.setString(3, p.getDescription());
			pstmt.setString(4, p.getManufacturer());
			pstmt.setString(5, p.getCategory());
			pstmt.setLong(6, p.getUnitsInStock());
			pstmt.setString(7, p.getCondition());
			pstmt.setInt(8, p.getQuantity());
			pstmt.setString(9, p.getFilename());
			pstmt.setString(10, p.getProductId());

			// Execute insert query.
			ret = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		
		return ret;
	}

	@Override
	public int delete(Product m) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Product readList(String id) {
		// TODO Auto-generated method stub
		Product model = null;
		
		try {
			//String sql = "select * from ma_201612015 where id='" + id + "'";
			String sql = "select * from p_201612015 where productId=?";
			conn = this.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  id);
			//stmt = conn.createStatement();
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				model = new Product(rs.getString(1),
						rs.getString(2),
						rs.getInt(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getLong(7),
						rs.getString(8),
						rs.getInt(9),
						rs.getString(10));
				
				return model;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ArrayList<Product> selectAll() {
		
		ArrayList<Product> modelList = null;
		
		try {
			String sql = "select * from p_201612015";
			conn = this.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			modelList = new ArrayList<Product>();
			while (rs.next()) {
				modelList.add(new Product(
							rs.getString(1),
							rs.getString(2),
							rs.getInt(3),
							rs.getString(4),
							rs.getString(5),
							rs.getString(6),
							rs.getLong(7),
							rs.getString(8),
							rs.getInt(9),
							rs.getString(10)
						));
						
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return modelList;
	}
	
	public void delete(String id) {
		try {
			String sql = "delete from p_201612015 where productId=?";
			conn = this.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	public boolean duplicationID(String id) {
		try {
			String sql = "select * from ma_201612015 where id=?";
			conn = this.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if (rs.next())
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			
		}
		
		return false;
	}
	
	public boolean confirmID(String id, String pw) {
		// TODO Auto-generated method stub
		Member model = null;
		
		try {
			//String sql = "select * from ma_201612015 where id='" + id + "'";
			String sql = "select * from ma_201612015 where id=? and pw=?";
			conn = this.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  id);
			pstmt.setString(2,  pw);
			//stmt = conn.createStatement();
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				model = new Member(rs.getString("id"), rs.getString("pw"),
						rs.getString("name"), rs.getString("phone"), rs.getString("email"), rs.getString("country"));
				return true;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
