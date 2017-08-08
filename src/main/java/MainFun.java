import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Calendar;
import java.util.Date;

import org.ansj.splitWord.analysis.DicAnalysis;

import Entity.HotMsg;
import Util.DealMsg;
import Util.TimeUtil;

public class MainFun {

	public static void main(String[] args) throws Exception {
		DealMsg dealMsg = new DealMsg();

		/*		File file = new File("./msg.txt");
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		FileWriter fileWriter=new FileWriter(new File("./result.txt"));
		BufferedWriter bufferedWriter =new BufferedWriter(fileWriter);
		String line = null;
		int count=0;
		while ((line = bufferedReader.readLine()) != null) {
			count++;
			HotMsg hotMsg = dealMsg.filterHotMsg(line,"12");
			if(hotMsg!=null) {
				bufferedWriter.write("content:"+hotMsg.getMsg_content()+",location:"+hotMsg.getMsg_province()+","+hotMsg.getEvt_class());
				bufferedWriter.newLine();
				System.out.println("content:"+hotMsg.getMsg_content()+",location:"+hotMsg.getMsg_province()+","+hotMsg.getEvt_class());
			}
			System.out.println("count:"+count+"done!");
		}
		fileWriter.flush();
		bufferedWriter.flush();
		fileWriter.close();
		bufferedWriter.close();*/
		
		String line="关于征求《苍南县土壤污染防治工作方案（征求意见稿）》意见建议的通知——大家提提意见，涉及到今后的工作及被考核任务,意见稿已挂科室QQ群。";
		HotMsg hotMsg=dealMsg.filterHotMsg(line, "12");
		if(hotMsg!=null)
			System.out.println(hotMsg.getMsg_content());
		else
			System.out.println("Filter out");
//		System.out.println(DicAnalysis.parse(line));
		System.out.println("done!");

	}
}
