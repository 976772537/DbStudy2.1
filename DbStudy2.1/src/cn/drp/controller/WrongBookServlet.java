package cn.drp.controller;

import cn.drp.tests.domain.Test;
import cn.drp.tests.service.TestService;
import cn.drp.user.domain.User;
import cn.drp.wrongbook.domain.WrongBook;
import cn.drp.wrongbook.service.WrongBookService;
import exception.FindTestFailedException;
import exception.NotFoundTestException;
import utils.BaseServlet;
import utils.Drputils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/WrongBookServlet")
public class WrongBookServlet extends BaseServlet {
    private WrongBookService ws = new WrongBookService (Drputils.getProperties ().getProperty ("WrongBook"));
    private TestService ts = new TestService (Drputils.getProperties ().getProperty ("TestDao"));

    public String getWrongBookByTime(HttpServletRequest req, HttpServletResponse resp) {
        String uid = ((User) req.getSession ().getAttribute ("user")).getUid ();
        List<WrongBook> wl = null;
        try {
            wl = ws.findWrongBookByTime (uid, req.getParameter ("date"));
            req.setAttribute ("wrongBookList", wl);
        } catch (NotFoundTestException e) {
            e.printStackTrace ();
            req.setAttribute ("wrongbook_error", e.getMessage ());
        }
        getWrongBookAnswer (req, wl);
        return "f:/main_jsp/wrong_book.jsp";
    }

    public String getWrongBook(HttpServletRequest req, HttpServletResponse resp) {
        String uid = ((User) req.getSession ().getAttribute ("user")).getUid ();
        List<WrongBook> wl = null;
        try {
            wl = ws.findWrongBook (uid);
            req.setAttribute ("wrongBookList", wl);
        } catch (NotFoundTestException e) {
            e.printStackTrace ();
            req.setAttribute ("wrongbook_error", e.getMessage ());
        }
        getWrongBookAnswer (req, wl);
        return "f:/main_jsp/wrong_book.jsp";
    }

    private void getWrongBookAnswer(HttpServletRequest req, List<WrongBook> wl) {
        ArrayList<Test> testList = new ArrayList<> ();
        for (WrongBook wrongBook : wl) {
            try {
                List<Test> tl = ts.findTestBySid (wrongBook.getSid ());
//                for (Test test: tl){
//                    testList.add (test);
//                }
                tl.forEach (test -> testList.add (test));

            } catch (FindTestFailedException e) {
                e.printStackTrace ();
                req.setAttribute ("wrongbook_error", e.getMessage ());
            }
        }
        req.setAttribute ("testList", testList);
    }
}
