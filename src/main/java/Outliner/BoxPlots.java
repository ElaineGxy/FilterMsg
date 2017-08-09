package Outliner;
import java.util.Arrays;

public class BoxPlots {
	public int[]data;
	public int QL;
	public int QU;
	public int IQR;
	public int length;
	public double expUpper;
	public double expLower;
	public int currentData;
	public BoxPlots(int[]data) {
		this.data=data;
		currentData=data[data.length-1];
		this.length=this.data.length;
	}
	public void sort() {
		Arrays.sort(data);
	}
	public void findQ() {
		this.sort();
		int QLIndex=length/4;
		int QUIndex=length-QLIndex;
		QL=data[QLIndex+1];
		QU=data[QUIndex];
		IQR=QU-QL;
		expUpper=QU+1.5*IQR;
		expLower=QL-1.5*IQR;
	}
	/**
	 * 1：热点事件 2：热点事件转换为非热点事件 3：非热点事件
	 * @return
	 */
	public int isOutliner() {
		findQ();
		System.out.println(this.currentData);
		if(this.currentData>this.expUpper)return 1;
		else if(this.currentData<this.expLower)return 2;
		return 3;
	}
}
