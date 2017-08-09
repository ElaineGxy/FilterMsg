/*package Servlet;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import DAO.MsgDAO;
import parquet.Log;

public class ClearTableServlet implements ServletContextListener {

	public static final long serialVersionUID =1L;
	private Timer timer=null;
	private static final Logger logger=LoggerFactory.getLogger(MsgDAO.class);
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		timer.cancel();
		logger.info("Timer invalid");
	}

	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		logger.info("Timer start!");
		Calendar calendar=Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND,59);
		Date time=calendar.getTime();
		
		timer=new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				MsgDAO.clearHotMsg();
			}
			
		}, time, 1000*60*60*24);
		logger.info("Add to task list");
	}

}
*/