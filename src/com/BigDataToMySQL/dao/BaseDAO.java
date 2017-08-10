package com.BigDataToMySQL.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO {
	public Connection conn;
	
	public int waitTime = 60000;
	public String host;
	public int port;
	public String database;
	public String user;
	public String password;
		
	public BaseDAO(String dbhost , int dbport){

//		host = "117.141.244.6";
//		port = 8636;
		host = dbhost;
		port = dbport;
		database = "xinnonghe";
		user = "root";
		password = "";
		String connectionUrl = "jdbc:mysql://" + host + ":" + port + "/" + database + "?user=" + user + "&password=" + password + "&useUnicode=true&amp;characterEncoding=UTF8";
		
		
		
		System.out.println(connectionUrl);
		try{
			loadDriver();
			conn = DriverManager.getConnection(connectionUrl);
		} catch (SQLException sqlex) {
			System.out.println("SQLException: " + sqlex.getMessage());
		    System.out.println("SQLState: " + sqlex.getSQLState());
		    System.out.println("VendorError: " + sqlex.getErrorCode());
		}
	}
	
	private void loadDriver(){
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
