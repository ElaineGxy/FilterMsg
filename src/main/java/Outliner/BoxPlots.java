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
	public void findQ(double ratio) {
		this.sort();
		int QLIndex=length/4;
		int QUIndex=length-QLIndex;
		QL=data[QLIndex];
		QU=data[QUIndex];
		IQR=QU-QL;
		expUpper=QU+ratio*IQR;
		expLower=QL-ratio*IQR;
	}
	/**
	 * judge whether this is a hot issue
	 * @return true: is hot issue else return false
	 */
	public boolean isOutliner() {
		findQ(3);
//		System.out.println(this.currentData);
		if(this.currentData>this.expUpper)return true;
		return false;
	}
}
