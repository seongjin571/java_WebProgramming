package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			ServletContext ctx = this.getServletContext();
			Class.forName(ctx.getInitParameter("driver"));
			conn = DriverManager.getConnection(ctx.getInitParameter("url"), ctx.getInitParameter("username"), ctx.getInitParameter("password"));
			String sql = "select MNO,MNAME,EMAIL,CRE_DATE from MEMBERS order by MNO ASC";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html><head><title>회원목록</title></head><body>");
			out.println("<h1>회원 목록</h1>");
			out.println("<p><a href='add'>신규 회원</a></p>");
			while (rs.next()) {
				out.println(
						rs.getInt("MNO") + "," +
						"<a href='update?no=" + rs.getInt("MNO") + "'>" +
						rs.getString("MNAME") + "</a>," +
						rs.getString("EMAIL") + "," + 
						rs.getDate("CRE_DATE") + 
						"<a href='delete?no=" + rs.getInt("MNO") + 
						"'>[삭제]</a><br>");
			}
			out.println("</body></html>");
		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			try {
				rs.close();
			} catch (Exception e) {
			}
			try {
				stmt.close();
			} catch (Exception e) {
			}
			try {
				conn.close();
			} catch (Exception e) {
			}

		}

	}

}
