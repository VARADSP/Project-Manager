//$Id$
package com.varad.services;

import com.varad.beans.LoginBean;
import com.varad.db.CommonLogic;

public class LoginService {
	
	public static String login(LoginBean loginBean) {
		String loginStatus = CommonLogic.login(loginBean.getUsername(), loginBean.getPassword());
		if(loginStatus.equals("authenticated")) {
			return "valid";
		}
		if(loginStatus.equals("notAuthenticated")) {
			return "invalid";
		}
		if(loginStatus.equals("passwordIncorrect")) {
			return "invalidPassword";
		}
		if(loginStatus.equals("disabled")) {
			return "disabledUser";
		}
		if(loginStatus.equals("exception")) {
			return "exception";
		}
		return "invalid";
		
	}
	
	public static boolean checkIfUserExists(LoginBean loginBean) {
		if(CommonLogic.fetchData(loginBean.getUsername()) != null) {
			return true;
		}
		return false;
	}

}
