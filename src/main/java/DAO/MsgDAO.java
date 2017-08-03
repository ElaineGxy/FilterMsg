package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Util.MppJdbcPool_dbcp;


public class MsgDAO {
	public Connection connection;
	
	public MsgDAO() {
		MppJdbcPool_dbcp.MppJdbcPoolinit();
		this.connection = MppJdbcPool_dbcp.getConnection();
	}
	/**
	 * 
	 * @param startTime 2017-08-01 00:00:00
	 * @param endTime 2017-08-01 23:00:00
	 */
	public List<String> getMessage(String startTime,String endTime,int count) {
		List<String>msgList=new ArrayList<String>();
		String sql= "SELECT * FROM tp_wxq_entire WHERE unix_timestamp(m_publish_time) BETWEEN unix_timestamp('"
				+ startTime
				+ "') AND unix_timestamp('"
				+ endTime
				+ "') AND m_type=1 LIMIT "+count;
		if(this.connection!=null) {
			try {
				PreparedStatement prepareStatement=this.connection.prepareStatement(sql);
				ResultSet rs = prepareStatement.executeQuery();
				while (rs.next()) {
					 String content = rs.getString("m_content");
					 msgList.add(content);
				}
				rs.close();
				prepareStatement.close();
				MppJdbcPool_dbcp.closeConn(connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return msgList;
	}
}
