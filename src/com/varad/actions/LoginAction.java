//$Id$
package com.varad.actions;
 
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.varad.beans.LoginBean;
import com.varad.services.LoginService;
import com.varad.services.UserUtils;

public class LoginAction extends ActionSupport implements ModelDriven<LoginBean>,SessionAware {
	
	private Map<String, Object> session;
	private LoginBean loginBean = new LoginBean();
	
	
	public LoginBean getLoginBean() {
		return loginBean;
	}


	@Override
	public void validate() {
		if(session.get("username")==null) {
		if(loginBean.getUsername() == null) {
			addActionError("Username is empty !");
		}
		if(loginBean.getPassword() == null) {
			addActionError("Password is empty !");
		}
		if(LoginService.checkIfUserExists(loginBean) == false) {
			addActionError("User does not exists !");
		}
		String loginStatus = LoginService.login(loginBean);
		if(loginStatus.equals("invalid")) {
			addActionError("Invalid Credentials!");
		}
		if(loginStatus.equals("invalidPassword")) {
			addActionError("Password is incorrect!");
		}
		if(loginStatus.equals("exception")) {
			addActionError("Error has occurred ,Please try again!");
		}
		if(loginStatus.equals("disabledUser")) {
			addActionError("You are disabled !");
		}
		if(loginStatus.equals("valid")) {
			addActionMessage(loginBean.getUsername());
		}
		}
	}


	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}


	public String execute() {
		String category = "";
		if(session.get("username")==null) {
			 category = UserUtils.getUserType(loginBean.getUsername());	
		}
		else {
			 category = UserUtils.getUserType(session.get("username").toString());	
		}
		
		if(session.get("username")==null) {
			session.put("username", loginBean.getUsername());
			session.put("usertype", category);			
		}
		
		if(category.equals("admin")) {
			return "admin";
		}
		if(category.equals("manager")) {
			return "manager";
		}
		if(category.equals("employee")) {
			return "employee";
		}
		
		return "error";
	}
	
	
	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> map) {
		this.session = map;
	}


	@Override
	public LoginBean getModel() {
		return loginBean;
	}


}
