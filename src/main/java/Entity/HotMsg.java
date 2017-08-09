package Entity;

import java.util.List;

public class HotMsg {
    private String msg_province;//一级地域 省份
    private String msg_city;//二级地域：城市
    private String evt_class;//事件类别 例如自然灾害、社会公共事件
    private String evt_word;//具体时间：山体滑坡，泥石流
    private List<String>keyword;
	public String getMsg_province() {
		return msg_province;
	}
	public void setMsg_province(String msg_province) {
		this.msg_province = msg_province;
	}
	public String getMsg_city() {
		return msg_city;
	}
	public void setMsg_city(String msg_city) {
		this.msg_city = msg_city;
	}
	public String getEvt_class() {
		return evt_class;
	}
	public void setEvt_class(String evt_class) {
		this.evt_class = evt_class;
	}
	public String getEvt_word() {
		return evt_word;
	}
	public void setEvt_word(String evt_word) {
		this.evt_word = evt_word;
	}
	public List<String> getKeyword() {
		return keyword;
	}
	public void setKeyword(List<String> keyword) {
		this.keyword = keyword;
	}
    
}
