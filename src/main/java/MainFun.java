import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.ansj.library.DicLibrary;
import org.ansj.splitWord.analysis.DicAnalysis;

import Entity.HotMsg;
import Util.DealMsg;
import Util.LoadDict;

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
        LoadDict userDict=new LoadDict();
        userDict.addEvtToDict();
        userDict.addLocationToDist();

        String line="今日偃师一男子砍死事件收到了广泛的关注";
        DicLibrary.insert(DicLibrary.DEFAULT, "偃师", "location", 10240);//代码显式指定是OK的
        HotMsg hotMsg=dealMsg.filterHotMsg(line);
        System.out.print("content:"+hotMsg.getMsg_content()+",location:"+hotMsg.getMsg_province()+","+hotMsg.getMsg_city()+","+hotMsg.getMsg_district()+",event:"+hotMsg.getEvt_class()+","+hotMsg.getEvt_word());

    }
}
