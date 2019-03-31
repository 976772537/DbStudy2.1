package cn.drp.wrongbook.dao;

public abstract class WrongBookDaoFactory {
    public static WrongBookDao getWrongBookDao(String name){
        try {
            Class<?> clazz = Class.forName (name);
           return (WrongBookDao) clazz.newInstance ();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace ();
        }
        return null;
    }
}
