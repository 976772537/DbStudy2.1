package cn.drp.findimgstore.Imp;

import java.sql.SQLException;

public interface UserImgStoreDao {
    public String findImgIDByid(String id)throws SQLException;
    public void updateUserImgStore(String id,String imgid)throws SQLException;
    public void deletedUserImgStoreByid(String id)throws SQLException;
    public void insertUserImgStore(String id, String imgID)throws SQLException;
}
