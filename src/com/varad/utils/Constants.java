/**
 *
 */
package com.varad.utils;

import java.util.ResourceBundle;


/**
 * @author: 	Varad Paralikar
 * Created Date:29/08/2019
 * Assignment:  Day 2
 * Task: 		Struts And Hibernate Skillup
 *
 */

/*
 * Class Constants is used to define constants
 * @author: Varad Parlikar
 * Created Date: 2019/08/29
 */
public class Constants {

	//Static final String variables
	public static final String DB_DriverName = "DBname";
	public static final String DB_URL="URL";
	public static final String DB_username  = "Username" ;
	public static final String DB_password = "Password";


	public static String db_URL,db_username,db_password,db_driver;


	/*
	 * method getPropertyValue is used to set property values
	 * return type : void
	 */
	public static void getPropertyValue() {

		// Passing the property file location
		ResourceBundle myBundle = ResourceBundle.getBundle("com.varad.utils.database");
		db_URL = myBundle.getString(Constants.DB_URL);
		db_driver = myBundle.getString(Constants.DB_DriverName);
		db_username = myBundle.getString(Constants.DB_username);
		db_password = myBundle.getString(Constants.DB_password);
	}

}
