package cn.drp.wrongbook.domain;

public class WrongBook {
    private String uid;
    private String sid;
    private String time;
    private String wid;

    public String getWid() {
        return wid;
    }

    public void setWid(String wid) {
        this.wid = wid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public WrongBook() {
    }

    @Override
    public String toString() {
        return "WrongBookDao{" +
                "uid='" + uid + '\'' +
                ", sid='" + sid + '\'' +
                ", time='" + time + '\'' +
                ", wid='" + wid + '\'' +
                '}';
    }
}
