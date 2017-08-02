
import java.io.*;

/**
 * 用于操作用户自定义词典，地点词汇表以及时间词汇表
 */
public class UserDict {
	private File dictFile=null;
	private FileWriter fileWriter=null;
	private BufferedWriter bufferedWriter=null;
    private String dictPath="/library/userLibrary.dic";
    private String locPath="library/location.csv";
    private String evtPath="library/event.csv";

    public UserDict(){
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
        //        File customFile = new File(classLoader.getResource(this.locPath).getFile());
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
