package cn.drp.essaystore.dao;

public abstract class EssayStoreFactory {
    public static EssayStoreDao getEssayStoreFactory(String name){
        try {
            Class<?> clazz = Class.forName (name);
            return (EssayStoreDao) clazz.newInstance ();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace ();
        }
        return null;
    }
}
