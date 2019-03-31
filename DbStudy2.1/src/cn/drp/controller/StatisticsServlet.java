package cn.drp.controller;

import cn.drp.user.service.UserService;
import utils.BaseServlet;
import utils.Drputils;
import utils.HttpServletFilter.UserFlowFilter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/StatisitcsServlet")
public class StatisticsServlet extends BaseServlet {
    public void ajaxGetUserFlow(HttpServletRequest req, HttpServletResponse resp){
        Long userCount=(Long)req.getServletContext ().getAttribute ("userCount");
        UserFlowFilter.setUserCount (0L);
        req.getServletContext ().setAttribute ("userCount",0L);
        try {
            resp.getWriter ().println (userCount.floatValue ());
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }

}
