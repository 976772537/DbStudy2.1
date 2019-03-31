package cn.drp.user.domain;

import cn.drp.Comment.domain.Comment;

import java.io.Serializable;

public class User implements Serializable{
	private String uid;
	private String username;
	private String password;
	private String email;
	private String name;
	private String sex;
	private String telephone;
	private String age;
	private String describle;
	private String type;
	private String head;
	private Comment comment;
	private String time;
	private int active;

	public String getTime() {
		return time;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}



	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "User{" +
				"uid='" + uid + '\'' +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", email='" + email + '\'' +
				", name='" + name + '\'' +
				", sex='" + sex + '\'' +
				", telephone='" + telephone + '\'' +
				", age='" + age + '\'' +
				", describle='" + describle + '\'' +
				'}';
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getDescrible() {
		return describle;
	}

	public void setDescrible(String describle) {
		this.describle = describle;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String uid, String username, String password, String email) {
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.email = email;
	}
}
