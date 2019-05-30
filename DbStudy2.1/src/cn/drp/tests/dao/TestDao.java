package cn.drp.tests.dao;

import cn.drp.tests.domain.Test;
import java.sql.SQLException;
import java.util.List;

public interface TestDao {
    public List<Test> findTest(int start, int num, String tid) throws SQLException ;
    public List<Test> findTestBySid(String sid) throws SQLException ;

    public void insertTest(Test tb) throws SQLException ;

    public List<Test> findhotTest(int start, int num) throws SQLException ;

    public int findTestHotSportBySid(String sid) throws SQLException ;
    public List<Test> findTestByUid(String uid) throws SQLException;
    public List<Test> findTestByUidWithTime(String uid,String time) throws SQLException;
    public void updateHotSportBySid(int hotsport, String sid) throws SQLException ;

    public void deleteTestBySid(String sid)throws SQLException;
}
