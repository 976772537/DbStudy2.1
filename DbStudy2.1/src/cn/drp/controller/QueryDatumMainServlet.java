package cn.drp.controller;

import cn.drp.Comment.domain.Comment;
import cn.drp.Comment.service.CommentService;
import cn.drp.essaystore.domain.EssayStore;
import cn.drp.essaystore.service.EssayStoreService;
import cn.drp.findimgstore.eassy_imgstore.service.EssayImgStoreService;
import cn.drp.findimgstore.user_imgstore.service.UserImgService;
import cn.drp.imgstore.service.ImgService;
import cn.drp.user.domain.User;
import cn.drp.user.service.UserService;
import exception.CommentNotFoundException;
import exception.EssayNotFoundException;
import exception.ImgNotFoundException;
import exception.NotFindUserException;
import utils.BaseServlet;
import utils.Drputils;
import utils.page.domain.Page;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet("/QueryDatumMainServlet")
public class QueryDatumMainServlet extends BaseServlet {
    private final EssayStoreService ess = new EssayStoreService (Drputils.getProperties ().getProperty ("EssayStore"));
    private final ImgService ims = new ImgService (Drputils.getProperties ().getProperty ("ImgStore"));
    private final EssayImgStoreService eiss = new EssayImgStoreService (Drputils.getProperties ().getProperty ("EssayImgStore"), ims);
    private final UserImgService uis = new UserImgService (Drputils.getProperties ().getProperty ("UserImgStore"), ims);
    private final UserService us = new UserService (Drputils.getProperties ().getProperty ("TeacherDao"));
    private final CommentService cs = new CommentService (Drputils.getProperties ().getProperty ("Comment"));

    public String dispathcerToMain(HttpServletRequest req, HttpServletResponse resp) {
        getNewDevelopment (req, resp);
        getUserRankList (req, resp);
        return "f:/main_jsp/query_datum.jsp";
    }

    private void getUserRankList(HttpServletRequest req, HttpServletResponse resp) {
        List<User> users = null;
        try {
            users = us.findUsersByParam ("type", "teacher");
        } catch (NotFindUserException e) {
            e.printStackTrace ();
            req.setAttribute ("user_rank_list", users);
            return;
        }
        TreeMap<User, Integer> usermap = new TreeMap<User, Integer> (new Comparator<User> () {
            @Override
            public int compare(User o1, User o2) {
                return o1.hashCode () - o2.hashCode ();
            }
        });
        for (User user : users) {
            try {
                List<EssayStore> essay = ess.findEssayStoreByUid (user.getUid ());
                int hotspot = 0;
                for (EssayStore es : essay) {
                    hotspot += es.getHotSpot ();
                }
                usermap.put (user, new Integer (hotspot));
            } catch (EssayNotFoundException e) {
                e.printStackTrace ();
            }
        }
        ArrayList<Map.Entry<User, Integer>> list = new ArrayList<> (usermap.entrySet ());
        Collections.sort (list, new Comparator<Map.Entry<User, Integer>> () {
            @Override
            public int compare(Map.Entry<User, Integer> o1, Map.Entry<User, Integer> o2) {
                return o2.getValue () - o1.getValue ();
            }
        });
        ArrayList<User> usersList = new ArrayList<> ();
        int size = list.size () < 5 ? list.size () : 5;
        for (int i = 0; i < size; i++) {
            usersList.add (list.get (i).getKey ());
        }
        ArrayList<String> usersHeadList = new ArrayList<> ();
        for (int i = 0; i < usersList.size (); i++) {
            try {
                usersHeadList.add (uis.findUserhead (usersList.get (i).getUid ()));
            } catch (ImgNotFoundException e) {
                e.printStackTrace ();
            }
        }
        req.setAttribute ("user_rank_list", usersList);
        req.setAttribute ("user_Head", usersHeadList);
    }

    private void getNewDevelopment(HttpServletRequest req, HttpServletResponse resp) {
        List<EssayStore> hotEssay = null;
        try {
            hotEssay = ess.findEssayHotSpot (12);
        } catch (EssayNotFoundException e) {
            e.printStackTrace ();
            req.setAttribute ("essay_error", e.getMessage ());
        }
        for (EssayStore es : hotEssay) {
            String essayid = es.getEssayid ();
            try {
                ArrayList allEssayImgPath = eiss.findAllEssayImgPath (essayid);
                es.setEssayImgPath (allEssayImgPath);
            } catch (ImgNotFoundException e) {
                e.printStackTrace ();
            }
        }
        req.setAttribute ("hot_essay_list", hotEssay);
    }

    public String getEssayStore(HttpServletRequest req, HttpServletResponse resp) {
        String essayid = req.getParameter ("essayid");
        try {
            EssayStore essay = ess.findEssayByEssayID (essayid);
            String essayImgPath = eiss.findEssayImg (essayid);
            String uid = essay.getUid ();
            User writer = us.findUserByParam ("uid", uid);
            String writerHead = uis.findUserhead (uid);
            req.setAttribute ("filepath",essayImgPath);
            req.setAttribute ("essay", essay);
            req.setAttribute ("essay_writer", writer);
            req.setAttribute ("essay_writer_head", writerHead);
        } catch (EssayNotFoundException | NotFindUserException | ImgNotFoundException e) {
            e.printStackTrace ();
            req.setAttribute ("essay_error", e.getMessage ());
        }
        try {
            List<Comment> comments = cs.findCommentByEssayid (essayid);
            ArrayList<User> users=new ArrayList<> ();
            for (Comment comment:comments){
                String uid = comment.getUid ();
                User user = us.findUsersByParam ("uid", uid).get (0);
                user.setHead (uis.findUserhead (user.getUid ()));
                user.setComment (comment);
                users.add (user);
            }
            req.setAttribute ("users_list",users);
        } catch (CommentNotFoundException | NotFindUserException | ImgNotFoundException e) {
            e.printStackTrace ();
            req.setAttribute ("comment_list_error",e.getMessage ());
        }
        return "f:/main_jsp/essay.jsp";
    }

    public void ajaxGiveThumbs(HttpServletRequest req, HttpServletResponse resp) {
        String essayid = req.getParameter ("essayid");
        try {
            EssayStore es = ess.findEssayByEssayID (essayid);
            int hotSpot = es.getHotSpot ();
            es.setHotSpot (++hotSpot);
            ess.updateEssayHotSpot (es);
            resp.getWriter ().println (true);
        } catch (EssayNotFoundException | IOException e) {
            e.printStackTrace ();
            try {
                resp.getWriter ().println (false);
            } catch (IOException e1) {
                e1.printStackTrace ();
            }
        }
    }
}
