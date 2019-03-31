package cn.drp.findimgstore.eassy_imgstore.domain;

public class EssayImgStore {
    private String imgId;
    private String essayId;

    @Override
    public String toString() {
        return "EssayImgStore{" +
                "imgId='" + imgId + '\'' +
                ", essayId='" + essayId + '\'' +
                '}';
    }

    public EssayImgStore() {
    }

    public EssayImgStore(String imgId, String essayId) {
        this.imgId = imgId;
        this.essayId = essayId;
    }

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    public String getEssayId() {
        return essayId;
    }

    public void setEssayId(String essayId) {
        this.essayId = essayId;
    }
}
