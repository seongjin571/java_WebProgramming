package spms.servlet;

import java.io.IOException;
import spms.dao.MemberDao;
import spms.dto.Member;
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

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			ServletContext sc = this.getServletContext();
			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
			int no = Integer.parseInt(request.getParameter("no"));
			request.setAttribute("member", memberDao.selectOne(no));
			request.setAttribute("viewUrl", "/member/MemberUpdateForm.jsp");
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			response.setContentType("text/html; charset=UTF-8");
			ServletContext sc = this.getServletContext();
			Connection conn = (Connection) sc.getAttribute("conn");
			MemberDao memberDao = new MemberDao();
			Member member = (Member) request.getAttribute("member");
			memberDao.update(member);
			request.setAttribute("viewUrl", "member/MemberList.jsp");
	

		} catch (Exception e) {
			throw new ServletException(e);
		}

	}

}
