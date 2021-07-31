//$Id$
package com.varad.actions;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.varad.beans.ShowEmployeeBean;
import com.varad.services.UserUtils;

public class ManagerAction extends ActionSupport implements ModelDriven<ShowEmployeeBean>,SessionAware,ServletRequestAware {
	private Map<String, Object> session;
	private HttpServletRequest request = null;
	private ArrayList<ShowEmployeeBean> projectemployeeslist = new ArrayList<ShowEmployeeBean>();
	private ShowEmployeeBean showEmployeeBean = new ShowEmployeeBean();
	
	
	public ArrayList<ShowEmployeeBean> getProjectemployeeslist() {
		return projectemployeeslist;
	}


	public void setProjectemployeeslist(ArrayList<ShowEmployeeBean> projectemployeeslist) {
		this.projectemployeeslist = projectemployeeslist;
	}


	public String execute() {
		projectemployeeslist = UserUtils.getEmployeesOfProject(session.get("username").toString());
		return "success";
	}
	
	
	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> map) {
		this.session = map;
	}
	
	@Override
	public ShowEmployeeBean getModel() {
		// TODO Auto-generated method stub
		return showEmployeeBean;
	}


	@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}


}
