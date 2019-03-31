package cn.drp.tests.dao;

import cn.drp.tests.domain.Test;
import utils.BeanUtils;
import utils.DSUtils;
import utils.SqlUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class TestDaoImp implements TestDao{
    private QueryRunner qr = DSUtils.getQueryRunner ();

    public TestDaoImp() {
    }

    public List<Test> findTest(int start, int num, String tid) throws SQLException {
        String sql = " SELECT * FROM `test` where tid = ? ORDER BY RAND() LIMIT " + start + "," + num;
        return qr.query (sql, new BeanListHandler<Test> (Test.class), tid);
    }

    public List<Test> findTestBySid(String sid) throws SQLException {
        String sql = " SELECT * FROM `test` where sid = ?";
        return qr.query (sql, new BeanListHandler<Test> (Test.class), sid);
    }

    public void insertTest(Test tb) throws SQLException {
        String sql = SqlUtils.getInsertSql (tb, "test");
        qr.update (sql, BeanUtils.getParam (tb));
    }

    public List<Test> findhotTest(int start, int num) throws SQLException {
        String sql = " SELECT * FROM `test` ORDER BY hotspot DESC LIMIT " + start + "," + num;
        return qr.query (sql, new BeanListHandler<Test> (Test.class));
    }

    public int findTestHotSportBySid(String sid) throws SQLException {
        String sql = "select hotspot from test where sid = ?";
        return qr.query (sql, new BeanHandler<> (Test.class), sid).getHotspot ();
    }

    @Override
    public List<Test> findTestByUid(String uid) throws SQLException {
        String sql = "select * from test where uid = ?";
        return qr.query (sql, new BeanListHandler<> (Test.class), uid);
    }

    @Override
    public List<Test> findTestByUidWithTime(String uid, String time) throws SQLException {
        String sql = "select * from test where uid = ? and time like '%"+time+"%' Order by time desc";
        return qr.query (sql, new BeanListHandler<> (Test.class), uid);
    }

    public void updateHotSportBySid(int hotsport, String sid) throws SQLException {
        String sql = "update test set hotspot = ? where sid = ?";
        qr.update (sql, hotsport, sid);
    }
}
