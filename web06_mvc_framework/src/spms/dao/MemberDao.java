package spms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import spms.dto.Member;

public class MemberDao {
	PreparedStatement stmt = null;
	ResultSet rs = null;

	DataSource ds;
	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}
	public List<Member> selectList() throws Exception{
		Connection conn = null;
		try {
			conn = ds.getConnection();
			String sql = "SELECT mno, mname, email, cre_date from members order by mno asc";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			List<Member> members = new ArrayList<Member>();
			while(rs.next()) {
				members.add(new Member().setNo(rs.getInt("mno")).setName(rs.getString("mname"))
						.setEmail(rs.getString("email")).setCreateDate(rs.getDate("cre_date")));
			}
			return members;
		}catch(Exception e) { 
			throw e;
		}finally {
			try{if(rs!=null) rs.close();}catch(Exception e) {}
			
			try{if(stmt!=null) stmt.close();}catch(Exception e) {}
			try {if(conn != null) conn.close();} catch(Exception e) {}
		}
		
	}
	
	public int insert(Member member) throws Exception{
		Connection conn = null;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(
					"INSERT INTO MEMBERS(EMAIL,PWD,MNAME,CRE_DATE,MOD_DATE)" + " VALUES (?,?,?,NOW(),NOW())");
			stmt.setString(1, member.getEmail());
			stmt.setString(2, member.getPassword());
			stmt.setString(3, member.getName());
			int result = stmt.executeUpdate();
			return result;

		} catch (Exception e) {
			throw e;

		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
			}
			try {if(conn != null) conn.close();} catch(Exception e) {}
		}
	}
	
	public Member selectOne(int no) throws Exception{
		Connection conn = null;
		try {
			conn = ds.getConnection();
			Member member = null;
			String sql = "select MNO,MNAME,EMAIL,CRE_DATE,PWD from MEMBERS where mno="+no;
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next()) {
				member = new Member().setNo(rs.getInt("MNO")).setName(rs.getString("MNAME")).setEmail(rs.getString("EMAIL"))
									 .setCreateDate(rs.getDate("CRE_DATE")).setPassword(rs.getString("PWD"));
			}
			return member;
		
		} catch (Exception e) {
			throw e;

		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
			}
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
			}
			try {if(conn != null) conn.close();} catch(Exception e) {}
		}
		
	}
	
	public int update(Member member) throws Exception{
		Connection conn = null;
		
		try {
			conn = ds.getConnection();
			String sql = "Update MEMBERS set EMAIL = ?, MNAME = ?, MOD_DATE=now() where mno = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getEmail());
			stmt.setString(2, member.getName());
			stmt.setInt(3, member.getNo());
			int result = stmt.executeUpdate();
			return result;
			
		} catch (Exception e) {
			throw e;

		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
			}
			try {if(conn != null) conn.close();} catch(Exception e) {}
		}
	}
	
	public Member exist(String email, String password) throws Exception{
		Connection conn = null;
		try {
			conn = ds.getConnection();
			Member member =null;
			String sql = "SELECT mname, email FROM members WHERE email=? and pwd=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				 member = new Member().setEmail(rs.getString("email")).setName(rs.getString("mname"));
			}
			return member;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {	
				try {
					if(rs != null)
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}try {
					if(stmt != null)
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {if(conn != null) conn.close();} catch(Exception e) {}
		}
		return null;
	}
	
	public int delete(int no) throws Exception{
		Connection conn = null;
		try {
			conn = ds.getConnection();
			String sql = "delete from MEMBERS where mno = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,no);
			int result = stmt.executeUpdate();
			return result;
		} catch (Exception e) {
			throw e;
		} finally {

			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {if(conn != null) conn.close();} catch(Exception e) {}

		}
	}

}
