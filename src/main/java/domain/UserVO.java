package domain;

public class UserVO {
	private String id;
	private String passwd;
	private String userName;
	private String mobile;
	private String email;
	private int studentId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	
	public void printLog() {
		System.out.println("ID : " + getId() + " / PW : " + getPasswd() + " / 이름 : " + getUserName() + " / 전화번호 : " + getMobile() + " / 이메일 : " + getEmail() + " / 학번 : " + getStudentId());
	}
	
	
}
