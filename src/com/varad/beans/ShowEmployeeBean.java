//$Id$
package com.varad.beans;

import java.util.ArrayList;

public class ShowEmployeeBean {
	
	private String userid;
	private String username;
	private String name;
	private String category;
	private String sex;
	private String address;
	private String emailid;
	private String isdisabled;
	private String isallocated;
	private String project;
	
	
	
	
	public ShowEmployeeBean() {
		super();
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
	public String getIsdisabled() {
		return isdisabled;
	}
	public void setIsdisabled(String isdisabled) {
		this.isdisabled = isdisabled;
	}
	public String getIsallocated() {
		return isallocated;
	}
	public void setIsallocated(String isallocated) {
		isallocated = isallocated.equals("1")?"Yes":"No";
		this.isallocated = isallocated;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		project = project==null?"Unallocated":project;
		this.project = project;
	}
	@Override
	public String toString() {
		return "ShowEmployeeBean [userid=" + userid + ", name=" + name + ", category=" + category + ", sex=" + sex + ", address=" + address + ", emailid=" + emailid + ", isdisabled=" + isdisabled + ", isallocated=" + isallocated + ", project=" + project + "]";
	}
	
	
	
		
	
}
