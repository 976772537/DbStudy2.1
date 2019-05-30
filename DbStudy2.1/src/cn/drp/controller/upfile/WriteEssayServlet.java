package cn.drp.controller.upfile;

import cn.drp.essaystore.domain.EssayStore;
import cn.drp.essaystore.service.EssayStoreService;
import cn.drp.findimgstore.eassy_imgstore.service.EssayImgStoreService;
import cn.drp.imgstore.domain.ImgStore;
import cn.drp.imgstore.service.ImgService;
import cn.drp.user.domain.User;
import exception.EssayInsertFaildException;
import exception.EssayNotFoundException;
import exception.ImgInsertFaildException;
import utils.BaseServlet;
import utils.BeanUtils;
import utils.Drputils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@WebServlet("/WriteEssayServlet")
@MultipartConfig(location = "D:\\", fileSizeThreshold = 2048)
public class WriteEssayServlet extends BaseServlet {
    private EssayStoreService ess = new EssayStoreService (Drputils.getProperties ().getProperty ("EssayStore"));
    private ImgService ims = new ImgService (Drputils.getProperties ().getProperty ("ImgStore"));
    private EssayImgStoreService eiss = new EssayImgStoreService (Drputils.getProperties ().getProperty ("EssayImgStore"), ims);
    protected String fileSize;
    protected String fileType;
    protected String filePage;
    protected String filePath;
    protected String type;
    public void setFileSize() {
        fileSize = "ImgFileSize";
    }

    public void setUpFileType() {
        fileType = "ImgFileType";
    }

    public void setFilePage() {
        filePage = "ImgFilePage";
    }

    public void setFilePath() {
        filePath = "ImgFilePath";
    }

    public void setType() {
        this.type = "Img";
    }

    public String writeEssay(HttpServletRequest req, HttpServletResponse resp) {
        setFilePage ();
        setType ();
        EssayStore es = BeanUtils.toBean (req.getParameterMap (), EssayStore.class);
        es.setUid (((User) req.getSession ().getAttribute ("user")).getUid ());
        String essayId = Drputils.getUUID ();
        es.setFiletype (Drputils.getProperties ().getProperty (type));
        es.setEssayid (essayId);
        try {
            ess.insertEssay (es);
        } catch (EssayInsertFaildException e) {
            e.printStackTrace ();
            req.setAttribute ("write_error", e.getMessage ());
            return "f:/main_jsp/teacher_jsp/" + Drputils.getProperties ().getProperty (filePage);
        }
        boolean essayImge = getEssayImge (req, resp);
        String essayimgPath = (String) req.getSession ().getAttribute ("essayimgPath");
        if (essayImge) {
            try {
                eiss.insertEssayImg (essayId, essayimgPath);
            } catch (ImgInsertFaildException e) {
                e.printStackTrace ();
                req.setAttribute ("img_error", "文件格式不正确");
                wrongFile (req, essayId, essayimgPath);
            }
            req.setAttribute ("success", "发布成功");
        }else{
            wrongFile (req, essayId, essayimgPath);
        }
        return "f:/main_jsp/teacher_jsp/" + Drputils.getProperties ().getProperty (filePage);
    }

    private void wrongFile(HttpServletRequest req, String essayId, String essayimgPath) {
        File file = new File (req.getServletContext ().getRealPath ("/") + "img/essay/" + essayimgPath);
        try {
            ess.deleteEssayByEssayID (essayId);
        } catch (EssayNotFoundException e1) {
            e1.printStackTrace ();
        }
        if (file.exists ()) {
            file.delete ();
        }
    }

    private boolean getEssayImge(HttpServletRequest req, HttpServletResponse resp) {
        setFileSize ();
        setFilePath ();
        Part imgPart = null;
        try {
            imgPart = req.getPart ("essayimg");
        } catch (IOException | ServletException e) {
            e.printStackTrace ();
            req.setAttribute ("img_error", "未设置文件");
            return false;
        }
        String imgName = imgPart.getName ();
        String headName = imgPart.getHeader ("content-disposition");
        String fileType = headName.substring (headName.lastIndexOf ("."), headName.length () - 1);
        String[] upFileType = getUPFileType ();
        if (!verifyImg (imgPart, fileType, Long.parseLong (Drputils.getProperties ().getProperty (fileSize)), upFileType)) {
            req.setAttribute ("img_error", "文件格式不正确");
            return false;
        }
        String fileName = ((User) req.getSession ().getAttribute ("user")).getUid () + System.currentTimeMillis () + fileType;
        try {
            String path=req.getServletContext ().getRealPath ("/")
                    + Drputils.getProperties ().getProperty (filePath);
            File file = new File (path);
            if(!file.exists ()){
                file.mkdirs ();
            }
            imgPart.write (path+ "/" + fileName);
            ImgStore img = new ImgStore ();
            String imgId = Drputils.getUUID ();
            img.setImgID (imgId);
            img.setImgPath (fileName);
            ims.insertImg (img, 1);
            req.getSession ().setAttribute ("essayimgPath", fileName);
            return true;
        } catch (IOException | ImgInsertFaildException e) {
            e.printStackTrace ();
            req.setAttribute ("img_error", "封面图片格式不正确");
            return false;
        }
    }

    private String[] getUPFileType() {
        setUpFileType ();
        return Drputils.getProperties ().getProperty (fileType).split ("&");
    }


    /**
     * @param imgPart
     * @param fileType
     * @param fileSize   上传文件大小
     * @param upFileType 上传文件要被验证类型
     * @return
     */
    private boolean verifyImg(Part imgPart, String fileType, long fileSize, String[] upFileType) {
        for (String up : upFileType) {
            if (fileType.equalsIgnoreCase (up)) {
                return true;
            }
        }
        if (imgPart.getSize () <= (fileSize)) {
            return true;
        }
        return false;
    }

}
