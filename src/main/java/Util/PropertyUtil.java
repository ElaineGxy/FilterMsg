package Util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {

	public static Properties getFilePathProperty() {
		Properties fileProperty = new Properties();
		String relativelyPath = System.getProperty("user.dir");
		String filePath = relativelyPath + "/FilePath.properties";
		FileInputStream in;
		try {
			in = new FileInputStream(filePath);
			fileProperty.load(in);
			in.close();
			return fileProperty;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static Properties getDBValProperty() {
		Properties DBProperty = new Properties();
		String relativelyPath = System.getProperty("user.dir");
		String filePath = relativelyPath + "/cts_env.properties";
		FileInputStream in;
		try {
			in = new FileInputStream(filePath);
			DBProperty.load(in);
			in.close();
			return DBProperty;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
