package com.stockmarket.algorithms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.stockmarket.csv.MySqlConnection;

public class RSITrendShares {
	
	HashMap<String,Double> dataMap = new HashMap<String,Double>();
	static ArrayList<Double> closeValues = new ArrayList<Double>();

	 public static void main(String[] args) {
		
		 RSITrendShares trend = new RSITrendShares();
		 
		 trend.getData("BSOFT","2022-02-02",15);
	}
	 
	 public void getData(String nsescriptCode,String date,int period){

			Connection connection = MySqlConnection.getConnection();

			String query = "select  * from stockrsi  where nsescriptcode=? and date <= ? order by date desc LIMIT ?";   

			try {
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1,nsescriptCode );
				statement.setString(2,date );
				statement.setInt(3,period );
				ResultSet rs= statement.executeQuery();
				while (rs.next()) {
					System.out.println(rs.getString("date")+"----"+rs.getString("rsi"));
					//dataMap.put(rs.getString("date"), Double.parseDouble(rs.getString("closevalue")));
					closeValues.add(Double.parseDouble(rs.getString("rsi")));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			double sum = closeValues.stream().mapToDouble( a -> a).sum();
			System.out.println(sum/period);
		}
}
