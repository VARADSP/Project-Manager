//$Id$
package com.varad.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.varad.beans.UserListBean;
import com.varad.services.UserUtils;

public class ShowUsersAction extends ActionSupport implements ModelDriven<UserListBean>,SessionAware {
	private Map<String, Object> session;
	private UserListBean userListBean = new UserListBean();
	private List<UserListBean> users = new ArrayList<UserListBean>();
	
	
	public UserListBean getUserListBean() {
		return userListBean;
	}


	public void setUserListBean(UserListBean userListBean) {
		this.userListBean = userListBean;
	}


	public List<UserListBean> getUsers() {
		return users;
	}


	public void setUsers(List<UserListBean> users) {
		this.users = users;
	}


	public String execute() {
		users = UserUtils.getAllUsers();
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
}
