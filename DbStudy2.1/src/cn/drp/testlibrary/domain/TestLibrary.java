package cn.drp.testlibrary.domain;

public class TestLibrary {
    private String tid;
    private  String t_name;
    private String desc;
    private String type;

    public TestLibrary() {
    }

    public TestLibrary(String tid, String t_name, String desc, String type) {
        this.tid = tid;
        this.t_name = t_name;
        this.desc = desc;
        this.type = type;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
