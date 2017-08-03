package Util;
import java.io.*;
import java.util.Properties;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 * 用于操作用户自定义词典，地点词汇表以及时间词汇表
 */
public class LoadDict {
	private final static Logger logger = LoggerFactory.getLogger(LoadDict.class);
	private File dictFile=null;
	private FileWriter fileWriter=null;
	private BufferedWriter bufferedWriter=null;
    private String dictPath=null;
    private String locPath=null;
    private String evtPath=null;

	public LoadDict() {
		try {
			Properties fileProperty = PropertyUtil.getFilePathProperty();
			if(fileProperty==null) {
				logger.info("FileProperty is null");
			}
			this.dictPath= fileProperty.getProperty("DICPATH");
			this.evtPath = fileProperty.getProperty("EVTPATH");
			this.locPath = fileProperty.getProperty("LOCPATH");
		} catch (Throwable e) {
			throw new ExceptionInInitializerError(e);
		}
		this.dictPath=this.getClass().getResource(this.dictPath).getPath();
        dictFile=new File(dictPath);
        try {
            fileWriter=new FileWriter(dictFile);
        } catch (IOException e) {
            System.err.println("Exception: UserDict.java/line22");
            e.printStackTrace();
        }
        bufferedWriter=new BufferedWriter(fileWriter);
	}
	
	public void init() {
		this.addEvtToDict();
		this.addLocationToDist();
	}

	/**
	 * 将location中的词汇加入到用户自定义词典中，同时生成locationMap
	 */
    public void addLocationToDist(){
        InputStream locInputStream=this.getClass().getResourceAsStream(this.locPath);
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
                String[]line_content=line.split(",");
                bufferedWriter.write(line_content[0]+"\tlocation\t10240");
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bufferedReader.close();
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void addEvtToDict(){
        InputStream evtInputStream=this.getClass().getResourceAsStream(this.evtPath);
        InputStreamReader reader= null;
        try {
            reader = new InputStreamReader(evtInputStream,"gbk");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader=new BufferedReader(reader);
        String line=null;
        try {
            while((line=bufferedReader.readLine())!=null){
                String[]line_content=line.split(",");
                bufferedWriter.write(line_content[1]+"\tevent\t10240");
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bufferedWriter.flush();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     *灏唚ord娣诲姞鍒拌嚜瀹氫箟璇嶅吀涓�
     * @param word
     * @param label:鐪佷唤锛歭c 浜嬩欢
     * @return
     */
    /*public boolean addToDist(String word,String label,int fre){
        try {
            bufferedWriter.write(word.trim()+"\t"+label+fre+"\n");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }*/
}
