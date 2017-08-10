package Outliner;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BoxPlotsThread implements Runnable {
	private List<Map<Integer,int[]>>datas;//第一个数据是对应的数据库id，最后一个为需要判断的数据
	private int count;
	private int finishedCount;
	private double ratio;
	public void setDatas(List<Map<Integer,int[]>>datas) {
		this.datas=datas;
		this.count=datas.size();
		this.finishedCount=0;
	}
	public void run() {
		Map<Integer,int[]>data;
		while(count>finishedCount) {
			synchronized(this) {
				data=datas.get(finishedCount);
				finishedCount=finishedCount+1;
			}
			for(int id:data.keySet()) {
				int[]dataArray=data.get(id);
				int length=dataArray.length;
				int currentData=dataArray[length-1];
				Arrays.sort(dataArray);
				int QLIndex=length/4;
				int QUIndex=length-QLIndex;
				int QL=dataArray[QLIndex];
				int QU=dataArray[QUIndex];
				int IQR=QU-QL;
				double expUpper=QU+ratio*IQR;
				if(currentData>expUpper) 
					System.out.println("id:A hot issue");
				else
					System.out.println("id:Not hot issue");
			}
		}
	}
}
