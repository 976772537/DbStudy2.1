package cn.drp.user.service;


import cn.drp.user.dao.UserDao;
import cn.drp.user.dao.UserDaoFactory;
import cn.drp.user.domain.User;
import exception.NotFindUserException;
import exception.PleaseInputTureMessageException;
import exception.SavePersonInfoFiledException;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private UserDao ud = null;

    public UserService(String name) {
        this.ud = UserDaoFactory.getUserDao (name);
    }

    public User findUserByParam(String method, String param) throws NotFindUserException {
        User user = null;
        try {
            user = ud.findUserByParam (method, param);
            if (user == null) {
                throw new NotFindUserException ("用户不存在");
            }
        } catch (SQLException e) {
            e.printStackTrace ();
            throw new NotFindUserException ("用户不存在");
        }
        return user;
    }

    public List<User> findUsersByParam(String method, String param) throws NotFindUserException {
        List<User> users = null;
        try {
            users = ud.findUsersByParam (method, param);
            if (users == null) {
                throw new NotFindUserException ("用户不存在");
            }
        } catch (SQLException e) {
            e.printStackTrace ();
            throw new NotFindUserException ("用户不存在");
        }
        return users;
    }

    public User findUser(User user) throws NotFindUserException {
        User user1 = null;
        try {
            user1 = ud.findUser (user);
            if (user1 == null) {
                throw new NotFindUserException ("用户不存在");
            }
        } catch (SQLException e) {
            e.printStackTrace ();
            throw new NotFindUserException ("用户不存在");
        }
        return user1;
    }

    public void insertUser(User user) throws PleaseInputTureMessageException {
        try {
            ud.insertUser (user);
        } catch (SQLException e) {
            e.printStackTrace ();
            throw new PleaseInputTureMessageException ("请输入合法的信息");
        }
    }

    public void updateUserbyParam(String method, String param, String uid) throws SavePersonInfoFiledException {
        try {
            ud.updateUserParam (method, param, uid);
        } catch (SQLException e) {
            e.printStackTrace ();
            throw new SavePersonInfoFiledException ("修改用户信息失败");
        }
    }

    public void updateUserInfo(User user) throws SavePersonInfoFiledException {
        try {
            ud.updateUserInfo (user);
        } catch (SQLException e) {
            e.printStackTrace ();
            throw new SavePersonInfoFiledException ("修改用户信息失败");
        }
    }
}
