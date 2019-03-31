package utils.mail;

import java.io.File;

public class AttachBean {
	private String attchName;
	private File file;
	@Override
	public String toString() {
		return "AttachBean [attchName=" + attchName + ", file=" + file + "]";
	}
	public AttachBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getAttchName() {
		return attchName;
	}
	public void setAttchName(String attchName) {
		this.attchName = attchName;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public AttachBean(File file,String fileName) {
		super();
		this.attchName = fileName;
		this.file = file;
	}

}
