package Util;

import java.util.ArrayList;
import java.util.Random;

/**
 * mining outliner
 * @author gaoxy
 *
 * 2017年8月8日
 */
public class Outliner {
	 	private int k;// 分成多少簇  
	    private int m;// 迭代次数  
	    private int dataSetLength;// 数据集元素个数，即数据集的长度  
	    private int[] dataSet;// 数据集链表  
	    private int[] center;// 中心链表  
	    private ArrayList<ArrayList<Integer>> cluster; // 簇  
	    private ArrayList<Float> jc;// 误差平方和，k越接近dataSetLength，误差越小  
	    private Random random;  
	  
	    /** 
	     * 设置需分组的原始数据集 
	     *  
	     * @param dataSet 
	     */  
	  
	    public void setDataSet(int[] dataSet) {  
	        this.dataSet = dataSet;  
	    }  
	  
	    /** 
	     * 获取结果分组 
	     *  
	     * @return 结果集 
	     */  
	  
	    public ArrayList<ArrayList<Integer>> getCluster() {  
	        return cluster;  
	    }  
	  
	    /** 
	     * 构造函数，传入需要分成的簇数量 
	     *  
	     * @param k 
	     *            簇数量,若k<=0时，设置为1，若k大于数据源的长度时，置为数据源的长度 
	     */  
	    public Outliner(int k) {  
	        if (k <= 0) {  
	            k = 1;  
	        }  
	        this.k = k;  
	    }  
	  
	    /** 
	     * 初始化 
	     */  
	    private void init() {  
	        m = 0;  
	        random = new Random();  
	        if (dataSet == null || dataSet.length== 0) {  
	            initDataSet();  
	        }  
	        dataSetLength = dataSet.length;
	        if (k > dataSetLength) {  
	            k = dataSetLength;  
	        }  
	        center = initCenters();  
	        cluster = initCluster();  
	        jc = new ArrayList<Float>();  
	    }  
	  
	    /** 
	     * 如果调用者未初始化数据集，则采用内部测试数据集 
	     */  
	    private void initDataSet() {  
	      
	        int[] dataSetArray = { 8, 2, 3, 4 , 2, 5,4, 2 };  
	        this.dataSet=dataSetArray;
	    }  
	  
	    /** 
	     * 初始化中心数据链表，分成多少簇就有多少个中心点 
	     *  
	     * @return 中心点集 
	     */  
	    private int[] initCenters() {  
	        int[] center = new int[k];  
	        int[] randoms = new int[k];  
	        boolean flag;  
	        int temp = random.nextInt(dataSetLength);  
	        randoms[0] = temp;  
	        for (int i = 1; i < k; i++) {  
//	        	随机从数据集中选取k个数据点作为中心点
	            flag = true;  
	            while (flag) {  
	                temp = random.nextInt(dataSetLength);  
	                int j = 0;  
	                while (j < i) {  
	                    if (temp == randoms[j]) {  
	                        break;  
	                    }  
	                    j++;  
	                }  
	                if (j == i) {  
	                    flag = false;  
	                }  
	            }  
	            randoms[i] = temp;  
	        }  
	        for (int i = 0; i < k; i++) {  
	            center[k]=dataSet[randoms[i]];// 生成初始化中心链表  
	        }  
	        return center;  
	    }  
	  
	    /** 
	     * 初始化簇集合 
	     *  
	     * @return 一个分为k簇的空数据的簇集合 
	     */  
	    private ArrayList<ArrayList<Integer>> initCluster() {  
	        ArrayList<ArrayList<Integer>> cluster = new ArrayList<ArrayList<Integer>>();  
	        for (int i = 0; i < k; i++) {  
	            cluster.add(new ArrayList<Integer>());  
	        }  
	        return cluster;  
	    }  
	  
	    /** 
	     * 计算两个点之间的距离 
	     *  
	     * @param element 
	     *            点1 
	     * @param center 
	     *            点2 
	     * @return 距离 
	     */  
	    private int distance(int element, int center) {  
	        int distance = (element-center)*(element-center); 
	        return distance;  
	    }  
	  
	    /** 
	     * 获取距离集合中最小距离的位置 
	     *  
	     * @param distance 
	     *            距离数组 
	     * @return 最小距离在距离数组中的位置 
	     */  
	    private int minDistance(float[] distance) {  
	        float minDistance = distance[0];  
	        int minLocation = 0;  
	        for (int i = 1; i < distance.length; i++) {  
	            if (distance[i] < minDistance) {  
	                minDistance = distance[i];  
	                minLocation = i;  
	            } else if (distance[i] == minDistance) // 如果相等，随机返回一个位置  
	            {  
	                if (random.nextInt(10) < 5) {  
	                    minLocation = i;  
	                }  
	            }  
	        }  
	  
	        return minLocation;  
	    }  
	  
	    /** 
	     * 核心，将当前元素放到最小距离中心相关的簇中 
	     */  
	    private void clusterSet() {  
	        float[] distance = new float[k];  
	        for (int i = 0; i < dataSetLength; i++) {  
	            for (int j = 0; j < k; j++) {  
	                distance[j] = distance(dataSet[i], center[j]);  
	                // System.out.println("test2:"+"dataSet["+i+"],center["+j+"],distance="+distance[j]);  
	  
	            }  
	            int minLocation = minDistance(distance);  
	  
	            cluster.get(minLocation).add(dataSet[i]);// 核心，将当前元素放到最小距离中心相关的簇中  
	  
	        }  
	    }  
	  
	    /** 
	     * 求两点误差平方的方法 
	     *  
	     * @param element 
	     *            点1 
	     * @param center 
	     *            点2 
	     * @return 误差平方 
	     */  
	    private float errorSquare(float[] element, float[] center) {  
	        float x = element[0] - center[0];  
	        float y = element[1] - center[1];  
	  
	        float errSquare = x * x + y * y;  
	  
	        return errSquare;  
	    }  
	  
	    /** 
	     * 计算误差平方和准则函数方法 
	     */  
	    private void countRule() {  
	        float jcF = 0;  
	        for (int i = 0; i < cluster.size(); i++) {  
	            for (int j = 0; j < cluster.get(i).size(); j++) {  
	                jcF += errorSquare(cluster.get(i).get(j), center[i]);  
	  
	            }  
	        }  
	        jc.add(jcF);  
	    }  
	  
	    /** 
	     * 设置新的簇中心方法 
	     */  
	    private void setNewCenter() {  
	        for (int i = 0; i < k; i++) {  
	            int n = cluster.get(i).size();  
	            if (n != 0) {  
	                int newCenter=0;
	                for (int j = 0; j < n; j++) {  
	                    newCenter += cluster.get(i).get(j);  
	                }  
	                // 设置一个平均值  
	                newCenter = newCenter / n;    
	                center[i]=newCenter;  
	            }  
	        }  
	    }  
	  
	    /** 
	     * 打印数据，测试用 
	     *  
	     * @param dataArray 
	     *            数据集 
	     * @param dataArrayName 
	     *            数据集名称 
	     */  
	    public void printDataArray(ArrayList<float[]> dataArray,  
	            String dataArrayName) {  
	        for (int i = 0; i < dataArray.size(); i++) {  
	            System.out.println("print:" + dataArrayName + "[" + i + "]={"  
	                    + dataArray.get(i)[0] + "," + dataArray.get(i)[1] + "}");  
	        }  
	        System.out.println("===================================");  
	    }  
	  
	    /** 
	     * Kmeans算法核心过程方法 
	     */  
	    private void kmeans() {  
	        init();  
	        // printDataArray(dataSet,"initDataSet");  
	        // printDataArray(center,"initCenter");  
	  
	        // 循环分组，直到误差不变为止  
	        while (true) {  
	            clusterSet();  
	            // for(int i=0;i<cluster.size();i++)  
	            // {  
	            // printDataArray(cluster.get(i),"cluster["+i+"]");  
	            // }  
	  
	            countRule();  
	  
	            // System.out.println("count:"+"jc["+m+"]="+jc.get(m));  
	  
	            // System.out.println();  
	            // 误差不变了，分组完成  
	            if (m != 0) {  
	                if (jc.get(m) - jc.get(m - 1) == 0) {  
	                    break;  
	                }  
	            }  
	  
	            setNewCenter();  
	            // printDataArray(center,"newCenter");  
	            m++;  
	            cluster.clear();  
	            cluster = initCluster();  
	        }  
	  
	        // System.out.println("note:the times of repeat:m="+m);//输出迭代次数  
	    }  
	  
	    /** 
	     * 执行算法 
	     */  
	    public void execute() {  
	        long startTime = System.currentTimeMillis();  
	        System.out.println("kmeans begins");  
	        kmeans();  
	        long endTime = System.currentTimeMillis();  
	        System.out.println("kmeans running time=" + (endTime - startTime)  
	                + "ms");  
	        System.out.println("kmeans ends");  
	        System.out.println();  
	    }  

	/**
	 * 
	 * @param msgCount:time series data
	 * @return return true if data is a outliner else return false;
	 */
	public boolean isOutliner(int[] msgCount,int data) {
		return false;
	}
}
