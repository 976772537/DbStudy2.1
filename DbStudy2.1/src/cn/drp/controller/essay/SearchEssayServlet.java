package cn.drp.controller.essay;

import cn.drp.essaystore.domain.EssayStore;
import cn.drp.essaystore.service.EssayStoreService;
import cn.drp.findimgstore.user_imgstore.service.UserImgService;
import cn.drp.imgstore.service.ImgService;
import cn.drp.user.domain.User;
import cn.drp.user.service.UserService;
import exception.EssayNotFoundException;
import exception.ImgNotFoundException;
import exception.NotFindUserException;
import utils.BaseServlet;
import utils.Drputils;
import utils.page.domain.Page;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/SearchEssayServlet")
public class SearchEssayServlet extends BaseServlet {
    private EssayStoreService ess = new EssayStoreService (Drputils.getProperties ().getProperty ("EssayStore"));
    private ImgService ims = new ImgService (Drputils.getProperties ().getProperty ("ImgStore"));
    private UserImgService uis = new UserImgService (Drputils.getProperties ().getProperty ("UserImgStore"), ims);
    private final UserService us = new UserService (Drputils.getProperties ().getProperty ("TeacherDao"));

    public String SearchEssayByTitle(HttpServletRequest req, HttpServletResponse resp) {
        String title = req.getParameter ("title");
        Page page = new Page ();
        try {
            page.setDataSize (ess.findAllEssayStroeByTitle (title));
            int cp = Integer.parseInt (req.getParameter ("cp"));
            if(cp<=0){
                cp=1;
            }
            else if(cp>page.getTotalPage ()){
                cp=page.getTotalPage ();
            }
            page.setCurrentPage (cp);
            ess.findEssayStoreByTitle (title, page);
        } catch (EssayNotFoundException e) {
            e.printStackTrace ();
            req.setAttribute ("search_error", "还没有要搜索的文章");
            return "f:/main_jsp/search_essay.jsp";
        }
        List<EssayStore> essayList = (List<EssayStore>) page.getList ();
        ArrayList<String> usersHead = new ArrayList<> ();
        ArrayList<User> users = new ArrayList<> ();
        for (EssayStore es : essayList){
            String uid = es.getUid ();
            try {
                users.add (us.findUserByParam ("uid",uid));
                usersHead.add (uis.findUserhead (uid));
            } catch (ImgNotFoundException | NotFindUserException e) {
                e.printStackTrace ();
                req.setAttribute ("search_error", "找不到这篇文章的作者了");
                return "f:/main_jsp/search_essay.jsp";
            }
        }
        System.out.println (title);
        try {
            req.setAttribute ("title",new String (title.getBytes ("utf-8"),"ISO-8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace ();
        }
        req.setAttribute ("page",page);
        req.setAttribute ("essay_list", essayList);
        req.setAttribute ("users_list",users);
        req.setAttribute ("users_head", usersHead);
        return "f:/main_jsp/search_essay.jsp";
    }
}
