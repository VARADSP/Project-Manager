//$Id$
package com.varad.beans;

public class UserListBean {
	private String userid;
	private String username;
	private String password;
	private String name;
	private String category;
	private String sex;
	private String address;
	private String emailid;
	private String isDisabled;
	
	
	public UserListBean() {
		super();
	}
	
	public String getIsDisabled() {
		return isDisabled;
	}

	public void setIsDisabled(String isDisabled) {
		this.isDisabled = isDisabled;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	@Override
	public String toString() {
		return "UserListBean [username=" + username + ", password=" + password + ", name=" + name + ", category=" + category + ", sex=" + sex + ", address=" + address + ", emailid=" + emailid + "]";
	}
	
	
	
}

