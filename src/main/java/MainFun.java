import java.util.Calendar;
import java.util.Date;

import org.ansj.splitWord.analysis.DicAnalysis;

import Entity.HotMsg;
import Util.DealMsg;

public class MainFun {

	public static void main(String[] args) throws Exception {
		DealMsg dealMsg = new DealMsg();
		/*
		 * File file=new File("./msg.txt"); FileReader fileReader=new FileReader(file);
		 * BufferedReader bufferedReader=new BufferedReader(fileReader); String
		 * line=null; while((line=bufferedReader.readLine())!=null){ String
		 * result=dealMsg.filterHotMsg(line);
		 * System.out.println("杩囨护涔嬪墠:"+line+"\n杩囨护涔嬪悗:"+result); }
		 */
		//

		/*String line = "新周开启快乐起航！大家早上好，把清晨的第一缕（☀）送给亲爱的朋友们！今天是公历7月31日，星期一，农历闰六月初九。"
				+ "请关注天气变化：哈尔滨，雷阵雨转多云，30℃/21℃，东南风3级，多云有阵雨或雷阵雨天气，局部地区还可能有短时强降水、雷暴大风等强对流天气出现，"
				+ "请您外出时做好防雨准备以防范降雨的突然袭击，同时也要注意雨中出行的交通安全。截至凌晨5时，哈市的AQI指数为51，PM2.5为23，空气质量良好。"
				+ "⛈️⛈️今天敢于做别人不敢做的事，明天才可以拥有别人不能拥有的一切。不要在乎别人怎么说，因为未来是自己的，谁都不会为你的人生负责。 "
				+ "给自己一个完美的交代，为自己而努力！路虽远，行则将至；事虽难，做则必成。想，都是问题；做，才有答案。输在犹豫，赢在行动！新周开启，早安！加油！";*/
/*		String line="通辽，禽流感，农业部网站最新消息，请大家注意。  ";
		String[]sentences=dealMsg.cutSentence(line);
		for(String sentence:sentences)
			System.out.println(sentence);
		//DicLibrary.insert(DicLibrary.DEFAULT, "偃师", "location", 10240);// 代码显式指定是OK的
		System.out.println(DicAnalysis.parse(line));
		HotMsg hotMsg = dealMsg.filterHotMsg(line, "125.70.11.136");
		if(hotMsg!=null) {
			System.out.print("content:" + hotMsg.getMsg_content() + ",location:" + hotMsg.getMsg_province() + ","
					+ hotMsg.getMsg_city() + "," + hotMsg.getMsg_district() + ",event:" + hotMsg.getEvt_class() + ","
					+ hotMsg.getEvt_word()+"sendIp:"+hotMsg.getMsg_send_province());			
		}else System.out.println("filter out");*/
		Date date = new Date();
		int hour = (date.getHours() / 3) * 3 + 2;
		System.out.println(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, hour); // 控制时
		calendar.set(Calendar.MINUTE, 59); // 控制分
		calendar.set(Calendar.SECOND, 00);
		Date time = calendar.getTime();
		
		/*Calendar calendar=Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND,59);
		Date time=calendar.getTime();*/
		System.out.println(time);

	}
}
