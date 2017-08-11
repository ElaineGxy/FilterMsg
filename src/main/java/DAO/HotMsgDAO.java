package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Util.JdbcConnector;

/**
 * 未完待续。。。。。。 用于从统计表中取出近十个小时的热度值
 * 
 * @author gaoxy
 *
 *         2017年8月10日
 */
public class HotMsgDAO {
	private final static Logger logger = LoggerFactory.getLogger(HotMsgDAO.class);
	private Connection connection;

	public HotMsgDAO() {
		this.connection = JdbcConnector.getConnection();
	}

	// 从当前时间
	public Map<String, int[]> getDataByHours(int length) {
		Map<String, int[]> dataMap = new HashMap<String, int[]>();
		// 查询
		String sql = "select * from message_tb WHERE publish_time BETWEEN DATE_SUB('2017-08-08 00:00:00',INTERVAL 10 HOUR)AND '2017-08-08 10:00:00' ORDER BY province,`event`,publish_time";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			int count = 0;
			int[] heats = null;
			String province = null, event = null;
			while (resultSet.next()) {
				if(count==0) {
					province = resultSet.getString("province");
					event = resultSet.getString("event");
					heats=new int[length];
				}
				heats[count] = resultSet.getInt("heat");
				count++;
				if (count == length) {
					dataMap.put(province + "," + event, heats);
					count = 0;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataMap;
	}

	/**
	 * 计算特定的省份和事件在length个时间点的均值
	 * @param province
	 * @param event
	 * @param length
	 * @return
	 */
	public double[] getAverage(String province,String event,int length) {
		double[] heatAverage=new double[length];
		Date date=new Date();
		int curHour=date.getHours(); 
		@SuppressWarnings("deprecation") 
		int startHour=curHour-length;
		String sql="SELECT province, `event`, HOUR(publish_time),AVG(heat)as heatAvg FROM message_tb WHERE province like '"+province+"' AND event like '"+event
				+ "' AND HOUR(publish_time)>="+startHour+" AND HOUR(publish_time)<"+curHour+" GROUP BY HOUR(publish_time) ORDER BY HOUR(publish_time)";
		System.out.println(sql);
		try {
			PreparedStatement prepareStatement=this.connection.prepareStatement(sql);
			ResultSet resultSet=prepareStatement.executeQuery();
			int count=0;
			while(resultSet.next()) {
				heatAverage[count]=resultSet.getDouble("heatAvg");
				count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return heatAverage;
	}
	
	public static void main(String[] args) {
		HotMsgDAO hotMsgDAO = new HotMsgDAO();
		double[]datas = hotMsgDAO.getAverage("四川", "地震", 10);
		System.out.print("[");
		for(double data:datas) {
			System.out.print(data+" ");
		}
		System.out.println("]");

	}
}
