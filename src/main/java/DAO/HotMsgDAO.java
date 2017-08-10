package DAO;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author gaoxy
 *
 * 2017年8月10日
 */
public class HotMsgDAO {
	private final static Logger logger = LoggerFactory.getLogger(HotMsgDAO.class);
	private Connection connection;
	
	
	//从当前时间
	public List<Map<Integer,int[]>> getDataByHours(){
		
	}
	
}
