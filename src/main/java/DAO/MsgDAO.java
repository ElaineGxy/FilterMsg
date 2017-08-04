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
		}else {
			logger.info("Connection is null!");
		}
		return msgList;
	}
	
	/**
	 * 通过地点信息获取热点新闻
	 * @param location
	 * @return
	 */
	public List<String> getHotByLoc(String location){
		List<String>msgList=new ArrayList<String>();
		String sql= "SELECT * FROM tp_wxq_hot where m_loc like "+location;
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
		}else {
			logger.info("connection is null");
		}
		return msgList;
	}
	
	/**
	 * 通过事件类型获取热点新闻
	 * @param location
	 * @return
	 */
	public List<String> getHotByEvt(String evt){
		List<String>msgList=new ArrayList<String>();
		String sql= "SELECT * FROM tp_wxq_hot where m_evt like "+evt;
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
		}else {
			logger.info("connection is null");
		}
		return msgList;
	}
	
	/**
	 * inset hot message
	 * @param hotMsg
	 */
	public void insertHotMsg(HotMsg hotMsg) {
		String sql="insert into tp_wxq_hot(msg_content,msg_province,msg_city,msg_district,evt_class,"
				+ "evt_word,msg_ip,msg_send_province,msg_send_city,msg_send_district) values("+hotMsg.getMsg_content()+","
				+hotMsg.getMsg_province()+","+hotMsg.getMsg_city()+","+hotMsg.getMsg_district()+","+hotMsg.getEvt_class()+","
				+hotMsg.getEvt_word()+","+hotMsg.getMsg_ip()+","+hotMsg.getMsg_send_province()+","+hotMsg.getMsg_send_city()+","
				+hotMsg.getMsg_send_district();
		if(this.connection!=null) {
			try {
				PreparedStatement prepareStatement=this.connection.prepareStatement(sql);
				ResultSet resultSet=prepareStatement.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			logger.info("connection is null");
		}
	}
	
}
