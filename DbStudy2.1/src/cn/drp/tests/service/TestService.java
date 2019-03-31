package cn.drp.tests.service;

import cn.drp.tests.domain.Test;
import exception.FindTestFailedException;
import cn.drp.tests.dao.TestDao;
import cn.drp.tests.dao.TestDaoFactory;
import exception.insertTestFailedException;

import java.sql.SQLException;
import java.util.List;

public class TestService {
    private TestDao test = null;

    public TestService(String name) {
        this.test = TestDaoFactory.getTestDao (name);
    }

    public List<Test> findTest(int start, int num, String tid) throws FindTestFailedException {
        try {
            return test.findTest (start, num, tid);
        } catch (SQLException e) {
            throw new FindTestFailedException ("没有题了,Σσ(・Д・；)我我我什么都没做!!!");
        }
    }

    public List<Test> findTestBySid(String sid) throws FindTestFailedException {
        try {
            return test.findTestBySid (sid);
        } catch (SQLException e) {
            throw new FindTestFailedException ("没有题了,Σσ(・Д・；)我我我什么都没做!!!");
        }
    }

    public List<Test> findHotSpot(int start, int num) throws FindTestFailedException {
        try {
            return test.findhotTest (start, num);
        } catch (SQLException e) {
            throw new FindTestFailedException ("没有题了,Σσ(・Д・；)我我我什么都没做!!!");
        }
    }
    public List<Test> findTestByUid(String uid) throws FindTestFailedException {
        try {
            return test.findTestByUid (uid);
        } catch (SQLException e) {
            throw new FindTestFailedException ("没有题了,Σσ(・Д・；)我我我什么都没做!!!");
        }
    }
    public List<Test> findTestByUidWithTime(String uid,String time) throws FindTestFailedException {
        try {
            return test.findTestByUidWithTime (uid,time);
        } catch (SQLException e) {
            throw new FindTestFailedException ("没有题了,Σσ(・Д・；)我我我什么都没做!!!");
        }
    }
    public void updateTestHotSpot(String sid) throws FindTestFailedException {
        try {
            int hotspot = test.findTestHotSportBySid (sid);
            test.updateHotSportBySid (++hotspot, sid);
        } catch (SQLException e) {
            e.printStackTrace ();
            throw new FindTestFailedException ("添加失败");
        }
    }

    public void insertTest(Test t) throws insertTestFailedException{
        try {
            test.insertTest (t);
        } catch (SQLException e) {
            e.printStackTrace ();
            throw new insertTestFailedException ();
        }
    }
}
