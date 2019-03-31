package cn.drp.imgstore.service;

import cn.drp.imgstore.dao.ImgDao;
import cn.drp.imgstore.dao.ImgDaoFactory;
import cn.drp.imgstore.domain.ImgStore;
import exception.ImgInsertFaildException;
import exception.ImgNotFoundException;
import utils.Drputils;

import java.sql.SQLException;
import java.util.List;

public class ImgService {
    private ImgDao imd;

    public ImgService(String name) {
        this.imd = ImgDaoFactory.getImgDao (name);
    }

    public List<ImgStore> findAllDefaultImgStore() throws ImgNotFoundException {
        try {
            return imd.findAllDefaultImgStore ();
        } catch (SQLException e) {
            e.printStackTrace ();
            throw new ImgNotFoundException ("图片没找到");
        }
    }

    public ImgStore findRandomImg() throws ImgNotFoundException {
        try {
            return imd.findRandomImgStore ();
        } catch (SQLException e) {
            e.printStackTrace ();
            throw new ImgNotFoundException ("图片没有找到");
        }
    }

    public void insertImg(ImgStore img,int state) throws ImgInsertFaildException {
        try {
            imd.insertNewImgStore (img,state);
        } catch (SQLException e) {
            e.printStackTrace ();
            throw new ImgInsertFaildException ("图片插入失败");
        }
    }

    public String findImgPathByImgID(String imgid) throws ImgNotFoundException {
        try {
            return imd.findImgStoreByImgID (imgid);
        } catch (SQLException e) {
            e.printStackTrace ();
            throw new ImgNotFoundException ("图片没有找到");
        }
    }

    public void deleteImgByImgPath(String imgPath, int state) throws ImgNotFoundException {
        try {
            imd.deleteImgStore (imgPath,state);
        } catch (SQLException e) {
            e.printStackTrace ();
            throw new ImgNotFoundException ("图片没找到");
        }
    }

    public String findImgIdByImgPath(String imgpath) throws ImgNotFoundException {
        try {
            ImgStore imgID= imd.findImgStoreByImgStorePath (imgpath);
            return imgID.getImgID ();
        } catch (SQLException e) {
            e.printStackTrace ();
            throw new ImgNotFoundException ("图片没找到");
        }
    }

    public void updateImgStateByImgPath(String imgPath, int state) throws ImgNotFoundException {
        try {
            imd.updateImgStateByImgPath (imgPath, state);
        }catch (SQLException e){
            e.printStackTrace ();
            throw new ImgNotFoundException ("图片没找到");
        }
    }
}
