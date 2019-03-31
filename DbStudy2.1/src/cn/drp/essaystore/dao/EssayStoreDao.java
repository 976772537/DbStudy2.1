package cn.drp.essaystore.dao;

import cn.drp.essaystore.domain.EssayStore;
import utils.page.domain.Page;

import java.sql.SQLException;
import java.util.List;

public interface EssayStoreDao {
    public List<EssayStore> findEssayByHotSpot(int start, int num) throws SQLException;

    public EssayStore findEssayByEssayID(String essayid) throws SQLException;

    public List<EssayStore> findEssayStoreByUidWithPage(String uid, Page page) throws SQLException;

    public List<EssayStore> findEssayStoreByUid(String uid) throws SQLException;

    public List<EssayStore> findEssayStoreByTitle(String title, Page page) throws SQLException;

    public int findAllEssayStoreByTitle(String title) throws SQLException;

    public List<EssayStore> findEssayStoreByBody(String field, Page page) throws SQLException;

    public List<EssayStore> findEssayStoreByTime(String time, Page page) throws SQLException;

    public void updateEssay(EssayStore essayStore) throws SQLException;

    public void updateEssayHotSpot(EssayStore essayStore) throws SQLException;

    public void insertEssay(EssayStore essayStore) throws SQLException;

    public void deleteEssayByEssayID(String essayid) throws SQLException;

    public void deleteEssayByUid(String uid) throws SQLException;
}
