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
			String query = "insert into Product values ('" +
					p.getProductId() + "', '" +
					p.getPname() + "', " +
					p.getUnitPrice() + ", '" +
					p.getDescription() + "', '" +
					p.getManufacturer() + "', '" +
					p.getCategory() + "', " +
					p.getUnitsInStock() + ", '" +
					p.getCondition() + "', '" +
					p.getFilename() + "', " +
					p.getQuantity() + ")";

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
			String query = "update Product set pname=?, unitPrice=?, description=?, manufacturer=?"
					+ ", category=?, unitsInStock=?, conditions=?, quantity=?, filename=? where productId=?";
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
			String sql = "select * from Product where productId=?";
			conn = this.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  id);
			//stmt = conn.createStatement();
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				model = new Product();
				model.setProductId(rs.getString(1));
				model.setPname(rs.getString(2));
				model.setUnitPrice(rs.getInt(3));
				model.setDescription(rs.getString(4));
				model.setManufacturer(rs.getString(5));
				model.setCategory(rs.getString(6));
				model.setUnitsInStock(rs.getInt(7));
				model.setCondition(rs.getString(8));
				model.setFilename(rs.getString(9));
				model.setQuantity(rs.getInt(10));
				
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
			String sql = "select * from Product order by pname";
			
			conn = this.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			
			modelList = new ArrayList<Product>();
			while (rs.next()) {
				Product model = new Product();
				
				model.setProductId(rs.getString(1));
				model.setPname(rs.getString(2));
				model.setUnitPrice(rs.getInt(3));
				model.setDescription(rs.getString(4));
				model.setManufacturer(rs.getString(5));
				model.setCategory(rs.getString(6));
				model.setUnitsInStock(rs.getInt(7));
				model.setCondition(rs.getString(8));
				model.setFilename(rs.getString(9));
				model.setQuantity(rs.getInt(10));
				
				modelList.add(model);
			}
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return modelList;
	}
	
	public int selectAllCount() {
		return this.selectAll().size();
	}
	
	public void delete(String id) {
		try {
			String sql = "delete from Product where productId=?";
			conn = this.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	


}
