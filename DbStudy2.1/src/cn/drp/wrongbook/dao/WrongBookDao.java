package cn.drp.wrongbook.dao;

import cn.drp.wrongbook.domain.WrongBook;

import java.sql.SQLException;
import java.util.List;

public interface WrongBookDao {

    public List<WrongBook> findWrongBookByUidWithTime(String uid, String time) throws SQLException;

    public List<WrongBook> findWrongBookByUid(String uid) throws SQLException;

    public void addUidAndSid(String uid, String sid, String wid) throws SQLException;
}
