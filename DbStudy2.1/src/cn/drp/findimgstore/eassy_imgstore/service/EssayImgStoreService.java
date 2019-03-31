package cn.drp.findimgstore.eassy_imgstore.service;

import cn.drp.findimgstore.Imp.UserImgStoreDao;
import cn.drp.findimgstore.Imp.UserImgStoreDaoFactory;
import cn.drp.findimgstore.eassy_imgstore.dao.EssayImgStoreDaoImp;
import cn.drp.findimgstore.eassy_imgstore.domain.EssayImgStore;
import cn.drp.imgstore.service.ImgService;
import exception.ImgInsertFaildException;
import exception.ImgNotFoundException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EssayImgStoreService {
    private UserImgStoreDao ud;
    private ImgService ims ;

    public EssayImgStoreService(String name, ImgService ims) {
        this.ud = UserImgStoreDaoFactory.getUserImgStoreDao (name);
        this.ims=ims;
    }

    public String findEssayImg(String essayid) throws ImgNotFoundException {
        try {
            String imgid = ud.findImgIDByid (essayid);
            return ims.findImgPathByImgID (imgid);
        } catch (SQLException | ImgNotFoundException e) {
            e.printStackTrace ();
            throw new ImgNotFoundException ("图片没找到");
        }
    }

    public ArrayList findAllEssayImgPath(String essayid) throws ImgNotFoundException {
        ArrayList arrayList=new ArrayList ();
        try {
            List<EssayImgStore> allImg = ((EssayImgStoreDaoImp) ud).findAllImgIDById (essayid);
            for(EssayImgStore eis : allImg){
                arrayList.add (ims.findImgPathByImgID (eis.getImgId ()));
            }
            return arrayList;
        } catch (SQLException e) {
            e.printStackTrace ();
            throw new ImgNotFoundException ("没有图片");
        }
    }

    public void insertRandomEssayImg(String essayid) throws ImgInsertFaildException {
        try {
            ud.insertUserImgStore (essayid, ims.findRandomImg ().getImgID ());
        } catch (SQLException | ImgNotFoundException e) {
            e.printStackTrace ();
            throw new ImgInsertFaildException ("发生未知错误");
        }
    }
    public void insertEssayImg(String essayid,String imgPath) throws ImgInsertFaildException {
        try {
            ud.insertUserImgStore (essayid, ims.findImgIdByImgPath (imgPath));
        } catch (SQLException | ImgNotFoundException e) {
            e.printStackTrace ();
            throw new ImgInsertFaildException ("发生未知错误");
        }
    }

    public void updateEssayImg(String essayid, String imgId) throws ImgNotFoundException {
        try {
            ud.updateUserImgStore (essayid, imgId);
        } catch (SQLException e) {
            e.printStackTrace ();
            throw new ImgNotFoundException ("图片没找到");
        }
    }

    public void deleteEssayImg(String essayid) throws ImgNotFoundException {
        try {
            ud.deletedUserImgStoreByid (essayid);
        } catch (SQLException e) {
            e.printStackTrace ();
            throw new ImgNotFoundException ("删除图片没找到");
        }
    }
}
