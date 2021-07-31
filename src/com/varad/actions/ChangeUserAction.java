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

public class ChangeUserAction extends ActionSupport implements ModelDriven<UserListBean>,SessionAware,ServletRequestAware {
	private Map<String, Object> session;
	private UserListBean userListBean = new UserListBean();
	private HttpServletRequest request = null;
	private String idOfUser = null;

	

	public UserListBean getUserListBean() {
		return userListBean;
	}


	public void setUserListBean(UserListBean userListBean) {
		this.userListBean = userListBean;
	}


	public String execute() {
		return "success";
	}
	
	public String changeUser() {
		boolean isSuccess = UserUtils.changeUser(userListBean);
		if(isSuccess == true){
			return "success";	
		}
		else {
			return "error";
		}
	}
	
	public String getUser() {
		idOfUser = request.getParameter("id");
		userListBean = UserUtils.getUser(idOfUser);
		return "success";
	}
	
	
	
	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> map) {
		this.session = map;
	}


	@Override
	public UserListBean getModel() {
		// TODO Auto-generated method stub
		return userListBean;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

}
