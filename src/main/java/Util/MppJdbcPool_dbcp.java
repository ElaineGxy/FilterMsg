package Util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 用于连接数据库
 * @author gaoxy
 *
 * 2017年8月3日
 */
public class MppJdbcPool_dbcp {
	public static List<Connection> sessionList = new ArrayList<Connection>();
	private static int MPPDBSIZE = 10;
	private static String MPPDBUSER = "";
	private static String MPPDBPASSWD = "";
	private static String MPPDBURL = "";
	private static String MPPDBDRIVER = "";

	public static void MppJdbcPoolinit() {
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
			// �������ļ��ж�ȡ
			// DBUSER = "sysdba";
			MPPDBUSER = propget.getProperty("MPPDBUSER");
			// DBPASSWD = "szoscar55";
			MPPDBPASSWD = propget.getProperty("MPPDBPASSWD");
			// DBURL = "jdbc:oscar://localhost:2003/osrdb";
			MPPDBURL = propget.getProperty("MPPDBURL");
			// DBDRIVER = "com.oscar.Driver";
			MPPDBDRIVER = propget.getProperty("MPPDBDRIVER");
			MPPDBSIZE = new Integer(propget.getProperty("MPPDBSIZE"));
			Class.forName(MPPDBDRIVER);
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
				s = DriverManager.getConnection(MPPDBURL, MPPDBUSER,
						MPPDBPASSWD);
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
