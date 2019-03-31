package cn.drp.controller;

import utils.page.domain.Page;
import cn.drp.tests.domain.Test;
import cn.drp.testlibrary.domain.TestLibrary;
import cn.drp.testlibrary.service.TestLibraryService;
import cn.drp.tests.service.TestService;
import exception.FindTestFailedException;
import exception.NotFoundTestException;
import utils.BaseServlet;
import utils.Drputils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/TestLibraryServlet")
public class TestLibraryServlet extends BaseServlet {
    private TestLibraryService testLibraryService = new TestLibraryService (Drputils.getProperties ().getProperty ("TestLibraryDao"));
    private int cp = 1;
    private Page page = new Page ();
    private  List<TestLibrary> list=null;
   /* public String getTestLibrary(HttpServletRequest req, HttpServletResponse resp) {
        try {
            getlist (req);
        } catch (NotFoundTestException e) {
            e.printStackTrace ();
        }
        req.setAttribute ("list", list);
        return "f:/main_jsp/sql_test_library.jsp";
    }*/
   public void ajaxGetTestLib(HttpServletRequest req,HttpServletResponse resp){
       String tc = req.getParameter ("testClass");
       System.out.println (tc);
       List<TestLibrary> testLibrarys=null;
       try {
           testLibrarys=testLibraryService.findTestLibraryByType (tc);
       } catch (NotFoundTestException e) {
           e.printStackTrace ();
           try {
               resp.getWriter ().println (false);
           } catch (IOException e1) {
               e1.printStackTrace ();
           }
       }
       req.setAttribute ("testLibrarys",testLibrarys);
       StringBuffer sb=new StringBuffer (100);
       assert testLibrarys != null;
       testLibrarys.forEach (tb-> sb.append (tb.getT_name ()).append (":"));
       try {
           String tls = sb.toString ().substring (0, sb.toString ().lastIndexOf (":"));
           resp.getWriter ().println (tls);
       } catch (IOException e) {
           e.printStackTrace ();
       }
   }

    public String getTestLibrary(HttpServletRequest req, HttpServletResponse resp) {
        page.setPageSize (10);
        String type = req.getParameter ("type");
        req.setAttribute ("type",type);
        try {
            testLibraryService.setDataSize (type,page);
        } catch (NotFoundTestException e) {
            e.printStackTrace ();
            return "f:/main_jsp/sql_test_library.jsp";
        }
        int num = Integer.parseInt (req.getParameter ("cp_stl"));
        if (num > 0 &&  num <=page.getTotalPage ()) {
            this.cp = num;
        }
        try {
            getlist (req);
        } catch (NotFoundTestException e) {
            e.printStackTrace ();
            cp = page.getTotalPage ();
        }
        try {
            List<Test> hotSpot = new TestService (Drputils.getProperties ().getProperty ("TestDao")).findHotSpot (0, 5);
            req.setAttribute ("hotSpotList",hotSpot);
        } catch (FindTestFailedException e) {
            e.printStackTrace ();
        }
        req.setAttribute ("list", list);
        req.setAttribute ("page",page);
        return "f:/main_jsp/sql_test_library.jsp";
    }

    private void getlist(HttpServletRequest req) throws NotFoundTestException {
        testLibraryService.findCurrentPageList (req.getParameter ("type"),cp,page);
        if(page.getList ()!=null) {
            list = (List<TestLibrary>) page.getList ();
        }
    }
}
