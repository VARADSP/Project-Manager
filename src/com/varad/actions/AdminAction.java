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

public class AdminAction extends ActionSupport implements ModelDriven<ProjectBean>,SessionAware,ServletRequestAware {
	private Map<String, Object> session;
	private ProjectBean projectBean = new ProjectBean();
	private ArrayList<ProjectBean> projectlist = new ArrayList<ProjectBean>();
	private HttpServletRequest request = null;

	private String idOfProject = null;

	public ProjectBean getProjectBean() {
		return projectBean;
	}


	public void setProjectBean(ProjectBean projectBean) {
		this.projectBean = projectBean;
	}

	
	
	public ArrayList<ProjectBean> getProjectlist() {
		return projectlist;
	}


	public void setProjectlist(ArrayList<ProjectBean> projectlist) {
		this.projectlist = projectlist;
	}


	public String execute() {
		projectlist = ProjectUtils.getProjectList();
		return "success";
	}
	
	public String deleteProject() {
		idOfProject = request.getParameter("projectid");
		boolean isSuccessful = ProjectUtils.deleteProject(idOfProject);
		if(isSuccessful == true) {
			return "success";
		}
		else {
			return "error";
		}
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
