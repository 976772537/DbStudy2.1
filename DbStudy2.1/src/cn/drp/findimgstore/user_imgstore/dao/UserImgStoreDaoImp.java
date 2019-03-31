package cn.drp.findimgstore.user_imgstore.dao;

import cn.drp.findimgstore.Imp.UserImgStoreDao;
import cn.drp.findimgstore.user_imgstore.domain.UserImgStore;
import utils.DSUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserImgStoreDaoImp implements UserImgStoreDao {
    private QueryRunner qr = DSUtils.getQueryRunner ();

    @Override
    public String findImgIDByid(String uid) throws SQLException {
        String sql = "select * from user_imgstore where uid=?";
        UserImgStore is = qr.query (sql, new BeanHandler<> (UserImgStore.class),uid);
        return is.getImgid ();
    }

    @Override
    public void updateUserImgStore(String uid, String imgid) throws SQLException {
        String sql = "update user_imgstore set imgid=? where uid=?";
        qr.update (sql,imgid,uid);
    }

    @Override
    public void deletedUserImgStoreByid(String uid) throws SQLException {
        String sql = "delete from user_imgstore where uid = ?";
        qr.update (sql,uid);
    }

    @Override
    public void insertUserImgStore(String uid, String imgID) throws SQLException {
        String sql="insert into user_imgstore (uid,imgid) values(?,?)";
        qr.update (sql,uid,imgID);
    }
}
