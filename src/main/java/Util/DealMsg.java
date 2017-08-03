package Util;

import Entity.HotMsg;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.DicAnalysis;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


/**
 * deal wechat message, if it is a hot, then get a HotMsg, else null
 */
public class DealMsg {
    public LoadMap maps;
    public AddressUtils addressUtil;
    public DealMsg(){
        this.maps=new LoadMap();
        LoadDict loadDict=new LoadDict();
        loadDict.init();
        LoadMap loadMap=new LoadMap();
        this.addressUtil=new AddressUtils();
    }
    public HotMsg filterHotMsg(String message){
        String[]sentences=this.cutSentence(message);
        if(sentences==null)return null;
        int length=sentences.length;
        for(int i=0;i<length;i++){
            Result result=DicAnalysis.parse(sentences[i]);
//            System.out.println("Result:"+result);
            List<String> locAndEvtList=haveLocationAndEvent(result);
            if(locAndEvtList!=null&&locAndEvtList.size()==2){
                HotMsg hotMsg=new HotMsg(message);
                this.trackLocation(locAndEvtList.get(0),hotMsg);
                this.trackEvent(locAndEvtList.get(1),hotMsg);
                //取得message的ip地址
                return hotMsg;
            }
        }
        return null;
    }

    /**
     * get the location and event keyword for a message
     * @param result: segmentation of a message
     * @return true if have event and location keyword else return false
     */
    private List<String> haveLocationAndEvent(Result result) {
        List<Term> termList=result.getTerms();
        String locKeyword=null;
        String evtKeyword=null;
        for(Term term:termList){
        	this.maps.updateHotWordMap(term.getName());
            if(term.getNatureStr().equals("location")){
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
     * cut a message to sentences
     * @param message: a text which may contains many sentences
     * @return:sentences
     */
    public String[] cutSentence(String message){
        String regEx="[？。！；!?.!;]";
        Pattern pattern= Pattern.compile(regEx);
        String[]sentences=pattern.split(message);
        if(sentences==null||sentences.length==0)return null;
        return sentences;
    }

    /**
     * get the superior for location e.g. tiananmen->beijing
     * @param location
     * @return
     */
    public void trackLocation(String location,HotMsg hotMsg){
        if(!this.maps.getLocation().containsKey(location)){
            System.err.println("Error:DealMsg.java/Fun trackLocation/line 73");
        }else{
            String upperLocation= (String) this.maps.getLocation().get(location);
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
     * get the class for an event e.g. earthquake->natural disaster
     * @param evtWord
     * @param hotMsg
     */
    public void trackEvent(String evtWord,HotMsg hotMsg){
        if(!this.maps.getEvent().containsKey(evtWord)){
            System.err.println("Error:DealMsg.java/Funt trackEvent/line 93");
        }else{
            String evtClass= (String) this.maps.getEvent().get(evtWord);
            hotMsg.setEvt_word(evtWord);
            hotMsg.setEvt_class(evtClass);
        }
    }

}
