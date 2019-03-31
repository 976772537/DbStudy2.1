package cn.drp.user.dao;

public abstract class UserDaoFactory {
    public static UserDao getUserDao(String name){
        try {
            Class<?> clazz = Class.forName (name);
            return (UserDao) clazz.newInstance ();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace ();
        }
        return null;
    }
}
