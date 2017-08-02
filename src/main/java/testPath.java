import java.io.*;

public class testPath {
    public String locPath="library/location.csv";
    public String evtPath="library/event.csv";
    public testPath(){

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
//                bufferedWriter.write(line_content[0]+"\tlocation\t10240");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bufferedReader.close();
//            bufferedWriter.flush();
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
//                bufferedWriter.write(line_content[1]+"\tevent\t10240");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
//            bufferedWriter.flush();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
