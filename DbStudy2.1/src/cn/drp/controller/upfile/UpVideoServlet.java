package cn.drp.controller.upfile;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;

@WebServlet("/UpVideoServlet")
@MultipartConfig(location = "D:\\",fileSizeThreshold = 2048)
public class UpVideoServlet extends WriteEssayServlet{
    @Override
    public void setFilePath() {
        filePath="VideoPath";
    }

    @Override
    public void setFileSize() {
        fileSize="VideoSize";
    }

    @Override
    public void setFilePage() {
        filePage="VideoPage";
    }

    @Override
    public void setUpFileType() {
        fileType="VideoType";
    }

    @Override
    public void setType() {
        type="Video";
    }
}

