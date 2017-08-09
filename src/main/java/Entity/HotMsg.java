package Entity;

import java.util.List;

public class HotMsg {
    private String msg_province;//一级地域 省份
    private String evt_class;//事件类别 例如自然灾害、社会公共事件
    private List<String>keyword;
	
	public String getMsg_province() {
		return msg_province;
	}
	public void setMsg_province(String msg_province) {
		this.msg_province = msg_province;
	}
	public String getEvt_class() {
		return evt_class;
	}
	public void setEvt_class(String evt_class) {
		this.evt_class = evt_class;
	}
	public List<String> getKeyword() {
		return keyword;
	}
	public void setKeyword(List<String> keyword) {
		this.keyword = keyword;
	}
}
