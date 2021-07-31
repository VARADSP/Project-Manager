//$Id$
package com.varad.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.varad.beans.ShowEmployeeBean;
import com.varad.beans.UserListBean;
import com.varad.db.CommonLogic;

public class UserUtils {

	public static String getUserType(String username) {
		String category = CommonLogic.getUserType(username);
		return category;

	}

	public static ArrayList<UserListBean> getAllUsers() {
		return CommonLogic.fetchAllUsers();
	}
	
	public static UserListBean getUser(String userid) {
		return CommonLogic.getUser(userid);
	}

	public static boolean deleteUser(String userid) {
		Integer isSuccessful = CommonLogic.deleteUser(userid);
		if(isSuccessful > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public static boolean disableUser(String userid) {
		Integer isSuccessful = CommonLogic.disableUser(userid);
		if(isSuccessful > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public static boolean enableUser(String userid) {
		Integer isSuccessful = CommonLogic.enableUser(userid);
		if(isSuccessful > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean changeUser(UserListBean userListBean) {
		Integer isSuccessful = CommonLogic.changeUser(userListBean);
		if(isSuccessful > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	public static ArrayList<ShowEmployeeBean> getEmployees(){
		ArrayList<ShowEmployeeBean> employees = new ArrayList<ShowEmployeeBean>();
		employees = CommonLogic.getEmployees();
		return employees;
	}
	
	public static ArrayList<ShowEmployeeBean> getEmployeesOfProject(String managerid){
		ArrayList<ShowEmployeeBean> employees = new ArrayList<ShowEmployeeBean>();
		employees = CommonLogic.getEmployeesOfProject(managerid);
		return employees;
	}
	
	public static boolean allocateEmployee(String userid,String managerusername) {
		Integer isSuccessful = CommonLogic.allocateEmployee(userid,managerusername);
		if(isSuccessful > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean deallocateEmployee(String userid,String managerusername) {
		Integer isSuccessful = CommonLogic.deallocateEmployee(userid,managerusername);
		if(isSuccessful > 0) {
			return true;
		}
		else {
			return false;
		}
	}
}
