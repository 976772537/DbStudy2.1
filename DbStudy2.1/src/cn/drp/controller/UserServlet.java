package cn.drp.controller;

import cn.drp.user.domain.User;
import cn.drp.imgstore.service.ImgService;
import cn.drp.findimgstore.user_imgstore.service.UserImgService;
import cn.drp.user.service.UserService;
import exception.*;
import utils.BaseServlet;
import utils.BeanUtils;
import utils.Drputils;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {

    private UserService us = new UserService (Drputils.getProperties ().getProperty ("StudentDao"));
    private ImgService imgService = new ImgService (Drputils.getProperties ().getProperty ("ImgStore"));
    private UserImgService userImgService = new UserImgService (Drputils.getProperties ().getProperty ("UserImgStore"),imgService);

    public String login(HttpServletRequest req, HttpServletResponse resp) {
        User user_success = null;
        HttpSession session = req.getSession ();
        String verifyCode = req.getParameter ("verifycode");
        User user = BeanUtils.toBean (req.getParameterMap (), User.class);
        session.setAttribute ("user", user);
        if (!verifyCode.equalsIgnoreCase ((String) (req.getSession ().getAttribute ("verifyCodeForLogin")))) {
            req.setAttribute ("verif_error", "验证码错误");
            return "f:/main_jsp/login.jsp";
        }
        try {
            user_success = us.findUser (user);
        } catch (NotFindUserException e) {
            req.setAttribute ("login_error", e.getMessage ());
            return "f:/main_jsp/login.jsp";
        }
        getCookieCheck (req, resp, user);
        session.setAttribute ("user", user_success);
        try {
            session.setAttribute ("userhead", userImgService.findUserhead (user_success.getUid ()));
        } catch (ImgNotFoundException e) {
            e.printStackTrace ();
        }
        return "f:/main_jsp/index.jsp";
    }

    private void getCookieCheck(HttpServletRequest req, HttpServletResponse resp, User user) {
        if (req.getParameter ("remmber") != null) {
            Cookie ck = new Cookie ("username", user.getUsername ());
            Cookie ck1 = new Cookie ("password", user.getPassword ());
            ck.setMaxAge (60 * 60 * 24 * 7);
            ck1.setMaxAge (60 * 60 * 24 * 7);
            resp.addCookie (ck);
            resp.addCookie (ck1);
        }
    }

    public String regist(HttpServletRequest req, HttpServletResponse resp) {
        String verifyCode = req.getParameter ("verifyCode");
        String vf = (String) req.getSession ().getAttribute ("verifyCodeForRegist");
        User user = BeanUtils.toBean (req.getParameterMap (), User.class);
        req.setAttribute ("user", user);
        if (!verifyCode.equalsIgnoreCase (vf)) {
            req.setAttribute ("verif_error", "验证码错误");
            return "f:/main_jsp/regist.jsp";
        }
        req.setAttribute ("user", user);
        getHideMsg (req, user);
        try {
            us.insertUser (user);
        } catch (PleaseInputTureMessageException e) {
            req.setAttribute ("regist_error", e.getMessage ());
            return "f:/main_jsp/regist.jsp";
        }
        resp.setHeader ("refresh", "5;url=" + req.getContextPath () + "/main_jsp/index.jsp");
        return "f:/main_jsp/regist_success.jsp";
    }

    private void getHideMsg(HttpServletRequest req, User user) {
        String uid = Drputils.getUUID ().substring (0, 20);
        user.setUid (uid);
        try {
            userImgService.insertRandomUserhead (uid);
        } catch (ImgInsertFaildException e) {
            e.printStackTrace ();
            req.setAttribute ("verif_error", e.getMessage ());
        }
    }

    public String updateUser(HttpServletRequest req, HttpServletResponse resp) {
        User user = BeanUtils.toBean (req.getParameterMap (), User.class);
        User user_su = (User) req.getSession ().getAttribute ("user");
        String uid = user_su.getUid ();
        user.setUid (uid);
        try {
            us.updateUserInfo (user);
        } catch (SavePersonInfoFiledException e) {
            req.setAttribute ("update_error", e.getMessage ());
            return "f:/main_jsp/person.jsp";
        }
        req.setAttribute ("update_success", "修改成功");
        User user_req = null;
        try {
            user_req = us.findUserByParam ("uid", uid);
        } catch (NotFindUserException e) {
            e.printStackTrace ();
            req.setAttribute ("update_error", "重新查询失败");
        }
        req.getSession ().setAttribute ("user", user);
        return "f:/main_jsp/person.jsp";
    }

    public String loginExit(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession ().invalidate ();
        return "f:/main_jsp/index.jsp";
    }

    public String ajaxValidateUsername(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter ("username");
        try {
            us.findUserByParam ("username", username);
            resp.getWriter ().println (true);
        } catch (Exception e) {
            e.printStackTrace ();
            try {
                resp.getWriter ().println (false);
            } catch (IOException e1) {
                e1.printStackTrace ();
            }
        }
        return null;
    }
}
