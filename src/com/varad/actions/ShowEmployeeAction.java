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
import com.varad.beans.ShowEmployeeBean;
import com.varad.beans.ShowProjectBean;
import com.varad.beans.UserListBean;
import com.varad.services.ProjectUtils;
import com.varad.services.UserUtils;

public class ShowEmployeeAction extends ActionSupport implements ModelDriven<ShowEmployeeBean>,SessionAware,ServletRequestAware {
	private Map<String, Object> session;
	private HttpServletRequest request = null;
	private ArrayList<ShowEmployeeBean> employeeslist = new ArrayList<ShowEmployeeBean>();
	private ShowEmployeeBean showEmployeeBean = new ShowEmployeeBean();
	

	public ArrayList<ShowEmployeeBean> getEmployeeslist() {
		return employeeslist;
	}


	public void setEmployeeslist(ArrayList<ShowEmployeeBean> employeeslist) {
		this.employeeslist = employeeslist;
	}


	public String execute() {	
		employeeslist = UserUtils.getEmployees();
		return "success";
	}
	
	
	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> map) {
		this.session = map;
	}

	
	@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}


	@Override
	public ShowEmployeeBean getModel() {
		// TODO Auto-generated method stub
		return showEmployeeBean;
	}

}
