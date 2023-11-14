package com.stockmarket.algorithms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Collections;

import com.stockmarket.csv.MySqlConnection;

public class PivotelPoints {

	
	double pivotel = 0.0;
	double r1 = 0.0;
	double r2 = 0.0;
	double r3 = 0.0;
	double s1 = 0.0;
	double s2 = 0.0;
	double s3 = 0.0;
	double high = 0.0;
	double open = 0.0;
	double close = 0.0;
	double low = 0.0;
	
	public static void main(String[] args) {
		
		PivotelPoints pp = new PivotelPoints();
		pp.getData("zomato","2022-01-24");
		
	}
	
	public void getData(String scriptCode,String date){
		DecimalFormat df = new DecimalFormat("####0.00");

		Connection connection = MySqlConnection.getConnection();
		

		String query = "select  * from stocksnsedaywisereport1  where nsescriptcode=? and date <= ? ";   

		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1,scriptCode );
			statement.setString(2,date );
			ResultSet rs= statement.executeQuery();
			while (rs.next()) {
				high = Double.parseDouble(rs.getString("highvalue"));
				open = Double.parseDouble(rs.getString("openvalue"));
				close = Double.parseDouble(rs.getString("closevalue"));
				low = Double.parseDouble(rs.getString("lowvalue"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pivotel = (high+low+close)/3;
		
		r1 = pivotel + (pivotel-low);
		r2 = pivotel + (high -low);
		r3 = high + 2 *( pivotel -low);
		
		s1 = pivotel - (high-pivotel);
		s2 = pivotel - (high-low);
		s3 = low - 2 * (high-pivotel);
		
		System.out.println(df.format(pivotel));
		System.out.println(r1);
		System.out.println(r2);
		System.out.println(r3);
		
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
		

	}
}
