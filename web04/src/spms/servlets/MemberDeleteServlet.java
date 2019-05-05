package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet {
	Connection conn = null;
	PreparedStatement stat = null;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext ctx = this.getServletContext();
		try {
			conn = (Connection)ctx.getAttribute("conn");
			String sql = "delete from MEMBERS where mno = ?";
			stat = conn.prepareStatement(sql);
			stat.setString(1, request.getParameter("no"));
			stat.executeUpdate();
			response.sendRedirect("list");
		} catch (Exception e) {
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		} finally {

			try {
				if (stat != null)
					stat.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

}
