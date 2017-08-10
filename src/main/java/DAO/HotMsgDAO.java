package DAO;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 未完待续。。。。。。
 * 用于从统计表中取出近十个小时的热度值
 * @author gaoxy
 *
 * 2017年8月10日
 */
public class HotMsgDAO {
	private final static Logger logger = LoggerFactory.getLogger(HotMsgDAO.class);
	private Connection connection;
	
	
	//从当前时间
	public List<Map<Integer,int[]>> getDataByHours(){
		List<Map<Integer,int[]>>dataList=new ArrayList<Map<Integer,int[]>>();
		String sql="select * from message_tb group by ";
		return dataList;
	}
	
}
