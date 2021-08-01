//$Id$
package com.varad.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.varad.beans.ManagerBean;
import com.varad.beans.ProjectBean;
import com.varad.beans.ShowProjectBean;
import com.varad.beans.UserListBean;
import com.varad.db.CommonLogic;

public class ProjectUtils {
	
	public static ArrayList<ManagerBean> getManagersList(){
		ArrayList<ManagerBean> managersList;
		managersList = CommonLogic.fetchAllManagers();
		return managersList;
	}
	
	public static boolean addProject(ProjectBean projectBean) {
		int isSuccess = CommonLogic.addProject(projectBean);
		if(isSuccess > 0) {
			return true;
		}
		return false;
	}
	
	public static boolean changeProject(ProjectBean projectBean) {
		int isSuccess = CommonLogic.changeProject(projectBean);
		if(isSuccess > 0) {
			return true;
		}
		return false;
	}
	
	public static ArrayList<ProjectBean> getProjectList(){
		ArrayList<ProjectBean> projectList;
		projectList = CommonLogic.fetchAllProjects();
		return projectList;
	}
	
	public static ArrayList<ProjectBean> getProjectAvailableList(String username){
		ArrayList<ProjectBean> projectList;
		projectList = CommonLogic.fetchAllAvailableProjects(username);
		return projectList;
	}
	
	public static boolean deleteProject(String projectid) {
		
		Integer isSuccessful = CommonLogic.deleteProject(projectid);
		
		if(isSuccessful > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static ProjectBean getProject(String projectid){
		ProjectBean project;
		project = CommonLogic.getProject(projectid);
		return project;
	}
	
	public static ShowProjectBean getMyProject(String username){
		ShowProjectBean project;
		project = CommonLogic.getMyProject(username);
		return project;
	}
	
	public static boolean deallocateMe(String username,String managerid){
		Integer isSuccess;
		isSuccess = CommonLogic.deallocateMe(username,managerid);
		if(isSuccess>0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
}
