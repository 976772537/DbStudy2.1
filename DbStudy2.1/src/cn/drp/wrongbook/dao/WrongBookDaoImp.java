package cn.drp.wrongbook.dao;

import cn.drp.wrongbook.domain.WrongBook;
import utils.DSUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class WrongBookDaoImp implements  WrongBookDao{
    private QueryRunner qr = DSUtils.getQueryRunner ();

    public List<WrongBook> findWrongBookByUidWithTime(String uid,String time) throws SQLException {
        String sql = "select * from wrongbook where uid = ? and time like '%"+time+"%' Order by time desc";
        return qr.query (sql, new BeanListHandler<WrongBook> (WrongBook.class), uid);
    }
    public List<WrongBook> findWrongBookByUid(String uid) throws SQLException {
        String sql = "select * from wrongbook where uid = ?  Order by time";
        return qr.query (sql, new BeanListHandler<WrongBook> (WrongBook.class), uid);
    }

    public void addUidAndSid(String uid, String sid, String wid) throws SQLException {
        String sql = "insert into wrongbook (uid,sid,wid) values(?,?,?)";
        qr.update (sql, uid, sid, wid);
    }

}
