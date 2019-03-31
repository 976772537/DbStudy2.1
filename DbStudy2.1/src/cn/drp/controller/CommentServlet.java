package cn.drp.controller;

import cn.drp.Comment.domain.Comment;
import cn.drp.Comment.service.CommentService;
import cn.drp.user.domain.User;
import exception.InsertCommentException;
import utils.BaseServlet;
import utils.Drputils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CommentServlet")
public class CommentServlet extends BaseServlet {
    private CommentService cs=new CommentService (Drputils.getProperties ().getProperty ("Comment"));
    public void getContent(HttpServletRequest req, HttpServletResponse resp){
        String essayid = req.getParameter ("essayid");
        String content = req.getParameter ("content");
        User user=(User)req.getSession ().getAttribute ("user");
        Comment comment = new Comment ();
        comment.setCid (Drputils.getUUID ());
        comment.setContent (content);
        comment.setUid (user.getUid ());
        comment.setHotspot (0);
        comment.setEssayid (essayid);
        try {
            cs.insertNewComment (comment);
        } catch (InsertCommentException e) {
            e.printStackTrace ();
            req.setAttribute ("comment_error", e.getMessage ());
        }
        try {
            req.getRequestDispatcher ("QueryDatumMainServlet?method=getEssayStore").forward (req,resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace ();
        }
    }
}
