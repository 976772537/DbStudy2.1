package cn.drp.wrongbook.service;

import cn.drp.wrongbook.dao.WrongBookDao;
import cn.drp.wrongbook.dao.WrongBookDaoFactory;
import cn.drp.wrongbook.domain.WrongBook;
import exception.InsertWrongBookFailedException;
import exception.NotFoundTestException;

import java.sql.SQLException;
import java.util.List;

public class WrongBookService {
    private WrongBookDao wd = null;

    public WrongBookService(String name) {
        this.wd = WrongBookDaoFactory.getWrongBookDao (name);
    }

    public List<WrongBook> findWrongBookByTime(String uid, String time) throws NotFoundTestException {
        try {
            return wd.findWrongBookByUidWithTime (uid, time);
        } catch (SQLException e) {
            e.printStackTrace ();
            throw new NotFoundTestException ("还没有错题");
        }
    }

    public List<WrongBook> findWrongBook(String uid) throws NotFoundTestException {
        try {
            return wd.findWrongBookByUid (uid);
        } catch (SQLException e) {
            e.printStackTrace ();
            throw new NotFoundTestException ("还没有错题");
        }
    }

    public void addWrongBookByUid(String sid, String uid) throws InsertWrongBookFailedException {
        try {
            String wid = uid.substring (0, 10) + sid.substring (0, 10);
            wd.addUidAndSid (uid, sid, wid);
        } catch (SQLException e) {
            e.printStackTrace ();
            throw new InsertWrongBookFailedException ("已在错题本中");
        }
    }

    public String getTime(String year, String month, String day) {
        return year + "-" + month + "-" + day;
    }
}
