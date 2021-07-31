//$Id$
package com.varad.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.varad.beans.ManagerBean;
import com.varad.beans.ProjectBean;
import com.varad.beans.UserListBean;
import com.varad.services.ProjectUtils;
import com.varad.services.UserUtils;

public class AddProjectAction extends ActionSupport implements ModelDriven<ProjectBean>,SessionAware {
	private Map<String, Object> session;
	private ProjectBean projectBean = new ProjectBean();
	private List<ManagerBean> managers = new ArrayList<ManagerBean>();
	
	

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
		boolean isSuccess = ProjectUtils.addProject(projectBean);
		if(isSuccess == true){
			return "success";	
		}
		else {
			return "error";
		}
	}
	
	public String getManagersList() {
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
}
