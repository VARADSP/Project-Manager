//$Id$
package com.varad.actions;
 
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.varad.beans.LoginBean;
import com.varad.beans.UserListBean;
import com.varad.services.LoginService;
import com.varad.services.SignUpService;

public class RegisterAction extends ActionSupport implements ModelDriven<UserListBean>,SessionAware {
	
	private Map<String, Object> session;
	private UserListBean userListBean = new UserListBean();
	
	


	public UserListBean getUserListBean() {
		return userListBean;
	}



	public void setUserListBean(UserListBean userListBean) {
		this.userListBean = userListBean;
	}



	@Override
	public void validate() {
		if(userListBean.getUsername() == null) {
			addActionError("Username is empty !");
		}
		if(userListBean.getPassword() == null) {
			addActionError("Password is empty !");
		}
		if(userListBean.getName() == null) {
			addActionError("Name is empty !");
		}
		if(userListBean.getCategory() == null) {
			addActionError("Category is empty !");
		}
		if(userListBean.getSex() == null) {
			addActionError("Gender is empty !");
		}
		if(userListBean.getAddress() == null) {
			addActionError("Address is empty !");
		}
		if(userListBean.getEmailid() == null) {
			addActionError("EmailId is empty !");
		}
	}



	public String execute() {
		if(SignUpService.register(userListBean)) {
			addActionMessage("Registration is successful !");
			return "success";			
		}
		else {
			addActionError("Something went wrong ! Please try again :)");
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
	public UserListBean getModel() {
		return userListBean;
	}


}
