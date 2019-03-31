package cn.drp.annunciate.dao;

import cn.drp.annunciate.domain.Annunciate;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.BeanUtils;
import utils.DSUtils;
import utils.SqlUtils;
import utils.page.domain.Page;

import java.sql.SQLException;
import java.util.List;

public class AnnunciateDao {
    private QueryRunner qr= DSUtils.getQueryRunner ();
    public void addAnnunciate(Annunciate ann)throws SQLException {
        String sql= SqlUtils.getInsertSql (ann,"annunciate");
        qr.update (sql,BeanUtils.getParam (ann));
    }

    public void deleteAnnunciateByTitle(String title)throws SQLException{
        String sql="delete from annunciate where title = ?";
        qr.update (sql,title);
    }

    public List<Annunciate> selectAnnunciateByTitle(String title)throws SQLException{
        String sql="select * from annunciate where title like '%"+title+"%'";
        return qr.query (sql,new BeanListHandler<> (Annunciate.class));
    }

    private List<Annunciate> getAnnunciatePage(Page page,String sql)throws  SQLException{
        int currentPage = page.getCurrentPage ();
        int pageSize = page.getPageSize ();
        return qr.query (sql,new BeanListHandler<> (Annunciate.class),(currentPage-1)*pageSize,pageSize);
    }
    public List<Annunciate> selectAllAnnunciateOrderByTime(Page page)throws SQLException{
        String sql="select * from annunciate order by time desc limit ?,? ";
        return getAnnunciatePage (page,sql);
    }

    public int getAllAnnunciateCount()throws SQLException{
        String sql="select count(*) from annunciate";
        Number num = qr.query (sql, new ScalarHandler<> ());
        return  num.intValue ();
    }

    public List<Annunciate> selectAnnunciateByTime(String time)throws SQLException{
        String sql="select * from annunciate where time like '%"+time+"%'";
        return  qr.query (sql,new BeanListHandler<> (Annunciate.class));
    }


    public Annunciate selectAnnciateByAnnID(String annId) throws SQLException {
        String sql="select * from annunciate where ann_id=?";
        return  qr.query (sql,new BeanHandler<> (Annunciate.class),annId);
    }
}
