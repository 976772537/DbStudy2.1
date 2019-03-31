package cn.drp.testlibrary.dao;

import org.apache.commons.dbutils.handlers.BeanHandler;
import utils.page.domain.Page;
import cn.drp.testlibrary.domain.TestLibrary;
import utils.BeanUtils;
import utils.DSUtils;
import utils.SqlUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TestLibraryDaoImp implements TestLibraryDao{
    private QueryRunner qr = new QueryRunner ();
    private Connection con = DSUtils.getConnection ();

    public TestLibraryDaoImp() {
    }

    public Number getDataNum(String type) throws SQLException {
        String sql = "select count(*) from testlibrary where type=?";
        return qr.query (con, sql, new ScalarHandler<Number> (),type);
    }

    public Page findTestLibraryByCurrentPage(String type,Page page) throws SQLException {
        int cp = page.getCurrentPage ();
        int ps = page.getPageSize ();
        String sql = "select * from testlibrary where type=? order by t_name limit ?,?";
        List<TestLibrary> qr = this.qr.query (con, sql, new BeanListHandler<TestLibrary> (TestLibrary.class),type,(cp - 1) * ps,ps);
        page.setList (qr);
        return page;
    }

    public List<TestLibrary> findAllTestLibrary(String type) throws SQLException {
        String sql = "select * from  testlibrary where type=?";
        return qr.query (con, sql, new BeanListHandler<> (TestLibrary.class),type);
    }

    public void insertTestLibrary(TestLibrary tb) throws SQLException {
        String sql = SqlUtils.getInsertSql (tb, "testlibrary");
        qr.update (con, sql, BeanUtils.getParam (tb));
    }

    @Override
    public List<TestLibrary> findTestLibraryByType(String type) throws SQLException {
        String sql="select * from testlibrary where type=?";
        return qr.query (con,sql, new BeanListHandler<> (TestLibrary.class), type);
    }

    @Override
    public TestLibrary findTestLibraryByName(String name) throws SQLException {
        String sql="select * from testlibrary where t_name=?";
        return qr.query (con,sql, new BeanHandler<> (TestLibrary.class), name);
    }
}
