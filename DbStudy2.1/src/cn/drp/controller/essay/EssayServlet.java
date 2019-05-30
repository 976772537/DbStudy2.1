package cn.drp.controller.essay;

import cn.drp.Comment.service.CommentService;
import cn.drp.essaystore.domain.EssayStore;
import cn.drp.essaystore.service.EssayStoreService;
import cn.drp.findimgstore.eassy_imgstore.domain.EssayImgStore;
import cn.drp.findimgstore.eassy_imgstore.service.EssayImgStoreService;
import cn.drp.imgstore.service.ImgService;
import cn.drp.user.domain.User;
import exception.CommentNotFoundException;
import exception.EssayNotFoundException;
import exception.ImgNotFoundException;
import utils.BaseServlet;
import utils.Drputils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

@WebServlet("/EssayServlet")
public class EssayServlet extends BaseServlet {
    private EssayImgStoreService eiss=new EssayImgStoreService (Drputils.getProperties ().getProperty ("EssayImgStore")
    ,new ImgService (Drputils.getProperties ().getProperty ("ImgStore")));
    private CommentService cs=new CommentService (Drputils.getProperties ().getProperty ("Comment"));
    private EssayStoreService ess=new EssayStoreService (Drputils.getProperties ().getProperty ("EssayStore"));
    protected String path;

    public void setPath() {
        path = "ImgFilePath";
    }

    public String  getEssayForPerson(HttpServletRequest req, HttpServletResponse resp){
        User user = (User) req.getSession ().getAttribute ("user");
        try {
            List<EssayStore> list = ess.findEssayStoreByUid (user.getUid ());
            req.setAttribute ("essay_list",list);
        } catch (EssayNotFoundException e) {
            e.printStackTrace ();
            req.setAttribute ("error","您还没有文章发表");
        }
        return "f:/main_jsp/teacher_jsp/essay_person.jsp";
    }

    public String deleteEssayByEssayid(HttpServletRequest req,HttpServletResponse resp){
        setPath ();
        String essayid=req.getParameter ("essayid");
        try {
            String essayImg = eiss.findEssayImg (essayid);
            eiss.deleteEssayImg (essayid);
            cs.deleteCommentByEssayid (essayid);
            ess.deleteEssayByEssayID (essayid);
            new File (req.getServletContext ().getRealPath ("/")+Drputils.getProperties ().getProperty (path)+"/"+essayImg).delete ();
            req.setAttribute ("delete_success","删除成功");
        } catch (EssayNotFoundException | ImgNotFoundException | CommentNotFoundException e) {
            e.printStackTrace ();
            req.setAttribute ("delete_error","删除失败");
        }
        return getEssayForPerson (req,resp);
    }
}
