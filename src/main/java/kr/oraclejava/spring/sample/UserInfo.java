package kr.oraclejava.spring.sample;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserInfo {
	private String userId;
	private String userName;
	private String deptNo;
	
	public UserInfo() {
		
	}
	
	public UserInfo(String userId, String userName, String deptNo) {
		this.userId = userId;
		this.userName = userName;
		this.deptNo = deptNo;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDeptNo() {
		return deptNo;
	}
	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}
	
	
}
