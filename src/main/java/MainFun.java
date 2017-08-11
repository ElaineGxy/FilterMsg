import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import Entity.HotMsg;
import Outliner.BoxPlots;
import Util.DealMsg;


public class MainFun {

	public static void main(String[] args) throws Exception {
/*		String line="九寨沟县发生7.0级地震";
		System.out.println(DicAnalysis.parse(line));
		DealMsg dealMsg=new DealMsg();
		HotMsg hotMsg=dealMsg.filterHotMsg(line);
		if(hotMsg!=null) {
			System.out.println("province:"+hotMsg.getMsg_province()+",city:"+hotMsg.getMsg_city()+"event:"+hotMsg.getEvt_word()+"event class:"+hotMsg.getEvt_class()+"keyword:"+hotMsg.getKeyword().toString());
		}else
			System.out.println("hotmsg is null");*/
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
		testBoxPlot();
	}
	/**
	 * 从文件中读取消息，并提取相关地点和事件关键词
	 */
	public static void hotIssue() {
		DealMsg dealMsg = new DealMsg();

		File file = new File("./msg.txt");
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(file);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(new File("./result.txt"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedWriter bufferedWriter =new BufferedWriter(fileWriter);
		String line = null;
		
		int count=0;
		try {
			while ((line = bufferedReader.readLine()) != null) {
				count++;
				HotMsg hotMsg = dealMsg.filterHotMsg(line);
				if(hotMsg!=null) {
					bufferedWriter.write("location:"+hotMsg.getMsg_province()+","+hotMsg.getEvt_class());
					bufferedWriter.newLine();
					System.out.println("location:"+hotMsg.getMsg_province()+","+hotMsg.getEvt_class());
				}
				System.out.println("count:"+count+"done!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fileWriter.flush();
			bufferedWriter.flush();
			fileWriter.close();
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void testBoxPlot() {
		Map<String,int[]>dataMap=new HashMap<String,int[]>();
		int[] data1= {1,2,3,4};
		dataMap.put("test1", data1);
		int[]data2= {2,3,4,5};
		dataMap.put("test2", data2);
		int[]data3= {3,4,5,6};
		dataMap.put("test3", data3);
		int[]data4= {4,5,6,7};
		dataMap.put("test4", data4);
		Iterator iterator=dataMap.entrySet().iterator();
		BoxPlots boxPlots=new BoxPlots();
		while(iterator.hasNext()) {
			Map.Entry<String, int[]>entry=(Entry<String, int[]>) iterator.next();
			String key=entry.getKey();
			int[]data=entry.getValue();
			boxPlots.setData(data);
			System.out.println(boxPlots.isOutliner());
		}
	}
}
