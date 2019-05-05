package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Member;

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
		
		int mno = 0;
		String name = null;
		String email = null;
		String cre_date = null;
		String password = null;
		response.setContentType("text/html; charset=UTF-8");
		try {
			conn = (Connection)ctx.getAttribute("conn");
			String sql = "select MNO,MNAME,EMAIL,CRE_DATE,PWD from MEMBERS where mno = ?";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, request.getParameter("no"));
			rs = stmt.executeQuery();
			while (rs.next()) {
				Member member = new Member().setNo(rs.getInt("MNO")).setName(rs.getString("MNAME")).setEmail(rs.getString("EMAIL"))
											.setCreateDate(rs.getDate("CRE_DATE")).setPassword(rs.getString("PWD"));
				request.setAttribute("member", member);
			}
			RequestDispatcher rd = request.getRequestDispatcher("../member/MemberUpdateForm.jsp");
			rd.forward(request, response);
		
		} catch (Exception e) {
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);

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
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		try {
			ServletContext ctx = this.getServletContext();
			conn = (Connection)ctx.getAttribute("conn");
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

		}
	}

}
