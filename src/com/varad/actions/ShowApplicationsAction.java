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
import com.varad.beans.ShowApplicationBean;
import com.varad.beans.ShowEmployeeBean;
import com.varad.beans.ShowProjectBean;
import com.varad.beans.UserListBean;
import com.varad.services.ProjectUtils;
import com.varad.services.UserUtils;

public class ShowApplicationsAction extends ActionSupport implements ModelDriven<ShowApplicationBean>,SessionAware,ServletRequestAware {
	private Map<String, Object> session;
	private HttpServletRequest request = null;
	private ArrayList<ShowApplicationBean> applicationslist = new ArrayList<ShowApplicationBean>();
	private ShowApplicationBean showApplicationBean = new ShowApplicationBean();
	

	

	public ArrayList<ShowApplicationBean> getApplicationslist() {
		return applicationslist;
	}


	public void setApplicationslist(ArrayList<ShowApplicationBean> applicationslist) {
		this.applicationslist = applicationslist;
	}


	public String execute() {	
		applicationslist = UserUtils.getApplicationsList(session.get("username").toString());
		return "success";
	}
	
	public String deleteApplication() {
		boolean isSuccess = false;
		String applicationId = request.getParameter("applicationid");
		isSuccess = UserUtils.deleteApplication(applicationId);
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
	public ShowApplicationBean getModel() {
		// TODO Auto-generated method stub
		return showApplicationBean;
	}

}
