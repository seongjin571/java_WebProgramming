package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/member/list")
public class MemberListServlet extends GenericServlet {

	private static final long serialVersionUID = 1L;
	private static String dburl = "jdbc:mysql://localhost/javaweb?characterEncoding=UTF-8&serverTimezone=UTC";
	private static String dbUser = "root";
	private static String dbpasswd = "7dnjf29dlf!";

	@Override
	public void service(ServletRequest reeuqest, ServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
			String sql = "select MNO,MNAME,EMAIL,CRE_DATE from MEMBERS order by MNO ASC";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html><head><title>회원목록</title></head><body>");
			while (rs.next()) {
				out.println(rs.getInt("MNO") + "," + rs.getString("MNAME") + "," + rs.getString("EMAIL") + ","
						+ rs.getString("CRE_DATE") + "<BR>");
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
