package com.stockmarket.algorithms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import com.stockmarket.csv.MySqlConnection;

/**
 * Simple Moving Average
 */
public class SimpleMovingAverage {

	private String result ;
	HashMap<String,Double> dataMap = new HashMap<String,Double>();
	static ArrayList<Double> closeValues = new ArrayList<Double>();

	public static void main(String[] args) {

		SimpleMovingAverage sma = new SimpleMovingAverage();
		sma.getData("PERSISTENT","2023-09-06",25);
		sma.calculate();
		
	}
	
	public void getData(String nsescriptCode,String date,int period){

		Connection connection = MySqlConnection.getConnection();

		String query = "select  date,closevalue from stocksnsedaywisereport1  where nsescriptcode=? and date <= ? order by date desc LIMIT ?";   

		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1,nsescriptCode );
			statement.setString(2,date );
			statement.setInt(3,period );
			ResultSet rs= statement.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString("date")+"----"+rs.getString("closevalue"));
				dataMap.put(rs.getString("date"), Double.parseDouble(rs.getString("closevalue")));
				closeValues.add(Double.parseDouble(rs.getString("closevalue")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void calculate() {
		
		 Double average = 0.0;
		 for (Double double1 : closeValues) {
			 average = average+double1;
		}
		 DecimalFormat df = new DecimalFormat("####0.00");
		 result = df.format(average/closeValues.size());
		 System.out.println(result);
	}

}
