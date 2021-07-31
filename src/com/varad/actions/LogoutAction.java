//$Id$
package com.varad.actions;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport implements SessionAware {
	private Map<String, Object> session;

	
	
	public String execute() {
		session.remove("username");
		session.clear();
		HttpServletRequest request=ServletActionContext.getRequest();  
		HttpSession httpSession=request.getSession();
		httpSession.invalidate();
		return "success";
	}
	
	
	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> map) {
		this.session = map;
	}
}
