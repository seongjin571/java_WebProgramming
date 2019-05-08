package spms.servlets;

import java.io.IOException;
import dao.MemberDao;
import dto.Member;
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



@SuppressWarnings("serial")
@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			ServletContext ctx = this.getServletContext();
			Connection conn = (Connection) ctx.getAttribute("ctx");
			response.setContentType("text/html; charset=UTF-8");
			MemberDao memberDao = new MemberDao(conn);
			int no = Integer.parseInt(request.getParameter("no"));
			request.setAttribute("member", memberDao.selectOne(no));

			RequestDispatcher rd = request.getRequestDispatcher("../member/MemberUpdateForm.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			response.setContentType("text/html; charset=UTF-8");
			ServletContext sc = this.getServletContext();
			Connection conn = (Connection) sc.getAttribute("conn");
			MemberDao memberDao = new MemberDao(conn);
			Member member = new Member().setEmail(request.getParameter("email")).setName(request.getParameter("name"))
					.setNo(Integer.parseInt(request.getParameter("no")));

			memberDao.update(member);
			response.sendRedirect("list");

		} catch (Exception e) {
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		}

	}

}
