package Util;

import Entity.HotMsg;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.DicAnalysis;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * deal wechat message, if it is a hot, then get a HotMsg, else null
 */
public class DealMsg {
    public MapUtil maps;
//    public AddressUtil addressUtil;
    public DealMsg(){
        this.maps=MapUtil.getInstance();
//        this.addressUtil=new AddressUtil();
    }
    public HotMsg filterHotMsg(String message,String ip){
        String[]sentences=this.cutSentence(message);
        if(sentences==null)return null;
        int length=sentences.length;
        for(int i=0;i<length;i++){
            Result result=DicAnalysis.parse(sentences[i]);
//            System.out.println("Result:"+result);
            List<String> locAndEvtList=this.haveLocationAndEvent(result);
            if(!this.containsFilterOutWord(sentences[i])&&locAndEvtList!=null&&locAndEvtList.size()==2&&!this.isQuestion(sentences[i])){
                HotMsg hotMsg=new HotMsg(message);
                String location=locAndEvtList.get(0);
                String event=locAndEvtList.get(1);
                String upperLocation=this.maps.trackLocation(location);
                if(upperLocation==null){
                    hotMsg.setMsg_province(locAndEvtList.get(0));
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
                String eventClass=this.maps.trackEvent(event);
                hotMsg.setEvt_class(eventClass);
                hotMsg.setEvt_word(event);
                /*try {
                    List<String> address=this.addressUtil.getAddresses("ip="+ip,"utf-8");
                    if(address==null)System.out.println("null");
                    hotMsg.setMsg_send_province(address.get(0));
                    hotMsg.setMsg_send_city(address.get(1));
                    hotMsg.setMsg_send_district(address.get(2));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }*/
                //取得message的ip地址
                return hotMsg;
            }
        }
        return null;
    }
    
    /**
	 * get the location and event keyword for a message
	 *
	 *update: add the limit for the number of noun in a sentence
	 * @param result:
	 *            segmentation of a message
	 * @return true if have event and location keyword else return false
	 */
	public List<String> haveLocationAndEvent(Result result) {
		List<Term> termList = result.getTerms();
		String locKeyword = null;
		String evtKeyword = null;
		int nounCount=0;
		for (Term term : termList) {
			this.maps.updateHotWordMap(term.getName());
			if (term.getNatureStr().equals("location")) {
				locKeyword = term.getName();
			}
			if (term.getNatureStr().equals("event")) {
				evtKeyword = term.getName();
			}
			if(term.getNatureStr().startsWith("n"))
				nounCount++;
		}
		if (locKeyword != null && evtKeyword != null&&nounCount>1) {
			List<String> resultList = new ArrayList<String>();
			resultList.add(locKeyword);
			resultList.add(evtKeyword);
			return resultList;
		}
		return null;
	}
    
	/**
	 * judge whether a sentence contains filterout words or not
	 * @param sentence
	 * @return
	 */
	public boolean containsFilterOutWord(String sentence) {
		Set<String> wordSet=this.maps.getFilterOutWord();
		for(String word:wordSet) {
			if(sentence.contains(word))return true;
		}
		return false;
	}
    /**
     * determine whether this sentence is a question or not
     * @param sentence
     * @return
     */
    public boolean isQuestion(String sentence) {
    	if(sentence.contains("?")||sentence.contains("？")||sentence.contains("吗")||sentence.contains("哪")||
    			sentence.contains("怎么")||sentence.contains("什么")) {
    		return true;
    	}
    	return false;
    }
    

    /**
     * compared with the previous version, this version can reserve punctuation 
     * cut a message to sentences
     * @param message: a text which may contains many sentences
     * @return:[]String
     */
    
    public String[] cutSentence(String message) {
    	String regEx="[？。！；!?.!;]";
        Pattern pattern= Pattern.compile(regEx);
        Matcher matcher=pattern.matcher(message);
        String[]sentences=pattern.split(message);
        if(sentences.length>0) {
        	int count=0;
        	while(count<sentences.length) {
        		if(matcher.find()) {
        			sentences[count]+=matcher.group();
        		}
        		count++;
        	}
        }
        return sentences;
    }


}
