package cn.drp.imgstore.dao;

import cn.drp.imgstore.domain.ImgStore;

import java.sql.SQLException;
import java.util.List;

public interface ImgDao {

    public List<ImgStore> findAllDefaultImgStore() throws SQLException ;


    public ImgStore findImgStoreByImgStorePath(String path) throws SQLException ;

    public String findImgStoreByImgID(String imgid)throws SQLException ;

    public ImgStore findRandomImgStore() throws SQLException ;

    public void insertNewImgStore(ImgStore imgStore,int state) throws SQLException ;

    public void deleteImgStore(String imgPath, int state) throws SQLException;

    public void updateImgStateByImgPath(String imgPath, int state)throws SQLException;
}
