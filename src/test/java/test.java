import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import DAO.MsgDAO;
import Entity.HotMsg;
import Util.DealMsg;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MsgDAO msgDao=new MsgDAO();
		String startTime="2017-07-31 00:00:00";
		String endTime="2017-08-03 00:00:00";
		List<String> msgList=msgDao.getMessage(startTime, endTime);
		try {
			FileWriter fileWriter=new FileWriter(new File("./msg.txt"));
			BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
			for(String msg:msgList) {
				if(msg!=null&&msg.length()>0) {
					System.out.println(msg);
					bufferedWriter.write(msg);
					bufferedWriter.newLine();
				}
			}
			fileWriter.flush();
			bufferedWriter.flush();
			fileWriter.close();
			bufferedWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*DealMsg dealMsg=new DealMsg();
		for(String msg:msgList) {
//			System.out.println(msg);
			HotMsg hotMsg=dealMsg.filterHotMsg(msg,"12");
			if(hotMsg!=null) {
				System.out.println("content:"+hotMsg.getMsg_content()+",location:"+hotMsg.getMsg_province()+","+hotMsg.getEvt_class());
			}
		}*/
		System.out.println("done");
	}

}
