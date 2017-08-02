import Util.DealMsg;

public class MainFun {

    public static void main(String[] args) throws Exception {
        DealMsg dealMsg=new DealMsg();
        /*File file=new File("./msg.txt");
        FileReader fileReader=new FileReader(file);
        BufferedReader bufferedReader=new BufferedReader(fileReader);
        String line=null;
        while((line=bufferedReader.readLine())!=null){
            String result=dealMsg.filterHotMsg(line);
            System.out.println("杩囨护涔嬪墠:"+line+"\n杩囨护涔嬪悗:"+result);
        }*/
//
        UserDict userDict=new UserDict();
        userDict.addEvtToDict();
        userDict.addLocationToDist();
/*
        testPath test=new testPath();
        test.addLocationToDist();
        test.addEvtToDict();
*/
    }
}
