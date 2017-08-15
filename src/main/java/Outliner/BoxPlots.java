package Outliner;
import java.util.Arrays;

import com.mysql.fabric.xmlrpc.base.Array;
/**
 * 需要将数据减去均值之后在进行箱型图分析
 * @author gaoxy
 *
 * 2017年8月14日
 */
public class BoxPlots {
	public double[]data;//减去均值后的数据
	public double QL;
	public double QU;
	public double IQR;
	public int length;
	public double expUpper;
	public double expLower;
	public double currentData;
	public BoxPlots() {}
	public BoxPlots(double[]data) {
		this.data=data;
		currentData=data[data.length-1];
		this.length=this.data.length;
	}
	public void setData(double[]data) {
		this.data=data;
		this.length=data.length;
		this.currentData=data[data.length-1];
	}
	public void sort() {
		Arrays.sort(data);
	}
	public void findQ(double ratio) {
		Arrays.sort(this.data);
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
		findQ(1.5);
//		System.out.println(this.currentData);
		if(this.currentData>this.expUpper)return true;
		return false;
	}
}
