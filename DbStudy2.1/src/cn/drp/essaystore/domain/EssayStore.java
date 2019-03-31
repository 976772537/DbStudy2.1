package cn.drp.essaystore.domain;

import cn.drp.findimgstore.eassy_imgstore.domain.EssayImgStore;
import utils.Drputils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EssayStore {
    private String essayid;
    private String uid;
    private String title;
    private String body;
    private String time;
    private int hotSpot;
    private String filetype;
    private List<String> essayImgPath=null;
    @Override
    public String toString() {
        return "EssayStore{" +
                "essayid='" + essayid + '\'' +
                ", uid='" + uid + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

    public EssayStore() {}

    public List<String> getEssayImgPath() {
        return essayImgPath;
    }

    public String getEssayid() {
        return essayid;
    }

    public void setEssayid(String essayid) {
        this.essayid = essayid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getHotSpot() {
        return hotSpot;
    }

    public void setHotSpot(int hotSpot) {
        this.hotSpot = hotSpot;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public void setEssayImgPath(List<String> essayImgPath) {
        this.essayImgPath = essayImgPath;
    }
}
