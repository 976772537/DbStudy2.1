package cn.drp.annunciate.domain;

public class Annunciate {
    private String title;
    private String context;
    private String time;
    private String uid;
    private String ann_id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAnn_id() {
        return ann_id;
    }

    public void setAnn_id(String ann_id) {
        this.ann_id = ann_id;
    }
}