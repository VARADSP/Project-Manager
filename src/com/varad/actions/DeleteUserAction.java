package com.varad.actions;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.varad.services.UserUtils;


public class DeleteUserAction extends ActionSupport implements SessionAware,ServletRequestAware {

	private static final long serialVersionUID = 1L;
	//UserDataBean
	private Map<String, Object> session;

	private HttpServletRequest request = null;

	private String idOfUser = null;

	/*
	 * method execute implemented method for struts action class
	 * return type : String
	 */
	// all struts logic here
	public String execute() {
		
		idOfUser = request.getParameter("id");

		//Deleting new user
		boolean isSuccessfull = false;
		System.out.println("In delete " + idOfUser);


		isSuccessfull = UserUtils.deleteUser(idOfUser);

		if(isSuccessfull == true){
			return "success";
		}
		else{
			addActionError("Delete Unsuccessfull ! Please Try Again !");
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
