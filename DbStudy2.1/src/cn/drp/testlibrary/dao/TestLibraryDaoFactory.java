package cn.drp.testlibrary.dao;

public abstract class TestLibraryDaoFactory {
    public static TestLibraryDao getTestLibrary(String name){
        try {
            Class<?> clazz = Class.forName (name);
           return (TestLibraryDao) clazz.newInstance ();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace ();
        }
        return null;
    }
}
