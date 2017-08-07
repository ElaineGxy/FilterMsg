import java.util.List;

import DAO.MsgDAO;
import Entity.HotMsg;
import Util.DealMsg;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MsgDAO msgDao=new MsgDAO();
		String startTime="2017-07-31 12:00:00";
		String endTime="2017-07-31 16:00:00";
		List<String> msgList=msgDao.getMessage(startTime, endTime,10000);
		DealMsg dealMsg=new DealMsg();
		for(String msg:msgList) {
			System.out.println(msg);
			HotMsg hotMsg=dealMsg.filterHotMsg(msg,"12");
			if(hotMsg!=null) {
				System.out.println("content:"+hotMsg.getMsg_content()+",location:"+hotMsg.getMsg_province()+","+hotMsg.getEvt_class());
			}
		}
		System.out.println("done");
	}

}
