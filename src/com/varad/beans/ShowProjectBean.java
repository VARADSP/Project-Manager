//$Id$
package com.varad.beans;

import java.util.ArrayList;

public class ShowProjectBean {
	
	private String projectid;
	private String projectname;
	private String projectlocation;
	private String managerid;
	private String managername;
	private int teamsize;
	private ArrayList<String> team = new ArrayList<String>();
	
	
	
	
	public ShowProjectBean() {
		super();
	}
	
	public String getProjectid() {
		return projectid;
	}
	
	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}
	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	public String getProjectlocation() {
		return projectlocation;
	}
	public void setProjectlocation(String projectlocation) {
		this.projectlocation = projectlocation;
	}
	public String getManagerid() {
		return managerid;
	}
	public void setManagerid(String managerid) {
		this.managerid = managerid;
	}
	public String getManagername() {
		return managername;
	}
	public void setManagername(String managername) {
		this.managername = managername;
	}
	public int getTeamsize() {
		return teamsize;
	}
	public void setTeamsize(int teamsize) {
		this.teamsize = teamsize;
	}
	
	
	public ArrayList<String> getTeam() {
		return team;
	}

	public void setTeam(ArrayList<String> team) {
		this.team = team;
	}

	@Override
	public String toString() {
		return "ProjectBean [projectid=" + projectid + ", projectname=" + projectname + ", projectlocation=" + projectlocation + ", managerid=" + managerid + ", managername=" + managername + ", teamsize=" + teamsize + "]";
	}
	
	
		
	
}
