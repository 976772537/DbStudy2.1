package cn.drp.Comment.dao;

import cn.drp.Comment.domain.Comment;

import java.sql.SQLException;
import java.util.List;

public interface CommentDao {
    public List<Comment> findCommentByUid(String uid) throws SQLException;

    public List<Comment> findCommentByEssayid(String essayid) throws SQLException;

    public List<Comment> findCommentByCid(String cid) throws SQLException;

    public void insertNewComment(Comment comment)throws SQLException;

    public void updateCommentContent(Comment comment)throws SQLException;

    public void deleteCommentByEssayid(String essayid)throws SQLException;
}
