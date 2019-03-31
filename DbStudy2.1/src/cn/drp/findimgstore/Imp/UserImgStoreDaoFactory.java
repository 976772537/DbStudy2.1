package cn.drp.findimgstore.Imp;

public abstract class UserImgStoreDaoFactory {
    public static UserImgStoreDao getUserImgStoreDao(String name){
        try{
            Class<?> clazz=Class.forName (name);
            return (UserImgStoreDao) clazz.newInstance ();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace ();
        }
        return null;
    }
}
