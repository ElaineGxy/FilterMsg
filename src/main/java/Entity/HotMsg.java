package Entity;

public class HotMsg {
    private String msg_content;
    private String msg_province;//一级地域 省份
    private String msg_city;//二级地域 城市
    private String msg_district;//三级地域

    private String evt_class;//事件类别 例如自然灾害、社会公共事件
    private String evt_word;//事件关键词,例如 地震、火灾

    public HotMsg(String msg_content){
        this.msg_content=msg_content;
    }

    public String getMsg_content() {
        return msg_content;
    }

    public String getMsg_province() {
        return msg_province;
    }

    public String getMsg_city() {
        return msg_city;
    }

    public String getMsg_district() {
        return msg_district;
    }

    public String getEvt_class() {
        return evt_class;
    }

    public String getEvt_word() {
        return evt_word;
    }

    public void setMsg_content(String msg_content) {
        this.msg_content = msg_content;
    }

    public void setMsg_province(String msg_province) {
        this.msg_province = msg_province;
    }

    public void setMsg_city(String msg_city) {
        this.msg_city = msg_city;
    }

    public void setMsg_district(String msg_district) {
        this.msg_district = msg_district;
    }

    public void setEvt_class(String evt_class) {
        this.evt_class = evt_class;
    }

    public void setEvt_word(String evt_word) {
        this.evt_word = evt_word;
    }
}