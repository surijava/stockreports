package com.stockmarket.algorithms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.stockmarket.csv.MySqlConnection;

public class PivotelReports {

	ArrayList<String> nsescriptcodes = new ArrayList<String>();
	ArrayList<String> supportone = new ArrayList<String>();
	ArrayList<String> supporttwo = new ArrayList<String>();
	ArrayList<String> supportthhree = new ArrayList<String>();
	ArrayList<String> resistanceone = new ArrayList<String>();
	ArrayList<String> resistancetwo = new ArrayList<String>();
	ArrayList<String> resistancethree = new ArrayList<String>();
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

		PivotelReports preport = new PivotelReports();
		preport.report();
	}

	public void report(){

		Connection connection = MySqlConnection.getConnection();
		String query1 = "select  distinct nsescriptcode from stocksnsedaywisereport1  ";

		try {

			PreparedStatement statement = connection.prepareStatement(query1);
			ResultSet rs= statement.executeQuery();
			while (rs.next()) {

				nsescriptcodes.add(rs.getString("nsescriptcode"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(nsescriptcodes.size());

		Date date = new Date();  
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
		String strDate= formatter.format(date);  
		int count = 0;
		for (String nsescriptCode : nsescriptcodes) {

			
			try {

				String query2 = "select  * from stockpivotel  where nsescriptcode=? and date =? ";
				PreparedStatement statement = connection.prepareStatement(query2);
				statement.setString(1,nsescriptCode );
				statement.setString(2,strDate );
				ResultSet rs= statement.executeQuery();
				while (rs.next()) {

					high = Double.parseDouble(rs.getString("highvalue"));
					open = Double.parseDouble(rs.getString("openvalue"));
					close = Double.parseDouble(rs.getString("closevalue"));
					low = Double.parseDouble(rs.getString("lowvalue"));
					
					
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			

			try {
				String query = "select  * from stockpivotel  where nsescriptcode=? and date < ? order by date desc LIMIT 1";   
				
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1,nsescriptCode );
				statement.setString(2,strDate );
				ResultSet rs= statement.executeQuery();
				while (rs.next()) {
					System.out.println(rs.getString("date")+"----"+rs.getString("nsescriptcode"));
					/*pivotel = Double.parseDouble(rs.getString("pivotelpoint"));
					r1= Double.parseDouble(rs.getString("resistanceone"));
					r2=Double.parseDouble(rs.getString("resistancetwo"));
					r3=Double.parseDouble(rs.getString("resistancethree"));
					s1=Double.parseDouble(rs.getString("supportone"));
					s2=Double.parseDouble(rs.getString("supporttwo"));*/
					s3=Double.parseDouble(rs.getString("supportthree"));
					
					
					if( s3 >= close && close > 100 ){
						
						System.out.println(nsescriptCode+"-------------"+s3);
						System.out.println(close +"Low value");
						count ++;
					}else{
						//System.out.println(nsescriptCode+"-------------");
					}
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			 pivotel = 0.0;
			 r1 = 0.0;
			 r2 = 0.0;
			 r3 = 0.0;
			 s1 = 0.0;
			 s2 = 0.0;
			 s3 = 0.0;
			 high = 0.0;
			 open = 0.0;
			 close = 0.0;
			 low = 0.0;
		}
		System.out.println(count);
	}

}
