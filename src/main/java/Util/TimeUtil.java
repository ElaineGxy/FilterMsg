package Util;

import java.io.BufferedReader;
import java.io.Reader;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class TimeUtil {
	public static String DateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = sdf.format(date);
		return str;
	}

	public static Date StringToDate(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(0L);
		try {
			date = sdf.parse(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	public static String TimestampToString(Timestamp timestamp) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = sdf.format(timestamp);
		return str;
	}

	public static String getHourAgo(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = sdf.format(new Date(date.getTime() - 1000 * 60 * 60));
		return str;
	}

	public static String getStarttime(Date date) {
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		String str2 = sdf2.format(date);
		str2 += " 00:00:00";
		return str2;
	}

	public static String getTstime(Date date) {
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		String str2 = sdf2.format(date);
		str2 += " 00:00:01";
		return str2;
	}

	// 降序排列
	public ArrayList<Map.Entry<Long, Integer>> sortMap(Map map) {
		List<Map.Entry<Long, Integer>> entries = new ArrayList<Map.Entry<Long, Integer>>(
				map.entrySet());
		Collections.sort(entries, new Comparator<Map.Entry<Long, Integer>>() {
			public int compare(Map.Entry<Long, Integer> obj1,
					Map.Entry<Long, Integer> obj2) {
				return obj2.getValue() - obj1.getValue();
			}
		});
		return (ArrayList<Entry<Long, Integer>>) entries;
	}

	// 升序排列
	public ArrayList<Map.Entry<String, Integer>> sortMapString(Map map) {
		List<Map.Entry<String, Integer>> entries = new ArrayList<Map.Entry<String, Integer>>(
				map.entrySet());
		Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> obj1,
					Map.Entry<String, Integer> obj2) {
				return obj1.getValue() - obj2.getValue();
			}
		});
		return (ArrayList<Entry<String, Integer>>) entries;
	}

	public List<Long> getTimeOfRange(String starttime, String endtime) {
		List<Long> li = new ArrayList<Long>();
		Timestamp da = Timestamp.valueOf(starttime);
		Timestamp ea = Timestamp.valueOf(endtime);
		da.setMinutes(0);
		da.setSeconds(0);
		da.setNanos(0);
		// //log.info("wx"+da);

		long tmp = da.getTime();
		while (tmp < ea.getTime()) {
			li.add(tmp);
			tmp += 60 * 60 * 1000;
		}
		return li;
	}

}
