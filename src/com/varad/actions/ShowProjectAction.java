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
import com.varad.beans.ShowProjectBean;
import com.varad.beans.UserListBean;
import com.varad.services.ProjectUtils;
import com.varad.services.UserUtils;

public class ShowProjectAction extends ActionSupport implements ModelDriven<ShowProjectBean>,SessionAware,ServletRequestAware {
	private Map<String, Object> session;
	private ShowProjectBean projectBean = new ShowProjectBean();
	private HttpServletRequest request = null;
	private String usernameOfUser = null;

	

	public ShowProjectBean getProjectBean() {
		return projectBean;
	}


	public void setProjectBean(ShowProjectBean projectBean) {
		this.projectBean = projectBean;
	}


	public String execute() {
		
		return "success";
	}
	
	public String getMyProject() {
		usernameOfUser = request.getParameter("username");
		projectBean = ProjectUtils.getMyProject(usernameOfUser);
		return "success";
	}
	
	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> map) {
		this.session = map;
	}


	@Override
	public ShowProjectBean getModel() {
		// TODO Auto-generated method stub
		return projectBean;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

}
