package cn.drp.user.dao;

import cn.drp.user.domain.User;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.DSUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;
import java.util.List;

public class StudentDaoImp implements UserDao{
    protected QueryRunner qr = DSUtils.getQueryRunner ();

    public StudentDaoImp() {
    }

    public User findUser(User user) throws SQLException {
        String sql = "Select * from users where username=? and password=?";
        return qr.query (sql, new BeanHandler<User> (User.class), user.getUsername (), user.getPassword ());
    }

    public User findUserByParam(String method, String param) throws SQLException {
        String sql = "select * from users where " + method + "= ?";
        return qr.query (sql, new BeanHandler<User> (User.class), param);
    }

    @Override
    public List<User> findUsersByParam(String method, String param) throws SQLException {
        String sql = "select * from users where " + method + "= ?";
        return qr.query (sql, new BeanListHandler<>(User.class), param);
    }


    public void insertUser(User user) throws SQLException {
        String sql = "insert into  users (username,password,uid,email,name,sex,telephone,age,describle,type,active) " +
                "values(?,?,?,?,?,?,?,?,?,?,?)";
        qr.update (sql, user.getUsername (), user.getPassword (), user.getUid (), user.getEmail (), user.getName (),
                user.getSex (), user.getTelephone (), user.getAge (), user.getDescrible (),"student",1);
    }

    public void updateUserInfo(User user) throws SQLException {
        String sql = "update users set name=? ,sex=? ,telephone=? ,age=? ,describle=? where uid=?";
        qr.update (sql, user.getName (), user.getSex (), user.getTelephone ()
                , user.getAge (), user.getDescrible (), user.getUid ());
    }

    public void updateUserParam(String method, String param, String uid) throws SQLException {
        String sql = "update users set " + method + "=? where uid=?";
        qr.update (sql, param, uid);
    }


}
