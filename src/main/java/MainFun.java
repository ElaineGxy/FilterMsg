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
		
/*		String line="@雨思 香港那边禽流感了，我还是不发烧好了 ";
		HotMsg hotMsg=dealMsg.filterHotMsg(line, "12");
		if(hotMsg!=null)System.out.println(hotMsg.getMsg_content());*/
		String line="《萍矿，想说爱你真不容易》如果在以前，有人问我，爱不爱萍矿？爱，我会毫不犹豫地回答，并且是非常的爱，她也确实有很让我深爱她的理由："
				+ "一：她有着深厚的文化底蕴和光荣的革命历史，自毛泽东刘少奇李立三等老一辈无产阶级革命家领导的安源路矿大罢工运动，迄今已有百年历史了，"
				+ "以至于儿时在湖南乡下读书上历史课时，我当着课堂师生的面讲我的爸爸就是在安源煤矿(严格地讲是高坑煤矿)工作时，沉浸在全体师生们羡慕眼神的同时，"
				+ "脸上溢出来的全是骄傲和自豪。二：她有着辉煌的过去，安源矿、高坑矿、青出矿、巨源矿、白源矿、杨桥矿、黄冲矿七大矿山共同奠定了萍矿在江西乃至全国的历史地位，"
				+ "其中又犹以安源高坑二矿最为出名，安源矿的知名度无须细述，高坑矿则有着江南第一大矿的头衔，在建国初期至六七十年代，原煤产量长期保持在120万吨以上，"
				+ "素有“北有开滦，南有高矿″的美誉，在上个世纪，说萍矿是整个萍乡巨无霸式企业毫不为过，举几个例子吧，当时高达十三层的萍矿办公楼(现在的黑天鹅酒店)就是整个"
				+ "萍乡市的天际线，并且鹤立鸡群了好多年，萍乡市的第一辆奔驰小车，就是萍乡矿务局局长大人的座驾，(要知道在当时，萍乡市市委书记的座驾也不过桑塔纳之流)，"
				+ "虽然这并不是件什么值得夸耀的事，但从侧面也反映了当时的萍矿有多么的牛逼！三：她有着令人称羡的非煤产业及第三产业群，有号称煤炭工业部最大的客车生产企业"
				+ "一一一安源客车厂，在宇通，大金龙等客车品牌还不知为何物的当时，其安源牌客车就早已驰骋大江南北誉满全中国了，有六六一火工机械厂，还有称为全国煤炭系统最大"
				+ "的转产项目浮法玻璃一厂浮玻二厂，工程玻璃厂，安源热能，建安公司，萍矿湘雅合作医院，电焊条厂，萍矿水泥厂，高坑煤矸石电厂，安源煤矸厂电厂，安源管道厂……，"
				+ "等等，正是由于这些厂矿，使得萍矿在经济上撑起了萍乡市大半壁江山。可以毫不夸张地讲，当年的萍矿打个喷嚏，整个萍乡都要感冒！在当时，只要一讲是萍矿正式职工，"
				+ "人们投过来的眼神里头除了是羡慕外，剩下的就只有嫉妒恨了。谈不尽辉腾过往，讲不完的企业繁华，唉，只是这一切，在跨过了新千年后的今天，这些辉煌就如同撕下的"
				+ "老黄历被尘封了，化为了昨日黄花，消亡在老一辈萍矿人的捶胸顿足中，沉沦在各级领导人们宴会上的杯盘狼藉中，哭泣在底层员工们的缺顿少粮中。为什么？"
				+ "我只想问个为什么？一个屹立了百年的老企，在白色恐惧的年代没倒，在枪林弹雨的年代没倒，在十年动乱的文革年代没倒，却倒在了我们这一代人手中，这当中，"
				+ "由于资源枯竭及国家去产能计划而造成煤矿关停，可以理解也无可厚非，问题是，萍矿的非煤转产项目起步得不可谓不早，像玻璃、客车等产业，为什么像宇通、金龙、"
				+ "福耀、旗滨他们能做得风生水起，而我们的企业却越做越死？难道真的是某些领导所讲的，是现在的工人越来越懒越来越刁了所造成的？投入巨资的宜萍煤矿和贵州六盘水"
				+ "煤矿，除了弄出了几块煤矸石外再无音讯，同样投入了巨资的光伏玻璃厂，除了有几座空荡荡的厂房外，没见过一块传说中的光伏玻璃，真是座\"光伏″玻璃厂，光光地伏在"
				+ "那里，一点动静都没有，至于那宜萍和贵州煤矿，只有那几块煤矸石裸露在外折射出一股刺眼的光，仿佛在嘲讽着某些个愚蠢的贪婪的萍矿人。萍矿已成贫矿，中煤早已中霉，"
				+ "这样的萍矿，如果有人问我还爱不爱，爱，萍矿我永远都爱，但我不爱萍矿的那些所谓的领导人，对他们，何止是不爱，简直是恨之入骨，恨不得啖其肉而寝其皮，"
				+ "假如杀人不犯法，我想你们中的某些人早已死了若干回了，你们中的某些人，注定要成萍矿历史上的罪人，就算是你们能侥幸逃过国法的制裁，我也诅咒你们必遭天谴，"
				+ "必定会钉在良心的耻辱柱上！";
		System.out.println(DicAnalysis.parse(line));
		System.out.println("done!");

	}
}
