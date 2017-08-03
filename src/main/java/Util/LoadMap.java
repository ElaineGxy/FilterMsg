package Util;

import java.io.*;
import java.util.*;

/**
 * manage location and event, including: location  event and location code ,event code
 */
public class LoadMap {

    public static HashMap<String,String> location=null;
    public static HashMap<String,String> event=null;
    public static HashMap<String,Integer> hotWordMap =null;
    public static HashMap<String,Integer>locationAndCode=null;
    public static HashMap<String,String> eventAndCode=null;
    
     
    /**
     * 更新热词的频率信息
     * @param keyword:要更新的热词
     */
    public void updateHotWordMap(String keyword){
        HashMap<String,Integer>hotWordMap=this.getHotWordMap();
        LoadMap.hotWordMap =new HashMap<String,Integer>();
        if(hotWordMap.containsKey(keyword)){
            int value= hotWordMap.get(keyword);
            hotWordMap.put(keyword,value+1);
        }else
            hotWordMap.put(keyword,1);
    }

    /**
     * 创建hotwordMap
     * @return
     */
    public synchronized static HashMap<String,Integer> getHotWordMap(){
        if(LoadMap.hotWordMap==null){
            LoadMap.hotWordMap=new HashMap<String, Integer>();
        }
        return LoadMap.hotWordMap;
    }

    /**
     * return top k hotword
     * @param k
     * @return: hotword list
     */
    public List<String> getTopK(int k){
        List<String> hotWordList=new ArrayList<String>();
        Map<String,Integer> sortedHotMap=this.sortHotWordMap(hotWordMap);
        Iterator<Map.Entry<String,Integer>> iterator=sortedHotMap.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String,Integer>entry=  iterator.next();
            hotWordList.add(entry.getKey());
        }
        return hotWordList;
    }

    public Map<String, Integer> sortHotWordMap(Map<String, Integer> hotwordMap) {
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        if (hotwordMap != null && !hotwordMap.isEmpty()) {
            List<Map.Entry<String, Integer>> entryList = new ArrayList<Map.Entry<String, Integer>>(hotwordMap.entrySet());
            Collections.sort(entryList, new Comparator<Map.Entry<String, Integer>>() {
                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                    return o1.getValue()-o2.getValue();
                }
            });
            Iterator<Map.Entry<String, Integer>> iter = entryList.iterator();
            Map.Entry<String, Integer> tmpEntry = null;
            while (iter.hasNext()) {
                tmpEntry = iter.next();
                sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
            }

        }
        return sortedMap;
    }

    /**
     * 获取地点列表（省市县三级）
     * @return
     */
    public synchronized static Map<String,String> getLocation() {
        if(LoadMap.location==null){
            location=new HashMap<String,String>();
            //获取location.csv的inputstream
            InputStream locInputStream=LoadMap.class.getResourceAsStream(PropertyUtil.getFilePathProperty().getProperty("LOCPATH"));
            InputStreamReader reader= null;
            try {
                reader = new InputStreamReader(locInputStream,"gbk");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            BufferedReader bufferedReader=new BufferedReader(reader);
            String line=null;
            try {
                while((line=bufferedReader.readLine())!=null){
                    String[]line_contents=line.split(",");
                    if(line_contents.length==1)
                        location.put(line_contents[0],null);
                    else if(line_contents.length==2)
                        location.put(line_contents[0],line_contents[1]);
                    else if(line_contents.length==3&&line_contents[1]!=null)
                        location.put(line_contents[0],line_contents[1]+","+line_contents[2]);
                    else if(line_contents.length==3&&line_contents[1]==null)
                        location.put(line_contents[0],line_contents[2]);
                    else
                        System.err.println("Error: The format of location is invalid");

                }
            } catch (IOException e) {
                System.out.println("Error: Common.java/Fun getLocation/line 24");
                e.printStackTrace();
            }
        }
        return location;
    }

    /**
     * 添加一个三级地点，例如，那么需要传递的参数为(五道口，海淀区，北京)
     * @param location
     * @param cityName
     * @param provinceName
     * @return return true if add successfully else return false;
     */
    public boolean addNewLocation(String location,String cityName,String provinceName){
        if(LoadMap.getLocation().containsKey(location)) return false;
        else{
            if(cityName==null){
                LoadMap.getLocation().put(location,provinceName);
            }else{
                if(LoadMap.getLocation().containsKey(location))
                    LoadMap.getLocation().put(location,cityName+","+provinceName);
            }
            return true;
        }
    }

    /**
     *添加一个二级地点，例如（天安门，北京）
     * @param locationName:地点名称
     * @param provinceName：所属地区
     * @return return true if add successfully else return false;
     */
    public boolean addNewLocation(String locationName,String provinceName){
        return  this.addNewLocation(locationName,null,provinceName);
    }

    /**
     * 对一个三级地点的信息进行更新
     * @param locationName
     * @param cityName
     * @param provinceName
     * @return
     */
    public boolean updateLocation(String locationName,String cityName,String provinceName){
        if (LoadMap.getLocation().containsKey(locationName)) {
            if(cityName==null)
                LoadMap.getLocation().put(locationName,provinceName);
            else
                LoadMap.getLocation().put(locationName,cityName+","+provinceName);
            return true;
        }else{
            System.err.println("This locationName doesn't exist!");
            return false;
        }
    }

    /**
     * 对一个二级地点进行更新
     * @param locationName
     * @param province
     * @return
     */
    public boolean updateLocation(String locationName,String province){
        return this.updateLocation(locationName,null,province);
    }
    /**
     * 获取事件列表
     * @return
     */
    public synchronized static Map<String,String> getEvent() {
        if(LoadMap.event==null){
            LoadMap.event=new HashMap<String,String>();
            //获取location.csv的inputstream
            InputStream locInputStream=LoadMap.class.getResourceAsStream(PropertyUtil.getFilePathProperty().getProperty("EVTPATH"));
            InputStreamReader reader= null;
            try {
                reader = new InputStreamReader(locInputStream,"gbk");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            BufferedReader bufferedReader=new BufferedReader(reader);
            String line=null;
            try {
                while((line=bufferedReader.readLine())!=null){
                    String[]line_contents=line.split(",");
                    if(line_contents.length==2)
                        LoadMap.event.put(line_contents[1],line_contents[0]);
                    else
                        System.err.println("Error:common.java/line64");
                }
            } catch (IOException e) {
                System.err.println("Exception:commom.java/line 61");
                e.printStackTrace();
            }
        }
        return LoadMap.event;
    }

    /**
     *
     * @param eventName:事件关键词
     * @param eventClass:事件所属的大类别
     * @return: return true if add successfully else return false;
     */
    public boolean addNewEvent(String eventName,String eventClass){
        if(LoadMap.event.containsKey(eventName)){
            System.err.println("This keyword for event has already existed!");
            return false;
        }
        else LoadMap.event.put(eventName,eventClass);
        return true;
    }

    /**
     * 对现有的eventName的大类别进行更改
     * @param eventName:事件关键词
     * @param eventClass:需要更新的类别
     * @return: return true if update successfully else return false
     */
    public boolean updataEvent(String eventName,String eventClass){
        if(!LoadMap.event.containsKey(eventName)){
            System.err.println("This keyword does't exist!");
            return false;
        }else LoadMap.event.put(eventName,eventClass);
        return true;
    }

    public synchronized static Map<String,Integer> loadLocationCode() {
    	if(LoadMap.locationAndCode==null) {
    		LoadMap.locationAndCode=new HashMap<String,Integer>();
    		InputStream locCodeInputStream=LoadMap.class.getResourceAsStream(PropertyUtil.getFilePathProperty().getProperty("LOCCODEJPATH"));
    		InputStreamReader reader= null;
            try {
                reader = new InputStreamReader(locCodeInputStream,"gbk");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            BufferedReader bufferedReader=new BufferedReader(reader);
            String line=null;
            try {
				while((line=bufferedReader.readLine())!=null) {
					String[]line_contents=line.split(",");
					LoadMap.locationAndCode.put(line_contents[1],Integer.parseInt(line_contents[0]));// 北京市,10000
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
    	}
    	return LoadMap.locationAndCode;
    }
    
    /**
     *  load event and code to Map：eventAndCode 
     * @return Map<String,String> e.g event keyword, code
     */
    public synchronized static Map<String,String> loadEventCode() {
    	if(LoadMap.eventAndCode==null) {
    		LoadMap.eventAndCode=new HashMap<String,String>();
    		InputStream locCodeInputStream=LoadMap.class.getResourceAsStream(PropertyUtil.getFilePathProperty().getProperty("EVTCODEPATH"));
    		InputStreamReader reader= null;
            try {
                reader = new InputStreamReader(locCodeInputStream,"gbk");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            BufferedReader bufferedReader=new BufferedReader(reader);
            String line=null;
            try {
				while((line=bufferedReader.readLine())!=null) {
					String[]line_contents=line.split(",");
					LoadMap.locationAndCode.put(line_contents[1],Integer.parseInt(line_contents[0]));// 水旱灾害,10000
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
            
    	}
    	return LoadMap.eventAndCode;
    }
}
