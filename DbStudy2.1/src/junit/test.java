package junit;

import cn.drp.testlibrary.dao.TestLibraryDaoImp;
import cn.drp.tests.dao.TestDaoImp;
import cn.drp.user.dao.StudentDaoImp;
import cn.drp.wrongbook.dao.WrongBookDaoImp;
import cn.drp.testlibrary.domain.TestLibrary;
import cn.drp.user.domain.User;
import cn.drp.wrongbook.domain.WrongBook;
import utils.Drputils;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class test {
    private StudentDaoImp ud = new StudentDaoImp ();
    private TestLibraryDaoImp td = new TestLibraryDaoImp ();

    @Test
    public void insertUserTest() {
        try {
            ud.insertUser (new User (Drputils.getUUID ().substring (0, 20), "976772537", "123546", "976772537@qq.com"));
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }

    @Test
    public void findUserByParamTest() {
        try {
            ud.findUserByParam ("username", "976772537");
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }

    @Test
    public void updateUserTest() {
        User user = new User ();
        user.setName ("小红");
        user.setSex ("女");
        user.setUid ("86085FB08A3E493887A7");
        try {
            ud.updateUserInfo (user);
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }

    @Test
    void updateUserParamTest() {
        User user = new User ();
        user.setTelephone ("4567145454545");
        try {
            ud.updateUserParam ("telephone", user.getTelephone (), "86085FB08A3E493887A7");
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }
    @Test
    public void findTest(){
        try {
            List<cn.drp.tests.domain.Test> list = new TestDaoImp ().findTest (0, 10, "RDFSDF5S4S5A4D5SD");
            for (cn.drp.tests.domain.Test t : list){
                System.out.println (t);
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }
    @Test
    public void insertTestLibrary() {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter (new OutputStreamWriter (new FileOutputStream ("E:\\a.txt")));
            for (int i = 3; i < 30; i++) {
                String uid = Drputils.getUUID ().substring (0, 20);
                bw.write ("测试题库" + i + ":uid:" + uid);
                bw.newLine ();
                bw.flush ();
                try {
                    td.insertTestLibrary (new TestLibrary (uid, "测试题库" + i, "这是测试题库","mysql"));
                } catch (SQLException e) {
                    e.printStackTrace ();
                    bw.close ();
                }
            }
        } catch (Exception e) {
            e.printStackTrace ();
        } finally {
            try {
                bw.close ();
            } catch (IOException e) {
                e.printStackTrace ();
            }
        }
    }
    @Test
    public void insertTest() {
        BufferedReader br = null;
        TestDaoImp td = new TestDaoImp ();
        char[] ch = {'好', '和','是', '应', '加','你','请','问','请' ,'求','函'
                , '主', '撤', '删', '关', '题', '难', '也', '数', '聚', '几'
                , '级', '除', '据', '目', '库','难','应'};
        char[] en = {'a', 'b', 'c', 'd'};
        try {
            br = new BufferedReader (new InputStreamReader (new FileInputStream ("E:\\a.txt")));
            String s = null;
            while ((s=br.readLine ()) != null) {
                for (int j = 0; j < (Drputils.getRandom (15)+5); j++) {
                   String str = getString(10,5,ch);
                    cn.drp.tests.domain.Test test = new cn.drp.tests.domain.Test (Drputils.getUUID ().substring (0, 20)
                            ,str
                            , getString (3,2,ch)
                            , getString (3,2,ch)
                            , getString (3,2,ch)
                            , getString (3,2,ch)
                            , String.valueOf (en[Drputils.getRandom (4)])
                            , (Drputils.getRandom (10)+5)
                            , s.split (":")[2],0);
                    td.insertTest (test);
                }
                System.out.println (s);
            }
        } catch (Exception e) {
            e.printStackTrace ();
            try {
                br.close ();
            } catch (IOException e1) {
                e1.printStackTrace ();
            }
        }
    }

    private String getString(int num1,int num2,char[] ch) {
        StringBuffer sb = new StringBuffer ();
        for (int i = 0; i < (Drputils.getRandom (num1) + num2); i++) {
            sb.append (ch[Drputils.getRandom (ch.length)]);
        }
        return sb.toString ();
    }

    @Test
    public void cycleTest(){
        String[] a={"ad","qeqeq","zdsad"};
        Arrays.asList (a).forEach (System.out::println);
    }

    @Test
    public void totalPageTest(){
        TestLibraryDaoImp td=new TestLibraryDaoImp ();
        try {
            Number dataNum = td.getDataNum ("mysql");
            System.out.println (dataNum.intValue ());
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }

    @Test
    public void WrongbookWithTimeTest(){
        WrongBookDaoImp wb= new WrongBookDaoImp ();
        try {
            List<WrongBook> wt = wb.findWrongBookByUidWithTime ("F748085316964CA0A3CB", "11");
            for(WrongBook w : wt){
                System.out.println (w.toString ());
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }

//    @Test
//    public void InsertImgTest(){
//        ImgDaoImp imd=new ImgDaoImp ();
//        try {
//            imd.insertNewImg (new Img (Drputils.getUUID ().substring (0,20),"people_3.jpg"));
//        } catch (SQLException e) {
//            e.printStackTrace ();
//        }
//    }

    @Test
    public void  flowTest(){
        Long userCount=100L;
        Long userNum=15L;
        float userFlow=userNum.floatValue ()/userCount.floatValue ();
        System.out.println (userFlow);
    }
}
