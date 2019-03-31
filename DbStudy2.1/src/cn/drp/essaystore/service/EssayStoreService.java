package cn.drp.essaystore.service;

import cn.drp.essaystore.dao.EssayStoreDao;
import cn.drp.essaystore.dao.EssayStoreFactory;
import cn.drp.essaystore.domain.EssayStore;
import exception.EssayInsertFaildException;
import exception.EssayNotFoundException;
import utils.page.domain.Page;

import java.sql.SQLException;
import java.util.List;

public class EssayStoreService {
    private EssayStoreDao es = null;

    public EssayStoreService(String name) {
        this.es = EssayStoreFactory.getEssayStoreFactory (name);
    }
    public List<EssayStore> findEssayHotSpot(int num) throws EssayNotFoundException{
        try {
            return es.findEssayByHotSpot (0,num);
        } catch (SQLException e) {
            e.printStackTrace ();
            throw new EssayNotFoundException ("还没有文章");
        }
    }
    public EssayStore findEssayByEssayID(String essayid) throws EssayNotFoundException {
        try {
            return es.findEssayByEssayID (essayid);
        } catch (SQLException e) {
            e.printStackTrace ();
            throw new EssayNotFoundException ("还没有这篇文章");
        }
    }

    public Page findEssayStoreByUidWithPage(String uid, Page page) throws EssayNotFoundException {
        page.setPageSize (10);
        try {
            List<EssayStore> list = es.findEssayStoreByUidWithPage (uid, page);
            page.setList (list);
            return page;
        } catch (SQLException e) {
            e.printStackTrace ();
            throw new EssayNotFoundException ("该用户还没有文章可以被查看(<ゝω・)☆");
        }
    }

    public List<EssayStore> findEssayStoreByUid(String uid) throws EssayNotFoundException {

        try {
            return es.findEssayStoreByUid (uid);
        } catch (SQLException e) {
            e.printStackTrace ();
            throw new EssayNotFoundException ("该用户还没有文章可以被查看(<ゝω・)☆");
        }
    }

    public Page findEssayStoreByTitle(String title, Page page) throws EssayNotFoundException {
        page.setPageSize (10);
        try {
            List<EssayStore> list = es.findEssayStoreByTitle (title,page);
            page.setList (list);
            return page;
        } catch (SQLException e) {
            e.printStackTrace ();
            throw new EssayNotFoundException ("还没有该文章的标题!!!∑(ﾟДﾟノ)ノ");
        }
    }

    public int findAllEssayStroeByTitle(String title) throws EssayNotFoundException {
        try {
            return es.findAllEssayStoreByTitle (title);
        } catch (SQLException e) {
            e.printStackTrace ();
            throw new EssayNotFoundException ("还没有该文章的标题!!!∑(ﾟДﾟノ)ノ");
        }
    }

    public Page findEssayStoreByBody(String field, Page page) throws EssayNotFoundException {
        page.setPageSize (10);
        try {
            List<EssayStore> list = es.findEssayStoreByBody (field,page);
            page.setList (list);
            return page;
        } catch (SQLException e) {
            e.printStackTrace ();
            throw new EssayNotFoundException ("文章内容为:"+field+" 的文章还没出现呢！!!!∑(ﾟДﾟノ)ノ");
        }
    }

    public Page findEssayStoreByTime(String time, Page page) throws EssayNotFoundException {
        page.setPageSize (10);
        try {
            List<EssayStore> list = es.findEssayStoreByTime (time,page);
            page.setList (list);
            return page;
        } catch (SQLException e) {
            e.printStackTrace ();
            throw new EssayNotFoundException (time+"没有任何文章哦!!!∑(ﾟДﾟノ)ノ");
        }
    }
    public void updateEssayHotSpot(EssayStore essayStore) throws EssayNotFoundException {
        try {
            es.updateEssayHotSpot (essayStore);
        } catch (SQLException e) {
            e.printStackTrace ();
            throw new EssayNotFoundException ("点赞失败");
        }
    }
    public void updateEssay(EssayStore essayStore) throws EssayNotFoundException {
        try{
            es.updateEssay (essayStore);
        } catch (SQLException e) {
            e.printStackTrace ();
            throw new EssayNotFoundException ("要修改的文章没找到");
        }
    }

    public void insertEssay(EssayStore essayStore) throws EssayInsertFaildException {
        try{
            es.insertEssay (essayStore);
        } catch (SQLException e) {
            e.printStackTrace ();
            throw new EssayInsertFaildException ("添加文章出现了问题请联系管理员");
        }
    }

    public void deleteEssayByEssayID(String essayid) throws EssayNotFoundException {
        try {
            es.deleteEssayByEssayID (essayid);
        } catch (SQLException e) {
            e.printStackTrace ();
            throw new EssayNotFoundException ("没找到要删除的文章");
        }
    }

    public void deleteEssayByUid(String uid) throws EssayNotFoundException {
        try {
            es.deleteEssayByUid (uid);
        } catch (SQLException e) {
            e.printStackTrace ();
            throw new EssayNotFoundException ("没找到要删除的文章");
        }
    }
}
