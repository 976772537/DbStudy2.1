package cn.drp.user.dao;

import cn.drp.user.domain.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    public User findUser(User user) throws SQLException;

    public User findUserByParam(String method, String param) throws SQLException;

    public List<User> findUsersByParam(String method, String param) throws SQLException;


    public void insertUser(User user) throws SQLException;

    public void updateUserInfo(User user) throws SQLException;

    public void updateUserParam(String method, String param, String uid) throws SQLException;
}
