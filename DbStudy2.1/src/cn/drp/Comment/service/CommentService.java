package cn.drp.Comment.service;

import cn.drp.Comment.dao.CommentDao;
import cn.drp.Comment.dao.CommentDaoFactory;
import cn.drp.Comment.domain.Comment;
import exception.CommentNotFoundException;
import exception.InsertCommentException;

import java.sql.SQLException;
import java.util.List;

public class CommentService {
    private CommentDao cd = null;

    public CommentService(String name) {
        this.cd = CommentDaoFactory.getCommentDao (name);
    }

    public List<Comment> findCommentByUid(String uid) throws CommentNotFoundException {
        try {
            return cd.findCommentByUid (uid);
        } catch (SQLException e) {
            e.printStackTrace ();
            throw new CommentNotFoundException ("该用户暂时没有评论");
        }
    }

    public List<Comment> findCommentByEssayid(String essayid) throws CommentNotFoundException {
        try {
            return cd.findCommentByEssayid (essayid);
        } catch (SQLException e) {
            e.printStackTrace ();
            throw new CommentNotFoundException ("快来抢沙发吧！！");
        }
    }

    public List<Comment> findCommentByCid(String cid) throws CommentNotFoundException {
        try {
            return cd.findCommentByCid (cid);
        } catch (SQLException e) {
            e.printStackTrace ();
            throw new CommentNotFoundException ("还没有这条评论");
        }
    }

    public void insertNewComment(Comment comment) throws InsertCommentException {
        try {
            cd.insertNewComment (comment);
        } catch (SQLException e) {
            e.printStackTrace ();
            throw new InsertCommentException ("发布评论失败");
        }
    }

    public void updateCommentContent(Comment comment) throws InsertCommentException {
        try {
            cd.updateCommentContent (comment);
        } catch (SQLException e) {
            e.printStackTrace ();
            throw new InsertCommentException ("更新评论内容失败");
        }
    }

    public void deleteCommentByEssayid(String essayid) throws CommentNotFoundException {
        try {
            cd.deleteCommentByEssayid (essayid);
        } catch (SQLException e) {
            e.printStackTrace ();
            throw new CommentNotFoundException ("没找到想要删除的评论");
        }
    }
}
