package cn.drp.controller.upfile;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;

@WebServlet("/UpFileServlet")
@MultipartConfig(location = "D:\\", fileSizeThreshold = 2048)
public class UpFileServlet extends WriteEssayServlet {
    @Override
    public void setFilePage() {
        filePage = "UpFilePage";
    }

    @Override
    public void setFileSize() {
        fileSize = "UpFileSize";
    }

    @Override
    public void setFilePath() {
        filePath = "UpFilePath";
    }

    @Override
    public void setUpFileType() {
        fileType = "UpFileType";
    }

    @Override
    public void setType() {
        type = "UpFile";
    }
}
