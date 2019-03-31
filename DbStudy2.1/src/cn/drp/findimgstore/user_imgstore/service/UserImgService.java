package cn.drp.findimgstore.user_imgstore.service;

import cn.drp.imgstore.service.ImgService;
import cn.drp.findimgstore.Imp.UserImgStoreDao;
import cn.drp.findimgstore.Imp.UserImgStoreDaoFactory;
import exception.ImgInsertFaildException;
import exception.ImgNotFoundException;

import java.sql.SQLException;

public class UserImgService {
    private UserImgStoreDao ud;
    private ImgService ims ;

    public UserImgService(String name, ImgService ims) {
        this.ud = UserImgStoreDaoFactory.getUserImgStoreDao (name);
        this.ims=ims;
    }

    public String findUserhead(String uid) throws ImgNotFoundException {
        try {
            String imgid = ud.findImgIDByid (uid);
            return ims.findImgPathByImgID (imgid);
        } catch (SQLException | ImgNotFoundException e) {
            e.printStackTrace ();
            throw new ImgNotFoundException ("图片没找到");
        }
    }

    public void insertRandomUserhead(String uid) throws ImgInsertFaildException {
        try {
            ud.insertUserImgStore (uid, ims.findRandomImg ().getImgID ());
        } catch (SQLException | ImgNotFoundException e) {
            e.printStackTrace ();
            throw new ImgInsertFaildException ("发生未知错误");
        }
    }

    public void updateUserHead(String uid, String imgId) throws ImgNotFoundException {
        try {
            ud.updateUserImgStore (uid, imgId);
        } catch (SQLException e) {
            e.printStackTrace ();
            throw new ImgNotFoundException ("图片没找到");
        }
    }

    public void deleteUserHead(String uid) throws ImgNotFoundException {
        try {
            ud.deletedUserImgStoreByid (uid);
        } catch (SQLException e) {
            e.printStackTrace ();
            throw new ImgNotFoundException ("删除图片没找到");
        }
    }
}