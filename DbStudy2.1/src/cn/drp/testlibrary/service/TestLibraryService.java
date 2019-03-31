package cn.drp.testlibrary.service;

import cn.drp.testlibrary.dao.TestLibraryDao;
import cn.drp.testlibrary.dao.TestLibraryDaoFactory;
import utils.page.domain.Page;
import cn.drp.testlibrary.domain.TestLibrary;
import exception.NotFoundTestException;

import java.sql.SQLException;
import java.util.List;

public class TestLibraryService {
    private TestLibraryDao td = null;

    public TestLibraryService(String  name) {
        this.td = TestLibraryDaoFactory.getTestLibrary (name);
    }

    public List<TestLibrary> findAllTestLibrary(String type) throws NotFoundTestException {
        try {
            return td.findAllTestLibrary (type);
        } catch (SQLException e) {
            e.printStackTrace ();
            throw new NotFoundTestException ("题库里还没有题");
        }
    }

    public void setDataSize(String type,Page page) throws NotFoundTestException {
        try {
            int num = td.getDataNum (type).intValue ();
            page.setDataSize (num);
        } catch (SQLException e) {
            e.printStackTrace ();
            throw new NotFoundTestException ("没有题了");
        }
    }
    public TestLibrary findTestLibraryByt_name(String t_name) throws NotFoundTestException{
        try {
            return td.findTestLibraryByName (t_name);
        } catch (SQLException e) {
            e.printStackTrace ();
            throw  new NotFoundTestException ("没题了");
        }
    }
    public Page findCurrentPageList(String type,int cp, Page page) throws NotFoundTestException {
        try {
            page.setCurrentPage (cp);
            if (cp > page.getTotalPage () || cp <= 0) {
                throw new NotFoundTestException ("没有题了");
            }
            return td.findTestLibraryByCurrentPage (type,page);
        } catch (SQLException e) {
            e.printStackTrace ();
            throw new NotFoundTestException ("没有题了");
        }
    }
    public List<TestLibrary> findTestLibraryByType(String type) throws NotFoundTestException{
        try {
            return td.findTestLibraryByType (type);
        } catch (SQLException e) {
            e.printStackTrace ();
            throw  new NotFoundTestException ("没题了");
        }
    }
}
