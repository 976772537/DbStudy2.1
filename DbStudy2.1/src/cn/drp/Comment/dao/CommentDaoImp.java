package cn.drp.Comment.dao;

import cn.drp.Comment.domain.Comment;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.DSUtils;

import java.sql.SQLException;
import java.util.List;

public class CommentDaoImp implements CommentDao {
    public QueryRunner qr=DSUtils.getQueryRunner();
    @Override
    public List<Comment> findCommentByUid(String uid) throws SQLException {
        String sql = "select * from comment where uid=?";
        return  qr.query (sql,new BeanListHandler<> (Comment.class),uid);
    }

    @Override
    public List<Comment> findCommentByEssayid(String essayid) throws SQLException {
        String sql="select * from comment where essayid=? order by time";
        return qr.query (sql,new BeanListHandler<> (Comment.class),essayid);
    }

    @Override
    public List<Comment> findCommentByCid(String cid) throws SQLException {
        String sql="select * from comment where cid=?";
        return qr.query (sql,new BeanListHandler<> (Comment.class),cid);
    }

    @Override
    public void insertNewComment(Comment comment) throws SQLException {
        String sql="insert into comment (cid,essayid,uid,content) values(?,?,?,?)";
        qr.update (sql,comment.getCid (),comment.getEssayid (),comment.getUid (),comment.getContent ());
    }


    @Override
    public void updateCommentContent(Comment comment) throws SQLException {
        String sql="update commment set content=? where essayid=?";
        qr.update (sql,comment.getContent (),comment.getEssayid ());
    }

    @Override
    public void deleteCommentByEssayid(String essayid) throws SQLException {
        String sql="delete from comment where essayid=?";
        qr.update (sql,essayid);
    }
}
