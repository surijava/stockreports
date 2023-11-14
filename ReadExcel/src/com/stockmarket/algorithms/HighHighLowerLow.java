package com.stockmarket.algorithms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import com.stockmarket.csv.MySqlConnection;

public class HighHighLowerLow {
	
	HashMap<String,Double> highdataMap = new HashMap<String,Double>();
	HashMap<String,Double> lowdataMap = new HashMap<String,Double>();
	ArrayList<Double> highValues = new ArrayList<Double>();
	ArrayList<Double> lowValues = new ArrayList<Double>();
	ArrayList<String> dates = new ArrayList<String>();
	
public static void main(String[] args) {
		
		HighHighLowerLow rsi = new HighHighLowerLow();
    	rsi.getData("VEDL","2022-02-09");
    	  
	}

public void getData(String scriptCode,String date){

	Connection connection = MySqlConnection.getConnection();

	String query = "select  date,closevalue,highvalue,lowvalue from stocksnsedaywisereport1  where nsescriptcode=? and date <= ? order by date desc LIMIT 15";   

	try {
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1,scriptCode );
		statement.setString(2,date );
		ResultSet rs= statement.executeQuery();
		while (rs.next()) {
			//System.out.println(rs.getString("date"));
			highdataMap.put(rs.getString("date"),Double.parseDouble(rs.getString("highvalue")));
			lowdataMap.put(rs.getString("date"),Double.parseDouble(rs.getString("lowvalue")));
			highValues.add(Double.parseDouble(rs.getString("highvalue")));
			lowValues.add(Double.parseDouble(rs.getString("lowvalue")));
			dates.add(rs.getString("date"));
		}

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	//Collections.sort(dates,Collections.reverseOrder());
	
	Collections.reverse(dates);
	System.out.println(highdataMap);
	System.out.println(dates);
	double highHigh = 0.0;
	double highLow = 0.0;
	
	int count =0; 
	for (int i = 0; i < dates.size(); i++) {
		if( i == 0){
			
			highHigh = highdataMap.get(dates.get(i));
			highLow = highdataMap.get(dates.get(i));
		}
		//System.out.println(dates.get(i));
		if( i!= 0 && (highdataMap.get(dates.get(i)) >= highLow ) ){
			System.out.println("high "+dates.get(i));
			highHigh = highdataMap.get(dates.get(i));
			highLow = highHigh;
			count++;
			//break;
		}
		
	}
	System.out.println(count);
	System.out.println(highHigh + "---------"+ highLow);

}

}
