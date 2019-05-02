package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext ctx = this.getServletContext();
		request.setCharacterEncoding("UTF-8");
		
		int mno = 0;
		String name = null;
		String email = null;
		String cre_date = null;
		String password = null;
		response.setContentType("text/html; charset=UTF-8");
		try {
			Class.forName(ctx.getInitParameter("driver"));
			conn = DriverManager.getConnection(ctx.getInitParameter("url"), ctx.getInitParameter("username"),
					ctx.getInitParameter("password"));
			String sql = "select MNO,MNAME,EMAIL,CRE_DATE,PWD from MEMBERS where mno = ?";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, request.getParameter("no"));
			rs = stmt.executeQuery();
			while (rs.next()) {
				mno = rs.getInt("MNO");
				name = rs.getString("MNAME");
				email = rs.getString("EMAIL");
				cre_date = rs.getString("CRE_DATE");
				password = rs.getString("PWD");
			}
			PrintWriter out = response.getWriter();
			out.println("<html><head><title>회원 정보</title></head>");
			out.println("<body><h1>회원 정보</h1>");
			out.println("<form action='update' method='post'>");
			out.println("번호: <input type='text' name='mno' value='" + mno + "'><br>");
			out.println("이름: <input type='text' name='name' value='" + name + "'><br>");
			out.println("이메일: <input type='text' name='email'value='" + email + "'><br>");
			out.println("암호: <input type='password' name='password' value ='" + password + "'><br>");
			out.println("가입일: <span>"+cre_date+"</span><br>");
			out.println("<input type='submit' value='수정'>");
			out.println("<input type='reset' value='취소'>");
			out.println("</form>");
			out.println("</body></html>");
		} catch (Exception e) {
			throw new ServletException(e);

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
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		try {
			ServletContext ctx = this.getServletContext();
			Class.forName(ctx.getInitParameter("driver"));
			conn = DriverManager.getConnection(ctx.getInitParameter("url"), ctx.getInitParameter("username"),
					ctx.getInitParameter("password"));
			String sql = "Update MEMBERS set EMAIL = ?, MNAME = ?, MOD_DATE=now() where mno = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, request.getParameter("email"));
			stmt.setString(2, request.getParameter("name"));
			stmt.setString(3, request.getParameter("mno"));
			stmt.executeUpdate();
			response.sendRedirect("list");
		}catch (Exception e) {
			throw new ServletException(e);
			
		}finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
	}

}
