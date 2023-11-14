package com.stockmarket.csv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {

	private MySqlConnection(){}
	
	private static Connection connection = null;
	
    public static Connection getConnection() {
    	
    	try {
			Class.forName("com.mysql.jdbc.Driver");  
			if(connection == null){
				connection = DriverManager.getConnection(  
				    	"jdbc:mysql://localhost:3306/stockmarket","root","root");  
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return connection;
    	
    }
    
    public static void main(String[] args) {
	
    	getConnection();
	}
	
}
