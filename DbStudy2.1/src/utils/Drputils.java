package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;

public class Drputils {
    /**
     * @return ��ȡ��֤��
     * @author cn.drp ��֤��
     */
    public static VerifyCode getVerifyCode() {
        return new VerifyCode ();
    }

    /**
     * ��ȡUUID
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID ().toString ().replace ("-", "").toUpperCase ();
    }


    public static int getRandom(int num) {
        return new Random ().nextInt (num);
    }

    public static Properties getProperties(){
        Properties pro = new Properties ();
        try {
            pro.load (new FileInputStream (Thread.currentThread().getContextClassLoader().getResource("Dao_Reflect_Config.properties").getFile()));
        } catch (IOException e) {
            e.printStackTrace ();
        }
        return pro;
    }
}
