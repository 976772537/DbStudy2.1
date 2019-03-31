package cn.drp.essaystore.dao;

import cn.drp.essaystore.domain.EssayStore;
import cn.drp.findimgstore.eassy_imgstore.domain.EssayImgStore;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.DSUtils;
import utils.page.domain.Page;

import java.sql.SQLException;
import java.util.List;

public class EssayStoreDaoImp implements EssayStoreDao {
    private QueryRunner qr = DSUtils.getQueryRunner ();

    @Override
    public List<EssayStore> findEssayByHotSpot(int start, int num) throws SQLException {
        String sql="select * from essaystore order by hotspot desc limit ?,?";
        return qr.query (sql,new BeanListHandler<> (EssayStore.class),start,num);
    }

    @Override
    public EssayStore findEssayByEssayID(String essayid) throws SQLException {
        String sql = "select * from essaystore where essayid=?";
        return qr.query (sql, new BeanHandler<> (EssayStore.class), essayid);
    }

    @Override
    public List<EssayStore> findEssayStoreByUidWithPage(String uid, Page page) throws SQLException {
        String sql = "select * from essaystore where uid=? order by time limit ?,?";
        return getEssayStoresPage (page,sql);
    }

    @Override
    public List<EssayStore> findEssayStoreByUid(String uid) throws SQLException {
        String sql = "select * from essaystore where uid=? ";
        return qr.query (sql, new BeanListHandler<> (EssayStore.class), uid);

    }

    private List<EssayStore> getEssayStoresPage(Page page, String sql) throws SQLException {
        int cp = page.getCurrentPage ();
        int ps = page.getPageSize ();
        return qr.query (sql, new BeanListHandler<> (EssayStore.class), (cp - 1) * ps, ps);
    }

    @Override
    public List<EssayStore> findEssayStoreByTitle(String title,Page page) throws SQLException {
        String sql = "select * from essaystore where title like '%" + title + "%' limit ?,?";
        return getEssayStoresPage (page, sql);
    }

    @Override
    public int findAllEssayStoreByTitle(String title) throws SQLException {
        String sql = "select count(*) from essaystore where title like '%" + title + "%'";
        Number num = qr.query (sql, new ScalarHandler<Number> ());
        return num.intValue ();
    }

    @Override
    public List<EssayStore> findEssayStoreByBody(String field, Page page) throws SQLException {
        String sql = "select * from essaystore where body like '%" + field + "%'  limit ?,?";
        return getEssayStoresPage (page, sql);
    }

    @Override
    public List<EssayStore> findEssayStoreByTime(String time, Page page) throws SQLException {
        String sql = "select * from essaystore where time like '%" + time + "%' order by time limit ?,?";
        return getEssayStoresPage (page, sql);
    }

    @Override
    public void updateEssay(EssayStore essayStore) throws SQLException {
        String sql = "update essaystore set title=? , body=? ,time =? where essayid =? ";
        qr.update (sql,essayStore.getTitle (),essayStore.getBody (),essayStore.getTime (),essayStore.getEssayid ());
    }

    @Override
    public void updateEssayHotSpot(EssayStore essayStore) throws SQLException {
        String sql="update essaystore set hotspot=? where essayid=?";
        qr.update (sql,essayStore.getHotSpot (),essayStore.getEssayid ());
    }

    @Override
    public void insertEssay(EssayStore essayStore) throws SQLException {
        String sql="insert into essaystore (essayid,uid,title,body,filetype) values(?,?,?,?,?)";
        qr.update (sql,essayStore.getEssayid (),essayStore.getUid (),essayStore.getTitle (),essayStore.getBody (),essayStore.getFiletype ());
    }

    @Override
    public void deleteEssayByEssayID(String essayid) throws SQLException {
        String sql="delete from essaystore where essayid =?";
        qr.update (sql,essayid);
    }

    @Override
    public void deleteEssayByUid(String uid) throws SQLException {
        String sql="delete from essaystore where uid =?";
        qr.update (sql,uid);
    }
}
