//$Id$
package com.varad.services;

import com.varad.beans.LoginBean;
import com.varad.beans.UserListBean;
import com.varad.db.CommonLogic;

public class SignUpService {
	
	public static boolean register(UserListBean userListBean) {
		Integer result = CommonLogic.addUser(userListBean);	
		if(result == 1) {
			return true;
		}
		else {
			return false;
		}
	}

}
