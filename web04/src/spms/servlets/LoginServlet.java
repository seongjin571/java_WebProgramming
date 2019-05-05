package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Member;

@SuppressWarnings("serial")
@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/auth/LoginForm.jsp");
		rd.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext sc = this.getServletContext();
		Connection conn = null;
		PreparedStatement smt = null;
		ResultSet rs = null;
		try {
			conn = (Connection)sc.getAttribute("conn");
			String sql = "SELECT mname, email FROM members WHERE email=? and pwd=?";
			smt = conn.prepareStatement(sql);
			smt.setString(1, request.getParameter("email"));
			smt.setString(2, request.getParameter("password"));
			rs = smt.executeQuery();
			
			if(rs.next()) {
				Member member = new Member().setEmail(rs.getString("email")).setName(rs.getString("mname"));
				HttpSession session = request.getSession();
				session.setAttribute("member", member);
				response.sendRedirect("../member/list");
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("/auth/LoginFail.jsp");
				rd.forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {	
				try {
					if(rs != null)
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}try {
					if(smt != null)
					smt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
	}

}
