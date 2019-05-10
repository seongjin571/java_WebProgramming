package listeners;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import dao.MemberDao;

@WebListener
public class ContextLoaderListener implements ServletContextListener {
	Connection conn;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		try {
			conn.close();
		}catch(Exception e) {
			
		}

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {
			ServletContext sc = event.getServletContext();
			Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(sc.getInitParameter("url"), sc.getInitParameter("username"), 
					sc.getInitParameter("password"));
			MemberDao memberDao = new MemberDao(conn);
			sc.setAttribute("memberDao", memberDao);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
