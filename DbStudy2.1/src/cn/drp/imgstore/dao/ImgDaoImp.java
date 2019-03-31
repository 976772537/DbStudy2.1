package cn.drp.imgstore.dao;

import cn.drp.imgstore.domain.ImgStore;
import utils.DSUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

class ImgDaoImp implements ImgDao {
    private QueryRunner qr = DSUtils.getQueryRunner ();

    @Override
    public List<ImgStore> findAllDefaultImgStore() throws SQLException {
        String sql = "select * from imgstore where imgpath like '%people%'";
        return qr.query (sql, new BeanListHandler<> (ImgStore.class));
    }

    @Override
    public ImgStore findImgStoreByImgStorePath(String path) throws SQLException {
        String sql = "select * from imgstore where imgpath = ?";
        return qr.query (sql, new BeanHandler<> (ImgStore.class), path);
    }

    @Override
    public String findImgStoreByImgID(String imgid) throws SQLException {
        String sql = "select * from imgstore where imgid = ?";
        return qr.query (sql, new BeanHandler<> (ImgStore.class), imgid).getImgPath ();
    }

    @Override
    public ImgStore findRandomImgStore() throws SQLException {
        String sql = "select * from imgstore  where imgpath='people_1.jpg'";
        return qr.query (sql, new BeanHandler<> (ImgStore.class));
    }

    @Override
    public void insertNewImgStore(ImgStore imgStore,int state) throws SQLException {
        String sql = "insert into imgstore (imgpath,imgid,state) values(?,?,?)";
        qr.update (sql, imgStore.getImgPath (), imgStore.getImgID (),state);
    }

    @Override
    public void deleteImgStore(String imgPath, int state) throws SQLException {
        String sql = "delete from imgstore where imgpath like " + "'%" + imgPath + "%'" + "and state=0";
        qr.update (sql);
    }

    @Override
    public void updateImgStateByImgPath(String imgPath, int state) throws SQLException {
        String sql="update imgstore set state = ? where imgpath like " + "'%" + imgPath + "%'";
        qr.update (sql,state);
    }
}
