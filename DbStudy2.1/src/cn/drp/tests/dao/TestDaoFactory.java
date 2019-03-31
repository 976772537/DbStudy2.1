package cn.drp.tests.dao;

public abstract class TestDaoFactory {
    public static TestDao getTestDao(String name){
        try {
            Class<?> clazz = Class.forName (name);
            return (TestDao) clazz.newInstance ();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace ();
        }
        return null;
    }
}
