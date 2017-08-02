package Util;

import Entity.HotMsg;
import Service.Common;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.DicAnalysis;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


/**
 * 浠庝紬澶氭秷鎭腑鍙戠幇鐑偣
 */
public class DealMsg {
    public Common common;
    public DealMsg(){
        this.common=new Common();
    }
    public HotMsg filterHotMsg(String message){
        String[]sentences=this.cutSentence(message);
        if(sentences==null)return null;
        //褰撲竴涓彞瀛愪腑鍚屾椂鍖呭惈鍦扮偣鍜屼簨浠跺叧閿瘝锛屽垯璁や负璇ュ彞瀛愭湁鏁�
        int length=sentences.length;
        for(int i=0;i<length;i++){
            Result result=DicAnalysis.parse(sentences[i]);
            List<String> locAndEvtList=haveLocationAndEvent(result);
            if(locAndEvtList!=null){
                HotMsg hotMsg=new HotMsg(message);
                this.trackLocation(locAndEvtList.get(0),hotMsg);
                this.trackEvent(locAndEvtList.get(1),hotMsg);
                return hotMsg;
            }
        }
        return null;
    }

    /**
     * 鍒ゆ柇涓�涓彞瀛愪腑鏄惁鍚屾椂鍖呭惈鍦扮偣鍜屼簨浠跺叧閿瘝
     * @param result:浼犲叆璇嶆�ф爣娉ㄥ悗鐨勫彞瀛�
     * @return true if have event and location keyword else return false
     */
    private List<String> haveLocationAndEvent(Result result) {
        List<Term> termList=result.getTerms();
        String locKeyword=null;
        String evtKeyword=null;
        for(Term term:termList){
            if(term.getNatureStr().equals("location")||term.getNatureStr().equals("ns")){
                locKeyword=term.getName();
            }
            if(term.getNatureStr().equals("event")){
                evtKeyword=term.getName();
            }
        }
        if(locKeyword!=null&&evtKeyword!=null){
            List<String>resultList=new ArrayList<String>();
            resultList.add(locKeyword);
            resultList.add(evtKeyword);
            return resultList;
        }
        return null;
    }


    /**
     * 鎸夌収鐗瑰畾鐨勭鍙峰垎鍓插彞瀛�
     * @param message:闇�瑕佽繘琛屽垎鍓茬殑鏂囨湰
     * @return:鍒嗗壊鍚庣殑鍙ュ瓙
     */
    public String[] cutSentence(String message){
        String regEx="[銆傦紵!锛�?.!;]";
        Pattern pattern= Pattern.compile(regEx);
        String[]sentences=pattern.split(message);
        if(sentences==null||sentences.length==0)return null;
        return sentences;
    }

    /**
     * 鏍规嵁message涓湴鐐硅瘝杩芥函瀹屾暣鐨勫湴鐐逛俊鎭�,姣斿 澶╁畨闂�-銆嬪寳浜�
     *
     * @param location
     * @return
     */
    public void trackLocation(String location,HotMsg hotMsg){
        if(!this.common.getLocation().containsKey(location)){
            System.err.println("Error:DealMsg.java/Fun trackLocation/line 73");
        }else{
            String upperLocation= (String) this.common.getLocation().get(location);
            if(upperLocation==null){
                hotMsg.setMsg_province(location);
            }else{
                String[]uppers=upperLocation.split(",");
                if(uppers.length==1){
                    hotMsg.setMsg_province(uppers[0]);
                    hotMsg.setMsg_city(location);
                }else{
                    hotMsg.setMsg_district(location);
                    hotMsg.setMsg_city(uppers[0]);
                    hotMsg.setMsg_province(uppers[1]);
                }
            }
        }
    }

    /**
     * 鏍规嵁message涓簨浠跺叧閿瘝杩芥函浜嬩欢鐨勭被鍨� 渚嬪 鍦伴渿->鑷劧鐏惧
     * @param evtWord
     * @param hotMsg
     */
    public void trackEvent(String evtWord,HotMsg hotMsg){
        if(!this.common.getEvent().containsKey(evtWord)){
            System.err.println("Error:DealMsg.java/Funt trackEvent/line 93");
        }else{
            String evtClass= (String) this.common.getEvent().get(evtWord);
            hotMsg.setEvt_word(evtWord);
            hotMsg.setEvt_class(evtClass);
        }
    }

}
