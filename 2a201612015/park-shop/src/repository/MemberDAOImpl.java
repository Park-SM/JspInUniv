package repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import domain.Member;

public class MemberDAOImpl extends DAOImpl implements MemberDAO {
	
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	@Override
	public int create(Member m) {
		int ret = 0;
		
		try {
			// Get statement instance from connection instance.
			conn = this.getConnection();
			stmt = conn.createStatement();
			// Write query.
			String query = "insert into ma_201612015 values ('"
					+ m.getId() + "', '"
					+ m.getPw() + "', '"
					+ m.getName() + "', '"
					+ m.getPhone() + "', '"
					+ m.getEmail() + "', '"
					+ m.getCountry() + "', '"
					+ m.getRegDate() + "', '"
					+ m.getRank() + "')";

			// Execute insert query.
			ret = stmt.executeUpdate(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		
		
		return ret;
	}

	@Override
	public int update(Member m) {
		int ret = 0;
		
		try {
			// Get statement instance from connection instance.
			conn = this.getConnection();
			stmt = conn.createStatement();
			// Write query.
			String query = "update ma_201612015 set name=?, phone=?, email=?, country=?, rank=? where id=? and pw=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getName());
			pstmt.setString(2, m.getPhone());
			pstmt.setString(3, m.getEmail());
			pstmt.setString(4, m.getCountry());
			pstmt.setString(5, m.getRank());
			pstmt.setString(6, m.getId());
			pstmt.setString(7, m.getPw());

			// Execute insert query.
			ret = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		
		return ret;
	}

	@Override
	public int delete(Member m) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Member readList(String id) {
		// TODO Auto-generated method stub
		Member model = null;
		
		try {
			//String sql = "select * from ma_201612015 where id='" + id + "'";
			String sql = "select * from ma_201612015 where id=?";
			conn = this.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  id);
			//stmt = conn.createStatement();
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				model = new Member(rs.getString("id"), rs.getString("pw"),
						rs.getString("name"), rs.getString("phone"), rs.getString("email"), rs.getString("country"), rs.getString("regdate"), rs.getString("rank"));
				return model;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ArrayList<Member> selectAll(String sortMethod, String search) {
		
		ArrayList<Member> modelList = null;
		
		try {
			if (search.equals("")) search = "";
			else search = "where name like '%" + search + "%'";
			
			System.out.println("Search: " + search);
			String sql;
			if (sortMethod.equals("0")) 
				sql = "select * from ma_201612015" + search;
			else if (sortMethod.equals("1"))
				sql = "select * from ma_201612015 " + search + " order by name";
			else
				sql = "select * from ma_201612015 " + search + " order by id";
			
			conn = this.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			modelList = new ArrayList<Member>();
			while (rs.next()) {
				modelList.add(new Member(rs.getString("id"), rs.getString("pw"),
						rs.getString("name"), rs.getString("phone"), rs.getString("email"), rs.getString("country"), rs.getString("regdate"), rs.getString("rank")));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return modelList;
	}
	
	public void delete(String id) {
		try {
			String sql = "delete from ma_201612015 where id=?";
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
						rs.getString("name"), rs.getString("phone"), rs.getString("email"), rs.getString("country"), rs.getString("regdate"), rs.getString("rank"));
				return true;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
