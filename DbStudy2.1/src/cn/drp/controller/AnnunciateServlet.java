package cn.drp.controller;

import cn.drp.annunciate.domain.Annunciate;
import cn.drp.annunciate.service.AnnunciateService;
import cn.drp.user.domain.User;
import exception.AddAnnunciateFaildException;
import exception.DeleteAnnunciateFaildException;
import exception.selectAnnunciateException;
import utils.BaseServlet;
import utils.BeanUtils;
import utils.Drputils;
import utils.page.domain.Page;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/AnnunciateServlet")
public class AnnunciateServlet extends BaseServlet {
    private AnnunciateService as=new AnnunciateService ();
    public String getAnnunciatePage(HttpServletRequest req, HttpServletResponse resp){
        int cp =Integer.parseInt ( req.getParameter ("cp"));
        return getAnnunciate (req, cp);
    }

    private String getAnnunciate(HttpServletRequest req, int cp) {
        Page page=new Page();
        if(cp<=0){
            cp=1;
        }
        else if(cp>(page.getTotalPage ()+1)){
            cp=page.getTotalPage ()+1;
        }
        page.setCurrentPage (cp);
        try {
            page.setDataSize (as.getAllAnnunciateCount ());
            as.selectAllAnnunciateOrderByTime (page);
            List<Annunciate> list = (List<Annunciate>) page.getList ();
            req.setAttribute ("annunciatelist",list);
            req.setAttribute ("page",page);
        } catch (selectAnnunciateException e) {
            e.printStackTrace ();
            req.setAttribute ("error",e);
        }
        return "f:/main_jsp/annunciate.jsp";
    }

    public String getAnnunciateByTitle(HttpServletRequest req,HttpServletResponse resp){
        String title = req.getParameter ("title");
        try {
            List<Annunciate> list = as.selectAnnunciateByTitle (title);
            req.setAttribute ("annunciatelist",list);
        } catch (selectAnnunciateException e) {
            e.printStackTrace ();
            req.setAttribute ("error",e);
        }
        return "f:/main_jsp/annunciate_context.jsp";
    }

    public String getAnnunciateByTime(HttpServletRequest req,HttpServletResponse resp){
        String time=req.getParameter ("time");
        try {
            List<Annunciate> list = as.selectAnnunciateByTime (time);
            req.setAttribute ("annunciate",list);
        } catch (selectAnnunciateException e) {
            e.printStackTrace ();
            req.setAttribute ("error",e);
        }
        return "f:/main_jsp/annunciate.jsp";
    }
    public String  addAnnunciate(HttpServletRequest req,HttpServletResponse resp){
        Annunciate annunciate = BeanUtils.toBean (req.getParameterMap (), Annunciate.class);
        User user=(User)req.getSession ().getAttribute ("user");
        annunciate.setUid (user.getUid ());
        annunciate.setAnn_id (Drputils.getUUID ());
        try {

            as.addAnnunciate (annunciate);
        } catch (AddAnnunciateFaildException e) {
            e.printStackTrace ();
            req.setAttribute ("error",e);
        }
        return getAnnunciate (req, 1);
    }

    public String getAnnunciateByAnnID(HttpServletRequest req,HttpServletResponse resp){
        String id = req.getParameter ("id");
        try {
            Annunciate ann = as.selectAnnciateByAnnID (id);
            req.setAttribute ("annunciate",ann);
        } catch (selectAnnunciateException e) {
            e.printStackTrace ();
        }
        return "f:/main_jsp/annunciate_context.jsp";
    }

    public String deleteAnnunciateByTitle(HttpServletRequest req,HttpServletResponse resp){
        String title = req.getParameter ("title");
        try {
            as.deleteAnnunciateByTitle (title);
        } catch (DeleteAnnunciateFaildException e) {
            e.printStackTrace ();
            req.setAttribute ("error",e);
        }
        return getAnnunciate (req, 1);
    }
}
