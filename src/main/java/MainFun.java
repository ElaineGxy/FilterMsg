import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.ansj.splitWord.analysis.DicAnalysis;
import org.apache.commons.lang.time.StopWatch;

import Entity.HotMsg;
import Outliner.BoxPlots;
import Util.DealMsg;


public class MainFun {

	public static void main(String[] args) throws Exception {
/*		DealMsg dealMsg = new DealMsg();

		File file = new File("./msg.txt");
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		FileWriter fileWriter=new FileWriter(new File("./result.txt"));
		BufferedWriter bufferedWriter =new BufferedWriter(fileWriter);
		String line = null;
		
		int count=0;
		while ((line = bufferedReader.readLine()) != null) {
			count++;
			HotMsg hotMsg = dealMsg.filterHotMsg(line);
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
		String line="九寨沟山体滑坡不能去了王总";
		System.out.println(DicAnalysis.parse(line));
		DealMsg dealMsg=new DealMsg();
		HotMsg hotMsg=dealMsg.filterHotMsg(line);
		if(hotMsg!=null) {
			System.out.println("province:"+hotMsg.getMsg_province()+",city:"+hotMsg.getMsg_city()+"event:"+hotMsg.getEvt_word()+"event class:"+hotMsg.getEvt_class()+"keyword:"+hotMsg.getKeyword().toString());
		}else
			System.out.println("hotmsg is null");
	}
}
