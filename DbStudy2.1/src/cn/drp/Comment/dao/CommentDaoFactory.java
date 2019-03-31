package cn.drp.Comment.dao;

import cn.drp.Comment.domain.Comment;
import java.util.List;

public abstract class CommentDaoFactory {
    public static CommentDao getCommentDao(String name){
        try {
            Class<?> clazz = Class.forName (name);
            return (CommentDao) clazz.newInstance ();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace ();
        }
        return null;
    }
}
