package cn.drp.user.dao;

import cn.drp.user.domain.User;

import java.sql.SQLException;

public class AdminDaoImp extends TeacherDaoImp {
    @Override
    public void insertUser(User user) throws SQLException {
        String sql = "insert into  users (username,password,uid,email,name,sex,telephone,age,describle,type,active) " +
                "values(?,?,?,?,?,?,?,?,?,?,?)";
        qr.update (sql, user.getUsername (), user.getPassword (), user.getUid (), user.getEmail (), user.getName (),
                user.getSex (), user.getTelephone (), user.getAge (), user.getDescrible (),"admin",1);
    }
}
