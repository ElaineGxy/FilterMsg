package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import DAO.MsgDAO;
import Entity.HotMsg;
import Util.TimeUtil;

public class GetHotMsgServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//get hot message
		String callback = request.getParameter("callback");
		String startTime=request.getParameter("st");
		String endTime=request.getParameter("et");
		String eventClass=request.getParameter("ec");//事件类型
		String location=request.getParameter("lc");//事件地点
		List<HotMsg>msgList=null;
		Date date=new Date();
		if(startTime == null || startTime.equals("")){
			startTime = TimeUtil.getStarttime(date);
		}
		if(endTime == null || endTime.equals("")){
			endTime = TimeUtil.DateToString(date);//得到当前的时间
		}
		
		if((eventClass==null||eventClass=="")&&(location==null||location=="")) {
			msgList=MsgDAO.getHotMsg(startTime, endTime);
		}else if((eventClass==null || eventClass=="")) {
			msgList=MsgDAO.getHotByLoc(location,startTime,endTime);
		}else {
			msgList=MsgDAO.getHotByEvt(eventClass, startTime, endTime);
		}
		
		String json = "{meda:{ },data:";
		if(msgList!=null){
			json += new Gson().toJson(msgList);
		}else{
			json  += "";
		}
		json +="}";
		KuaiyuResponse(callback, json, response);
	}
	public static void KuaiyuResponse(String callback, String json,
			HttpServletResponse response) {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/json");
			response.setDateHeader("Expires", 0);
			PrintWriter out = response.getWriter();
			out.print(callback + "(" + json + ")");
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
