package cn.drp.controller.test;

import cn.drp.testlibrary.service.TestLibraryService;
import cn.drp.tests.domain.Test;
import cn.drp.user.domain.User;
import cn.drp.tests.service.TestService;
import cn.drp.wrongbook.service.WrongBookService;
import exception.FindTestFailedException;
import exception.InsertWrongBookFailedException;
import exception.NotFoundTestException;
import exception.insertTestFailedException;
import utils.BaseServlet;
import utils.BeanUtils;
import utils.Drputils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/TestServlet")
public class TestServlet extends BaseServlet {
    private final TestService ts = new TestService (Drputils.getProperties ().getProperty ("TestDao"));
    private final TestLibraryService tls = new TestLibraryService (Drputils.getProperties ().getProperty ("TestLibraryDao"));

    public String getTest(HttpServletRequest req, HttpServletResponse resp) {
        List<Test> list = null;
        String tid = req.getParameter ("tid");
        try {
            list = ts.findTest (0, 10, tid);
        } catch (FindTestFailedException e) {
            e.printStackTrace ();
            req.setAttribute ("test_error", e.getMessage ());
            return "f:/main_jsp/test.jsp";
        }
        if (list.size () < 10) {
            resp.setHeader ("refresh", "5;url=" + req.getContextPath () + "/main_jsp/index.jsp");
            req.getSession ().setAttribute ("list", list);
        } else {
            req.getSession ().setAttribute ("list", list);
        }
        return "f:/main_jsp/test.jsp";
    }

    public String getResult(HttpServletRequest req, HttpServletResponse resp) {
        ArrayList<Test> list = (ArrayList<Test>) (req.getSession ().getAttribute ("list"));
        ArrayList<String> rightAnswer = new ArrayList<> ();
        ArrayList<String> errorAnswer = new ArrayList<> ();
        int score = 0;
        int i = 0;
        for (Test st : list) {
            if (req.getParameter ("answer" + i) != null) {
                if (st.getResult ().equalsIgnoreCase (req.getParameter ("answer" + i))) {
                    rightAnswer.add ("answer" + i + st.getResult ().toUpperCase ());
                    score += st.getScore ();
                } else {
                    errorAnswer.add ("answer" + i + req.getParameter ("answer" + i).toUpperCase ());
                }
            }
            i++;
        }
        req.setAttribute ("rightAnswer", rightAnswer);
        req.setAttribute ("errorAnswer", errorAnswer);
        req.setAttribute ("score", score);

        return "f:/main_jsp/test.jsp";
    }


    public String addTest(HttpServletRequest req, HttpServletResponse resp) {
        Test test = BeanUtils.toBean (req.getParameterMap (), Test.class);
        String testLib = req.getParameter ("testLib");
        test.setSid (Drputils.getUUID ());
        User user = (User) req.getSession ().getAttribute ("user");
        test.setUid (user.getUid ());
        try {
            test.setTid (tls.findTestLibraryByt_name (testLib).getTid ());
        } catch (Exception | NotFoundTestException e) {
            e.printStackTrace ();
            req.setAttribute ("error","输入题库不能为空");
            return "f:/main_jsp/teacher_jsp/add_test.jsp";
        }
        if(!test.getResult ().matches ("[a-dA-D]{1}")){
            req.setAttribute ("error","输入答案不合法");
            return "f:/main_jsp/teacher_jsp/add_test.jsp";
        }
        try {
            ts.insertTest (test);
            req.setAttribute ("success","添加成功");
        } catch (insertTestFailedException e) {
            e.printStackTrace ();
            req.setAttribute ("error",e.getMessage ());
        }
        return "f:/main_jsp/teacher_jsp/add_test.jsp";
    }

    public String testManger(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession ().getAttribute ("user");
        try {
            List<Test> tests = ts.findTestByUid (user.getUid ());
            req.setAttribute ("testList", tests);
            return "f:/main_jsp/teacher_jsp/test_manger.jsp";
        } catch (FindTestFailedException e) {
            e.printStackTrace ();
            req.setAttribute ("test_error", e.getMessage ());
            return "f:/main_jsp/teacher_jsp/test_manger.jsp";
        }
    }
    public String getTestByTime(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession ().getAttribute ("user");
        String date = req.getParameter ("date");
        try {
            List<Test> testList = ts.findTestByUidWithTime (user.getUid (), date);
            req.setAttribute ("testList",testList);
        } catch (FindTestFailedException e) {
            e.printStackTrace ();
            req.setAttribute ("test_error", e.getMessage ());
        }
        return "f:/main_jsp/teacher_jsp/test_manger.jsp";
    }
    public String deleteTest(HttpServletRequest req,HttpServletResponse resp){
        String sid=req.getParameter ("sid");
        try {
            ts.deleteTestBySid (sid);
            req.setAttribute ("success","删除题目成功");
        } catch (FindTestFailedException e) {
            e.printStackTrace ();
            req.setAttribute ("error","删除题目失败");
        }
        return "f:/main_jsp/teacher_jsp/test_manger.jsp";
    }
    public void ajaxAddWrongBook(HttpServletRequest req, HttpServletResponse resp) {
        String sid = req.getParameter ("sid");
        String uid = ((User) req.getSession ().getAttribute ("user")).getUid ();
        try {
            new WrongBookService (Drputils.getProperties ().getProperty ("WrongBook")).addWrongBookByUid (sid, uid);
            ts.updateTestHotSpot (sid);
            resp.getWriter ().println (true);
        } catch (FindTestFailedException | IOException | InsertWrongBookFailedException e) {
            e.printStackTrace ();
            try {
                resp.getWriter ().println (false);
            } catch (IOException e1) {
                e1.printStackTrace ();
            }
        }
    }
}
