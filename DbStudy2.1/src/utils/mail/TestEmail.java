package utils.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class TestEmail {
	/*
	 * 四大参数：
	 * 	授权吗
	 * 	账号
	 * 	端口
	 * 	服务器地址
	 */
	private static final String PWD = "uexcdkxsxvacdecf";
	private static final String ACCOUNT = "1728778931@qq.com";
	private static final String SMTPPORT = "465";
	private static final String SMTPSERVER = "smtp.qq.com";
	
	public static void main(String[] args) throws AddressException, MessagingException {
		/*
		 * Session对象：是与邮箱服务器连接成功后产生一个会话
		 * MimeMessage:邮件对象
		 * Transport.sendMessage(message);
		 * 
		 */
		/*
		 * 1.连接服务器的其他参数：
		 * 	协议:mail.transport.protocol : smtp
		 * 	smtp主机：   mail.smtp.host  :   SMTPSERVER
		 * 	mail.smtp.port :  SMTPPORT
		 * 	认证： mail.smtp.auth :  true
		 * 	安全传输：  mail.smtp.ssl.enable : true
		 */
		Properties ps = new Properties();
		ps.setProperty("mail.transport.protocol", "smtp");
		ps.setProperty("mail.smtp.host", SMTPSERVER);
		ps.setProperty("mail.smtp.port", SMTPPORT);
		ps.setProperty("mail.smtp.auth", "true");
		ps.setProperty("mail.smtp.ssl.enable","true");
		ps.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		
		//1.创建session对象
		Session session = Session.getDefaultInstance(ps);
		
		//2.设置session.debug(true);  
		session.setDebug(true);
		
		
		//3.创建邮件对象
		MimeMessage  message = new MimeMessage(session);
		
		//4.向邮件里写入数据
		message.setFrom(new InternetAddress(ACCOUNT));//jason，是发送另一端显示的用户名
		
		//5.设置收件人
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress("1468359547@qq.com"));
//		message.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress("jasonisoft@aliyun.com"));
//		message.setRecipient(MimeMessage.RecipientType.BCC, new InternetAddress("jason13552648187@sina.com"));
		
		//6.设置邮件主题
		message.setSubject("你好，这是一封测试邮件！","utf-8");
		
		//7.设置邮件正文
		message.setText("我是个程序员，一天我坐在路边一边喝水一边苦苦检查程序。 这时一个乞丐在我边上坐下了，"
				+ "开始要饭，我觉得可怜，就给了他1块钱。 然后接着调试程序。"
				+ "他可能生意不好，就无聊的看看我在干什么，然后过了一会，他缓缓地指着我的屏幕说，这里少了个分号");
		
		
		//8.设置邮件的发送时间
		message.setSentDate(new Date());
		
		//9.保存邮件
		message.saveChanges();
		
		//10.打通连接
		Transport tp = session.getTransport("smtp");
		
		//11.连接服务器
		tp.connect(SMTPSERVER, ACCOUNT, PWD);
		
		
		//12.发送
		tp.sendMessage(message, message.getAllRecipients());
		
		
		//13.关闭连接
		tp.close();
		
		
	}
}
