package spms.listeners;

import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.commons.dbcp.BasicDataSource;

import spms.controls.LoginController;
import spms.controls.LogoutController;
import spms.controls.MemberAddController;
import spms.controls.MemberDeleteController;
import spms.controls.MemberListController;
import spms.controls.MemberUpdateController;
import spms.dao.MysqlMemberDao;

@WebListener
public class ContextLoaderListener implements ServletContextListener {
	BasicDataSource ds;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		try {
			if (ds != null)
				ds.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {
			ServletContext sc = event.getServletContext();
			ds = new BasicDataSource();
			ds.setDriverClassName(sc.getInitParameter("driver"));
			ds.setUrl(sc.getInitParameter("url"));
			ds.setUsername(sc.getInitParameter("username"));
			ds.setPassword(sc.getInitParameter("password"));
			
			MysqlMemberDao memberDao = new MysqlMemberDao();
			memberDao.setDataSource(ds);
			sc.setAttribute("/auth/login.do", new LoginController().setMemberDao(memberDao));
			sc.setAttribute("/auth/logout.do", new LogoutController());
			sc.setAttribute("/member/list.do", new MemberListController().setMemberDao(memberDao));
			sc.setAttribute("/member/add.do", new MemberAddController().setMemberDao(memberDao));
			sc.setAttribute("/member/update.do", new MemberUpdateController().setMemberDao(memberDao));
			sc.setAttribute("/member/delete.do", new MemberDeleteController().setMemberDao(memberDao));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
