package utils.mail;

import javax.mail.MessagingException;
import javax.mail.Session;


public class MailTest {

	public void test() {
		Mail mail = new Mail();
		mail.setFrom("drpqwer@163.com");
		mail.setRescipent("1057930898@qq.com");
		mail.setSubject("你好，这是一封测试邮件！");
		mail.setText("哈哈哈哈哈");
		Session session = MailUtils.createSession("smtp.163.com", "drpqwer@163.com","d976772537");
		try {
			MailUtils.send(session, mail);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
