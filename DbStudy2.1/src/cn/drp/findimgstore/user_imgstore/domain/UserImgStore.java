package cn.drp.findimgstore.user_imgstore.domain;

public class UserImgStore {
    private String uid;
    private String imgid;
    @Override
    public String toString() {
        return "UserImgStore{" +
                "uid='" + uid + '\'' +
                ", imgid='" + imgid + '\'' +
                '}';
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getImgid() {
        return imgid;
    }

    public void setImgid(String imgid) {
        this.imgid = imgid;
    }
}
