package utils.mail;

import java.util.ArrayList;
import java.util.List;

import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class Mail {
	private String from;
	private List<Address> list = new ArrayList<Address>();
	private String subject;
	private String text;
	private List<AttachBean> beanlist = new ArrayList<AttachBean>();
	
	public List<AttachBean> getAttachBean(){
		return beanlist;
	}

	public Mail(String from, String rescipent, String subject,
			String text) {
		super();
		this.from = from;
		this.subject = subject;
		subResipent(rescipent);
		this.text = text;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public List<Address> getList() {
		return list;
	}

	public void setRescipent(String rescipent) {
		subResipent(rescipent);
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Mail() {
		super();
	}
	
	private void subResipent(String res){
		String[] arr = res.split(",");
		
		try {
			for (int i = 0; i < arr.length; i++) {
				list.add(new InternetAddress(arr[i]));
			}
		} catch (AddressException e) {
			throw new RuntimeException("收件人错误！");
		}
	}
	

	public void addAttach(AttachBean bean) {
		beanlist.add(bean);
	}

}
	