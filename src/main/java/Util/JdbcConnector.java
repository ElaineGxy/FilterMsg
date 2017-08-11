package Util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JdbcConnector {
	public static List<Connection> sessionList = new ArrayList<Connection>();
	private static int MPPDBSIZE = 10;
	private static String ODBUSER = "";
	private static String ODBPASSWD = "";
	private static String ODBURL = "";
	private static String ODBDRIVER = "";

	//静态代码块：加载变量值
	static{
		try {
			Properties propget = new Properties();
			try {
				String relativelyPath = System.getProperty("user.dir");
				String filePath = relativelyPath + "/cts_env.properties";
				FileInputStream in = new FileInputStream(filePath);
				propget.load(in);
				in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
/*			// DBUSER = "sysdba";
			ODBUSER = propget.getProperty("ODBUSER");
			// DBPASSWD = "szoscar55";
			ODBPASSWD = propget.getProperty("ODBPASSWD");
			// DBURL = "jdbc:oscar://localhost:2003/osrdb";
			ODBURL = propget.getProperty("ODBURL");
			// DBDRIVER = "com.oscar.Driver";
			ODBDRIVER = propget.getProperty("ODBDRIVER");
			MPPDBSIZE = new Integer(propget.getProperty("ODBSIZE"));
			Class.forName(ODBDRIVER);*/
			ODBUSER = propget.getProperty("MYUSER");
			ODBPASSWD = propget.getProperty("ODBPASSWD");
			ODBURL = propget.getProperty("MYURL");
			ODBDRIVER = propget.getProperty("MYDRIVER");
			Class.forName(ODBDRIVER);
		} catch (Throwable e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	public static Connection getConnection() {
		Connection s = null;
		synchronized (sessionList) {
			if (sessionList.size() > 0)
				s = sessionList.remove(0);
		}
		if (s == null) {
			try {
				s = DriverManager.getConnection(ODBURL, ODBUSER,
						"gaoxiyan4429");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return s;
	}

	public static void closeConn(Connection session) {
		synchronized (sessionList) {
			try {
				if (!session.isClosed() && session != null) {
					if (sessionList.size() >= MPPDBSIZE) {
						session.close();
					} else {
						sessionList.add(session);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
