package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import dto.Member;

public class MemberDao {
	Connection conn;
	PreparedStatement stmt = null;
	ResultSet rs = null;
//	public void setConnection(Connection conn) {
//		this.conn = conn;
//	}
	public MemberDao(Connection conn) {
		this.conn = conn;
	}
	public List<Member> selectList() throws Exception{

		
		try {
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
		}
		
	}
	
	public int insert(Member member) throws Exception{
		try {
			
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
		}
	}
	
	public Member selectOne(int no) throws Exception{
		try {
			Member member = null;
			String sql = "select MNO,MNAME,EMAIL,CRE_DATE,PWD from MEMBERS where mno = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,no);
			rs = stmt.executeQuery();
			while (rs.next()) {
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

		}
		
	}
//	
	public int update(Member member) throws Exception{
		
		try {
			String sql = "Update MEMBERS set EMAIL = ?, MNAME = ?, MOD_DATE=now() where mno = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getEmail());
			stmt.setString(2, member.getName());
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

		}
	}
//	
//	public Member exist(String email, String password) throws Exception{
//		
//	}

	
	

}
