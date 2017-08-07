package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Entity.HotMsg;
import Util.MapUtil;
import Util.MppJdbcPool_dbcp;

public class MsgDAO {
	private final static Logger logger = LoggerFactory.getLogger(MsgDAO.class);
	public Connection connection;

	public MsgDAO() {
		MppJdbcPool_dbcp.MppJdbcPoolinit();
		this.connection = MppJdbcPool_dbcp.getConnection();
	}

	public static List<String> getMessage(String startTime,String endTime,int count){
		MppJdbcPool_dbcp.MppJdbcPoolinit();
		Connection connection = MppJdbcPool_dbcp.getConnection();
		List<String> msgList = new ArrayList<String>();
		String sql = "SELECT m_content FROM tp_wxq_entire WHERE unix_timestamp(m_publish_time) BETWEEN unix_timestamp('"
				+ startTime + "') AND unix_timestamp('" + endTime + "') AND m_type=1 LIMIT " + count;
		if (connection != null) {
			try {
				PreparedStatement prepareStatement = connection.prepareStatement(sql);
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
		} else {
			logger.info("Connection is null!");
		}
		return msgList;
	}


	/**
	 * 
	 * @param startTime
	 *            2017-08-01 00:00:00
	 * @param endTime
	 *            2017-08-01 23:00:00
	 */
	public static List<String> getMessage(String startTime, String endTime) {
		MppJdbcPool_dbcp.MppJdbcPoolinit();
		Connection connection = MppJdbcPool_dbcp.getConnection();
		List<String> msgList = new ArrayList<String>();
		String sql = "SELECT * FROM tp_wxq_entire WHERE unix_timestamp(m_publish_time) BETWEEN unix_timestamp('"
				+ startTime + "') AND unix_timestamp('" + endTime + "')";
		if (connection != null) {
			try {
				PreparedStatement prepareStatement = connection.prepareStatement(sql);
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
		} else {
			logger.info("Connection is null!");
		}
		return msgList;
	}

	/**
	 * 通过地点信息获取热点新闻
	 * 
	 * @param location
	 * @return
	 */
	public static List<HotMsg> getHotByLoc(String location,String startTime,String endTime) {
		MppJdbcPool_dbcp.MppJdbcPoolinit();
		Connection connection = MppJdbcPool_dbcp.getConnection();
		List<HotMsg> msgList = new ArrayList<HotMsg>();
		String sql = "SELECT * FROM tp_wxq_hot WHERE unix_timestamp(m_publish_time) BETWEEN unix_timestamp('"
				+ startTime + "') AND unix_timestamp('" + endTime + "') AND m_content_province like "+location;
		if (connection != null) {
			try {
				PreparedStatement prepareStatement = connection.prepareStatement(sql);
				ResultSet rs = prepareStatement.executeQuery();
				while (rs.next()) {
					String content = rs.getString("m_content");
					String gId=rs.getString("g_id");
					Timestamp publishtime=rs.getTimestamp("m_publish_time");
					String msgProvince=rs.getString("m_content_province");
					String evtClass=rs.getString("m_evt_class");
					long zhId=rs.getLong("m_ch_id");
					long roomId=rs.getLong("m_chat_room");
					HotMsg hotMsg=new HotMsg(content);
					hotMsg.setId(gId);
					hotMsg.setPublishtime(publishtime);
					hotMsg.setMsg_province(msgProvince);
					hotMsg.setEvt_class(evtClass);
					hotMsg.setZhid(zhId);
					hotMsg.setChatroomId(roomId);
					msgList.add(hotMsg);
				}
				rs.close();
				prepareStatement.close();
				MppJdbcPool_dbcp.closeConn(connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			logger.info("connection is null");
		}
		return msgList;
	}

	/**
	 * 通过事件类型获取热点新闻
	 * 
	 * @param location
	 * @return
	 */
	public static List<HotMsg> getHotByEvt(String evt, String startTime, String endTime) {
		MppJdbcPool_dbcp.MppJdbcPoolinit();
		Connection connection = MppJdbcPool_dbcp.getConnection();
		List<HotMsg> msgList = new ArrayList<HotMsg>();
		String sql = "SELECT * FROM tp_wxq_hot WHERE m_event_class like" + evt
				+ " unix_timestamp(m_publish_time) BETWEEN unix_timestamp('" + startTime + "') AND unix_timestamp('"
				+ endTime + "')";
		if (connection != null) {
			try {
				PreparedStatement prepareStatement = connection.prepareStatement(sql);
				ResultSet rs = prepareStatement.executeQuery();
				while (rs.next()) {
					String content = rs.getString("m_content");
					String gId=rs.getString("g_id");
					Timestamp publishtime=rs.getTimestamp("m_publish_time");
					String msgProvince=rs.getString("m_content_province");
					String evtClass=rs.getString("m_evt_class");
					long zhId=rs.getLong("m_ch_id");
					long roomId=rs.getLong("m_chat_room");
					HotMsg hotMsg=new HotMsg(content);
					hotMsg.setId(gId);
					hotMsg.setPublishtime(publishtime);
					hotMsg.setMsg_province(msgProvince);
					hotMsg.setEvt_class(evtClass);
					hotMsg.setZhid(zhId);
					hotMsg.setChatroomId(roomId);
					msgList.add(hotMsg);
				}
				rs.close();
				prepareStatement.close();
				MppJdbcPool_dbcp.closeConn(connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			logger.info("connection is null");
		}
		return msgList;
	}

	/**
	 * return all hot message ordered by time(publish time)
	 * 
	 * @return
	 */
	public static List<HotMsg> getHotMsg(String startTime, String endTime) {
		MppJdbcPool_dbcp.MppJdbcPoolinit();
		Connection connection = MppJdbcPool_dbcp.getConnection();
		List<HotMsg> msgList = new ArrayList<HotMsg>();
		String sql = "SELECT * FROM tp_wxq_entire WHERE unix_timestamp(m_publish_time) BETWEEN unix_timestamp('"
				+ startTime + "') AND unix_timestamp('" + endTime + "')";
		if (connection != null) {
			try {
				PreparedStatement prepareStatement = connection.prepareStatement(sql);
				ResultSet rs = prepareStatement.executeQuery();
				while (rs.next()) {
					String content = rs.getString("m_content");
					String gId=rs.getString("g_id");
					Timestamp publishtime=rs.getTimestamp("m_publish_time");
					String msgProvince=rs.getString("m_content_province");
					String evtClass=rs.getString("m_evt_class");
					long zhId=rs.getLong("m_ch_id");
					long roomId=rs.getLong("m_chat_room");
					HotMsg hotMsg=new HotMsg(content);
					hotMsg.setId(gId);
					hotMsg.setPublishtime(publishtime);
					hotMsg.setMsg_province(msgProvince);
					hotMsg.setEvt_class(evtClass);
					hotMsg.setZhid(zhId);
					hotMsg.setChatroomId(roomId);
					msgList.add(hotMsg);
				}
				rs.close();
				prepareStatement.close();
				MppJdbcPool_dbcp.closeConn(connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			logger.info("Connection is null!");
		}
		return msgList;

	}
	
	/**
	 * insert hot message
	 * 
	 * @param hotMsg
	 */
	public static void insertHotMsg(HotMsg hotMsg) {
		MppJdbcPool_dbcp.MppJdbcPoolinit();
		Connection connection = MppJdbcPool_dbcp.getConnection();
		String sql = "insert into tp_wxq_hot(msg_content,msg_province,msg_city,msg_district,evt_class,"
				+ "evt_word,msg_ip,msg_send_province,msg_send_city,msg_send_district) values(" + hotMsg.getMsg_content()
				+ "," + hotMsg.getMsg_province() + "," + hotMsg.getMsg_city() + "," + hotMsg.getMsg_district() + ","
				+ hotMsg.getEvt_class() + "," + hotMsg.getEvt_word() + "," + hotMsg.getMsg_ip() + ","
				+ hotMsg.getMsg_send_province() + "," + hotMsg.getMsg_send_city() + "," + hotMsg.getMsg_send_district();
		if (connection != null) {
			try {
				PreparedStatement prepareStatement = connection.prepareStatement(sql);
				ResultSet resultSet = prepareStatement.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			logger.info("connection is null");
		}
	}

	/**
	 * 将热点消息的表格清空
	 */
	public static void clearHotMsg() {
		MppJdbcPool_dbcp.MppJdbcPoolinit();
		Connection connection = MppJdbcPool_dbcp.getConnection();
		String truncateSql = "TRUNCATE TABLE tp_wxq_hot";
		if (connection != null) {
			PreparedStatement preparedStatement;
			try {
				preparedStatement = connection.prepareStatement(truncateSql);
				int result = preparedStatement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.info("Exception: PrepareStatement");
				e.printStackTrace();
			}
		}
	}

}
