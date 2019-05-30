package cn.drp.controller.user;

import cn.drp.imgstore.domain.ImgStore;
import cn.drp.user.domain.User;
import cn.drp.imgstore.service.ImgService;
import cn.drp.findimgstore.user_imgstore.service.UserImgService;
import exception.ImgInsertFaildException;
import exception.ImgNotFoundException;
import utils.BaseServlet;
import utils.Drputils;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet("/HeadImgStoreServlet")
@MultipartConfig(location = "D:\\temp", fileSizeThreshold = 1024)
public class HeadImgStoreSerlvet extends BaseServlet {
    private ImgService imgService = new ImgService (Drputils.getProperties ().getProperty ("ImgStore"));
    private UserImgService userImgService = new UserImgService (Drputils.getProperties ().getProperty ("UserImgStore"), imgService);
    private String fileName;
    private String filePath;
    private Part p;
    private String headName;
    private String fileType;

    public void getHeadImg(HttpServletRequest req, HttpServletResponse resp) {
        try {
            List<ImgStore> allImg = imgService.findAllDefaultImgStore ();
            req.setAttribute ("headlist", allImg);
            resp.getWriter ().println (true);
        } catch (ImgNotFoundException e) {
            e.printStackTrace ();
            req.setAttribute ("imgerror", e.getMessage ());
        } catch (IOException e) {
            e.printStackTrace ();
        }
        try {
            req.getRequestDispatcher ("main_jsp/person.jsp").forward (req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace ();
        }
    }

    public void changeHeadImg(HttpServletRequest req, HttpServletResponse resp) {
        String imgPath = req.getParameter ("imgPath");
        User user = (User) req.getSession ().getAttribute ("user");
        changeUserHead (req, resp, imgPath, user);
    }

    public void userHeadUpLoad(HttpServletRequest req, HttpServletResponse resp) {
        String path = req.getServletContext ().getRealPath ("/") + "img\\user_head";
        User user = (User) req.getSession ().getAttribute ("user");
        try {
            getPartAndNameAndType (req);
            if (!verifyFile (req, resp, path, p, fileType)) return;
        } catch (IOException |ServletException e) {
            e.printStackTrace ();
            getHeadImg (req, resp);
            return;
        }
        fileName = user.getUid () + "_" + System.currentTimeMillis () + fileType;
        filePath = (path + "\\" + fileName).replaceAll ("\\\\", "/");
        deleteExistFile (path, user);
        try {
            p.write (filePath);
        } catch (IOException e) {
            e.printStackTrace ();
            getHeadImg (req, resp);
            return;
        }
        updateUserHeadByImgStoreAndUserImgStore (req, resp, user);
    }

    private void changeUserHead(HttpServletRequest req, HttpServletResponse resp, String imgPath, User user) {
        try {
            userImgService.deleteUserHead (user.getUid ());
        } catch (ImgNotFoundException e) {
            e.printStackTrace ();
        }
        try {
            String imgId = imgService.findImgIdByImgPath (imgPath);
            userImgService.updateUserHead (user.getUid (), imgId);
            req.setAttribute ("changeimg_success", "头像更换成功");
            req.getSession ().setAttribute ("userhead", userImgService.findUserhead (user.getUid ()));
        } catch (ImgNotFoundException e) {
            e.printStackTrace ();
            req.setAttribute ("changeimg_error", e.getMessage ());
        }
        getHeadImg (req, resp);
    }

    private void updateUserHeadByImgStoreAndUserImgStore(HttpServletRequest req, HttpServletResponse resp, User user) {
        try {
            imgService.updateImgStateByImgPath (user.getUid (), 0);
            imgService.insertImg (getImgStore (fileName), 1);
        } catch (ImgInsertFaildException | ImgNotFoundException e) {
            e.printStackTrace ();
        }
        changeUserHead (req, resp, fileName, user);
        try {
            imgService.deleteImgByImgPath (user.getUid (), 0);
        } catch (ImgNotFoundException e) {
            e.printStackTrace ();
        }
    }

    private void getPartAndNameAndType(HttpServletRequest req) throws IOException, ServletException {
        p = req.getPart ("fileName");
        headName = p.getHeader ("content-disposition");
        fileType = headName.substring (headName.lastIndexOf ("."), headName.length () - 1);
    }

    private ImgStore getImgStore(String fileName) {
        ImgStore img = new ImgStore ();
        img.setImgID (Drputils.getUUID ());
        img.setImgPath (fileName);
        return img;
    }

    private void deleteExistFile(String path, User user) {
        File[] files = new File (path).listFiles ();
        for (File file : files != null ? files : new File[0]) {
            if (file.getName ().substring (0, file.getName ().lastIndexOf ("_")).equals (user.getUid ())) {
                file.delete ();
                break;
            }
        }
    }

    private boolean verifyFile(HttpServletRequest req, HttpServletResponse resp, String path, Part p, String fileType) throws IOException {
        if (!fileType.equalsIgnoreCase (".jpg") && !fileType.equalsIgnoreCase (".png")) {
            getHeadImg (req, resp);
            return false;
        }
        if (p.getSize () > 102400) {
            p.delete ();
            req.setAttribute ("changeimg_error", "文件太大");
            getHeadImg (req, resp);
            return false;
        } else {
            File file = new File (path);
            if (!file.exists ()) {
                file.mkdirs ();
            }
        }
        return true;
    }
}
