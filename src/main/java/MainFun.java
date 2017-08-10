import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ansj.splitWord.analysis.DicAnalysis;

import Entity.HotMsg;
import Outliner.BoxPlots;
import Outliner.BoxPlotsThread;
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
		String line="九寨沟地震20死";
		System.out.println(DicAnalysis.parse(line));
		DealMsg dealMsg=new DealMsg();
		HotMsg hotMsg=dealMsg.filterHotMsg(line);
		if(hotMsg!=null) {
			System.out.println("province:"+hotMsg.getMsg_province()+",city:"+hotMsg.getMsg_city()+"event:"+hotMsg.getEvt_word()+"event class:"+hotMsg.getEvt_class()+"keyword:"+hotMsg.getKeyword().toString());
		}else
			System.out.println("hotmsg is null");
/*		int[]data= {5,4,1,2,9,0,6,9,22};
		BoxPlots box=new BoxPlots(data);
		System.out.println(box.isOutliner());*/
		
/*		List<Map<Integer, int[]>>dataEntries=new ArrayList<Map<Integer,int[]>>();
		
		BoxPlotsThread boxThread=new BoxPlotsThread();
		boxThread.setDatas(dataEntries);
		for(int i=0;i<4;i++) {
			Thread thread=new Thread(boxThread);
			thread.start();
		}*/
		
	}
}
