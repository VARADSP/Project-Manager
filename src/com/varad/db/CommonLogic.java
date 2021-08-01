/**
 *
 */
package com.varad.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.varad.beans.ManagerBean;
import com.varad.beans.ProjectBean;
import com.varad.beans.ShowApplicationBean;
import com.varad.beans.ShowEmployeeBean;
import com.varad.beans.ShowProjectBean;
import com.varad.beans.UserListBean;
import com.varad.db.DbLogic;

/**
 * @author: Varad Paralikar Created Date:29/08/2019 Assignment: Day 2 Task: Struts And Hibernate Skillup
 *
 */

/*
 * Class CommonLogic is used to implement common logic
 * 
 * @author: Varad Parlikar Created Date: 2019/08/29
 */
public class CommonLogic {
	static Connection connection;

	/*
	 * method getRowCount returns total number of rows in a table. return type : int
	 */
	public static int getRowCount(ResultSet resultSet) {
		int size = 0;
		// calculating total resultset size
		try {
			resultSet.last();
			size = resultSet.getRow();
			resultSet.beforeFirst();

		} catch (Exception ex) {

		}
		return size;
	}

	public static ArrayList<UserListBean> fetchAllUsers() {
		connection = DbLogic.connect();
		// Query fire for insertion operation with column name and values
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		ArrayList<UserListBean> users = new ArrayList<>();
		try {

			// admin and normal user query detects if admin returns all rows
			preparedStatement = connection.prepareStatement("SELECT ul.userid,u.username,u.password,ul.name,ul.category,ul.sex,ul.address,ul.emailid,ul.isdisabled FROM project_userlist as ul INNER JOIN project_users as u ON ul.userid = u.userid");

			// preparedStatement.setString(2, password.trim());
			// executing the query for prapared statment
			resultSet = preparedStatement.executeQuery();

			if (getRowCount(resultSet) == 0) {
				return null;
			}
			while(resultSet.next()) {
				UserListBean userListBean = new UserListBean();
				userListBean.setUserid(resultSet.getString(1));
				userListBean.setUsername(resultSet.getString(2));
				userListBean.setPassword(resultSet.getString(3));
				userListBean.setName(resultSet.getString(4));
				userListBean.setCategory(resultSet.getString(5));
				userListBean.setSex(resultSet.getString(6));
				userListBean.setAddress(resultSet.getString(7));
				userListBean.setEmailid(resultSet.getString(8));
				userListBean.setIsDisabled(resultSet.getString(9));
				
				users.add(userListBean);
			}

			// disconnecting the database
			 DbLogic.disconnect();
			 connection.close();

			return users;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	
	public static ArrayList<ShowEmployeeBean> getEmployees() {
		connection = DbLogic.connect();
		// Query fire for insertion operation with column name and values
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		ArrayList<ShowEmployeeBean> employees = new ArrayList<ShowEmployeeBean>();
		try {

			// admin and normal user query detects if admin returns all rows
			preparedStatement = connection.prepareStatement("SELECT ul.userid,(SELECT username FROM project_users WHERE userid=ul.userid),ul.name,ul.category,ul.sex,ul.address,ul.emailid,ul.isdisabled,EXISTS(SELECT * FROM project_projectrelations where userid=ul.userid) as isallocated,(SELECT projectname FROM project_projects WHERE projectid=(SELECT projectid FROM project_projectrelations WHERE userid=ul.userid)) as project FROM project_userlist as ul WHERE ul.category='employee'");

			// preparedStatement.setString(2, password.trim());
			// executing the query for prapared statment
			resultSet = preparedStatement.executeQuery();

			if (getRowCount(resultSet) == 0) {
				return null;
			}
			while(resultSet.next()) {
				ShowEmployeeBean userListBean = new ShowEmployeeBean();
				userListBean.setUserid(resultSet.getString(1));
				userListBean.setUsername(resultSet.getString(2));
				userListBean.setName(resultSet.getString(3));
				userListBean.setCategory(resultSet.getString(4));
				userListBean.setSex(resultSet.getString(5));
				userListBean.setAddress(resultSet.getString(6));
				userListBean.setEmailid(resultSet.getString(7));
				userListBean.setIsdisabled(resultSet.getString(8));	
				userListBean.setIsallocated(resultSet.getString(9));
				userListBean.setProject(resultSet.getString(10));
				employees.add(userListBean);
			}

			// disconnecting the database
			 DbLogic.disconnect();
			 connection.close();

			return employees;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	
	public static ArrayList<ShowEmployeeBean> getEmployeesOfProject(String managerusername) {
		connection = DbLogic.connect();
		// Query fire for insertion operation with column name and values
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		ArrayList<ShowEmployeeBean> employees = new ArrayList<ShowEmployeeBean>();
		try {

			// admin and normal user query detects if admin returns all rows
			preparedStatement = connection.prepareStatement("SELECT ul.userid,(SELECT username FROM project_users WHERE userid=ul.userid),ul.name,ul.category,ul.sex,ul.address,ul.emailid,ul.isdisabled,EXISTS(SELECT * FROM project_projectrelations where userid=ul.userid) as isallocated,(SELECT projectname FROM project_projects WHERE projectid=(SELECT projectid FROM project_projectrelations WHERE userid=ul.userid)) as project FROM project_userlist as ul WHERE (SELECT projectname FROM project_projects WHERE projectid=(SELECT projectid FROM project_projectrelations WHERE userid=ul.userid))=(SELECT projectname FROM project_projects WHERE managerid=(SELECT userid FROM project_users WHERE username=?))");
			preparedStatement.setString(1, managerusername);
			// preparedStatement.setString(2, password.trim());
			// executing the query for prapared statment
			resultSet = preparedStatement.executeQuery();

			if (getRowCount(resultSet) == 0) {
				return null;
			}
			while(resultSet.next()) {
				ShowEmployeeBean userListBean = new ShowEmployeeBean();
				userListBean.setUserid(resultSet.getString(1));
				userListBean.setUsername(resultSet.getString(2));
				userListBean.setName(resultSet.getString(3));
				userListBean.setCategory(resultSet.getString(4));
				userListBean.setSex(resultSet.getString(5));
				userListBean.setAddress(resultSet.getString(6));
				userListBean.setEmailid(resultSet.getString(7));
				userListBean.setIsdisabled(resultSet.getString(8));	
				userListBean.setIsallocated(resultSet.getString(9));
				userListBean.setProject(resultSet.getString(10));
				employees.add(userListBean);
			}

			// disconnecting the database
			 DbLogic.disconnect();
			 connection.close();

			return employees;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	public static ArrayList<ShowApplicationBean> getApplicationsList(String managerusername) {
		connection = DbLogic.connect();
		// Query fire for insertion operation with column name and values
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		ArrayList<ShowApplicationBean> applications = new ArrayList<ShowApplicationBean>();
		try {

			// admin and normal user query detects if admin returns all rows
			preparedStatement = connection.prepareStatement("SELECT * from project_application WHERE managerid=(SELECT userid FROM project_users WHERE username=?)");
			preparedStatement.setString(1, managerusername);
			// preparedStatement.setString(2, password.trim());
			// executing the query for prapared statment
			resultSet = preparedStatement.executeQuery();

			if (getRowCount(resultSet) == 0) {
				return null;
			}
			while(resultSet.next()) {
				ShowApplicationBean applicationBean = new ShowApplicationBean();
				applicationBean.setId(resultSet.getString(1));
				applicationBean.setUserid(resultSet.getString(2));
				applicationBean.setProjectid(resultSet.getString(3));
				applicationBean.setManagerid(resultSet.getString(4));
				applicationBean.setIsapproved(resultSet.getString(5));
				applicationBean.setRequest(resultSet.getString(6));
				applicationBean.setRequesttype(resultSet.getString(7));
				applications.add(applicationBean);
			}

			// disconnecting the database
			 DbLogic.disconnect();
			 connection.close();

			return applications;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	
	public static Integer getApplicationsCount(String managerusername) {
		connection = DbLogic.connect();
		// Query fire for insertion operation with column name and values
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		Integer count = 0;
		try {

			// admin and normal user query detects if admin returns all rows
			preparedStatement = connection.prepareStatement("SELECT * FROM project_application WHERE managerid=(SELECT userid FROM project_users WHERE username=?)");
			preparedStatement.setString(1, managerusername);
			// preparedStatement.setString(2, password.trim());
			// executing the query for prapared statment
			resultSet = preparedStatement.executeQuery();
			
			count = getRowCount(resultSet);
			// disconnecting the database
			 DbLogic.disconnect();
			 connection.close();

			return count;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	public static UserListBean getUser(String userid) {
		connection = DbLogic.connect();
		// Query fire for insertion operation with column name and values
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		UserListBean userListBean = new UserListBean();
		
		try {

			// admin and normal user query detects if admin returns all rows
			preparedStatement = connection.prepareStatement("SELECT ul.userid,u.username,u.password,ul.name,ul.category,ul.sex,ul.address,ul.emailid,ul.isdisabled FROM project_userlist as ul INNER JOIN project_users as u ON ul.userid = u.userid WHERE ul.userid=?");
			preparedStatement.setString(1, userid.trim());
			
			// preparedStatement.setString(2, password.trim());
			// executing the query for prapared statment
			resultSet = preparedStatement.executeQuery();

			if (getRowCount(resultSet) == 0) {
				return null;
			}
			while(resultSet.next()) {
				userListBean.setUserid(resultSet.getString(1));
				userListBean.setUsername(resultSet.getString(2));
				userListBean.setPassword(resultSet.getString(3));
				userListBean.setName(resultSet.getString(4));
				userListBean.setCategory(resultSet.getString(5));
				userListBean.setSex(resultSet.getString(6));
				userListBean.setAddress(resultSet.getString(7));
				userListBean.setEmailid(resultSet.getString(8));
				userListBean.setIsDisabled(resultSet.getString(9));
			}

			// disconnecting the database
			 DbLogic.disconnect();
			 connection.close();

			return userListBean;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	public static ArrayList<ManagerBean> fetchAllManagers() {
		connection = DbLogic.connect();
		// Query fire for insertion operation with column name and values
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		ArrayList<ManagerBean> managers = new ArrayList<>();
		try {

			// admin and normal user query detects if admin returns all rows
			preparedStatement = connection.prepareStatement("SELECT ul.userid,ul.name FROM project_userlist as ul WHERE ul.category='manager' AND ul.userid NOT IN (SELECT userid FROM project_projectrelations)");

			// preparedStatement.setString(2, password.trim());
			// executing the query for prapared statment
			resultSet = preparedStatement.executeQuery();

			if (getRowCount(resultSet) == 0) {
				return null;
			}
			while(resultSet.next()) {
				ManagerBean managerBean = new ManagerBean();
				managerBean.setManagerid(resultSet.getString(1));
				managerBean.setManagername(resultSet.getString(2));
				managers.add(managerBean);
			}

			// disconnecting the database
			 DbLogic.disconnect();
			 connection.close();

			return managers;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	
	public static ArrayList<ProjectBean> fetchAllProjects() {
		connection = DbLogic.connect();
		// Query fire for insertion operation with column name and values
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		ArrayList<ProjectBean> projects = new ArrayList<ProjectBean>();
		try {

			// admin and normal user query detects if admin returns all rows
			preparedStatement = connection.prepareStatement("SELECT p.projectid,p.projectname,p.projectlocation,p.managerid,(SELECT name FROM project_userlist where userid=p.managerid),(SELECT COUNT(*) FROM project_projectrelations WHERE projectid=p.projectid) FROM project_projects as p");

			// preparedStatement.setString(2, password.trim());
			// executing the query for prapared statment
			resultSet = preparedStatement.executeQuery();

			if (getRowCount(resultSet) == 0) {
				return null;
			}
			while(resultSet.next()) {
				ProjectBean projectBean = new ProjectBean();
				projectBean.setProjectid(resultSet.getString(1));
				projectBean.setProjectname(resultSet.getString(2));
				projectBean.setProjectlocation(resultSet.getString(3));
				projectBean.setManagerid(resultSet.getString(4));
				projectBean.setManagername(resultSet.getString(5));
				projectBean.setTeamsize(Integer.parseInt(resultSet.getString(6)));
				
				projects.add(projectBean);
			}

			// disconnecting the database
			 DbLogic.disconnect();
			 connection.close();

			return projects;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	
	public static ArrayList<ProjectBean> fetchAllAvailableProjects(String username) {
		connection = DbLogic.connect();
		// Query fire for insertion operation with column name and values
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		ArrayList<ProjectBean> projects = new ArrayList<ProjectBean>();
		try {

			// admin and normal user query detects if admin returns all rows
			preparedStatement = connection.prepareStatement("SELECT p.projectid,p.projectname,p.projectlocation,p.managerid,(SELECT name FROM project_userlist where userid=p.managerid),(SELECT COUNT(*) FROM project_projectrelations WHERE projectid=p.projectid) FROM project_projects as p WHERE p.projectid NOT IN (SELECT projectid FROM project_projectrelations WHERE userid=(SELECT userid FROM project_users WHERE username=?))");
			preparedStatement.setString(1, username);
			// preparedStatement.setString(2, password.trim());
			// executing the query for prapared statment
			resultSet = preparedStatement.executeQuery();

			if (getRowCount(resultSet) == 0) {
				return null;
			}
			while(resultSet.next()) {
				ProjectBean projectBean = new ProjectBean();
				projectBean.setProjectid(resultSet.getString(1));
				projectBean.setProjectname(resultSet.getString(2));
				projectBean.setProjectlocation(resultSet.getString(3));
				projectBean.setManagerid(resultSet.getString(4));
				projectBean.setManagername(resultSet.getString(5));
				projectBean.setTeamsize(Integer.parseInt(resultSet.getString(6)));
				
				projects.add(projectBean);
			}

			// disconnecting the database
			 DbLogic.disconnect();
			 connection.close();

			return projects;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	
	public static ProjectBean getProject(String projectid) {
		connection = DbLogic.connect();
		// Query fire for insertion operation with column name and values
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		ProjectBean projectBean = new ProjectBean();
		try {

			// admin and normal user query detects if admin returns all rows
			preparedStatement = connection.prepareStatement("SELECT p.projectid,p.projectname,p.projectlocation,p.managerid,(SELECT name FROM project_userlist where userid=p.managerid),(SELECT COUNT(*) FROM project_projectrelations WHERE projectid=p.projectid) FROM project_projects as p WHERE p.projectid = ?");
			preparedStatement.setString(1, projectid.trim());
			// preparedStatement.setString(2, password.trim());
			// executing the query for prapared statment
			resultSet = preparedStatement.executeQuery();

			if (getRowCount(resultSet) == 0) {
				return null;
			}
			while(resultSet.next()) {
				projectBean.setProjectid(resultSet.getString(1));
				projectBean.setProjectname(resultSet.getString(2));
				projectBean.setProjectlocation(resultSet.getString(3));
				projectBean.setManagerid(resultSet.getString(4));
				projectBean.setManagername(resultSet.getString(5));
				projectBean.setTeamsize(Integer.parseInt(resultSet.getString(6)));	
			}

			// disconnecting the database
			 DbLogic.disconnect();
			 connection.close();

			return projectBean;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	public static ShowProjectBean getMyProject(String username) {
		connection = DbLogic.connect();
		// Query fire for insertion operation with column name and values
		PreparedStatement preparedStatement;
		PreparedStatement preparedStatement1;
		
		ResultSet resultSet;
		ResultSet resultSet1;
		
		ShowProjectBean projectBean = new ShowProjectBean();
		try {

			// admin and normal user query detects if admin returns all rows
			preparedStatement = connection.prepareStatement("SELECT p.projectid,p.projectname,p.projectlocation,p.managerid,(SELECT name FROM project_userlist where userid=p.managerid),(SELECT COUNT(*) FROM project_projectrelations WHERE projectid=p.projectid) FROM project_projects as p WHERE p.projectid = (SELECT projectid FROM project_projectrelations WHERE userid=(SELECT userid FROM project_users WHERE username=?))");
			preparedStatement1 = connection.prepareStatement("SELECT u.name FROM project_userlist as u WHERE u.userid IN (SELECT p.userid FROM project_projectrelations as p WHERE p.projectid = (SELECT projectid FROM project_projectrelations WHERE userid=(SELECT userid FROM project_users WHERE username=?)))");
			
			// preparedStatement.setString(2, password.trim());
			// executing the query for prapared statment
			preparedStatement.setString(1, username);
			preparedStatement1.setString(1, username);
			resultSet = preparedStatement.executeQuery();
			resultSet1 = preparedStatement1.executeQuery();

			if (getRowCount(resultSet) == 0) {
				return null;
			}
			
			while(resultSet.next()) {
				projectBean.setProjectid(resultSet.getString(1));
				projectBean.setProjectname(resultSet.getString(2));
				projectBean.setProjectlocation(resultSet.getString(3));
				projectBean.setManagerid(resultSet.getString(4));
				projectBean.setManagername(resultSet.getString(5));
				projectBean.setTeamsize(Integer.parseInt(resultSet.getString(6)));	
			}
			ArrayList<String> team = new ArrayList<String>();
			while(resultSet1.next()) {
				team.add(resultSet1.getString(1));
			}
			projectBean.setTeam(team);

			// disconnecting the database
			 DbLogic.disconnect();
			 connection.close();

			return projectBean;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	/*
	 * method fetchData fetches all data of given username from table and returns resultSet return type : ResultSet
	 */
	public static ResultSet fetchData(String name) {
		// connecting to database
		connection = DbLogic.connect();
		// Query fire for insertion operation with column name and values
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		try {

			// admin and normal user query detects if admin returns all rows
			preparedStatement = connection.prepareStatement("SELECT ul.UserId,(SELECT username FROM project_users WHERE userid = ul.UserId),ul.Name,ul.Category,ul.Sex,ul.Address,ul.EmailId,ul.isDisabled FROM project_userlist AS ul INNER JOIN project_users AS u ON u.username = ? WHERE ul.UserId = u.userid OR 1 = EXISTS(SELECT Category FROM project_userlist AS ul1 WHERE ul1.Category = 'Admin' AND ul1.UserId = u.userid)");

			preparedStatement.setString(1, name.trim());
			// preparedStatement.setString(2, password.trim());
			// executing the query for prapared statment
			resultSet = preparedStatement.executeQuery();

			if (getRowCount(resultSet) == 0) {
				return null;
			}

			// disconnecting the database
			// DbLogic.disconnect();
			// connection.close();

			return resultSet;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/*
	 * method fetchData fetches all data of given username from table and returns resultSet return type : ResultSet
	 */
	public static String getUserType(String username) {
		// connecting to database
		connection = DbLogic.connect();
		// Query fire for insertion operation with column name and values
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		String category = "";
		try {

			// admin and normal user query detects if admin returns all rows
			preparedStatement = connection.prepareStatement("SELECT ul.category FROM project_userlist as ul WHERE ul.userid = (SELECT u.userid FROM project_users as u WHERE u.username=?)");

			preparedStatement.setString(1, username.trim());
			// preparedStatement.setString(2, password.trim());
			// executing the query for prapared statment
			resultSet = preparedStatement.executeQuery();

			if (getRowCount(resultSet) == 0) {
				return null;
			}
			while (resultSet.next()) {
				category = resultSet.getString(1);
			}

			// disconnecting the database
			DbLogic.disconnect();
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return category;
	}

	/*
	 * method login is used to login return type : String
	 */
	public static String login(String userName, String password) {
		// connecting to database
		try {
			connection = DbLogic.connect();

			// Query fire for insertion operation with column name and values
			PreparedStatement preparedStatement1, preparedStatement2;

			preparedStatement1 = connection.prepareStatement("SELECT userid,password from project_users where username = ?");

			preparedStatement1.setString(1, userName.trim());
			// preparedStatement.setString(2, password.trim());
			// executing the query for prapared statment
			ResultSet resultSet = preparedStatement1.executeQuery();

			if (getRowCount(resultSet) == 0) {
				// wrong username and password
				return "notAuthenticated";
			}
			// successfully authenticated user
			if (resultSet.next() && resultSet.getString(2).trim().equals(password.trim())) {

				String id = resultSet.getString(1).trim();

				preparedStatement2 = connection.prepareStatement("SELECT isDisabled from project_userlist where userid = ?");

				preparedStatement2.setString(1, id);
				// executing the query for prapared statment
				ResultSet resultSet1 = preparedStatement2.executeQuery();

				if (getRowCount(resultSet1) == 0) {
					// wrong username and password
					// disconnecting the database
					DbLogic.disconnect();
					connection.close();
					return "notAuthenticated";
				}
				resultSet1.next();

				if (resultSet1.getString(1) != null && resultSet1.getString(1).trim().equalsIgnoreCase("true")) {
					// disconnecting the database
					DbLogic.disconnect();
					connection.close();
					return "disabled";
				} else {
					System.out.println("Not disabled ");
					// disconnecting the database
					DbLogic.disconnect();
					connection.close();
					return "authenticated";
				}
			} else {
				// disconnecting the database
				DbLogic.disconnect();
				connection.close();
				return "passwordIncorrect";
			}

		} catch (Exception e) {
			e.printStackTrace();
			// disconnecting the database
			DbLogic.disconnect();
			try {
				connection.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			return "exception";
		}
	}
	
	
	public static Integer addProject(ProjectBean projectBean) {

		// connecting to database
		connection = DbLogic.connect();

		// Query fire for insertion operation with column name and values
		PreparedStatement preparedStatement1, preparedStatement2;
		try {
			preparedStatement1 = connection.prepareStatement("INSERT INTO project_projects(projectname,projectlocation,teamsize,managerid) VALUES(?,?,?,?)");
			preparedStatement2 = connection.prepareStatement("INSERT INTO project_projectrelations(userid,projectid) VALUES(?,(SELECT projectid FROM project_projects WHERE projectname = ?))");

			preparedStatement1.setString(1, projectBean.getProjectname());
			preparedStatement1.setString(2, projectBean.getProjectlocation());
			preparedStatement1.setInt(3, 1);
			preparedStatement1.setString(4,projectBean.getManagerid());

			preparedStatement2.setString(1, projectBean.getManagerid());
			preparedStatement2.setString(2, projectBean.getProjectname());

			// executing the query for prapared statment
			int i1 = preparedStatement1.executeUpdate();
			// executing the query for prapared statment
			int i2 = preparedStatement2.executeUpdate();

			// disconnecting the database
			DbLogic.disconnect();
			connection.close();

			if (i1 > 0 && i2 > 0) {
				System.out.println("Project added successfully !");
				return 1;
			} else {
				System.out.println("Project adding operation unsuccessful !");
				return 0;
			}

			// successfully added user

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}
	
	
	public static Integer changeProject(ProjectBean projectBean) {

		// connecting to database
		connection = DbLogic.connect();

		// Query fire for insertion operation with column name and values
		PreparedStatement preparedStatement1, preparedStatement2;
		try {
			preparedStatement2 = connection.prepareStatement("UPDATE project_projectrelations SET userid=? WHERE userid=(SELECT p.managerid FROM project_projects as p WHERE p.projectid=?) AND projectid=?");

			preparedStatement1 = connection.prepareStatement("UPDATE project_projects SET projectname=?,projectlocation=?,managerid=? WHERE projectid=?");
			
			preparedStatement1.setString(1, projectBean.getProjectname());
			preparedStatement1.setString(2, projectBean.getProjectlocation());
			preparedStatement1.setString(3, projectBean.getManagerid());
			preparedStatement1.setString(4,projectBean.getProjectid());

			preparedStatement2.setString(1, projectBean.getManagerid());
			preparedStatement2.setString(2, projectBean.getProjectid());
			preparedStatement2.setString(3, projectBean.getProjectid());
				

			int i2 = preparedStatement2.executeUpdate();

			// executing the query for prapared statment
			int i1 = preparedStatement1.executeUpdate();
			// executing the query for prapared statment

			// disconnecting the database
			DbLogic.disconnect();
			connection.close();

			if (i1 > 0 && i2 > 0) {
				System.out.println("Project updated successfully !");
				return 1;
			} else {
				System.out.println("Project updated operation unsuccessful !");
				return 0;
			}

			// successfully added user

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}
	

	public static Integer addUser(UserListBean userDataBean) {

		// connecting to database
		connection = DbLogic.connect();

		// Query fire for insertion operation with column name and values
		PreparedStatement preparedStatement1, preparedStatement2;
		try {
			preparedStatement1 = connection.prepareStatement("INSERT INTO project_users(username, PASSWORD) VALUES(?, ?)");
			preparedStatement2 = connection.prepareStatement("INSERT INTO project_userlist(userid,name,category,sex,address,emailid) VALUES((SELECT userid FROM project_users WHERE username = ?),?,?,?,?,?)");

			preparedStatement1.setString(1, userDataBean.getUsername());
			preparedStatement1.setString(2, userDataBean.getPassword());

			preparedStatement2.setString(1, userDataBean.getUsername());
			preparedStatement2.setString(2, userDataBean.getName());
			preparedStatement2.setString(3, userDataBean.getCategory());
			preparedStatement2.setString(4, userDataBean.getSex());
			preparedStatement2.setString(5, userDataBean.getAddress());
			preparedStatement2.setString(6, userDataBean.getEmailid());

			// executing the query for prapared statment
			int i1 = preparedStatement1.executeUpdate();
			// executing the query for prapared statment
			int i2 = preparedStatement2.executeUpdate();

			// disconnecting the database
			DbLogic.disconnect();
			connection.close();

			if (i1 > 0 && i2 > 0) {
				System.out.println("User added successfully !");
				return 1;
			} else {
				System.out.println("User adding operation unsuccessful !");
				return 0;
			}

			// successfully added user

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}
	
	
	public static Integer changeUser(UserListBean userListBean) {

		// connecting to database
		connection = DbLogic.connect();

		// Query fire for insertion operation with column name and values
		PreparedStatement preparedStatement1, preparedStatement2;
		try {
			preparedStatement1 = connection.prepareStatement("UPDATE project_users SET username=?,password=? WHERE userid=?");

			preparedStatement2 = connection.prepareStatement("UPDATE project_userlist SET name=?,category=?,sex=?,address=?,emailid=?,isdisabled=? WHERE userid=?");
			
			preparedStatement1.setString(1, userListBean.getUsername());
			preparedStatement1.setString(2, userListBean.getPassword());
			preparedStatement1.setString(3, userListBean.getUserid());
			
			
			preparedStatement2.setString(1,userListBean.getName());
			preparedStatement2.setString(2,userListBean.getCategory());
			preparedStatement2.setString(3,userListBean.getSex());
			preparedStatement2.setString(4,userListBean.getAddress());
			preparedStatement2.setString(5,userListBean.getEmailid());
			preparedStatement2.setString(6,userListBean.getIsDisabled());
			preparedStatement2.setString(7,userListBean.getUserid());
			
			

			int i2 = preparedStatement2.executeUpdate();

			// executing the query for prapared statment
			int i1 = preparedStatement1.executeUpdate();
			// executing the query for prapared statment

			// disconnecting the database
			DbLogic.disconnect();
			connection.close();

			if (i1 > 0 && i2 > 0) {
				System.out.println("User updated successfully !");
				return 1;
			} else {
				System.out.println("User updated operation unsuccessful !");
				return 0;
			}

			// successfully added user

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}
	

	public static Integer enableUser(String id) {

		// connecting to database
		connection = DbLogic.connect();

		// Query fire for insertion operation with column name and values
		PreparedStatement preparedStatement1;
		try {
			preparedStatement1 = connection.prepareStatement("UPDATE project_userlist SET isdisabled=? WHERE userid=?");

			preparedStatement1.setString(1, "false");

			preparedStatement1.setString(2, id);

			// executing the query for prapared statment
			int i1 = preparedStatement1.executeUpdate();

			// disconnecting the database
			DbLogic.disconnect();
			connection.close();

			if (i1 > 0) {
				System.out.println("User enabled successfully !");
				return 1;
			} else {
				System.out.println("User enable operation unsuccessfull !");
				return 0;
			}

			// successfully added user

		} catch (Exception e) {

			return 0;
		}
	}

	public static Integer disableUser(String id) {

		// connecting to database
		connection = DbLogic.connect();

		// Query fire for insertion operation with column name and values
		PreparedStatement preparedStatement1;
		try {
			preparedStatement1 = connection.prepareStatement("UPDATE project_userlist SET isdisabled=? WHERE userid=?");

			preparedStatement1.setString(1, "true");

			preparedStatement1.setString(2, id);

			// executing the query for prapared statment
			int i1 = preparedStatement1.executeUpdate();

			// disconnecting the database
			DbLogic.disconnect();
			connection.close();

			if (i1 > 0) {
				System.out.println("User disable successfully !");
				return 1;
			} else {
				System.out.println("User disable operation unsuccessfull !");
				return 0;
			}

			// successfully added user

		} catch (Exception e) {

			return 0;
		}
	}
	
	
	public static Integer allocateEmployee(String userid,String managerusername) {

		// connecting to database
		connection = DbLogic.connect();

		// Query fire for insertion operation with column name and values
		PreparedStatement preparedStatement1;
		try {
			preparedStatement1 = connection.prepareStatement("INSERT INTO project_projectrelations(userid,projectid) values(?,(SELECT projectid FROM project_projects WHERE managerid = (SELECT userid FROM project_users WHERE username=?)))");

			preparedStatement1.setString(1, userid);

			preparedStatement1.setString(2, managerusername);

			// executing the query for prapared statment
			int i1 = preparedStatement1.executeUpdate();

			// disconnecting the database
			DbLogic.disconnect();
			connection.close();

			if (i1 > 0) {
				System.out.println("User allocated successfully !");
				return 1;
			} else {
				System.out.println("User allocation operation unsuccessfull !");
				return 0;
			}

			// successfully added user

		} catch (Exception e) {

			return 0;
		}
	}
	
	public static Integer deallocateEmployee(String userid,String managerusername) {

		// connecting to database
		connection = DbLogic.connect();

		// Query fire for insertion operation with column name and values
		PreparedStatement preparedStatement1;
		try {
			preparedStatement1 = connection.prepareStatement("DELETE FROM project_projectrelations WHERE userid=?");

			preparedStatement1.setString(1, userid);

			// executing the query for prapared statment
			int i1 = preparedStatement1.executeUpdate();

			// disconnecting the database
			DbLogic.disconnect();
			connection.close();

			if (i1 > 0) {
				System.out.println("User deallocated successfully !");
				return 1;
			} else {
				System.out.println("User deallocation operation unsuccessfull !");
				return 0;
			}

			// successfully added user

		} catch (Exception e) {

			return 0;
		}
	}
		
		

	public static Integer deleteUsers(String[] ids) {

		int i1 = 0;

		// connecting to database
		connection = DbLogic.connect();

		// Query fire for insertion operation with column name and values
		PreparedStatement preparedStatement1;

		for (String id : ids) {
			System.out.println("Deleting user no " + id);
			try {
				preparedStatement1 = connection.prepareStatement("DELETE FROM project_userlist WHERE userid = ?");

				preparedStatement1.setString(1, id);
				// executing the query for prapared statment
				i1 = +preparedStatement1.executeUpdate();
				// executing the query for prapared statment

				// successfully deleted user

			} catch (Exception e) {

				return 0;
			}

		}

		// disconnecting the database
		DbLogic.disconnect();
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}

		if (i1 > 0) {
			System.out.println("User deleted successfully !");
			return 1;
		} else {
			System.out.println("User delete operation unsuccessfull !");
			return 0;
		}
	}
	
	public static Integer deleteUser(String id) {

		int i1 = 0;

		// connecting to database
		connection = DbLogic.connect();

		// Query fire for insertion operation with column name and values
		PreparedStatement preparedStatement1;

			System.out.println("Deleting user no " + id);
			try {
				preparedStatement1 = connection.prepareStatement("DELETE FROM project_users WHERE userid = ?");

				preparedStatement1.setString(1, id);
				// executing the query for prapared statment
				i1 = +preparedStatement1.executeUpdate();
				// executing the query for prapared statment

				// successfully deleted user

			} catch (Exception e) {

				return 0;
			}

		

		// disconnecting the database
		DbLogic.disconnect();
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}

		if (i1 > 0) {
			System.out.println("User deleted successfully !");
			return 1;
		} else {
			System.out.println("User delete operation unsuccessfull !");
			return 0;
		}
	}
	
	
	public static Integer deleteApplication(String applicationid) {

		int i1 = 0;

		// connecting to database
		connection = DbLogic.connect();

		// Query fire for insertion operation with column name and values
		PreparedStatement preparedStatement1;

			System.out.println("Deleting application id no " + applicationid);
			try {
				preparedStatement1 = connection.prepareStatement("DELETE FROM project_application WHERE id = ?");

				preparedStatement1.setString(1, applicationid);
				// executing the query for prapared statment
				i1 = +preparedStatement1.executeUpdate();
				// executing the query for prapared statment

				// successfully deleted user

			} catch (Exception e) {

				return 0;
			}

		

		// disconnecting the database
		DbLogic.disconnect();
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}

		if (i1 > 0) {
			System.out.println("Application deleted successfully !");
			return 1;
		} else {
			System.out.println("Application delete operation unsuccessfull !");
			return 0;
		}
	}
	
	public static Integer deleteProject(String projectid) {

		int i1 = 0;

		// connecting to database
		connection = DbLogic.connect();

		// Query fire for insertion operation with column name and values
		PreparedStatement preparedStatement1;

			System.out.println("Deleting project no " + projectid);
			try {
				preparedStatement1 = connection.prepareStatement("DELETE FROM project_projects WHERE projectid = ?");

				preparedStatement1.setString(1, projectid);
				// executing the query for prapared statment
				i1 = +preparedStatement1.executeUpdate();
				// executing the query for prapared statment

				// successfully deleted user
				
				preparedStatement1 = connection.prepareStatement("DELETE FROM project_projectrelations WHERE projectid = ?");

				preparedStatement1.setString(1, projectid);
				// executing the query for prapared statment
				i1 = +preparedStatement1.executeUpdate();


			} catch (Exception e) {

				return 0;
			}

		

		// disconnecting the database
		DbLogic.disconnect();
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}

		if (i1 > 0) {
			System.out.println("Project deleted successfully !");
			return 1;
		} else {
			System.out.println("Project delete operation unsuccessfull !");
			return 0;
		}
	}
	
	
	public static Integer deallocateMe(String username,String managerid) {

		int i1 = 0;

		// connecting to database
		connection = DbLogic.connect();

		// Query fire for insertion operation with column name and values
		PreparedStatement preparedStatement1;

			System.out.println("request of deallocation for user " + username);
			try {
				preparedStatement1 = connection.prepareStatement("INSERT INTO project_application(userid,projectid,managerid,isapproved,request,requesttype) VALUES((SELECT userid FROM project_users WHERE username=?),(SELECT projectid FROM project_projectrelations WHERE userid=(SELECT userid FROM project_users WHERE username=?)),?,'false','deallocation request','deallocate')");

				preparedStatement1.setString(1, username);
				preparedStatement1.setString(2, username);
				preparedStatement1.setString(3, managerid);
				
				// executing the query for prapared statment
				i1 = +preparedStatement1.executeUpdate();
				// executing the query for prapared statment

				// successfully deleted user

			} catch (Exception e) {

				return 0;
			}

		

		// disconnecting the database
		DbLogic.disconnect();
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}

		if (i1 > 0) {
			System.out.println("Requested for deallocation successfully !");
			return 1;
		} else {
			System.out.println("Deallocation request operation unsuccessfull !");
			return 0;
		}
	}
	
	public static boolean checkIfAllocated(String username) {
		// connecting to database
		connection = DbLogic.connect();
		// Query fire for insertion operation with column name and values
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		boolean isAllocated = true;
		try {

			// admin and normal user query detects if admin returns all rows
			preparedStatement = connection.prepareStatement("SELECT * FROM project_projectrelations where userid=(SELECT userid FROM project_users WHERE username=?)");

			preparedStatement.setString(1, username.trim());
			// preparedStatement.setString(2, password.trim());
			// executing the query for prapared statment
			resultSet = preparedStatement.executeQuery();

			if (getRowCount(resultSet) == 0) {
				isAllocated = false;
			}

			// disconnecting the database
			DbLogic.disconnect();
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return isAllocated;
	}
	
}