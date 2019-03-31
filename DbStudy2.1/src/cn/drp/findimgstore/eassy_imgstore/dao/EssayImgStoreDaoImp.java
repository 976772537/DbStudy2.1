package cn.drp.findimgstore.eassy_imgstore.dao;

import cn.drp.essaystore.domain.EssayStore;
import cn.drp.findimgstore.Imp.UserImgStoreDao;
import cn.drp.findimgstore.eassy_imgstore.domain.EssayImgStore;
import cn.drp.findimgstore.user_imgstore.domain.UserImgStore;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.DSUtils;

import java.sql.SQLException;
import java.util.List;

public class EssayImgStoreDaoImp implements UserImgStoreDao {
    private QueryRunner qr = DSUtils.getQueryRunner ();

    @Override
    public String findImgIDByid(String essayId) throws SQLException {
        String sql = "select * from essay_imgstore where essayid=?";
        EssayImgStore is = qr.query (sql, new BeanHandler<> (EssayImgStore.class), essayId);
        return is.getImgId ();
    }

    public List<EssayImgStore> findAllImgIDById(String essayId) throws SQLException {
        String sql = "select * from essay_imgstore where essayid=?";
        return qr.query (sql, new BeanListHandler<> (EssayImgStore.class), essayId);
    }

    @Override
    public void updateUserImgStore(String essayid, String imgid) throws SQLException {
        String sql = "update essay_imgstore set imgid=? where essayid=?";
        qr.update (sql, imgid, essayid);
    }

    @Override
    public void deletedUserImgStoreByid(String uid) throws SQLException {
        String sql = "delete from essay_imgstore where essayid = ?";
        qr.update (sql, uid);
    }

    @Override
    public void insertUserImgStore(String essayid, String imgID) throws SQLException {
        String sql = "insert into essay_imgstore (essayid,imgid) values(?,?)";
        qr.update (sql, essayid, imgID);
    }
}
