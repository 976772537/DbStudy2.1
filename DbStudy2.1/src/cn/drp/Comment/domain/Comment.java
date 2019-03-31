package cn.drp.Comment.domain;

public class Comment {
    private String cid;
    private String essayid;
    private String uid;
    private int hotspot;
    private String content;
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Comment() {
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
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

    public int getHotspot() {
        return hotspot;
    }

    public void setHotspot(int hotspot) {
        this.hotspot = hotspot;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
