package listeners;

import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.commons.dbcp.BasicDataSource;

import dao.MemberDao;

@WebListener
public class ContextLoaderListener implements ServletContextListener {
//	DBConnectionPool connPool;
	BasicDataSource ds;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
//		connPool.closeAll();
			try {
				if(ds != null)
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
			
			MemberDao memberDao = new MemberDao();
			memberDao.setDataSource(ds);
			sc.setAttribute("memberDao", memberDao);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
