package cn.drp.imgstore.dao;

public abstract class ImgDaoFactory {
    public static ImgDao getImgDao(String name){
        try {
            Class<?> clazz = Class.forName (name);
          return (ImgDao) clazz.newInstance ();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace ();
        }
        return null;
    }
}
