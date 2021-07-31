package com.varad.actions;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.varad.services.UserUtils;


public class EnableDisableUserAction extends ActionSupport implements SessionAware,ServletRequestAware {

	private static final long serialVersionUID = 1L;
	//UserDataBean
	// array of users data
	private Map<String, Object> session;

	private HttpServletRequest request = null;

	private String idOfUser = null;
	/*
	 * method getUsers returns array of user list
	 * return type : ArrayList
	 */

	/*
	 * method execute implemented method for struts action class
	 * return type : String
	 */
	// all struts logic here
	public String execute() {
		return "success";
	}


	public String enable(){

		idOfUser = request.getParameter("id");

		System.out.println("Enabling " + idOfUser);
		
		
		boolean isSuccessfull = false;

		isSuccessfull = UserUtils.enableUser(idOfUser);

		if(isSuccessfull == true){
			return "success";
		}
		else{
			addActionError("Enable User Operation Unsuccessfull ! Please Try Again !");
			return "error";
		}
	}
	
	public String disable(){

		idOfUser = request.getParameter("id");

		System.out.println("Disabling " + idOfUser);
		
		boolean isSuccessfull = false;

		isSuccessfull = UserUtils.disableUser(idOfUser);

		if(isSuccessfull == true){
			return "success";
		}
		else{
			addActionError("Disable User Operation Unsuccessfull ! Please Try Again !");
			return "error";
		}
	}



	@Override
	public void setSession(Map<String, Object> session) {
		 this.session = session;
	}


	@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}


}
