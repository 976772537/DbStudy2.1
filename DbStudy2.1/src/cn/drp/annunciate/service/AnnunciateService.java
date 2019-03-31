package cn.drp.annunciate.service;

import cn.drp.annunciate.dao.AnnunciateDao;
import cn.drp.annunciate.domain.Annunciate;
import com.sun.org.apache.bcel.internal.generic.Select;
import exception.AddAnnunciateFaildException;
import exception.DeleteAnnunciateFaildException;
import exception.selectAnnunciateException;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.page.domain.Page;

import java.sql.SQLException;
import java.util.List;

public class AnnunciateService {
    private AnnunciateDao ad=new AnnunciateDao ();
    public void addAnnunciate(Annunciate ann) throws AddAnnunciateFaildException {
        try {
            ad.addAnnunciate (ann);
        } catch (SQLException e) {
            e.printStackTrace ();
            throw new AddAnnunciateFaildException ("新增公告失败");
        }
    }
    public Annunciate selectAnnciateByAnnID(String annId)throws selectAnnunciateException {
        try {
            return ad.selectAnnciateByAnnID(annId);
        } catch (SQLException e) {
            e.printStackTrace ();
            throw new selectAnnunciateException ("搜索失败");
        }
    }
    public void deleteAnnunciateByTitle(String title) throws DeleteAnnunciateFaildException {
        try {
            ad.deleteAnnunciateByTitle (title);
        } catch (SQLException e) {
            e.printStackTrace ();
            throw  new DeleteAnnunciateFaildException ("删除公告失败");
        }
    }

    public List<Annunciate> selectAnnunciateByTitle(String title) throws selectAnnunciateException {
        try {
            return ad.selectAnnunciateByTitle (title);
        } catch (SQLException e) {
            e.printStackTrace ();
            throw  new selectAnnunciateException ("搜索失败");
        }
    }

    public Page selectAllAnnunciateOrderByTime(Page page) throws selectAnnunciateException {
        page.setPageSize (10);
        try {
            List<Annunciate> annunciates = ad.selectAllAnnunciateOrderByTime (page);
            page.setList (annunciates);
            return page;
        } catch (SQLException e) {
            e.printStackTrace ();
            throw  new selectAnnunciateException ("搜索失败");
        }
    }

    public int getAllAnnunciateCount() throws selectAnnunciateException {
        try {
            return ad.getAllAnnunciateCount ();
        } catch (SQLException e) {
            e.printStackTrace ();
            throw new selectAnnunciateException ("搜索所有数据失败");
        }
    }

    public List<Annunciate> selectAnnunciateByTime(String time) throws selectAnnunciateException {
        try {
            return ad.selectAnnunciateByTime (time);
        } catch (SQLException e) {
            e.printStackTrace ();
            throw new selectAnnunciateException ("搜索数据失败");
        }
    }
}
