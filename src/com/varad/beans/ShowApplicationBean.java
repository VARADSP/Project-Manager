//$Id$
package com.varad.beans;

import java.util.ArrayList;

public class ShowApplicationBean {
	
	private String id;
	private String userid;
	private String projectid;
	private String managerid;
	private String isapproved;
	private String request;
	private String requesttype;
	
	
	
	
	
	public ShowApplicationBean() {
		super();
	}





	public String getId() {
		return id;
	}





	public void setId(String id) {
		this.id = id;
	}





	public String getUserid() {
		return userid;
	}





	public void setUserid(String userid) {
		this.userid = userid;
	}





	public String getProjectid() {
		return projectid;
	}





	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}





	public String getManagerid() {
		return managerid;
	}





	public void setManagerid(String managerid) {
		this.managerid = managerid;
	}





	public String getIsapproved() {
		return isapproved;
	}





	public void setIsapproved(String isapproved) {
		this.isapproved = isapproved;
	}





	public String getRequest() {
		return request;
	}





	public void setRequest(String request) {
		this.request = request;
	}





	public String getRequesttype() {
		return requesttype;
	}





	public void setRequesttype(String requesttype) {
		this.requesttype = requesttype;
	}





	@Override
	public String toString() {
		return "ShowApplicationBean [id=" + id + ", userid=" + userid + ", projectid=" + projectid + ", managerid=" + managerid + ", isapproved=" + isapproved + ", request=" + request + ", requesttype=" + requesttype + "]";
	}
	
	
	
	
		
	
}
