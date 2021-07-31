//$Id$
package com.varad.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.varad.beans.ManagerBean;
import com.varad.beans.ProjectBean;
import com.varad.beans.UserListBean;
import com.varad.services.ProjectUtils;
import com.varad.services.UserUtils;

public class ChangeProjectAction extends ActionSupport implements ModelDriven<ProjectBean>,SessionAware,ServletRequestAware {
	private Map<String, Object> session;
	private ProjectBean projectBean = new ProjectBean();
	private List<ManagerBean> managers = new ArrayList<ManagerBean>();
	private HttpServletRequest request = null;
	private String idOfProject = null;

	

	public ProjectBean getProjectBean() {
		return projectBean;
	}


	public void setProjectBean(ProjectBean projectBean) {
		this.projectBean = projectBean;
	}


	public List<ManagerBean> getManagers() {
		return managers;
	}


	public void setManagers(List<ManagerBean> managers) {
		this.managers = managers;
	}


	public String execute() {
		boolean isSuccess = ProjectUtils.changeProject(projectBean);
		if(isSuccess == true){
			return "success";	
		}
		else {
			return "error";
		}
	}
	
	public String changeProject() {
		boolean isSuccess = ProjectUtils.changeProject(projectBean);
		if(isSuccess == true){
			return "success";	
		}
		else {
			return "error";
		}
	}
	
	public String getProject() {
		idOfProject = request.getParameter("projectid");
		projectBean = ProjectUtils.getProject(idOfProject);
		managers = ProjectUtils.getManagersList();
		return "success";
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
