package utils.mail;

import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class MailUtils {
	/*
	 * 通过传入的host , username ,pwd来创建session
	 */
	public static Session  createSession(String host,final String username,final String password){
		/*
		 * host
		 * 认证
		 * username
		 * pasword
		 */
		Properties ps = new Properties();
		ps.setProperty("mail.host", host);
		ps.setProperty("mail.smtp.auth", "true");
		
		//创建认证对象
		Authenticator auth = new Authenticator(){
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		};
		
		return Session.getDefaultInstance(ps,auth);
	}
	
	/*
	 * 发�?邮件�?session  mail
	 */
	public static void send(Session session,Mail mail) throws MessagingException{
		/* transport.send(message);
		 */
		MimeMessage message = new MimeMessage(session);
		List<AttachBean> beanlist = mail.getAttachBean();
		
		try {
			message.setFrom(new InternetAddress(mail.getFrom()));
			message.addRecipients(RecipientType.TO, mail.getList().toArray(new Address[mail.getList().size()]));
			message.setSubject(mail.getSubject());
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		/////////////////////////////向message里添加附�?
		MimeMultipart part = new MimeMultipart();
		try {
			MimeBodyPart ma = new MimeBodyPart();
			ma.setContent(mail.getText(),"text/html;charset=utf-8");
			part.addBodyPart(ma);
		} catch (MessagingException e1) {
			
		}
		
		for (AttachBean attach : beanlist) {
			MimeBodyPart mt = new MimeBodyPart();
			try {
				mt.attachFile(attach.getFile());
				mt.setFileName(MimeUtility.encodeText(attach.getAttchName()));
				part.addBodyPart(mt);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
		try {
			message.setContent(part);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		//////////////////////////////
			Transport.send(message);
	}
}
