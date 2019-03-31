package cn.drp.testlibrary.dao;

import utils.page.domain.Page;
import cn.drp.testlibrary.domain.TestLibrary;

import java.sql.SQLException;
import java.util.List;

public interface TestLibraryDao {
    public Number getDataNum(String type) throws SQLException ;

    public Page findTestLibraryByCurrentPage(String type,Page page) throws SQLException ;

    public List<TestLibrary> findAllTestLibrary(String type) throws SQLException ;

    public void insertTestLibrary(TestLibrary tb) throws SQLException ;

    public List<TestLibrary> findTestLibraryByType(String type)throws SQLException;

    public TestLibrary findTestLibraryByName(String name)throws  SQLException;
}
