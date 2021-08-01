//$Id$
package com.varad.actions;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.varad.beans.ProjectBean;
import com.varad.services.ProjectUtils;
import com.varad.services.UserUtils;

public class EmployeeAction extends ActionSupport implements ModelDriven<ProjectBean>,SessionAware,ServletRequestAware {
	private Map<String, Object> session;
	private ProjectBean projectBean = new ProjectBean();
	private ArrayList<ProjectBean> projectlist = new ArrayList<ProjectBean>();
	private HttpServletRequest request = null;
	private boolean isAllocated;

	
	public String execute() {
		projectlist = ProjectUtils.getProjectAvailableList(session.get("username").toString());
		isAllocated = UserUtils.isAllocated(session.get("username").toString());
		session.put("isAllocated", isAllocated);
		return "success";
	}
	
	
	public ProjectBean getProjectBean() {
		return projectBean;
	}

	

	public void setProjectBean(ProjectBean projectBean) {
		this.projectBean = projectBean;
	}

	

	public boolean isAllocated() {
		return isAllocated;
	}


	public void setAllocated(boolean isAllocated) {
		this.isAllocated = isAllocated;
	}


	public ArrayList<ProjectBean> getProjectlist() {
		return projectlist;
	}



	public void setProjectlist(ArrayList<ProjectBean> projectlist) {
		this.projectlist = projectlist;
	}



	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> map) {
		this.session = map;
	}
	
	@Override
	public ProjectBean getModel() {
		// TODO Auto-generated method stub
		return projectBean;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}
}
