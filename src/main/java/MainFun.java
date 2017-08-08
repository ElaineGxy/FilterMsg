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

		File file = new File("./msg.txt");
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
		bufferedWriter.close();
		
/*		String line="激情八月，草原最美的季节/:rose/:rose/:rose/:rose/:rose/:rose/:rose/:rose/:rose/:rose/:rose陪你一起去关山牧场看草原，看青青的草儿蓝蓝的天。"
				+ "陪你参加草原运动会/::B/::B/::B骑马.. 射箭。陪你篝火晚会手牵手，拉歌派对响彻云霄/::D/::D/::D陪你去太白山水上乐园/:circle/:circle/:jump/:jump/:jump/"
				+ ":jump/:jump/:beer/:beer体验惊险刺激的山洪海啸，七彩漂流，秀泳装，关中美食，激情嗨皮，让爱留心间！暴走空间庆阳分会【3】群8月2日        "
				+ "星期三   监督员      豆宁平1、李树宏69742、王国庆10075/:rose3、何希顺66904、刘国鹏60085、付梅梅54146、王东强88997、豆宁平54848、郭彬彬60099、"
				+ "党建国12250/:rose10、秦树文16302/:rose11、付文龙601212丶高玉莲13135/:rose13、张君燕802714、张恒铭590315、张凌力10805/:rose16、"
				+ "王锁平20181/:rose/:rose17、张莉郧14978/:rose18、邢喜彩10291/:rose19、米艳12220/:rose20、张小霞11333/:rose21、王渊786322、何娜10822/:rose23、徐涛920724、宋晓娟11912/:rose25、徐宁霞15321/:rose26、李勇奎11273/:rose27、刘长银10669🌹28、崔香维755929、安洁11613/:rose30、高永谋12260/:rose31、杨海珍12908/:rose32、徐家富806333、乔亚萍12334/:rose34、赵秉琴19925/:rose35、后波506936、王丽花525237、李娜！525138、杨会会507739、麻倩978640、张小琴1725041、姚志虎13391/:rose42、李安民823043、梅    冰15681/:rose44、45、薛辉11252/:rose46、47、48、49、50、51、52、53、54、55、56、57、58、59、60、61、62、63、64、65、66、67、68、69、70、71、72、73、74、75、76、77、78、79、80、/:<O>/:<O>/:<O>预祝暴走空间八月户外活动圆满成功";
		System.out.println(DicAnalysis.parse(line));
		System.out.println("done!");*/

	}
}
